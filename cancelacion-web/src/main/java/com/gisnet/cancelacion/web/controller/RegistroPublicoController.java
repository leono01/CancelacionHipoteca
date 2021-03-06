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
import com.gisnet.cancelacion.events.FindByRequest;
import com.gisnet.cancelacion.events.info.CartaCancelacionInfo;
import com.gisnet.cancelacion.events.info.CasoInfo;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author marco-g8
 */
@Controller
public class RegistroPublicoController {
    
    @Autowired
    private CartaCancelacionService cartaCancelacionService;
    @Autowired
    private CasoService casoService;
    
    @RequestMapping(value = "/registrop/", method = RequestMethod.GET)
    public String registroPublico(Model model) {
        return "/registrop/index";
    }

    @RequestMapping(value = "/registrop/", method = RequestMethod.POST)
    public String registroPublico(
            @RequestParam("numeroCaso") String numeroCaso,
            Model model,
            RedirectAttributes redirectAttributes) {
        List<String> mensajes = Utils.getFlashMensajes(model, redirectAttributes);
        CasoInfo caso = casoService.find(new FindByRequest("numeroCaso", numeroCaso)).getInfo();
        redirectAttributes.addFlashAttribute("caso", caso);
        mensajes.add(caso != null ?
                "info::Mostrando carta de cancelacion, caso numero " + numeroCaso :
                "info::No se encontro ningun caso con el numero " + numeroCaso);
        return "redirect:/registrop/";
    }

    @RequestMapping(value = "/registrop/carta/{id}/carta_de_cancelacion.pdf", method = RequestMethod.GET, produces = "application/pdf")
    public @ResponseBody byte[] renderpdf(@PathVariable long id, HttpServletResponse response) {
        CartaCancelacionInfo carta = cartaCancelacionService.find(new FindByRequest(id)).getInfo();
        if (carta == null) {
            return new byte[0];
        }
        return carta.getPdf();
    }
    
}
