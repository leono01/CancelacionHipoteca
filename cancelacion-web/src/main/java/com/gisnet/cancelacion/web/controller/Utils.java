/*
 * Copyright (C) 2015 marco-g8
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

import java.util.ArrayList;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author marco-g8
 */
public class Utils {
    
    public static List<String> getMensajes(Model model) {
        List<String> messages;
        if (model.containsAttribute("mensajes")) {
            messages = (List<String>) model.asMap().get("mensajes");
        } else {
            messages = new ArrayList<>();
            model.addAttribute("mensajes", messages);
        }
        return messages;
    } 
    
    public static List<String> getFlashMensajes(Model model, RedirectAttributes redirectAttributes) {
        List<String> messages = getMensajes(model);
        redirectAttributes.addFlashAttribute("mensajes", messages);
        return messages;
    }
    
}
