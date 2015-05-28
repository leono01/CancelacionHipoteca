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
import com.gisnet.cancelacion.core.services.NotarioService;
import com.gisnet.cancelacion.events.FindByRequest;
import com.gisnet.cancelacion.events.FindResponse;
import com.gisnet.cancelacion.events.ListRequest;
import com.gisnet.cancelacion.events.ListResponse;
import com.gisnet.cancelacion.events.SaveRequest;
import com.gisnet.cancelacion.events.SaveResponse;
import com.gisnet.cancelacion.events.info.EntidadInfo;
import com.gisnet.cancelacion.events.info.MunicipioInfo;
import com.gisnet.cancelacion.events.info.NotarioInfo;
import com.gisnet.cancelacion.web.domain.NotarioForm;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author marco-g8
 */
@Controller
public class JuridicoController {

    @Autowired
    private NotarioService service;
    @Autowired
    private EntidadService entidadservice;

    public String index(Model model, Principal principal) {
        ListRequest lr = new ListRequest();
        ListResponse<NotarioInfo> list = service.list(lr);
        model.addAttribute("list", list.getList());
        return "/juridico/index";
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

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.register", result);
            redirectAttributes.addFlashAttribute("notarioInfo", form);
            return "redirect:/juridico/registrar";
        }

        NotarioInfo info = new NotarioInfo();
        info.setNombre(form.getNombre());
        info.setNotariaNumero(form.getNotariaNumero());
        info.setConvenio(form.isConvenioInfonavit() ? "SI" : "NO");
        info.setEmail(form.getEmail());
        info.setTelefono(form.getTelefono());
        info.setCalleNotaria(form.getCalleNotaria());
        info.setNumeroCalle(form.getNumeroCalle());
        info.setColoniaNotaria(form.getColoniaNotaria());
        info.setHabilitado(form.isActivo());
        
        info.setMunicipioId(0l);
        info.setEntidadId(form.getEntidadId());
        FindResponse<EntidadInfo> find = entidadservice.find(new FindByRequest(form.getEntidadId()));
        if (find.getInfo() != null) {
            info.setEntidad2(find.getInfo().getClave());
        }

        SaveRequest<NotarioInfo> saveRequest = new SaveRequest<>();
        saveRequest.setInfo(info);
        SaveResponse<NotarioInfo> save = service.save(saveRequest);

        return "redirect:/";
    }

    @ModelAttribute("notarioForm")
    public NotarioForm notarioForm() {
        return new NotarioForm();
    }
    
    @RequestMapping(value = "/juridico/municipios", method = RequestMethod.GET)
    public @ResponseBody List<MunicipioInfo> jsonmunicipios() {
        List<MunicipioInfo> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            MunicipioInfo info = new MunicipioInfo();
            info.setId(i);
            info.setNombre("mpio " + i);
            list.add(info);
        }
        return list;
    }
    

}
