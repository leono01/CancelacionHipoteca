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

import com.gisnet.cancelacion.core.services.CartaCancelacionService;
import com.gisnet.cancelacion.core.services.CasoService;
import com.gisnet.cancelacion.core.services.EmpleadoService;
import com.gisnet.cancelacion.core.services.NotarioService;
import com.gisnet.cancelacion.core.services.ProyectoCancelacionService;
import com.gisnet.cancelacion.events.*;
import com.gisnet.cancelacion.events.info.*;
import com.gisnet.cancelacion.web.domain.SesionNotario;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
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
    private SesionNotario sesion;

    public String index(Model model, Principal principal) {
        List<String> mensajes = Utils.getMensajes(model);
        if (sesion.getNotarioInfo() == null) {
            FindResponse<NotarioInfo> findresponse = notarioService.find(
                    new FindByRequest("nombreUsuario", principal.getName()));
            if (findresponse.getInfo() == null) {
                model.addAttribute("casos", new ArrayList<CasoInfo>());
                return "/notario/index";
            }
            sesion.setNotarioInfo(findresponse.getInfo());
        }
        ListResponse<CasoInfo> list2 = casoService.notarioInfonavitListaCasosPendientes(sesion.getNotarioInfo().getId());
        model.addAttribute("casos", list2.getList());
        for (CasoInfo caso : list2.getList()) {
            if (caso.getStatusCaso().getClave() == 14) {
                FindResponse<ProyectoCancelacionInfo> find1 = proyectoCancelacionService.find(
                        new FindByRequest(caso.getProyectoCancelacionId()));
                ProyectoCancelacionInfo info = find1.getInfo();
                mensajes.add("info::Pasar a firmar el caso " + caso.getNumeroCaso() + ", el dia " + info.getFechaAsignadaParaFirma());
            }
        }

        return "/notario/index";
    }

    private void sesionSetCaso(String numeroCaso) throws CancelacionWebException, NullPointerException {
        if (sesion.getNotarioInfo() == null) {
            throw new CancelacionWebException("Notario no encontrado");
        }
        if (sesion.getCasoInfo() != null) {
            if (sesion.getCasoInfo().getNotarioId() != sesion.getNotarioInfo().getId()) {
                throw new CancelacionWebException("Caso no correspondiente al notario activo");
            }
            if (!sesion.getCasoInfo().getNumeroCaso().equals(numeroCaso)) {
                sesion.getCancelacionArchivos().clear();
                FindResponse<CasoInfo> find1 = casoService.find(new FindByRequest("numeroCaso", numeroCaso));
                if (find1.getInfo() == null) {
                    throw new NullPointerException("Caso no encontrado.");
                }
                sesion.setCasoInfo(find1.getInfo());
            }
        } else {
            sesion.getCancelacionArchivos().clear();
            FindResponse<CasoInfo> find1 = casoService.find(new FindByRequest("numeroCaso", numeroCaso));
            if (find1.getInfo() == null) {
                throw new NullPointerException("Caso no encontrado.");
            }
            if (find1.getInfo().getNotarioId() != sesion.getNotarioInfo().getId()) {
                throw new CancelacionWebException("Caso no correspondiente al notario activo");
            }
            sesion.setCasoInfo(find1.getInfo());
        }
        
    }

    @RequestMapping(value = "/notario/caso/{numeroCaso}", method = RequestMethod.GET)
    public String verCaso(@PathVariable String numeroCaso, Model model, RedirectAttributes redirectAttributes) {
        List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
        try {
            sesionSetCaso(numeroCaso);
        } catch (CancelacionWebException | NullPointerException ex) {
            mensajes.add("warning::El caso " + numeroCaso + " no existe.");
            return "redirect:/";
        }
        CasoInfo caso = sesion.getCasoInfo();
        model.addAttribute("caso", caso);
        
        FindResponse<ProyectoCancelacionInfo> find1 = proyectoCancelacionService.find(
                new FindByRequest(caso.getProyectoCancelacionId()));
        model.addAttribute("pcancelacion", find1.getInfo());

        return "/notario/view";
    }

    @RequestMapping(value = "/archivos/{id}/carta_de_cancelacion.pdf", method = RequestMethod.GET, produces = "application/pdf")
    public @ResponseBody
    byte[] renderpdf(@PathVariable long id, HttpServletResponse response) {
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
    public String verAgregaArchivos(@PathVariable String numeroCaso, Model model, RedirectAttributes redirectAttributes) {
        List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
        try {
            sesionSetCaso(numeroCaso);
        } catch (CancelacionWebException | NullPointerException ex) {
            mensajes.add("warning::El caso " + numeroCaso + " no existe.");
            return "redirect:/";
        }
        CasoInfo caso = sesion.getCasoInfo();
        model.addAttribute("caso", caso);
        return "/notario/accept";
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
        List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
        try {
            sesionSetCaso(numeroCaso);
        } catch (CancelacionWebException | NullPointerException ex) {
            mensajes.add("warning::El caso " + numeroCaso + " no existe.");
            return "redirect:/";
        }
        CasoInfo caso = sesion.getCasoInfo();
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
    }

    private void guardaCancelacionArchivo(MultipartFile file) throws IOException {
        CancelacionArchivoInfo archivo = new CancelacionArchivoInfo();
        archivo.setArchivo(file.getBytes());
        archivo.setNombre(file.getOriginalFilename());
        archivo.setMimetype(file.getContentType());
        sesion.getCancelacionArchivos().add(archivo);
    }

    @RequestMapping(value = "/notario/caso/{numeroCaso}/aceptar/jefecobranza", method = RequestMethod.GET)
    public String verSeleccionGerenteCobranza(
            @PathVariable String numeroCaso,
            Model model,
            RedirectAttributes redirectAttributes) {
        List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
        try {
            sesionSetCaso(numeroCaso);
        } catch (CancelacionWebException | NullPointerException ex) {
            mensajes.add("warning::El caso " + numeroCaso + " no existe.");
            return "redirect:/";
        }
        CasoInfo caso = sesion.getCasoInfo();
        model.addAttribute("caso", caso);

        ListResponse<EmpleadoInfo> list1 = empleadoService.listarGerentesCobranza();
        model.addAttribute("jefesc", list1.getList());

        return "/notario/jefeCobranza";
    }

    @RequestMapping(value = "/notario/caso/{numeroCaso}/aceptar/jefecobranza", method = RequestMethod.POST)
    public String aceptaCaso(
            @PathVariable String numeroCaso,
            @RequestParam("jefec") long jefec,
            Model model,
            RedirectAttributes redirectAttributes) {
        List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
        try {
            sesionSetCaso(numeroCaso);
        } catch (CancelacionWebException | NullPointerException ex) {
            mensajes.add("warning::El caso " + numeroCaso + " no existe.");
            return "redirect:/";
        }
        StatusResponse notarioInfonavitAceptaCaso = casoService.notarioInfonavitAceptaCaso(
                sesion.getNotarioInfo().getId(),
                sesion.getCasoInfo().getId(),
                jefec,
                sesion.getCancelacionArchivos());
        if (notarioInfonavitAceptaCaso.getStatus() == 1) {
            mensajes.add("success::El caso ha sido aceptado y remitido al gerente de cobranza.");
            return "redirect:/";
        } else {
            mensajes.add("warning::Ocurrio un error.");
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/notario/caso/{numeroCaso}/rechazar", method = RequestMethod.GET)
    public String verRechazaCaso(
            @PathVariable String numeroCaso,
            Model model,
            RedirectAttributes redirectAttributes) {
        List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
        try {
            sesionSetCaso(numeroCaso);
        } catch (CancelacionWebException | NullPointerException ex) {
            mensajes.add("warning::El caso " + numeroCaso + " no existe.");
            return "redirect:/";
        }
        CasoInfo caso = sesion.getCasoInfo();
        model.addAttribute("caso", caso);
        return "/notario/reject";
    }

    @RequestMapping(value = "/notario/caso/{numeroCaso}/rechazar", method = RequestMethod.POST)
    public String rechazaCaso(
            @PathVariable String numeroCaso,
            @RequestParam("motivo") String motivoRechazo,
            Model model,
            RedirectAttributes redirectAttributes) {
        List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
        try {
            sesionSetCaso(numeroCaso);
        } catch (CancelacionWebException | NullPointerException ex) {
            mensajes.add("warning::El caso " + numeroCaso + " no existe.");
            return "redirect:/";
        }
        StatusResponse notarioInfonavitRechazaCaso = casoService.notarioInfonavitRechazaCaso(
                sesion.getNotarioInfo().getId(),
                sesion.getCasoInfo().getId(),
                motivoRechazo);
        if (notarioInfonavitRechazaCaso.getStatus() == 1) {
            mensajes.add("success::El caso ha sido rechazado.");
            return "redirect:/";
        } else {
            mensajes.add("warning::Ocurrio un error.");
            return "redirect:/notario/caso/" + numeroCaso + "/rechazar";
        }
    }

}
