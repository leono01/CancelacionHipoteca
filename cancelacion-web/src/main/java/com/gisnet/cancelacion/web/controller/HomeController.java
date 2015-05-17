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

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author marco-g8
 */
@Controller
public class HomeController {
    
    @Autowired JCobranzaController cobranza;
    @Autowired JuridicoController juridico;
    @Autowired NotarioController notario;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model,
            HttpServletRequest request,
            Principal principal) {
        
        List<String> mensajes = new ArrayList<>();
        //mensajes.add("danger::prueba mensaje");
        //mensajes.add("success::prueba mensaje");
        //mensajes.add("info::prueba mensaje");
        //mensajes.add("warning::prueba mensaje");
        model.addAttribute("mensajes", mensajes);
        
        if (request.isUserInRole("ROLE_JURIDICO")) {
            return juridico.index(model, principal);
        }
        else if (request.isUserInRole("ROLE_NOTARIO")) {
            return notario.index(model, principal);
        }
        else if (request.isUserInRole("ROLE_JEFE_COBRANZA")) {
            return cobranza.index(model, principal);
        }
        else {
            return "/norol";
        }
    }
    
}

