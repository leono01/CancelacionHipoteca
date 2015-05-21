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
package com.gisnet.cancelacion.wsclient.pms;

import com.gisnet.cancelacion.events.SaveRequest;
import com.gisnet.cancelacion.events.SaveResponse;
import com.gisnet.cancelacion.events.UpdateRequest;
import com.gisnet.cancelacion.events.info.CasoInfo;


public class ClienteMicroflujoServiceHandler implements ClienteMicroflujoService {
    
    private Pms_Service wsclientePms;

    public ClienteMicroflujoServiceHandler() {
        wsclientePms = new Pms_Service();
    }

    @Override
    public SaveResponse<CasoInfo> validarCredito(SaveRequest<CasoInfo> event) {
        CasoInfo caso = event.getInfo();
        
        Pms puerto = wsclientePms.getPmsPort();
        InfoPms respuesta = puerto.validarCredito(
                caso.getNumeroCredito(),
                caso.getNumeroCaso(),
                caso.getEntidad(),
                caso.getStatusCaso().getClave(),
                caso.getStatusCaso().getNombre(),
                null, null, caso.getNombreAcreditado(), 1);
        
        if (respuesta.getProcede().equals("Procede")) {
            caso.setProcedeCredito("PROCEDE");
        }
        else {
            caso.setProcedeCredito("NO PROCEDE");
        }
        return new SaveResponse<>(caso);
    }

    @Override
    public UpdateRequest<CasoInfo> actualizarCaso(UpdateRequest<CasoInfo> event) {
        CasoInfo caso = event.getInfo();
        
        Pms puerto = wsclientePms.getPmsPort();
        InfoStatusCaso statusCaso = puerto.statusCaso(
                caso.getNumeroCredito(),
                caso.getNumeroCaso(),
                caso.getEntidad(),
                caso.getStatusCaso().getClave(),
                caso.getStatusCaso().getNombre(),
                null, null, null, 4);
        System.out.println(statusCaso.getStatus());
        return new UpdateRequest<>(caso);
    }
    
}
