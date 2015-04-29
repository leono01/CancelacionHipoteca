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

import com.gisnet.cancelacion.webservices.helpers.CCasoHelper;
import com.gisnet.cancelacion.webservices.dto.InfoDeActualizacion;
import com.gisnet.cancelacion.webservices.dto.InfoDeConsulta;
import com.gisnet.cancelacion.webservices.dto.StatusOperacion;
import java.util.Date;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author leonel
 */
@WebService(serviceName = "RegistraActualizaYConsultaCaso")
public class RegistraActualizaYConsultaCaso {

    /**
     * This is a sample web service operation
     *
     * @param numeroDeCredito
     * @param numeroDeCaso
     * @param nombreAcreditado
     * @return infoDeRegistro
     */
    @WebMethod(operationName = "registraCaso")

    public StatusOperacion registraCaso(@WebParam(name = "numeroDeCredito")     int numeroDeCredito,
                                        @WebParam(name = "numeroDeCaso")        int numeroDeCaso,
                                        @WebParam(name = "nombreAcreditado")    String nombreAcreditado
    ) {

        StatusOperacion so = new StatusOperacion();
        CCasoHelper cch = new CCasoHelper();

        so = cch.queryRegistraCaso(numeroDeCredito, numeroDeCaso, nombreAcreditado);

        return so;

    }

    /**
     *
     * @param fechaDeCierre
     * @param notarioId
     * @param status
     * @param statusDelCasoId
     *
     * @return Si la información se actualizó correctamente o no
     */
    @WebMethod(operationName = "actualizaCaso")
    public InfoDeActualizacion actualizaCaso(   @WebParam(name = "numeroDeCredito")         int     numeroDeCredito,
                                            @WebParam(name = "numeroDeCaso")            int     numeroDeCaso,                                            
                                            @WebParam(name = "fecha")                   Date    fecha,
                                            @WebParam(name = "status")                  int     status,
                                            @WebParam(name = "cartaDeCancelacionPdf")   String  cartaDeCancelacionPdf,
                                            @WebParam(name = "fechaEmisionCarta")       Date    fechaEmisionCarta,
                                            @WebParam(name = "numeroDeFolio")           String  numeroDeFolio   
                                        ) {

        InfoDeActualizacion ida = new InfoDeActualizacion();
        CCasoHelper cch = new CCasoHelper();

        ida = cch.queryActualizaCaso(numeroDeCredito, numeroDeCaso, fecha,status, cartaDeCancelacionPdf,fechaEmisionCarta,numeroDeFolio);

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
    
    @WebMethod(operationName = "consultaCaso")
    public InfoDeConsulta consultaCaso(    @WebParam(name = "numeroDeCredito")  int numeroDeCredito,
                                            @WebParam(name = "numeroDeCaso")    int numeroDeCaso
    ) {
        InfoDeConsulta idc = new InfoDeConsulta();
        CCasoHelper cch = new CCasoHelper();
        idc = cch.queryConsultaCaso(numeroDeCredito,numeroDeCaso);
        return idc;
    }
}
