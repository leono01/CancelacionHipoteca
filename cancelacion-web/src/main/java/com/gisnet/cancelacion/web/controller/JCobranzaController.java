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
import com.gisnet.cancelacion.core.services.CasoService;
import com.gisnet.cancelacion.core.services.EmpleadoService;
import com.gisnet.cancelacion.core.services.ProyectoCancelacionService;
import com.gisnet.cancelacion.core.services.StatusCasoService;
import com.gisnet.cancelacion.core.services.StatusProyectoService;
import com.gisnet.cancelacion.events.*;
import com.gisnet.cancelacion.events.info.*;
import com.gisnet.cancelacion.web.domain.SesionJCobranza;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
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

    private static final Logger logger = Logger.getLogger(JCobranzaController.class);

    @Autowired
    private CancelacionArchivoService cancelacionArchivoService;
    @Autowired
    private CasoService casoService;
    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private ProyectoCancelacionService proyectoCancelacionService;

    @Autowired
    private SesionJCobranza sesion;

    public String index(Model model, Principal principal) {
        sesionSetEmpleado(principal);
        EmpleadoInfo empleado = sesion.getEmpleado();
        if (empleado == null) {
            System.err.println("Datos del empleado no encontrados.");
            model.addAttribute("casosrevizar", new ArrayList<>());
            model.addAttribute("casosespera", new ArrayList<>());
            return "/jcobranza/index";
        }

        MapResponse<List<CasoInfo>> mapa1 = casoService.gerenteCobranzaMapaCasosPendientes(
                sesion.getEmpleado().getId());
        model.addAttribute("casosrevizar", mapa1.getMapa().get("casosRevizar"));
        model.addAttribute("casosespera", mapa1.getMapa().get("casosEspera"));
        
        return "/jcobranza/index";
    }

    private void sesionSetEmpleado(Principal principal) {
        if (sesion.getEmpleado() == null) {
            FindResponse<EmpleadoInfo> findresponse = empleadoService.find(
                    new FindByRequest("nombreUsuario", principal.getName()));
            EmpleadoInfo empleado = findresponse.getInfo();
            sesion.setEmpleado(empleado);
        }
    }

    private void sesionSetProyectoCancelacion() throws NullPointerException {
        if (sesion.getCaso().getProyectoCancelacionId() > 0) {
            FindResponse<ProyectoCancelacionInfo> find = proyectoCancelacionService.find(
                    new FindByRequest(sesion.getCaso().getProyectoCancelacionId()));
            if (find.getInfo() == null) {
                sesion.setProyectoCancelacion(null);
                throw new NullPointerException("No existe proyecto de cancelacion con id " + sesion.getCaso().getProyectoCancelacionId());
            }
            sesion.setProyectoCancelacion(find.getInfo());
        } else {
            sesion.setProyectoCancelacion(new ProyectoCancelacionInfo());
        }
    }

    private void sesionSetCaso(String numeroCaso) throws CancelacionWebException, NullPointerException {
        if (sesion.getCaso() != null) {
            if (sesion.getCaso().getNumeroCaso().equals(numeroCaso)) {
                sesionSetProyectoCancelacion();
            } else {
                sesion.setCaso(null);
            }
        }
        if (sesion.getCaso() == null) {
            FindResponse<CasoInfo> find = casoService.find(new FindByRequest("numeroCaso", numeroCaso));
            sesion.setCaso(find.getInfo());
            if (find.getInfo() != null) {
                sesionSetProyectoCancelacion();
            } else {
                sesion.setProyectoCancelacion(null);
                throw new NullPointerException("No existe caso con id " + numeroCaso);
            }
        }
        if (sesion.getProyectoCancelacion().getEmpleadoId() > 0) {
            if (sesion.getProyectoCancelacion().getEmpleadoId() != sesion.getEmpleado().getId()) {
                throw new CancelacionWebException("Asignado a otro jefe de cobranza");
            }
        }
    }

    @RequestMapping(value = "/cobranza/caso/{numeroCaso}", method = RequestMethod.GET)
    public String ver(@PathVariable String numeroCaso, Model model, RedirectAttributes redirectAttributes, Principal principal) {
        sesionSetEmpleado(principal);
        List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
        try {
            sesionSetCaso(numeroCaso);
        } catch (CancelacionWebException ex) {
            mensajes.add("warning::Caso asignado a otro jefe de cobranza");
            return "redirect:/";
        } catch (NullPointerException ex) {
            mensajes.add("warning::El caso " + numeroCaso + " no existe.");
            return "redirect:/";
        }
        CasoInfo caso = sesion.getCaso();
        model.addAttribute("caso", caso);

        ProyectoCancelacionInfo proyecto = sesion.getProyectoCancelacion();
        model.addAttribute("proyecto", proyecto);
        if (proyecto == null) {
            model.addAttribute("archivos", new ArrayList<CancelacionArchivoInfo>());
        } else {
            ListResponse<CancelacionArchivoInfo> list1 = cancelacionArchivoService.list(
                    new ListRequest("proyectoCancelacionId", proyecto.getId()));
            model.addAttribute("archivos", list1.getList());
        }
        return "/jcobranza/ver";
    }

    @RequestMapping(value = "/cobranza/caso/{numeroCaso}/validaCredito", method = RequestMethod.POST)
    public String actualizaProcedeCredito(@PathVariable String numeroCaso, Model model, RedirectAttributes redirectAttributes) {
        List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
        try {
            sesionSetCaso(numeroCaso);
        } catch (CancelacionWebException ex) {
            mensajes.add("warning::Caso asignado a otro jefe de cobranza");
            return "redirect:/";
        } catch (NullPointerException ex) {
            mensajes.add("warning::El caso " + numeroCaso + " no existe.");
            return "redirect:/";
        }
        UpdateResponse<CasoInfo> update1 = casoService.gerenteCobranzaActualizaProcedeCredito(sesion.getCaso());
        sesion.setCaso(update1.getInfo());

        return "redirect:/cobranza/caso/" + numeroCaso;
    }

    @RequestMapping(value = "/archivoss/{id}/{filename}", method = RequestMethod.GET, produces = MediaType.ALL_VALUE)
    public @ResponseBody
    byte[] descargar(
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
        try {
            sesionSetCaso(numeroCaso);
        } catch (CancelacionWebException ex) {
            mensajes.add("warning::Caso asignado a otro jefe de cobranza");
            return "redirect:/";
        } catch (NullPointerException ex) {
            mensajes.add("warning::El caso " + numeroCaso + " no existe.");
            return "redirect:/";
        }
        CasoInfo caso = sesion.getCaso();
        model.addAttribute("caso", caso);
        model.addAttribute("proyecto", sesion.getProyectoCancelacion());

        return "/jcobranza/firma";
    }

    @RequestMapping(value = "/cobranza/caso/{numeroCaso}/fechafirma", method = RequestMethod.POST)
    public String guardaFechaFirmaNotario(
            @PathVariable String numeroCaso,
            @RequestParam("fechaFirma") Date fechaAsignada,
            Model model,
            RedirectAttributes redirectAttributes) {
        List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
        try {
            sesionSetCaso(numeroCaso);
        } catch (CancelacionWebException ex) {
            mensajes.add("warning::Caso asignado a otro jefe de cobranza");
            return "redirect:/";
        } catch (NullPointerException ex) {
            mensajes.add("warning::El caso " + numeroCaso + " no existe.");
            return "redirect:/";
        }
        StatusResponse gerenteCobranzaProgramaFechaFirma = casoService.gerenteCobranzaProgramaFechaFirma(
                sesion.getCaso(),
                sesion.getProyectoCancelacion(),
                fechaAsignada);
        if (gerenteCobranzaProgramaFechaFirma.getStatus() == 1) {
            mensajes.add("success::Fecha de firma asignada.");
            return "redirect:/";
        } else {
            mensajes.add("warning::Ocurrio un error.");
            return "redirect:/cobranza/caso/" + numeroCaso + "/fechafirma";
        }
    }

    @RequestMapping(value = "/cobranza/caso/{numeroCaso}/registrarfirma", method = RequestMethod.POST)
    public String guardaRegistrarFechaFirmaNotario(
            @PathVariable String numeroCaso,
            @RequestParam("fechaFirma") Date fechaAsignada,
            Model model,
            RedirectAttributes redirectAttributes) {
        List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
        try {
            sesionSetCaso(numeroCaso);
        } catch (CancelacionWebException ex) {
            mensajes.add("warning::Caso asignado a otro jefe de cobranza");
            return "redirect:/";
        } catch (NullPointerException ex) {
            mensajes.add("warning::El caso " + numeroCaso + " no existe.");
            return "redirect:/";
        }
        StatusResponse gerenteCobranzaRegistraFechaFirma = casoService.gerenteCobranzaRegistraFechaFirma(
                sesion.getCaso(),
                sesion.getProyectoCancelacion(),
                fechaAsignada);
        if (gerenteCobranzaRegistraFechaFirma.getStatus() == 1) {
            mensajes.add("success::Fecha de firma registrada.");
            return "redirect:/";
        } else {
            mensajes.add("warning::Ocurrio un error.");
            return "redirect:/cobranza/caso/" + numeroCaso + "/registrarfirma";
        }
    }

    @RequestMapping(value = "/cobranza/buscar", method = RequestMethod.GET)
    public String buscar(Model model, RedirectAttributes redirectAttributes) {
        return "/jcobranza/buscarcarta";
    }

    @RequestMapping(value = "/cobranza/buscar", method = RequestMethod.POST)
    public String buscarResultado(
            @RequestParam("numeroCaso") String numeroCaso,
            @RequestParam("numeroCredito") String numeroCredito,
            Model model,
            RedirectAttributes redirectAttributes) {
        List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
        FindResponse<CasoInfo> find1 = casoService.gerenteCobranzaBuscaCasoParaValidar(
                sesion.getEmpleado().getId(), numeroCaso, numeroCredito);
        CasoInfo caso = find1.getInfo();
        if (caso == null) {
            mensajes.add("info::Caso no encontrado.");
            return "redirect:/cobranza/buscar";
        } else {
            return "redirect:/cobranza/caso/" + caso.getNumeroCaso();
        }
    }

    @RequestMapping(value = "/cobranza/caso/{numeroCaso}/autorizar", method = RequestMethod.POST)
    public String autorizarProyecto(
            @PathVariable String numeroCaso,
            Model model,
            RedirectAttributes redirectAttributes) {
        List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
        try {
            sesionSetCaso(numeroCaso);
        } catch (CancelacionWebException ex) {
            mensajes.add("warning::Caso asignado a otro jefe de cobranza");
            return "redirect:/";
        } catch (NullPointerException ex) {
            mensajes.add("warning::El caso " + numeroCaso + " no existe.");
            return "redirect:/";
        }
        StatusResponse gerenteCobranzaValidaCaso = casoService.gerenteCobranzaAutorizaCaso(sesion.getEmpleado().getId(), sesion.getCaso(), sesion.getProyectoCancelacion());
        if (gerenteCobranzaValidaCaso.getStatus() == 1) {
            mensajes.add("success::Caso autorizado.");
            return "redirect:/cobranza/caso/" + sesion.getCaso().getNumeroCaso();
        } else {
            mensajes.add("warning::Ocurrio un error.");
            return "redirect:/cobranza/caso/" + sesion.getCaso().getNumeroCaso();
        }
    }

}
