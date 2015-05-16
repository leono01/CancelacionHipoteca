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
import com.gisnet.cancelacion.core.services.UsuarioService;
import com.gisnet.cancelacion.events.FindByRequest;
import com.gisnet.cancelacion.events.FindResponse;
import com.gisnet.cancelacion.events.ListRequest;
import com.gisnet.cancelacion.events.ListResponse;
import com.gisnet.cancelacion.events.SaveRequest;
import com.gisnet.cancelacion.events.SaveResponse;
import com.gisnet.cancelacion.events.UpdateRequest;
import com.gisnet.cancelacion.events.UpdateResponse;
import com.gisnet.cancelacion.events.info.CancelacionArchivoInfo;
import com.gisnet.cancelacion.events.info.CartaCancelacionInfo;
import com.gisnet.cancelacion.events.info.CasoInfo;
import com.gisnet.cancelacion.events.info.EmpleadoInfo;
import com.gisnet.cancelacion.events.info.NotarioInfo;
import com.gisnet.cancelacion.events.info.ProyectoCancelacionInfo;
import com.gisnet.cancelacion.events.info.StatusCasoInfo;
import com.gisnet.cancelacion.events.info.UsuarioInfo;
import com.gisnet.cancelacion.persistance.repository.CasoRepository;
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
        model.addAttribute("casos", listresponse.getList());
        
        ///////
        ListResponse<UsuarioInfo> list = usuarioService.list(new ListRequest("rol", "JEFE_COBRANZA"));
        System.err.println("user cobranza found " + list.getList().size());
        for (UsuarioInfo ui : list.getList()) {
            System.err.println("user found " + ui.getNombreUsuario());
        }
        ///////
        
        
        return "/notario/index";
    }
    
    @RequestMapping(value = "/notario/caso/{numeroCaso}", method = RequestMethod.GET)
    public String ver(@PathVariable int numeroCaso, Model model) {
        FindResponse<CasoInfo> find = casoService.find(new FindByRequest("numeroCaso", numeroCaso));
        CasoInfo caso = find.getInfo();
        model.addAttribute("caso", caso);
        
        FindResponse<CartaCancelacionInfo> findcarta = cartaCancelacionService.findBy(
                new FindByRequest(caso.getCartaCancelacionId()));
        CartaCancelacionInfo cartac = findcarta.getInfo();
        return "/notario/view";
    }
    
    @RequestMapping(value = "/archivos/{id}/carta_de_cancelacion.pdf", method = RequestMethod.GET, produces = "application/pdf")
    public @ResponseBody byte[] renderpdf(@PathVariable long id, HttpServletResponse response) {
        FindResponse<CartaCancelacionInfo> findcarta = cartaCancelacionService.findBy(
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
        proyecto.setStatusProyectoId(1l);
        
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
            System.err.println("find empleado w");
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
        proyecto.setStatusProyectoId(5l);
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
        proyecto.setStatusProyectoId(11l); // cerrado por rechazo del notario
        proyecto.setMotivoRechazo(motivoRechazo);
        
        SaveResponse<ProyectoCancelacionInfo> saved = proyectoCancelacionService.save(
                new SaveRequest<>(proyecto));
        proyecto = saved.getInfo();
        
        caso.setProyectoCancelacionId(proyecto.getId());
        StatusCasoInfo status = new StatusCasoInfo();
        status.setId(1l);
        caso.setStatusCaso(status);
        
        // cambiar de notario
        caso.setNotarioId(2l);
        
        UpdateResponse<CasoInfo> updated = casoService.update(new UpdateRequest<>(caso));
        caso = updated.getInfo();
        
        return "redirect:/";
    }
    
}

