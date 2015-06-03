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

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.gisnet.cancelacion.core.services.CasoService;
import com.gisnet.cancelacion.events.FindByRequest;
import com.gisnet.cancelacion.events.FindResponse;
import com.gisnet.cancelacion.events.info.CasoInfo;
import com.gisnet.cancelacion.webservices.dto.StatusOperacion;


public class ValidarCredito extends SpringBeanAutowiringSupport{
	
	@Autowired
    private CasoService service;
	
	public StatusOperacion validaCredito(String numeroDeCredito){
		
		StatusOperacion so = new StatusOperacion();
		
		
		try{
			
			FindByRequest fbr = new FindByRequest("numeroCredito",numeroDeCredito);
	        FindResponse<CasoInfo> casoresponse = service.find(fbr);
	        
		        if(casoresponse.getInfo().getId() != 0){
		        	so.setCodigo(0);
		        	so.setDescripcion("El número de crédito tiene asociado un caso");
		        }
	        
        }catch(Exception e){
        	System.out.println("ERROR VALIDACIÖN     "+ e.getMessage());
        	if (e.getMessage().equals("Could not open connection; nested exception is org.hibernate.exception.JDBCConnectionException: Could not open connection")){
    			so.setCodigo(2);
				so.setDescripcion("No hay conexión con la base de datos.");
        	}
        	else{
        		so.setCodigo(1);
        		so.setDescripcion("El número de crédito no tiene asociado un caso");
        	}
        	
        }
        	
        
		return so;
	}
	

}
