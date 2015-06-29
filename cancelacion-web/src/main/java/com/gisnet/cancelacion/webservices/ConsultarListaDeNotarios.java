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
import java.util.Collections;
import java.util.Comparator;
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

	//NoHayConexionBD nobd = new NoHayConexionBD();
    
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
    	List<CNotario> notariosSinConvenio = new ArrayList<CNotario>();
    	
    	try{
    		
    		
	        ListRequest lr = new ListRequest();
	        ListResponse<NotarioInfo> list = service.list(lr);
	        
	        if(!list.getList().isEmpty()){
	        
		        for(NotarioInfo ni : list.getList()){
		        	CNotario cn = new CNotario();
		        	cn.setEntidad(ni.getEntidad2());
		        	cn.setNombreNotario(ni.getNombre());
		        	cn.setCodigoNotario(ni.getCodigoNotario());
		        	cn.setNotariaNumero(ni.getNotariaNumero());
		        	cn.setConvenioInfonavit(ni.getConvenio());
		        	cn.setEmail(ni.getEmail());
		        	cn.setTelefono(ni.getTelefono());
		        	cn.setCodigo(0);
		        	cn.setDescripcion("Se consulto satisfactoriamente el notario.");
		        	
		        	if(cn.getConvenioInfonavit().length() > 0){
		        		//System.out.println("Se agrega notario CON convenio");
		        		notarios.add(cn);
		        	}
		        	else{
		        		if(cn.getConvenioInfonavit().length() == 0){
			        		//System.out.println("Se agrega notario SIN convenio");
			        		notariosSinConvenio.add(cn);
		        		}
		        	}
		        }
		        
		      //System.out.println("-----Ordenado por Nombre de Notario CON convenio-----");
	    		Collections.sort(notarios, new Comparator<CNotario>(){
	     
	    			@Override
	    			public int compare(CNotario cn1, CNotario cn2) {
	    				return cn1.getNombreNotario().compareTo(cn2.getNombreNotario());
	    			}
	    			
	    			
	    		});
	    		
	    		/**for(CNotario notario : notarios){
	    			System.out.println("Notario: "+ notario.getNombreNotario()+ " CONVENIO: " + notario.getConvenioInfonavit());
	    		}**/
	        	
	    		//System.out.println("-----Ordenado por Nombre de Notario SIN convenio-----");
	    		Collections.sort(notariosSinConvenio, new Comparator<CNotario>(){
	     
	    			@Override
	    			public int compare(CNotario cn1, CNotario cn2) {
	    				return cn1.getNombreNotario().compareTo(cn2.getNombreNotario());
	    			}
	    			
	    			
	    		});
	    		
	    		/**for(CNotario notario : notariosSinConvenio){
	    			System.out.println("Notario: "+ notario.getNombreNotario()+ " CONVENIO: " + notario.getConvenioInfonavit());
	    		}**/
	        	
	        	notarios.addAll(notariosSinConvenio);
		        
	        }
	        else{
	        	CNotario cn = new CNotario();
	        	cn.setEntidad("");
	        	cn.setNombreNotario("");
	        	cn.setCodigoNotario("");
	        	cn.setNotariaNumero("");
	        	cn.setConvenioInfonavit("");
	        	cn.setEmail("");
	        	cn.setTelefono("");
	    		cn.setCodigo(1);
	    		cn.setDescripcion("No se encontraron notarios.");
	    		notarios.add(cn);
	    		System.out.println("Status 1");
				System.out.println("No se encontraron notarios.");
	        }
	        
	    }
    	catch(Exception e){
    		CNotario cn = new CNotario();
    		cn.setCodigo(2);
    		cn.setDescripcion("No hay conexión con la base de datos.");
    		notarios.add(cn);
    		System.out.println("Status 2");
			System.out.println("No hay conexión con la base de datos.");
    		
    		
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

    public List<CNotario> consultarListaDeNotariosPorEntidad(String entidad) {
        
    	List<CNotario> notarios = new ArrayList<CNotario>();
    	List<CNotario> notariosSinConvenio = new ArrayList<CNotario>();
    	
    	try{
    		
    		
	        // Operación que regresa una lista de los notarios de la determinada entidad.
	    	ListRequest lr = new ListRequest("entidad",entidad);
	        ListResponse<NotarioInfo> list = service.list(lr);
	        
	        if(!list.getList().isEmpty()){
		    
	        	for(NotarioInfo ni : list.getList()){
		        	CNotario cn = new CNotario();
		        	cn.setEntidad(ni.getEntidad2());
		        	cn.setNombreNotario(ni.getNombre());
		        	cn.setCodigoNotario(ni.getCodigoNotario());
		        	cn.setNotariaNumero(ni.getNotariaNumero());
		        	cn.setConvenioInfonavit(ni.getConvenio());
		        	cn.setEmail(ni.getEmail());
		        	cn.setTelefono(ni.getTelefono());
		        	cn.setCodigo(0);
		        	cn.setDescripcion("Se consulto satisfactoriamente el notario.");
		        	cn.setSinNotario("Con Notarios");
		        	/**System.out.println("("+cn.getConvenioInfonavit()+")");
		        	System.out.println("(length:"+cn.getConvenioInfonavit().length()+")");**/
		        	
		        	if(cn.getConvenioInfonavit().length() > 0){
		        		//System.out.println("Se agrega notario CON convenio");
		        		notarios.add(cn);
		        	}
		        	else{
		        		if(cn.getConvenioInfonavit().length() == 0){
			        		//System.out.println("Se agrega notario SIN convenio");
			        		notariosSinConvenio.add(cn);
		        		}
		        	}
		        	
		        }
	        	
	        	/**System.out.println("-----Se muestran los notarios con convenio -----");
	        	for(CNotario notario : notarios){
	    			System.out.println("Notario: "+ notario.getNombreNotario()+ " CONVENIO: " + notario.getConvenioInfonavit());
	    		}
	        	
	        	System.out.println("-----Se muestran los notarios que no tienen convenio-----");
	        	for(CNotario notario : notariosSinConvenio){
	    			System.out.println("Notario: "+ notario.getNombreNotario()+ " CONVENIO: " + notario.getConvenioInfonavit());
	    		}**/
	        	
	        	//System.out.println("-----Ordenado por Nombre de Notario CON convenio-----");
	    		Collections.sort(notarios, new Comparator<CNotario>(){
	     
	    			@Override
	    			public int compare(CNotario cn1, CNotario cn2) {
	    				return cn1.getNombreNotario().compareTo(cn2.getNombreNotario());
	    			}
	    			
	    			
	    		});
	    		
	    		/**for(CNotario notario : notarios){
	    			System.out.println("Notario: "+ notario.getNombreNotario()+ " CONVENIO: " + notario.getConvenioInfonavit());
	    		}**/
	        	
	    		//System.out.println("-----Ordenado por Nombre de Notario SIN convenio-----");
	    		Collections.sort(notariosSinConvenio, new Comparator<CNotario>(){
	     
	    			@Override
	    			public int compare(CNotario cn1, CNotario cn2) {
	    				return cn1.getNombreNotario().compareTo(cn2.getNombreNotario());
	    			}
	    			
	    			
	    		});
	    		
	    		/**for(CNotario notario : notariosSinConvenio){
	    			System.out.println("Notario: "+ notario.getNombreNotario()+ " CONVENIO: " + notario.getConvenioInfonavit());
	    		}**/
	        	
	        	notarios.addAll(notariosSinConvenio);
	        	//System.out.println(" posición (0):" + notarios.get(0).getNombreNotario());
	        	/**System.out.println("-----Se muestran primero los notarios CON convenio y después los que no tienen convenio (EN TEORIA jajaja)-----");
	        	for(CNotario notario : notarios){
	    			System.out.println("Notario: "+ notario.getNombreNotario()+ " CONVENIO: " + notario.getConvenioInfonavit());
	    		}**/
	        	
	        	
	        	
	        }
	        else{
        	
	        	CNotario cn = new CNotario();
	        	cn.setEntidad("");
	        	cn.setNombreNotario("");
	        	cn.setCodigoNotario("");
	        	cn.setNotariaNumero("");
	        	cn.setConvenioInfonavit("");
	        	cn.setEmail("");
	        	cn.setTelefono("");
	    		cn.setCodigo(1);
	    		cn.setDescripcion("No se encontraron notarios en la entidad " + entidad + ".");
	    		cn.setSinNotario("Sin Notario");
	    		notarios.add(cn);
	    		System.out.println("Status 1");
				System.out.println("No se encontraron notarios en la entidad " + entidad + ".");
	        
	        }
    	}catch(Exception e){
    		
    		CNotario cn = new CNotario();
			cn.setCodigo(2);
			cn.setDescripcion("No hay conexión con la base de datos.");
			notarios.add(cn);
			System.out.println("Status 2");
			System.out.println("No hay conexión con la base de datos.");
    		
    	}
       
        return notarios;
    }

    
}
