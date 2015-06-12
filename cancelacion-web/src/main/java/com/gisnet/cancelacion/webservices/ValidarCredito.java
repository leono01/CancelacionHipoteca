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
import com.gisnet.cancelacion.webservices.dto.InfoValidarCredito;;


public class ValidarCredito extends SpringBeanAutowiringSupport{
	
	@Autowired
    private CasoService service;
	
	public InfoValidarCredito validaCredito(String numeroDeCredito){
		
		InfoValidarCredito ivc = new InfoValidarCredito();
		
		
		try{
			
			FindByRequest fbr = new FindByRequest("numeroCredito",numeroDeCredito);
	        FindResponse<CasoInfo> casoresponse = service.find(fbr);
	        
		        if(casoresponse.getInfo() != null){
		        	ivc.setCodigo(0);
		        	ivc.setDescripcion("El número de crédito tiene asociado un caso");
		        	ivc.setNumeroDeCredito(casoresponse.getInfo().getNumeroCredito());
		        	ivc.setNumeroDeCaso(casoresponse.getInfo().getNumeroCaso());
		        }
		        else{
	        		ivc.setCodigo(1);
	        		ivc.setDescripcion("El número de crédito no tiene asociado un caso");
	        		ivc.setNumeroDeCredito(numeroDeCredito);
	        	}
	        
        }catch(Exception e){
        	System.out.println("ERROR VALIDACIÖN     "+ e.getMessage());
        	
        	if (e.getMessage().equals("Could not open connection; nested exception is org.hibernate.exception.JDBCConnectionException: Could not open connection") || 
        		e.getMessage().equals("could not inspect JDBC autocommit mode; nested exception is org.hibernate.exception.GenericJDBCException: could not inspect JDBC autocommit mode")){
    			ivc.setCodigo(2);
				ivc.setDescripcion("No hay conexión con la base de datos.");
        	}
        	
        	
        }
        	
        
		return ivc;
	}
	

}
