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

import com.gisnet.cancelacion.core.services.CasoService;
import com.gisnet.cancelacion.events.FindByRequest;
import com.gisnet.cancelacion.events.FindResponse;
import com.gisnet.cancelacion.events.SaveRequest;
import com.gisnet.cancelacion.events.SaveResponse;
import com.gisnet.cancelacion.events.info.CasoInfo;
import com.gisnet.cancelacion.events.info.StatusCasoInfo;
import com.gisnet.cancelacion.webservices.dto.CCaso;
import com.gisnet.cancelacion.webservices.dto.InfoDeActualizacion;
import com.gisnet.cancelacion.webservices.dto.InfoDeConsulta;
import com.gisnet.cancelacion.webservices.dto.StatusCaso;
import com.gisnet.cancelacion.webservices.dto.StatusOperacion;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;



/**
 *
 * @author leonel
 */
public class RegistraActualizaYConsultaCaso extends SpringBeanAutowiringSupport {
	@Autowired
    private CasoService service;

    public StatusOperacion registraCaso(int numeroDeCredito,
                                        int numeroDeCaso,
                                        String nombreAcreditado
    ) {

        StatusOperacion so = new StatusOperacion();


        Date fechaDeCreacion = new Date();

        /**if(numeroDeCaso != (int) numeroDeCaso || numeroDeCredito != (int) numeroDeCredito){
        
            so.setCodigo(2);
            so.setDescripcion("Error de datos de entrada");
        }else{
        
        if (numeroDeCredito <=0 || numeroDeCaso <= 0 || numeroDeCredito > 999999999 || numeroDeCaso > 999999999) {
            so.setCodigo(2);
            so.setDescripcion("Error de datos de entrada");
        } else {
            CCaso caso = new CCaso();

            //caso.setCasoId(1);
            caso.setNumeroDeCredito(numeroDeCredito);
            caso.setNumeroDeCaso(numeroDeCaso);
            caso.setFechaDeCreacion(fechaDeCreacion);
            caso.setFechaDeCierre(null);

            //caso.setNotarioId();

            caso.setNombreAcreditado(nombreAcreditado);

            SaveRequest<CasoInfo> saveRequest = new SaveRequest<>();
            saveRequest.setInfo(caso);
            SaveResponse<CasoInfo> save = service.save(saveRequest);
            
            System.out.println(caso.getNumeroDeCaso());
            so.setCodigo(0);
            so.setDescripcion("Caso creado con éxito");
        }

        }**/
        CasoInfo caso = new CasoInfo();

        caso.setNombreAcreditado(nombreAcreditado);
        caso.setNumeroCaso(numeroDeCaso);
        caso.setNumeroCredito(numeroDeCredito);
        caso.setFechaCreacion(fechaDeCreacion);
        
        try{
        SaveRequest<CasoInfo> saveRequest = new SaveRequest<>();
        saveRequest.setInfo(caso);
        SaveResponse<CasoInfo> save = service.save(saveRequest);
        so.setCodigo(0);
        so.setDescripcion("Caso registrado con éxito");
        
    	}catch(Exception e){
    		so.setCodigo(1);
    		so.setDescripcion("No se hizo registro del caso");
    		System.out.println(e);
    	}
        
        
        return so;

    }


    public InfoDeActualizacion actualizaCaso(	int     numeroDeCredito,
                                            	int     numeroDeCaso,                                            
                                            	Date    fecha,
                                            	int     status,
                                            	String  cartaDeCancelacionPdf,
                                            	Date    fechaEmisionCarta,
                                            	String  numeroDeFolio   
                                        ) {

        InfoDeActualizacion ida = new InfoDeActualizacion();

        
        /**CCaso caso = new CCaso();

        caso.setCasoId(1);
        caso.setNumeroDeCredito(numeroDeCredito);
        caso.setNumeroDeCaso(numeroDeCaso);
        caso.setFechaDeCreacion(fecha);
        caso.setFechaDeCierre(null);

        caso.setCNotario(null);
        
        ida.setCodigo(0);
        ida.setDescripcion("Caso actualizado con éxito");
        ida.setNumeroDeCaso(numeroDeCaso);
        ida.setNumeroDeCredito(numeroDeCredito);

        System.out.println(caso);**/
        try{
        FindByRequest fbr = new FindByRequest("numeroCaso",numeroDeCaso);
        FindResponse<CasoInfo> response = service.find(fbr);
        
        response.getInfo().setNombreAcreditado("algo");
        
        ida.setCodigo(0);
        ida.setDescripcion("Se actualizó correctamente el caso.");
        }catch(Exception e){
        	ida.setCodigo(4);
        	ida.setDescripcion("No se actualizó la información del caso");
        	System.out.println(e);
        }
        return ida;
    }
    
    
    
    public InfoDeConsulta consultaCaso(	int numeroDeCredito,
                                       	int numeroDeCaso
    ) {
        InfoDeConsulta idc = new InfoDeConsulta();

        return idc;
    }
    
    
    
    public StatusCaso estadoDelCaso (int numeroDeCaso){
    	StatusCaso sc = new StatusCaso();
    	try{
    	FindByRequest fbr = new FindByRequest("numeroCaso",numeroDeCaso);
        FindResponse<CasoInfo> response = service.find(fbr);
    	
    	StatusCasoInfo sci = response.getInfo().getStatusCaso();
			sci.getClave();
			sci.getNombre();
			sci.getDescripcion();
    	}catch(Exception e){
    		
    		System.out.println(e);
    	}
    	return sc;
    }
}
