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
package com.gisnet.cancelacion.wsclient.autenticacion;

import com.gisnet.cancelacion.events.FindByRequest;

/**
 *
 * @author marco-g8
 */
public class ClienteAutenticacionServiceHandler implements ClienteAutenticacionService {
    
    private final LDAPConnectionJAASService clientews;

    public ClienteAutenticacionServiceHandler() {
        this.clientews = new LDAPConnectionJAASService();
    }
    
    @Override
    public boolean loguear(FindByRequest event) {
        if (!(event.getValue() instanceof String)) {
            return false;
        }
        
        try {
	        LDAPConnectionJAAS puerto = clientews.getLDAPConnectionJAAS();
	        String loguear = puerto.loguear(event.getKey(), (String) event.getValue());
	        return loguear.equals("SI");
        } catch (Exception ex) {
        	System.err.println("WSC Autentica Fallo, " + ex.getMessage());
        	return false;
        }
    }
    
    
}
