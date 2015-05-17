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
import com.gisnet.cancelacion.web.domain.CasoCompleto;
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
    private StatusProyectoService statusProyectoService;
    @Autowired
    private UsuarioService usuarioService;
    
    public String index(Model model, Principal principal) {
        FindResponse<NotarioInfo> findresponse = notarioService.find(
                new FindByRequest("nombreUsuario", principal.getName()));
        NotarioInfo notario = findresponse.getInfo();
        if (notario == null) {
            model.addAttribute("casos", new ArrayList<CasoInfo>());
            return "/notario/index";
        }
        ListResponse<CasoInfo> listresponse = casoService.list(
                new ListRequest("notarioId", notario.getId()));
        
        List<CasoCompleto> casos = new ArrayList<>();
        for (CasoInfo caso : listresponse.getList()) {
            CasoCompleto casocompleto = new CasoCompleto();
            casocompleto.setCaso(caso);
            if (caso.getProyectoCancelacionId() > 0) {
                FindResponse<ProyectoCancelacionInfo> find = proyectoCancelacionService.find(
                        new FindByRequest(caso.getProyectoCancelacionId()));
                casocompleto.setProyectoCancelacion(find.getInfo());
            }
            casos.add(casocompleto);
        }
        model.addAttribute("casos", casos);

        return "/notario/index";
    }
    
    @RequestMapping(value = "/notario/caso/{numeroCaso}", method = RequestMethod.GET)
    public String ver(@PathVariable int numeroCaso, Model model) {
        FindResponse<CasoInfo> find = casoService.find(new FindByRequest("numeroCaso", numeroCaso));
        CasoInfo caso = find.getInfo();
        model.addAttribute("caso", caso);
        
        return "/notario/view";
    }
    
    @RequestMapping(value = "/archivos/{id}/carta_de_cancelacion.pdf", method = RequestMethod.GET, produces = "application/pdf")
    public @ResponseBody byte[] renderpdf(@PathVariable long id, HttpServletResponse response) {
        FindResponse<CartaCancelacionInfo> findcarta = cartaCancelacionService.find(
                new FindByRequest(id));
        CartaCancelacionInfo cartac = findcarta.getInfo();
        return cartac.getPdf();
    }

    @RequestMapping(value = "/notario/caso/{numeroCaso}/aceptar", method = RequestMethod.GET)
    public String aceptar(@PathVariable int numeroCaso, Model model) {
        FindResponse<CasoInfo> find = casoService.find(new FindByRequest("numeroCaso", numeroCaso));
        CasoInfo caso = find.getInfo();
        model.addAttribute("caso", caso);
        return "/notario/accept";
    }
    
    @RequestMapping(value = "/notario/caso/{numeroCaso}/aceptar", method = RequestMethod.POST)
    public String aceptarCaso(
            @PathVariable int numeroCaso,
            Model model,
            RedirectAttributes redirectAttributes,
            @RequestParam("file1") MultipartFile file1,
            @RequestParam("file2") MultipartFile file2,
            @RequestParam("file3") MultipartFile file3,
            @RequestParam("file4") MultipartFile file4,
            @RequestParam("file5") MultipartFile file5) {
        
        List<String> messages = new ArrayList<>();
        redirectAttributes.addFlashAttribute("messages", messages);
        
        FindResponse<CasoInfo> find = casoService.find(new FindByRequest("numeroCaso", numeroCaso));
        CasoInfo caso = find.getInfo();
        model.addAttribute("caso", caso);
        
        // crea proyecto cancelacion
        
        ProyectoCancelacionInfo proyecto = new ProyectoCancelacionInfo();
        proyecto.setFechaCreacion(new Date());
        FindResponse<StatusProyectoInfo> find1 = statusProyectoService.find(
                new FindByRequest("clave", 1));
        proyecto.setStatusProyecto(find1.getInfo());
        
        SaveResponse<ProyectoCancelacionInfo> saved = proyectoCancelacionService.save(
                new SaveRequest<>(proyecto));
        proyecto = saved.getInfo();
        
        caso.setProyectoCancelacionId(proyecto.getId());
        UpdateResponse<CasoInfo> updated = casoService.update(new UpdateRequest<>(caso));
        //caso = updated.getInfo();
        
        // guarda archivos
        
        if (!file1.isEmpty()) {
            try {
                guardaCancelacionArchivo(file1, proyecto.getId());
            } catch (IOException ex) {
                return "redirect:/";
            }
        }
        if (!file2.isEmpty()) {
            try {
                guardaCancelacionArchivo(file2, proyecto.getId());
            } catch (IOException ex) {
                return "redirect:/";
            }
        }
        if (!file3.isEmpty()) {
            try {
                guardaCancelacionArchivo(file3, proyecto.getId());
            } catch (IOException ex) {
                return "redirect:/";
            }
        }
        if (!file4.isEmpty()) {
            try {
                guardaCancelacionArchivo(file4, proyecto.getId());
            } catch (IOException ex) {
                return "redirect:/";
            }
        }
        if (!file5.isEmpty()) {
            try {
                guardaCancelacionArchivo(file5, proyecto.getId());
            } catch (IOException ex) {
                return "redirect:/";
            }
        }
        return "redirect:/notario/caso/" + numeroCaso + "/aceptar/jefecobranza";
    }
    
    private void guardaCancelacionArchivo(MultipartFile file, long proyectoCancelacionId) throws IOException {
        CancelacionArchivoInfo archivo = new CancelacionArchivoInfo();
        archivo.setArchivo(file.getBytes());
        archivo.setNombre(file.getOriginalFilename());
        archivo.setMimetype(file.getContentType());
        archivo.setProyectoCancelacionId(proyectoCancelacionId);
        
        SaveResponse<CancelacionArchivoInfo> save = cancelacionArchivoService.save(new SaveRequest<>(archivo));
    }
    
    @RequestMapping(value = "/notario/caso/{numeroCaso}/aceptar/jefecobranza", method = RequestMethod.GET)
    public String seleccionaJefeCobranza(
            @PathVariable int numeroCaso,
            Model model) {
        
        FindResponse<CasoInfo> find0 = casoService.find(
                new FindByRequest("numeroCaso", numeroCaso));
        CasoInfo caso = find0.getInfo();
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
    }
    
    @RequestMapping(value = "/notario/caso/{numeroCaso}/aceptar/jefecobranza", method = RequestMethod.POST)
    public String guardaJefeCobranza(
            @PathVariable int numeroCaso,
            @RequestParam("jefec") long jefec,
            Model model) {
        
        FindResponse<CasoInfo> find = casoService.find(
                new FindByRequest("numeroCaso", numeroCaso));
        CasoInfo caso = find.getInfo();
        model.addAttribute("caso", caso);
        
        // obtiene proyecto cancelacion
        FindResponse<ProyectoCancelacionInfo> find1 = proyectoCancelacionService.find(
                new FindByRequest(caso.getProyectoCancelacionId()));
        ProyectoCancelacionInfo proyecto = find1.getInfo();
        
        // agregamos jefe cobranza, actualiza estado
        proyecto.setEmpleadoId(jefec);
        FindResponse<StatusProyectoInfo> find2 = statusProyectoService.find(
                new FindByRequest("clave", 5));
        proyecto.setStatusProyecto(find2.getInfo());
        UpdateResponse<ProyectoCancelacionInfo> update = proyectoCancelacionService.update(
                new UpdateRequest<>(proyecto));
        //proyecto = update.getInfo();
        
        // actualiza estado caso
        StatusCasoInfo sc = new StatusCasoInfo();
        sc.setId(4l);
        caso.setStatusCaso(sc);
        casoService.update(new UpdateRequest<>(caso));
        
        return "redirect:/";
    }
    
    
    @RequestMapping(value = "/notario/caso/{numeroCaso}/rechazar", method = RequestMethod.GET)
    public String rechazar(
            @PathVariable int numeroCaso,
            Model model) {
        FindResponse<CasoInfo> find = casoService.find(
                new FindByRequest("numeroCaso", numeroCaso));
        CasoInfo caso = find.getInfo();
        model.addAttribute("caso", caso);
        return "/notario/reject";
    }
    
    @RequestMapping(value = "/notario/caso/{numeroCaso}/rechazar", method = RequestMethod.POST)
    public String rechazar(
            @PathVariable int numeroCaso,
            @RequestParam("motivo") String motivoRechazo,
            Model model) {
        FindResponse<CasoInfo> find = casoService.find(
                new FindByRequest("numeroCaso", numeroCaso));
        CasoInfo caso = find.getInfo();
        model.addAttribute("caso", caso);
        
        // crea proyecto cancelacion
        ProyectoCancelacionInfo proyecto = new ProyectoCancelacionInfo();
        proyecto.setFechaCreacion(new Date());
        
        FindResponse<StatusProyectoInfo> findstatus = statusProyectoService.find(
                new FindByRequest("clave", 11));
        proyecto.setStatusProyecto(findstatus.getInfo());
        proyecto.setMotivoRechazo(motivoRechazo);
        
        SaveResponse<ProyectoCancelacionInfo> saved = proyectoCancelacionService.save(
                new SaveRequest<>(proyecto));
        proyecto = saved.getInfo();
        
        caso.setProyectoCancelacionId(proyecto.getId());
        //StatusCasoInfo status = new StatusCasoInfo();
        //status.setId(1l); // ** NO CAMBIAR ESTADO, CAMBIAR DE NOTARIO **
        //caso.setStatusCaso(status);
        
        // cambiar de notario
        caso.setNotarioId(0l);
        
        UpdateResponse<CasoInfo> updated = casoService.update(new UpdateRequest<>(caso));
        caso = updated.getInfo();
        
        return "redirect:/";
    }
    
}

