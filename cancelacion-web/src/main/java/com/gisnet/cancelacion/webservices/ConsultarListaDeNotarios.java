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

import com.gisnet.cancelacion.core.services.NotarioService;
import com.gisnet.cancelacion.events.FindByRequest;
import com.gisnet.cancelacion.events.FindResponse;
import com.gisnet.cancelacion.events.ListRequest;
import com.gisnet.cancelacion.events.ListResponse;
import com.gisnet.cancelacion.events.SaveRequest;
import com.gisnet.cancelacion.events.SaveResponse;
import com.gisnet.cancelacion.events.info.CasoInfo;
import com.gisnet.cancelacion.events.info.NotarioInfo;
import com.gisnet.cancelacion.web.domain.NotarioForm;
import com.gisnet.cancelacion.webservices.dto.CNotario;

import java.security.Principal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


/**
 *
 * @author leonel
 */
public class ConsultarListaDeNotarios extends SpringBeanAutowiringSupport {

	@Autowired
    private NotarioService service;
	
    
    
    /**
     * Es el Servicio Web que permite consultar la lista de notarios
     *
     * @param numeroDeCredito
     * @param entidad
     * @return Una lista en donde se encuentran los datos del notario consultado
     */
    public List<CNotario> consultarListaDeNotarios() {       
    	// Operación que regresa una lista del total de notarios
    	List<CNotario> notarios = new ArrayList<CNotario>();
    	
        ListRequest lr = new ListRequest();
        ListResponse<NotarioInfo> list = service.list(lr);
        
        for(NotarioInfo ni : list.getList()){
        	CNotario cn = new CNotario();
        	cn.setEntidad(ni.getEntidad2());
        	cn.setNombreNotario(ni.getNombre());
        	cn.setCodigoNotario(ni.getCodigo());
        	cn.setNotariaNumero(ni.getNotariaNumero());
        	cn.setConvenioInfonavit(ni.getConvenio());
        	cn.setEmail(ni.getEmail());
        	cn.setTelefono(ni.getTelefono());
        	
        	notarios.add(cn);
        }
        
       
        return notarios;
    }
        
    public String entidad(int numeroDeCredito) {

        String entidad = "";

        
        FindByRequest fbr = new FindByRequest("numeroCredito",numeroDeCredito);
        FindResponse<NotarioInfo> response = service.find(fbr);
        
        //List <NotarioInfo> ls = response.getList();
        NotarioInfo notario = response.getInfo();
        
        entidad = notario.getEntidad2();
        return entidad;
    }

    public List<CNotario> consultarListaDeNotariosAdai(String entidad) {
        
    	List<CNotario> notarios = new ArrayList<CNotario>();
    	
        // Operación que regresa una lista de los notarios de la determinada entidad.
    	ListRequest lr = new ListRequest("entidad",entidad);
        ListResponse<NotarioInfo> list = service.list(lr);
       
        for(NotarioInfo ni : list.getList()){
        	CNotario cn = new CNotario();
        	cn.setEntidad(ni.getEntidad2());
        	cn.setNombreNotario(ni.getNombre());
        	cn.setCodigoNotario(ni.getCodigo());
        	cn.setNotariaNumero(ni.getNotariaNumero());
        	cn.setConvenioInfonavit(ni.getConvenio());
        	cn.setEmail(ni.getEmail());
        	cn.setTelefono(ni.getTelefono());
        	
        	notarios.add(cn);
        }
        
       
        return notarios;
    }

    
}
