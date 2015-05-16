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

import com.gisnet.cancelacion.events.SaveRequest;
import com.gisnet.cancelacion.events.SaveResponse;
import com.gisnet.cancelacion.events.info.CartaCancelacionInfo;
import com.gisnet.cancelacion.persistance.services.CartaCancelacionPersistanceService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author marco-g8
 */
@Controller
public class UploadController {
    
    @Autowired
    private CartaCancelacionPersistanceService ccps;
    
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(
            @RequestParam("name") String name,
            @RequestParam("file") MultipartFile file){
        
        String r = "--- upload ---\n";
        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                
                r += "file uploaded\n";
                r += new String(bytes) + "\n";
            }
            else {
                r += "got empty file";
            }
        }
        catch (IOException|NullPointerException ex) {
            r += "--IOException|NullPointerException--\n" + ex + "\n";
        }
        return r + "---end---";
    }

    @RequestMapping(value = "/uploadccp", method = RequestMethod.POST)
    public @ResponseBody String uploadCartaCancelacionPDF(
            @RequestParam("codigocarta") String codigocarta,
            @RequestParam("file") MultipartFile file) {
        
        CartaCancelacionInfo cc = new CartaCancelacionInfo();
        cc.setCodigoCarta(codigocarta);
        try {
            cc.setPdf(file.getBytes());
        } catch (IOException ex) {
            return "<pre>" + ex + "</pre>";
        }
        SaveResponse<CartaCancelacionInfo> save = ccps.save(new SaveRequest<>(cc));
        return "<pre>success, id=" + save.getInfo().getId() + "</pre>";
    }
    
}
