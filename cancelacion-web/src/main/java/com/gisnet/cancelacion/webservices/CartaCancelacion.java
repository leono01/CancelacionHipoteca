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

import com.gisnet.cancelacion.webservices.dto.CCarta;
import com.gisnet.cancelacion.webservices.dto.InfoDeActualizacion;
import com.gisnet.cancelacion.webservices.dto.StatusOperacion;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author leonel
 */
public class CartaCancelacion {


    public StatusOperacion registraCartaCancelacion(	String codigoCarta,
                                                    	File pdfCarta,
                                                        File xmlCarta
                                                        ) {
        
        //List <InfoDeRegistro> infoDeRegistro = new ArrayList<InfoDeRegistro>();
        // guarda en base
        
        StatusOperacion status = new StatusOperacion();
        
        status.setCodigo(10);
        status.setDescripcion("Registro existoso");
        //infoDeRegistro.add(idr);
        
        return status;
    }
    
    public StatusOperacion actualizaCartaCancelacion( 	String codigoCarta,
                                             			File pdfCarta,
                                             			File xmlCarta
                                           ) {
        
        /*List <InfoDeActualizacion> infoDeActualizacion = new ArrayList<InfoDeActualizacion>();
        InfoDeActualizacion ida = new InfoDeActualizacion();*/
        
        StatusOperacion status = new StatusOperacion();
        //Se actualizan los dos datos de entrada en la BD
        
        status.setCodigo(20);
        status.setDescripcion("Actualización correcta");
        
        
        
        //Enviar el status del registro según corresponda
        
        
        return status;
    }
    
    public List<CCarta> consultaCartaCancelacion(int codigoCarta){
    
    List <CCarta> listaCartas = new ArrayList<CCarta>();
    CCarta carta = new CCarta();
    
    carta.setCodigoCarta("11111111111111111");
    carta.setPdfCarta(null);
    carta.setXmlCarta(null);
    
    
    listaCartas.add(carta);
    return listaCartas;    
    }
    
}
