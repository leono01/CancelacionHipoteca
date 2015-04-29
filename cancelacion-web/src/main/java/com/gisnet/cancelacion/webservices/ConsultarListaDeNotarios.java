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

import com.gisnet.cancelacion.webservices.helpers.CEntidadHelper;
import com.gisnet.cancelacion.webservices.helpers.CNotarioHelper;
import com.gisnet.cancelacion.webservices.dto.CNotario;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author leonel
 */
@WebService(serviceName = "ConsultarListaDeNotarios")
public class ConsultarListaDeNotarios {

    /**
     * Es el Servicio Web que permite consultar la lista de notarios
     *
     * @param numeroDeCredito
     * @param entidad
     * @return Una lista en donde se encuentran los datos del notario consultado
     */
    @WebMethod(operationName = "consultarListaDeNotarios")
    public List<CNotario> consultarListaDeNotarios( @WebParam(name = "numeroDeCredito") int     numeroDeCredito,
                                                    @WebParam(name = "entidad")         String  entidad
    ) {
        List<CNotario> notarios = new ArrayList<CNotario>();
        CNotarioHelper cnh = new CNotarioHelper();
        notarios = cnh.queryConsultarListaDeNotarios(numeroDeCredito,entidad);
        return notarios;
    }
        
    @WebMethod(operationName = "entidad")
    public String entidad(@WebParam(name = "numeroDeCredito") int numeroDeCredito) {

        String entidad = "";

        CEntidadHelper eh = new CEntidadHelper();
        entidad = eh.buscaNumeroDeCredito(numeroDeCredito);
        return entidad;
    }

    @WebMethod(operationName = "consultarListaDeNotariosAdai")
    public List<CNotario> consultarListaDeNotariosAdai(@WebParam(name = "entidad") String entidad) {
        List<CNotario> notarios;
        notarios = new ArrayList<CNotario>();
        CNotarioHelper cnh = new CNotarioHelper();
        notarios = cnh.queryConsultarListaDeNotariosAdai(entidad);
        return notarios;
    }

    
}
