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

package com.gisnet.cancelacion.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.gisnet.cancelacion.core.services.EntidadService;
import com.gisnet.cancelacion.events.FindByRequest;
import com.gisnet.cancelacion.events.FindResponse;
import com.gisnet.cancelacion.events.info.EntidadInfo;
import com.gisnet.cancelacion.webservices.dto.InfoValidarConvenio;


/**
*
* @author leonel
*/

public class VerificarConvenioDeEntidad  extends SpringBeanAutowiringSupport {

	@Autowired
    private EntidadService entidadService;
	
	private static final int 	DOS = 2;
	
	private static final int	EXITO_ZERO = 0;
	private static final String	DESCRIPCION_EXITO_CERO = "Se realizó la consulta correctamente";	
	private static final String	DESCRIPCION_EXITO_UNO = "No existe la entidad";
	
	private static final int 	ERROR_UNO = 1; 	// No se encontraron datos.
	private static final int 	ERROR_DOS = 2;	// Error de conexión en la base de datos.
	private static final int 	ERROR_TRES = 3;	// Error en los datos de entrada. Verificar que la clave de la entidad sea de DOS caracteres.
	
	private static final String	DESCRIPCION_ERROR_UNO = "Error en la consulta.";
	private static final String	DESCRIPCION_ERROR_DOS = "Error de conexión en la base de datos.";
	private static final String	DESCRIPCION_ERROR_TRES = "Error en los datos de entrada. Verificar que la clave de la entidad sea de DOS caracteres.";
	
	private static final String ERROR_BASE_DATOS = "Could not open connection; nested exception is org.hibernate.exception.JDBCConnectionException: Could not open connection";
	
	public InfoValidarConvenio verificarConvenioDeEntidad(String entidad) {
		
		String conOSinConvenio = "";
		InfoValidarConvenio ivc = new InfoValidarConvenio();
		
		try{
		
			if (entidad.length() != DOS){
				ivc.setConOSinConvenio("");
				ivc.setCodigo(ERROR_TRES);
				ivc.setDescripcion(DESCRIPCION_ERROR_TRES);
			}
			else{
				
				FindByRequest laEntidad = new FindByRequest("porClave",entidad);
		        FindResponse<EntidadInfo> entidadResponse = entidadService.find(laEntidad);
		        
		        
		        if(entidadResponse.getInfo() != null){
		        	
		        	if(entidadResponse.getInfo().isConvenio()){
		        			        	
			        	ivc.setConOSinConvenio("CON CONVENIO");
			        	ivc.setCodigo(EXITO_ZERO);
			        	ivc.setDescripcion(DESCRIPCION_EXITO_CERO);
			        	System.out.println(ivc.getDescripcion());
			        	
		        	}
		        	else{
		        		
		        		ivc.setConOSinConvenio("SIN CONVENIO");
			        	ivc.setCodigo(EXITO_ZERO);
			        	ivc.setDescripcion(DESCRIPCION_EXITO_CERO);
			        	System.out.println(ivc.getDescripcion());
		        	}
		        }
		        else{
		        	
		        	ivc.setConOSinConvenio("");
		        	ivc.setCodigo(EXITO_ZERO);
		        	ivc.setDescripcion(DESCRIPCION_EXITO_UNO);
		        	System.out.println(ivc.getDescripcion());
		        }
			}
		}catch(Exception e){
			
			if (e.getMessage().equals(ERROR_BASE_DATOS)){
				ivc.setConOSinConvenio("");
				ivc.setCodigo(ERROR_DOS);
				ivc.setDescripcion(DESCRIPCION_ERROR_DOS);
			}
			else{
				ivc.setConOSinConvenio("");
				ivc.setCodigo(ERROR_UNO);
				ivc.setDescripcion(DESCRIPCION_ERROR_UNO);
			}
		}
		
		return ivc;
	}
}
