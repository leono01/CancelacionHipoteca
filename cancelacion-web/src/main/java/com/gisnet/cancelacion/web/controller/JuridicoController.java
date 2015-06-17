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

import com.gisnet.cancelacion.core.services.EntidadService;
import com.gisnet.cancelacion.core.services.MunicipioService;
import com.gisnet.cancelacion.core.services.NotarioService;
import com.gisnet.cancelacion.events.FindByRequest;
import com.gisnet.cancelacion.events.FindResponse;
import com.gisnet.cancelacion.events.ListRequest;
import com.gisnet.cancelacion.events.ListResponse;
import com.gisnet.cancelacion.events.MultipleParams;
import com.gisnet.cancelacion.events.SaveRequest;
import com.gisnet.cancelacion.events.SaveResponse;
import com.gisnet.cancelacion.events.UpdateRequest;
import com.gisnet.cancelacion.events.info.EntidadInfo;
import com.gisnet.cancelacion.events.info.MunicipioInfo;
import com.gisnet.cancelacion.events.info.NotarioInfo;
import com.gisnet.cancelacion.web.domain.NotarioForm;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class JuridicoController {
    
    private static final Logger logger = Logger.getLogger(JuridicoController.class);

    @Autowired
    private NotarioService service;
    @Autowired
    private EntidadService entidadservice;
    @Autowired
    private MunicipioService municipioservice;

    public String index(Model model, Principal principal) {
        ListRequest lr = new ListRequest();
        ListResponse<NotarioInfo> list = service.list(lr);
        model.addAttribute("list", list.getList());
        return "/juridico/index";
    }
    
    @RequestMapping(value = "/juridico/buscar", method = RequestMethod.GET)
    public String verBuscar(Model model) {
        ListResponse<EntidadInfo> list = entidadservice.list(new ListRequest());
        model.addAttribute("entidades", list.getList());
        return "/juridico/buscar";
    }
    
    @RequestMapping(value = "/juridico/buscar", method = RequestMethod.POST)
    public String buscar(
            @RequestParam("nombre") String nombre,
            @RequestParam("entidad") long entidad,
            Model model,
            RedirectAttributes redirectAttributes) {
        List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
        if (entidad < 1) { // por nombre notario
            ListResponse<NotarioInfo> list = service.list(new ListRequest("likeNombre", nombre));
            mensajes.add("info::Notarios llamados " + nombre);
            redirectAttributes.addFlashAttribute("list", list.getList());
        } else { // por numero notarida/entidad
            MultipleParams params = new MultipleParams();
            params.add("notariaNumero", nombre);
            params.add("entidadId", entidad);
            FindResponse<NotarioInfo> find = service.find(new FindByRequest("notariaNumeroYentidadId", params));
            List<NotarioInfo> list = new ArrayList<>();
            if (find.getInfo() != null) {
                list.add(find.getInfo());
            } else {
                mensajes.add("info::No se encontro la notaria numero " + nombre);
            }
            redirectAttributes.addFlashAttribute("list", list);
        }
        return "redirect:/juridico/buscar";
    }

    @RequestMapping(value = "/juridico/registrar", method = RequestMethod.GET)
    public String verRegistrar(Model model, Principal principal) {
        ListResponse<EntidadInfo> list = entidadservice.list(new ListRequest());
        model.addAttribute("entidades", list.getList());
        return "/juridico/registrar";
    }

    @RequestMapping(value = "/juridico/registrar", method = RequestMethod.POST)
    public String registrar(
            @Valid @ModelAttribute("notarioForm") NotarioForm form,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model,
            Principal principal) {
        List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
        if (result.hasErrors() || form.getEntidadId() < 1 || form.getMunicipioId() < 1) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.register", result);
            redirectAttributes.addFlashAttribute("notarioForm", form);
            mensajes.add("warning::Datos incorrectos.");
            return "redirect:/juridico/registrar";
        }
        NotarioInfo info = new NotarioInfo();
        info.setNombre(form.getNombre());
        info.setNotariaNumero(form.getNotariaNumero());
        info.setConvenio(form.getConvenioInfonavit());
        info.setEmail(form.getEmail());
        info.setTelefono(form.getTelefono());
        info.setCalleNotaria(form.getCalleNotaria());
        info.setNumeroCalle(form.getNumeroCalle());
        info.setColoniaNotaria(form.getColoniaNotaria());
        info.setHabilitado(form.isActivo());
        
        info.setMunicipioId(form.getMunicipioId());
        info.setEntidadId(form.getEntidadId());
        //info.setCodigoNotario(form.getCodigoNotario);
        FindResponse<EntidadInfo> find = entidadservice.find(new FindByRequest(form.getEntidadId()));
        if (find.getInfo() != null) {
            info.setEntidad2(find.getInfo().getClave());
        }

        SaveRequest<NotarioInfo> saveRequest = new SaveRequest<>();
        saveRequest.setInfo(info);
        try {
            SaveResponse<NotarioInfo> save = service.save(saveRequest);
            mensajes.add("success::Notario registrado.");
            return "redirect:/";
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.register", result);
            redirectAttributes.addFlashAttribute("notarioForm", form);
            mensajes.add("warning::Ya esta registrada la notaria numero " + info.getNotariaNumero() + " en el estado seleccionado.");
            return "redirect:/juridico/registrar";
        }
    }

    @RequestMapping(value = "/juridico/modificar/{notario}", method = RequestMethod.GET)
    public String verActualizar(@PathVariable long notario, Model model, RedirectAttributes redirectAttributes, Principal principal) {
        FindResponse<NotarioInfo> find = service.find(new FindByRequest(notario));
        NotarioInfo info = find.getInfo();
        if (info == null) {
            List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
            logger.error("Notario " + notario + " no existe.");
            mensajes.add("danger::Notario " + notario + " no existe.");
            return "redirect:/";
        }
        ListResponse<EntidadInfo> list = entidadservice.list(new ListRequest());
        model.addAttribute("entidades", list.getList());

        NotarioForm form = new NotarioForm();
        form.setId(info.getId());
        form.setEmail(info.getEmail());
        form.setTelefono(info.getTelefono());
        form.setCalleNotaria(info.getCalleNotaria());
        form.setColoniaNotaria(info.getColoniaNotaria());
        form.setNumeroCalle(info.getNumeroCalle());
        form.setConvenioInfonavit(info.getConvenio());
        form.setActivo(info.isHabilitado());
        model.addAttribute("notarioForm", form);
        model.addAttribute("modificar", true);
        return "/juridico/registrar";
    }
    
    @RequestMapping(value = "/juridico/modificar/{notario}", method = RequestMethod.POST)
    public String actualizar(
            @PathVariable long notario,
            @Valid @ModelAttribute("notarioForm") NotarioForm form,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model,
            Principal principal) {
        List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.register", result);
            redirectAttributes.addFlashAttribute("notarioInfo", form);
            mensajes.add("warning::Datos incorrectos.");
            return "redirect:/juridico/modificar/" + form.getId();
        }
        FindResponse<NotarioInfo> find = service.find(new FindByRequest(notario));
        NotarioInfo info = find.getInfo();
        if (info == null) {
            logger.error("Notario " + notario + " no existe.");
            mensajes.add("danger::Notario " + notario + " no existe.");
            return "redirect:/";
        }
        info.setEmail(form.getEmail());
        info.setTelefono(form.getTelefono());
        info.setCalleNotaria(form.getCalleNotaria());
        info.setColoniaNotaria(form.getColoniaNotaria());
        info.setNumeroCalle(form.getNumeroCalle());
        info.setConvenio(form.getConvenioInfonavit());
        info.setHabilitado(form.isActivo());
        service.update(new UpdateRequest<>(info));
        mensajes.add("success::Notario modificado correctamente");
        return "redirect:/";
    }

    @ModelAttribute("notarioForm")
    public NotarioForm notarioForm() {
        return new NotarioForm();
    }

    @RequestMapping(value = "/juridico/municipios", headers="Accept=application/json", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String municipiosjson2(@RequestParam(required = true) long clave) {
        ListResponse<MunicipioInfo> list1 = municipioservice.list(new ListRequest("entidadId", clave));
        if (list1.getList() == null) { return "[]"; }
        StringBuilder json = new StringBuilder();
        json.append("[");
        boolean first = true;
        for (MunicipioInfo m : list1.getList()) {
            if (first) {
                first = !first;
            } else {
                json.append(",");
            }
            json.append("{\"id\":");
            json.append(m.getId());
            json.append(",\"nombre\":\"");
            json.append(m.getNombre());
            json.append("\"}");
        }
        json.append("]");
        return json.toString();
    }
    

}
