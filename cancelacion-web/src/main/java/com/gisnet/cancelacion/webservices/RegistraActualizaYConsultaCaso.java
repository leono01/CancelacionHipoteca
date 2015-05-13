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



/**
 *
 * @author leonel
 */
public class RegistraActualizaYConsultaCaso {


    public StatusOperacion registraCaso(int numeroDeCredito,
                                        int numeroDeCaso,
                                        String nombreAcreditado
    ) {

        StatusOperacion so = new StatusOperacion();


        Date fechaDeCreacion = new Date();

        if(numeroDeCaso != (int) numeroDeCaso || numeroDeCredito != (int) numeroDeCredito){
        
            so.setCodigo(2);
            so.setDescripcion("Error de datos de entrada");
        }else{
        
        if (numeroDeCredito <=0 || numeroDeCaso <= 0 || numeroDeCredito > 999999999 || numeroDeCaso > 999999999) {
            so.setCodigo(2);
            so.setDescripcion("Error de datos de entrada");
        } else {
            CCaso caso = new CCaso();

            caso.setCasoId(1);
            caso.setNumeroDeCredito(numeroDeCredito);
            caso.setNumeroDeCaso(numeroDeCaso);
            caso.setFechaDeCreacion(fechaDeCreacion);
            caso.setFechaDeCierre(null);

            caso.setCNotario(null);

            caso.setNombreAcreditado(nombreAcreditado);

            System.out.println(caso.getNumeroDeCaso());
            so.setCodigo(0);
            so.setDescripcion("Caso creado con éxito");
        }

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

        
        CCaso caso = new CCaso();

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

        System.out.println(caso);
        
        
        //Se actualizan los dos datos de entrada en la BD
        

        //Enviar el status del registro según corresponda
        return ida;
    }

    /**
     *
     * @param numeroDeCredito
     * @param numeroDeCaso
     * @return el estado de la consulta
     */
    /*@WebMethod(operationName = "consultaCaso")
    public List<CCaso> consultaCaso(    @WebParam(name = "numeroDeCredito") String numeroDeCredito,
    @WebParam(name = "numeroDeCaso") String numeroDeCaso
    ) {
    List<CCaso> notarios = null;
    CCasoHelper cch = new CCasoHelper();
    notarios = cch.queryConsultaCaso(numeroDeCredito,numeroDeCaso);
    return notarios;
    }*/
    
    
    
    public InfoDeConsulta consultaCaso(	int numeroDeCredito,
                                       	int numeroDeCaso
    ) {
        InfoDeConsulta idc = new InfoDeConsulta();
        Date fechaDeActualizacion = new Date();

        int dias = -15;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaDeActualizacion);
        calendar.add(Calendar.DAY_OF_YEAR, dias);

        Date fechaDeEmision;
        fechaDeEmision = calendar.getTime();

        String f1;
        String f2;

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

        f1 = df.format(fechaDeActualizacion);
        f2 = df.format(fechaDeEmision);

        System.out.println(f1);
        System.out.println(f2);
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        try {
            Date ffda = formatter.parse(f1);
            Date ffe = formatter.parse(f2);
 
            System.out.println(formatter.format(ffda));
            System.out.println(formatter.format(ffe));
            
            idc.setNumeroDeCaso(numeroDeCaso);
            idc.setNumeroDeCredito(numeroDeCredito);
            idc.setNumeroDeFolio("43HG5K3H4G5KJH");
            idc.setCartaDeCancelacion("CARTA DE CANCELACION");
            idc.setStatus(20);
            idc.setFechaDeActualizacion(fechaDeActualizacion);
            idc.setFechaDeEmisionDeCarta(fechaDeEmision);
        } catch (Exception ex) {
            Logger.getLogger(CCaso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idc;
    }
    
    public StatusCaso estadoDelCaso (int numeroDeCaso){
    	StatusCaso sc = new StatusCaso();
    	
    	if(numeroDeCaso > 0 && numeroDeCaso <= 99999999){
			sc.setStatus(1);
			sc.setDescripcion("El caso se ha creado");
			sc.setFechaActualizacion(new Date());
		}
    	return sc;
    }
}
