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
package com.gisnet.cancelacion.wsclient.microflujo;

import java.math.BigInteger;

import com.gisnet.cancelacion.events.SaveRequest;
import com.gisnet.cancelacion.events.SaveResponse;
import com.gisnet.cancelacion.events.UpdateRequest;
import com.gisnet.cancelacion.events.UpdateResponse;
import com.gisnet.cancelacion.events.info.CasoInfo;


public class ClienteMicroflujoServiceHandler implements ClienteMicroflujoService {
    
    private final SICANCELACIONOUService clientews;

    public ClienteMicroflujoServiceHandler() {
        clientews = new SICANCELACIONOUService();
    }

    @Override
    public SaveResponse<CasoInfo> validarCredito(SaveRequest<CasoInfo> event) {
        CasoInfo caso = event.getInfo();
        
        DTCANCELACIONREQ input = new DTCANCELACIONREQ();
        input.setNumeroCredito(caso.getNumeroCredito());
        input.setNumeroCaso(caso.getNumeroCaso());
        input.setEntidad(caso.getEntidad());
        input.setEstatus(BigInteger.valueOf(caso.getStatusCaso().getClave()));
        input.setNombreAcreditado(caso.getNombreAcreditado());
        input.setTipoOperacion(BigInteger.valueOf(1));
        
        try {
        	SICANCELACIONOU puerto = clientews.getHTTPPort();
        	DTCANCELACIONRESP credito = puerto.siCANCELACIONOU(input);
            caso.setProcedeCredito(credito.getDatosCredito().isProcede() ? "PROCEDE" : "NO PROCEDE");
        } catch (Exception ex) {
            System.err.println("Excepcion ClienteMicroflujoService validarCredito, " + ex.getMessage());
            caso.setProcedeCredito("DESCONOCIDO");
        }
        return new SaveResponse<>(caso);
    }

    @Override
    public UpdateResponse<CasoInfo> actualizarCaso(UpdateRequest<CasoInfo> event) {
        CasoInfo caso = event.getInfo();
        
        DTCANCELACIONREQ input = new DTCANCELACIONREQ();
        input.setNumeroCredito(caso.getNumeroCredito());
        input.setNumeroCaso(caso.getNumeroCaso());
        input.setEntidad(caso.getEntidad());
        input.setEstatus(BigInteger.valueOf(caso.getStatusCaso().getClave()));
        input.setNombreAcreditado(caso.getNombreAcreditado());
        input.setTipoOperacion(BigInteger.valueOf(4));
        
        try {
            SICANCELACIONOU puerto = clientews.getHTTPPort();
            DTCANCELACIONRESP actualiza = puerto.siCANCELACIONOU(input);
            
            System.out.println("Salida del WS PMS -> status : " + actualiza.getDatosCredito().getEstatus());
	        System.out.println("Salida del WS PMS -> descripción : " + actualiza.getDatosCredito().getDescripcion());
	        
        } catch (Exception ex) {
            System.err.println("Excepcion ClienteMicroflujoService actualizarCaso, " + ex.getMessage());
        }
        return new UpdateResponse<>(caso);
    }
    
}
