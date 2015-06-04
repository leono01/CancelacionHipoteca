/*
 * Copyright (C) 2015 GISNET
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gisnet.cancelacion.web.controller;

import com.gisnet.cancelacion.core.services.CancelacionArchivoService;
import com.gisnet.cancelacion.core.services.CartaCancelacionService;
import com.gisnet.cancelacion.core.services.CasoService;
import com.gisnet.cancelacion.core.services.EmpleadoService;
import com.gisnet.cancelacion.core.services.NotarioService;
import com.gisnet.cancelacion.core.services.ProyectoCancelacionService;
import com.gisnet.cancelacion.core.services.StatusCasoService;
import com.gisnet.cancelacion.core.services.StatusProyectoService;
import com.gisnet.cancelacion.core.services.UsuarioService;
import com.gisnet.cancelacion.events.*;
import com.gisnet.cancelacion.events.info.*;
import com.gisnet.cancelacion.web.domain.SesionNotario;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author marco-g8
 */
@Controller
public class NotarioController {
    
    @Autowired
    private CancelacionArchivoService cancelacionArchivoService;
    @Autowired
    private CartaCancelacionService cartaCancelacionService;
    @Autowired
    private CasoService casoService;
    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private NotarioService notarioService;
    @Autowired
    private ProyectoCancelacionService proyectoCancelacionService;
    @Autowired
    private StatusCasoService statusCasoService;
    @Autowired
    private StatusProyectoService statusProyectoService;
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private SesionNotario sesion;
    
    public String index(Model model, Principal principal) {
        if (sesion.getNotarioInfo() == null) {
            FindResponse<NotarioInfo> findresponse = notarioService.find(
                    new FindByRequest("nombreUsuario", principal.getName()));
            if (findresponse.getInfo() == null) {
                model.addAttribute("casos", new ArrayList<CasoInfo>());
                return "/notario/index";
            }
            sesion.setNotarioInfo(findresponse.getInfo());
        }

        List<String> mensajes = Utils.getMensajes(model);

        ListResponse<CasoInfo> listresponse = casoService.list(
                new ListRequest("notarioId", sesion.getNotarioInfo().getId()));
        
        for (CasoInfo caso : listresponse.getList()) {
            if (caso.getProyectoCancelacionId() > 0) {
                FindResponse<ProyectoCancelacionInfo> find = proyectoCancelacionService.find(
                        new FindByRequest(caso.getProyectoCancelacionId()));
                ProyectoCancelacionInfo info = find.getInfo();
                if (info.getStatusProyecto().getClave() == 8) {
                    mensajes.add("info::Pasar a firmar el caso " + caso.getNumeroCaso() + ", el dia " + info.getFechaAsignadaParaFirma());
                }
            }
        }
        model.addAttribute("casos", listresponse.getList());

        return "/notario/index";
    }
    
    private CasoInfo getCaso(String numeroCaso) throws CancelacionWebException {
    	if (sesion.getNotarioInfo() == null) { 
    		throw new CancelacionWebException("Notario no encontrado");
    	}
        if (sesion.getCasoInfo() != null) {
        	if (sesion.getCasoInfo().getNotarioId() != sesion.getNotarioInfo().getId()) {
        		throw new CancelacionWebException("Caso no correspondiente al notario activo");
        	}
            if (sesion.getCasoInfo().getNumeroCaso().equals(numeroCaso)) {
                return sesion.getCasoInfo();
            }
        }
        sesion.getCancelacionArchivos().clear();
        FindResponse<CasoInfo> find = casoService.find(new FindByRequest("numeroCaso", numeroCaso));
        if (find.getInfo() == null) {
        	throw new CancelacionWebException("Caso no existe");
        }
        if (find.getInfo().getNotarioId() != sesion.getNotarioInfo().getId()) {
    		throw new CancelacionWebException("Caso no correspondiente al notario activo");
    	}
        sesion.setCasoInfo(find.getInfo());
        return sesion.getCasoInfo();
    }
    
    @RequestMapping(value = "/notario/caso/{numeroCaso}", method = RequestMethod.GET)
    public String ver(@PathVariable String numeroCaso, Model model, RedirectAttributes redirectAttributes) {
    	try {
    		CasoInfo caso = getCaso(numeroCaso);
    		model.addAttribute("caso", caso);
            return "/notario/view";
    	} catch (CancelacionWebException ex) {
    		List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
    		mensajes.add("warning::El caso " + numeroCaso + " no existe.");
    		return "redirect:/";
    	}
    }
    
    @RequestMapping(value = "/archivos/{id}/carta_de_cancelacion.pdf", method = RequestMethod.GET, produces = "application/pdf")
    public @ResponseBody byte[] renderpdf(@PathVariable long id, HttpServletResponse response) {
        FindResponse<CartaCancelacionInfo> findcarta = cartaCancelacionService.find(
                new FindByRequest(id));
        CartaCancelacionInfo cartac = findcarta.getInfo();
        if (cartac == null) {
        	response.setContentType("text/plain");
        	return "Archivo no encontrado.".getBytes();
        }
        return cartac.getPdf();
    }

    @RequestMapping(value = "/notario/caso/{numeroCaso}/aceptar", method = RequestMethod.GET)
    public String aceptar(@PathVariable String numeroCaso, Model model, RedirectAttributes redirectAttributes) {
    	try {
	        CasoInfo caso = getCaso(numeroCaso);
	        model.addAttribute("caso", caso);
	        return "/notario/accept";
    	} catch (CancelacionWebException ex) {
    		List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
    		mensajes.add("warning::El caso " + numeroCaso + " no existe.");
    		return "redirect:/";
    	}
    }
    
    @RequestMapping(value = "/notario/caso/{numeroCaso}/aceptar", method = RequestMethod.POST)
    public String agregaArchivos(
            @PathVariable String numeroCaso,
            Model model,
            RedirectAttributes redirectAttributes,
            @RequestParam("file1") MultipartFile file1,
            @RequestParam("file2") MultipartFile file2,
            @RequestParam("file3") MultipartFile file3,
            @RequestParam("file4") MultipartFile file4,
            @RequestParam("file5") MultipartFile file5) {
    	try {
    		CasoInfo caso = getCaso(numeroCaso);
    		model.addAttribute("caso", caso);
    		
    		// guarda archivos
            sesion.getCancelacionArchivos().clear();
            if (!file1.isEmpty()) {
                try {
                    guardaCancelacionArchivo(file1);
                } catch (IOException ex) {
                    return "redirect:/";
                }
            }
            if (!file2.isEmpty()) {
                try {
                    guardaCancelacionArchivo(file2);
                } catch (IOException ex) {
                    return "redirect:/";
                }
            }
            if (!file3.isEmpty()) {
                try {
                    guardaCancelacionArchivo(file3);
                } catch (IOException ex) {
                    return "redirect:/";
                }
            }
            if (!file4.isEmpty()) {
                try {
                    guardaCancelacionArchivo(file4);
                } catch (IOException ex) {
                    return "redirect:/";
                }
            }
            if (!file5.isEmpty()) {
                try {
                    guardaCancelacionArchivo(file5);
                } catch (IOException ex) {
                    return "redirect:/";
                }
            }
            return "redirect:/notario/caso/" + numeroCaso + "/aceptar/jefecobranza";
    	} catch (CancelacionWebException ex) {
    		List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
    		mensajes.add("warning::El caso " + numeroCaso + " no existe.");
    		return "redirect:/";
    	}
    }

    private void guardaCancelacionArchivo(MultipartFile file) throws IOException {
        CancelacionArchivoInfo archivo = new CancelacionArchivoInfo();
        archivo.setArchivo(file.getBytes());
        archivo.setNombre(file.getOriginalFilename());
        archivo.setMimetype(file.getContentType());
        sesion.getCancelacionArchivos().add(archivo);
    }
    
    @RequestMapping(value = "/notario/caso/{numeroCaso}/aceptar/jefecobranza", method = RequestMethod.GET)
    public String seleccionaJefeCobranza(
            @PathVariable String numeroCaso,
            Model model,
            RedirectAttributes redirectAttributes) {
    	try {
    		CasoInfo caso = getCaso(numeroCaso);
    		model.addAttribute("caso", caso);
    		ListResponse<UsuarioInfo> list = usuarioService.list(
                    new ListRequest("rol", "JEFE_COBRANZA"));
            List<EmpleadoInfo> jefesc = new ArrayList<>();
            for (UsuarioInfo ui : list.getList()) {
                FindResponse<EmpleadoInfo> find = empleadoService.find(
                        new FindByRequest("usuarioId", ui.getId()));
                jefesc.add(find.getInfo());
            }
            model.addAttribute("jefesc", jefesc);

            return "/notario/jefeCobranza";
    	} catch (CancelacionWebException ex) {
    		List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
    		mensajes.add("warning::El caso " + numeroCaso + " no existe.");
    		return "redirect:/";
    	}
    }
    
    @RequestMapping(value = "/notario/caso/{numeroCaso}/aceptar/jefecobranza", method = RequestMethod.POST)
    public String aceptaCasoYGuardaArchivos(
            @PathVariable String numeroCaso,
            @RequestParam("jefec") long jefec,
            Model model,
            RedirectAttributes redirectAttributes) {
    	try {
    		CasoInfo caso = getCaso(numeroCaso);
    		model.addAttribute("caso", caso);

    		// crea proyecto cancelacion
            ProyectoCancelacionInfo proyecto = new ProyectoCancelacionInfo();
            proyecto.setFechaCreacion(new Date());
            proyecto.setEmpleadoId(jefec);
            FindResponse<StatusProyectoInfo> find2 = statusProyectoService.find(
                    new FindByRequest("clave", 5));
            proyecto.setStatusProyecto(find2.getInfo());
            
            SaveResponse<ProyectoCancelacionInfo> saved = proyectoCancelacionService.save(
                    new SaveRequest<>(proyecto));
            proyecto = saved.getInfo();
            
            // guarda archivos
            for (CancelacionArchivoInfo archivo : sesion.getCancelacionArchivos()) {
                archivo.setProyectoCancelacionId(proyecto.getId());
                cancelacionArchivoService.save(new SaveRequest<>(archivo));
            }
            sesion.getCancelacionArchivos().clear();
            
            // Actualiza caso
            caso.setProyectoCancelacionId(proyecto.getId());
            FindResponse<StatusCasoInfo> find = statusCasoService.find(new FindByRequest("clave", 4));
            caso.setStatusCaso(find.getInfo());
            UpdateResponse<CasoInfo> updated = casoService.update(new UpdateRequest<>(caso));
            caso = updated.getInfo();
            
            return "redirect:/";
    	} catch (CancelacionWebException ex) {
    		List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
    		mensajes.add("warning::El caso " + numeroCaso + " no existe.");
    		return "redirect:/";
    	}
    }
    
    
    @RequestMapping(value = "/notario/caso/{numeroCaso}/rechazar", method = RequestMethod.GET)
    public String rechazar(
            @PathVariable String numeroCaso,
            Model model,
            RedirectAttributes redirectAttributes) {
    	try {
	        CasoInfo caso = getCaso(numeroCaso);
	        model.addAttribute("caso", caso);
	        return "/notario/reject";
    	} catch (CancelacionWebException ex) {
    		List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
    		mensajes.add("warning::El caso " + numeroCaso + " no existe.");
    		return "redirect:/";
    	}
    }
    
    @RequestMapping(value = "/notario/caso/{numeroCaso}/rechazar", method = RequestMethod.POST)
    public String rechazaCaso(
            @PathVariable String numeroCaso,
            @RequestParam("motivo") String motivoRechazo,
            Model model,
            RedirectAttributes redirectAttributes) {
    	try {
    		CasoInfo caso = getCaso(numeroCaso);
    		model.addAttribute("caso", caso);
    		
    		// crea proyecto cancelacion
            ProyectoCancelacionInfo proyecto = new ProyectoCancelacionInfo();
            FindResponse<StatusProyectoInfo> findstatus = statusProyectoService.find(
                    new FindByRequest("clave", 11));
            proyecto.setStatusProyecto(findstatus.getInfo());
            proyecto.setMotivoRechazo(motivoRechazo);
            
            SaveResponse<ProyectoCancelacionInfo> saved = proyectoCancelacionService.save(
                    new SaveRequest<>(proyecto));
            proyecto = saved.getInfo();
            
            caso.setProyectoCancelacionId(proyecto.getId());
            // cambiar de notario
            caso.setNotarioId(0l);
            
            UpdateResponse<CasoInfo> updated = casoService.update(new UpdateRequest<>(caso));
            caso = updated.getInfo();
            
            return "redirect:/";
    	} catch (CancelacionWebException ex) {
    		List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
    		mensajes.add("warning::El caso " + numeroCaso + " no existe.");
    		return "redirect:/";
    	}
    }
    
}

