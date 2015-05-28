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
import com.gisnet.cancelacion.core.services.StatusProyectoService;
import com.gisnet.cancelacion.core.services.UsuarioService;
import com.gisnet.cancelacion.events.*;
import com.gisnet.cancelacion.events.info.*;
import com.gisnet.cancelacion.web.domain.SesionJCobranza;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author marco-g8
 */
@Controller
public class JCobranzaController {

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
    private StatusProyectoService statusProyectoService;
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private SesionJCobranza sesion;

    public String index(Model model, Principal principal) {
        if (sesion.getEmpleado() == null) {
            FindResponse<EmpleadoInfo> findresponse = empleadoService.find(
                new FindByRequest("nombreUsuario", principal.getName()));
            EmpleadoInfo empleado = findresponse.getInfo();
            if (empleado == null) {
                System.err.println("Datos del empleado no encontrados.");
                model.addAttribute("casosrevizar", new ArrayList<>());
                model.addAttribute("casosespera", new ArrayList<>());
                return "/jcobranza/index";
            }
            sesion.setEmpleado(empleado);
        }
        sesion.setCaso(null);
        ListResponse<ProyectoCancelacionInfo> listresponse = proyectoCancelacionService.list(
                new ListRequest("empleadoId", sesion.getEmpleado().getId()));
        List<CasoInfo> casosRevizar = new ArrayList<>();
        List<CasoInfo> casosEspera = new ArrayList<>();
        for (ProyectoCancelacionInfo info : listresponse.getList()) {
            if (info.getStatusProyecto().getClave() == 5) {
                FindResponse<CasoInfo> find = casoService.find(
                        new FindByRequest("proyectoCancelacionId", info.getId()));
                casosRevizar.add(find.getInfo());
            }
            else if (info.getStatusProyecto().getClave() == 8) {
                FindResponse<CasoInfo> find = casoService.find(
                        new FindByRequest("proyectoCancelacionId", info.getId()));
                casosEspera.add(find.getInfo());
            }
        }
        model.addAttribute("casosrevizar", casosRevizar);
        model.addAttribute("casosespera", casosEspera);
        return "/jcobranza/index";
    }

    private CasoInfo getCaso(String numeroCaso) {
    	if (sesion.getCaso() != null) {
            if (sesion.getCaso().getNumeroCaso().equals(numeroCaso)) {
                return sesion.getCaso();
            }
        }
        FindResponse<CasoInfo> find = casoService.find(new FindByRequest("numeroCaso", numeroCaso));
        sesion.setCaso(find.getInfo());
        return sesion.getCaso();
    }

    @RequestMapping(value = "/cobranza/caso/{numeroCaso}", method = RequestMethod.GET)
    public String ver(@PathVariable String numeroCaso, Model model, RedirectAttributes redirectAttributes) {
    	List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
        CasoInfo caso = getCaso(numeroCaso);
        if (caso == null) {
        	mensajes.add("warning::El caso " + numeroCaso + " no existe.");
        	return "redirect:/";
        }
        model.addAttribute("caso", caso);
        
        FindResponse<ProyectoCancelacionInfo> find1 = proyectoCancelacionService.find(
                new FindByRequest(caso.getProyectoCancelacionId()));
        ProyectoCancelacionInfo proyecto = find1.getInfo();
        model.addAttribute("proyecto", proyecto);

        ListResponse<CancelacionArchivoInfo> listresp = cancelacionArchivoService.list(
                new ListRequest("proyectoCancelacionId", proyecto.getId()));
        model.addAttribute("archivos", listresp.getList());

        return "/jcobranza/ver";
    }

    @RequestMapping(value = "/cobranza/caso/{numeroCaso}/validaCredito", method = RequestMethod.POST)
    public String actualizaProcedeCredito(@PathVariable String numeroCaso, Model model, RedirectAttributes redirectAttributes) {
    	List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
    	CasoInfo caso = getCaso(numeroCaso);
        if (caso == null) {
        	mensajes.add("warning::El caso " + numeroCaso + " no existe.");
        	return "redirect:/";
        }
    	String antes = caso.getProcedeCredito();
    	SaveResponse<CasoInfo> validarCredito = casoService.validarCredito(new SaveRequest<>(caso));
        caso = validarCredito.getInfo();
        if (!antes.equals(caso.getProcedeCredito())) {
        	casoService.update(new UpdateRequest<>(caso));
        }
        sesion.setCaso(caso);
    	return "redirect:/cobranza/caso/" + numeroCaso;
    }

    @RequestMapping(value = "/archivoss/{id}/{filename}", method = RequestMethod.GET, produces = MediaType.ALL_VALUE)
    public @ResponseBody byte[] descargar(
            @PathVariable long id,
            @PathVariable String filename,
            HttpServletResponse response) {
        FindResponse<CancelacionArchivoInfo> file = cancelacionArchivoService.findBy(new FindByRequest(id));
        if (file.getInfo() == null) {
        	response.setContentType("text/plain");
        	return "Archivo no encontrado.".getBytes();
        }
        CancelacionArchivoInfo info = file.getInfo();
        response.setContentType(info.getMimetype());
        return info.getArchivo();
    }

    @RequestMapping(value = "/cobranza/caso/{numeroCaso}/fechafirma", method = RequestMethod.GET)
    public String asignarFechaFirmaNotario(@PathVariable String numeroCaso, Model model, RedirectAttributes redirectAttributes) {
    	List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
    	CasoInfo caso = getCaso(numeroCaso);
        if (caso == null) {
        	mensajes.add("warning::El caso " + numeroCaso + " no existe.");
        	return "redirect:/";
        }
        model.addAttribute("caso", caso);
        
        FindResponse<ProyectoCancelacionInfo> find1 = proyectoCancelacionService.find(
                new FindByRequest(caso.getProyectoCancelacionId()));
        model.addAttribute("proyecto", find1.getInfo());
        
        return "/jcobranza/firma";
    }

    @RequestMapping(value = "/cobranza/caso/{numeroCaso}/fechafirma", method = RequestMethod.POST)
    public String guardaFechaFirmaNotario(
            @PathVariable String numeroCaso,
            @RequestParam("fechaFirma") Date fechaAsignada,
            Model model,
            RedirectAttributes redirectAttributes) {
    	List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
    	CasoInfo caso = getCaso(numeroCaso);
        if (caso == null) {
        	mensajes.add("warning::El caso " + numeroCaso + " no existe.");
        	return "redirect:/";
        }
        model.addAttribute("caso", caso);

        // TODO guarda fecha firma con notario / actualizacion de estados
        FindResponse<ProyectoCancelacionInfo> find1 = proyectoCancelacionService.find(
                new FindByRequest(caso.getProyectoCancelacionId()));
        ProyectoCancelacionInfo proyecto = find1.getInfo();
        FindResponse<StatusProyectoInfo> find2 = statusProyectoService.find(new FindByRequest("clave", 8));
        proyecto.setStatusProyecto(find2.getInfo());
        proyecto.setFechaAsignadaParaFirma(fechaAsignada);
        
        UpdateResponse<ProyectoCancelacionInfo> update = proyectoCancelacionService.update(new UpdateRequest<>(proyecto));
        return "redirect:/";
    }

    @RequestMapping(value = "/cobranza/caso/{numeroCaso}/registrarfirma", method = RequestMethod.POST)
    public String guardaRegistrarFechaFirmaNotario(
            @PathVariable String numeroCaso,
            @RequestParam("fechaFirma") Date fechaAsignada,
            Model model,
            RedirectAttributes redirectAttributes) {
    	List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
    	CasoInfo caso = getCaso(numeroCaso);
        if (caso == null) {
        	mensajes.add("warning::El caso " + numeroCaso + " no existe.");
        	return "redirect:/";
        }
        model.addAttribute("caso", caso);

        // TODO guarda fecha firma con notario / actualizacion de estados
        FindResponse<ProyectoCancelacionInfo> find1 = proyectoCancelacionService.find(
                new FindByRequest(caso.getProyectoCancelacionId()));
        ProyectoCancelacionInfo proyecto = find1.getInfo();
        FindResponse<StatusProyectoInfo> find2 = statusProyectoService.find(new FindByRequest("clave", 9));
        proyecto.setStatusProyecto(find2.getInfo());
        proyecto.setFechaFirmaNotario(fechaAsignada);
        
        UpdateResponse<ProyectoCancelacionInfo> update = proyectoCancelacionService.update(new UpdateRequest<>(proyecto));
        return "redirect:/";
    }
}
