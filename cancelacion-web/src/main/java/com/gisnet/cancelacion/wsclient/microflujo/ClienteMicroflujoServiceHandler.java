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

import com.gisnet.cancelacion.events.SaveRequest;
import com.gisnet.cancelacion.events.SaveResponse;
import com.gisnet.cancelacion.events.UpdateRequest;
import com.gisnet.cancelacion.events.info.CasoInfo;


public class ClienteMicroflujoServiceHandler implements ClienteMicroflujoService {
    
    private final StartFlowClient clientews;

    public ClienteMicroflujoServiceHandler() {
        clientews = new StartFlowClient();
    }

    @Override
    public SaveResponse<CasoInfo> validarCredito(SaveRequest<CasoInfo> event) {
        CasoInfo caso = event.getInfo();
        
        Input input = new Input();
        input.setNumeroCredito(caso.getNumeroCredito());
        input.setNumeroCaso(caso.getNumeroCaso());
        input.setEntidad(caso.getEntidad());
        input.setEstatus(caso.getStatusCaso().getClave());
        input.setNombreAcreditado(caso.getNombreAcreditado());
        input.setTipoOperacion(1);
        
        try {
            StartFlowSEI puerto = clientews.getStartFlowBindingPort();
            DatosCredito credito = puerto.validarCredito(input);
            caso.setProcedeCredito(credito.isProcede() ? "PROCEDE" : "NO PROCEDE");
        } catch (Exception ex) {
            System.err.println("Excepcion ClienteMicroflujoService validarCredito, " + ex.getMessage());
            caso.setProcedeCredito("DESCONOCIDO");
        }
        return new SaveResponse<>(caso);
    }

    @Override
    public UpdateRequest<CasoInfo> actualizarCaso(UpdateRequest<CasoInfo> event) {
        CasoInfo caso = event.getInfo();
        
        Input input = new Input();
        input.setNumeroCredito(caso.getNumeroCredito());
        input.setNumeroCaso(caso.getNumeroCaso());
        input.setEntidad(caso.getEntidad());
        input.setEstatus(caso.getStatusCaso().getClave());
        input.setNombreAcreditado(caso.getNombreAcreditado());
        input.setTipoOperacion(4);
        
        try {
            StartFlowSEI puerto = clientews.getStartFlowBindingPort();
            puerto.cambioEstatus(input);
        } catch (Exception ex) {
            System.err.println("Excepcion ClienteMicroflujoService actualizarCaso, " + ex.getMessage());
        }
        return new UpdateRequest<>(caso);
    }
    
}