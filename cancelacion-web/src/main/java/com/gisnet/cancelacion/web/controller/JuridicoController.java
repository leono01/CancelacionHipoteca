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

import com.gisnet.cancelacion.core.services.NotarioService;
import com.gisnet.cancelacion.web.domain.NotarioInfo;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author marco-g8
 */
@Controller
public class JuridicoController {
    
    @Autowired
    private NotarioService ns;
    
    public String index(Model model, Principal principal) {
        Map<String, Object> list = ns.list(new HashMap<String, Object>());
        model.addAttribute("list", (List<Map<String, Object>>) list.get("list"));
        return "/juridico/index";
    }
    
    @RequestMapping(value = "/juridico/registrar", method = RequestMethod.GET)
    public String ver(Model model, Principal principal) {
        
        return "/juridico/registrar";
    }
    
    @RequestMapping(value = "/juridico/registrar", method = RequestMethod.POST)
    public String registrar(
            @Valid @ModelAttribute("notarioInfo") NotarioInfo info,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model,
            Principal principal) {
        
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.register", result);
            redirectAttributes.addFlashAttribute("notarioInfo", info);
            return "redirect:/juridico/registrar";
        }
        
        Map<String, Object> notario = new HashMap<>();        
        notario.put("numeroNotaria", info.getNonotaria());
        notario.put("nombre", info.getNombre());
        notario.put("municipio", info.getMunicipio());
        notario.put("estado", info.getEstado());
        notario.put("domicilio", info.getDomicilio());
        notario.put("correoElectronico", info.getCorreo());
        notario.put("telefono", info.getTelefono());
        notario.put("convenio", info.isConvenio());
        
        return "redirect:/";
    }
    
    @ModelAttribute("notarioInfo")
    public NotarioInfo notarioInfo() {
        return new NotarioInfo();
    }
    
}

