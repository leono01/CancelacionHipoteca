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
import com.gisnet.cancelacion.events.ListRequest;
import com.gisnet.cancelacion.events.ListResponse;
import com.gisnet.cancelacion.events.SaveRequest;
import com.gisnet.cancelacion.events.SaveResponse;
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


/**
 *
 * @author leonel
 */
public class ConsultarListaDeNotarios {

	//@Autowired
    //private NotarioService service;
	
    
    /**
    @RequestMapping(value = "/juridico/registrar", method = RequestMethod.GET)
    public String ver(Model model, Principal principal) {
        
        return "/juridico/registrar";
    }
    
    @RequestMapping(value = "/juridico/registrar", method = RequestMethod.POST)
    public String registrar(
            @Valid @ModelAttribute("notarioForm") NotarioForm form,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model,
            Principal principal) {
        
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.register", result);
            redirectAttributes.addFlashAttribute("notarioInfo", form);
            return "redirect:/juridico/registrar";
        }
        
        NotarioInfo info = new NotarioInfo();
        info.setNombre(form.getNombre());
        info.setCodigo(form.getCodigo());
        info.setNotariaNumero(form.getNotariaNumero());
        info.setConvenioInfonavit(form.isConvenioInfonavit());
        info.setEmail(form.getEmail());
        info.setTelefono(form.getTelefono());
        info.setCalleNotaria(form.getCalleNotaria());
        info.setNumeroCalle(form.getNumeroCalle());
        info.setColoniaNotaria(form.getColoniaNotaria());
        info.setCodigoPostalNotaria(form.getCodigoPostalNotaria());
        
        info.setUsuarioId(0l);
        info.setMunicipioId(0l);
        info.setEntidadId(0l);
        
        SaveRequest<NotarioInfo> saveRequest = new SaveRequest<>();
        saveRequest.setInfo(info);
        SaveResponse<NotarioInfo> save = service.save(saveRequest);
        return "redirect:/";
    }
	**/
	
    /**
     * Es el Servicio Web que permite consultar la lista de notarios
     *
     * @param numeroDeCredito
     * @param entidad
     * @return Una lista en donde se encuentran los datos del notario consultado
     */
    public List<CNotario> consultarListaDeNotarios(int     numeroDeCredito,
                                                   String  entidad
    ) {       
    
    	List<CNotario> notarios = new ArrayList<CNotario>();
        //ListRequest lr = new ListRequest();
        //ListResponse<NotarioInfo> list = service.list(lr);
       
        
        /**for (String nombre : list) {
        	 
        	if (nombre.equals("Pedro")) {
        	 
        	lista.remove("Pedro");
        	}
        	 
        }**/
        //notarios = list.getList();
      
        
        /*try {
         org.hibernate.Transaction tx = session.beginTransaction();
         Query q = session.createQuery("from CNotario as cn where cn.notarioId=" + numeroDeCredito);
         cn = (List<CNotario>) q.list();
         } catch (Exception e) {
         e.printStackTrace();
         }*/
        
        
        boolean esNumero= false;
        
        if (numeroDeCredito > 0 && numeroDeCredito <=99999999){
            esNumero = true;
        }else{esNumero=false;}
        
        if (entidad.matches("[A-ZA-ZA-Z]") || esNumero == true) {
           
            CNotario n3 = new CNotario();
            n3.setCodigoNotario(234 + (int) Math.random());
            n3.setNombreNotario("Teodoro Ortíz");
            n3.setNotariaNumero("Notaria 100");
            n3.setCEntidad(entidad);
            n3.setConvenioInfonavit(1);
            n3.setEmail("tortiz@notaria100.com");
            n3.setTelefono("7687699876");
            n3.setCodigoRespuesta("LN00");

            notarios.add(n3);
            
            CNotario n1 = new CNotario();
                n1.setCodigoNotario(234 + (int) Math.random());
                n1.setNombreNotario("Daniel Mejía");
                n1.setNotariaNumero("Notaria 65");
                n1.setCEntidad(entidad);
                n1.setConvenioInfonavit(1);
                n1.setEmail("dmejia@notaria65.com");
                n1.setTelefono("7687699876");
                n1.setCodigoRespuesta("LN00");

                CNotario n2 = new CNotario();
                n2.setCodigoNotario(464 + (int) Math.random());
                n2.setNombreNotario("Damian Tapía");
                n2.setNotariaNumero("Notaria 23");
                n2.setCEntidad(entidad);
                n2.setConvenioInfonavit(1);
                n2.setEmail("dmejia@notaria23.com");
                n2.setTelefono("4444843576");

                notarios.add(n1);
                notarios.add(n2);
            
            
            
        } else {
                CNotario n1 = new CNotario();
                n1.setCodigoRespuesta("LN03");
                notarios.add(n1);
                System.out.println("***** No fue numero o no fue clave");
           
        }

        //return list.getList();
        return notarios;
    }
        
    public String entidad(int numeroDeCredito) {

        String entidad = "";

        if (numeroDeCredito > 0 && numeroDeCredito <= 999999999) {

            if (numeroDeCredito >= 0 && numeroDeCredito <= 30000) {
                entidad = "CH";
            }
            if (numeroDeCredito >= 30001 && numeroDeCredito <= 60000) {
                entidad = "SO";
            }
            if (numeroDeCredito >= 60001 && numeroDeCredito <= 90000) {
                entidad = "CO";
            }
            if (numeroDeCredito >= 90001 && numeroDeCredito <= 120000) {
                entidad = "DU";
            }
            if (numeroDeCredito >= 120001 && numeroDeCredito <= 150000) {
                entidad = "OA";
            }
            if (numeroDeCredito >= 150001 && numeroDeCredito <= 180000) {
                entidad = "TA";
            }
            if (numeroDeCredito >= 180001 && numeroDeCredito <= 210000) {
                entidad = "JA";
            }
            if (numeroDeCredito >= 210001 && numeroDeCredito <= 240000) {
                entidad = "ZA";
            }
            if (numeroDeCredito >= 240001 && numeroDeCredito <= 270000) {
                entidad = "BS";
            }
            if (numeroDeCredito >= 270001 && numeroDeCredito <= 300000) {
                entidad = "CH";
            }
            if (numeroDeCredito >= 300001 && numeroDeCredito <= 330000) {
                entidad = "VE";
            }
            if (numeroDeCredito >= 330001 && numeroDeCredito <= 360000) {
                entidad = "BC";
            }
            if (numeroDeCredito >= 360001 && numeroDeCredito <= 390000) {
                entidad = "NL";
            }
            if (numeroDeCredito >= 390001 && numeroDeCredito <= 410000) {
                entidad = "GU";
            }
            if (numeroDeCredito >= 410001 && numeroDeCredito <= 430000) {
                entidad = "SL";
            }
            if (numeroDeCredito >= 430001 && numeroDeCredito <= 450000) {
                entidad = "MI";
            }
            if (numeroDeCredito >= 450001 && numeroDeCredito <= 480000) {
                entidad = "CA";
            }
            if (numeroDeCredito >= 480001 && numeroDeCredito <= 510000) {
                entidad = "SI";
            }
            if (numeroDeCredito >= 510001 && numeroDeCredito <= 540000) {
                entidad = "QR";
            }
            if (numeroDeCredito >= 540001 && numeroDeCredito <= 570000) {
                entidad = "YU";
            }
            if (numeroDeCredito >= 570001 && numeroDeCredito <= 600000) {
                entidad = "PU";
            }
            if (numeroDeCredito >= 600001 && numeroDeCredito <= 630000) {
                entidad = "GU";
            }
            if (numeroDeCredito >= 630001 && numeroDeCredito <= 660000) {
                entidad = "NA";
            }
            if (numeroDeCredito >= 660001 && numeroDeCredito <= 690000) {
                entidad = "TA";
            }
            if (numeroDeCredito >= 690001 && numeroDeCredito <= 720000) {
                entidad = "EM";
            }
            if (numeroDeCredito >= 720001 && numeroDeCredito <= 750000) {
                entidad = "HI";
            }
            if (numeroDeCredito >= 750001 && numeroDeCredito <= 780000) {
                entidad = "QR";
            }
            if (numeroDeCredito >= 780001 && numeroDeCredito <= 810000) {
                entidad = "CO";
            }
            if (numeroDeCredito >= 810001 && numeroDeCredito <= 840000) {
                entidad = "AG";
            }
            if (numeroDeCredito >= 840001 && numeroDeCredito <= 870000) {
                entidad = "MO";
            }
            if (numeroDeCredito >= 870001 && numeroDeCredito <= 900000) {
                entidad = "TA";
            }
            if (numeroDeCredito >= 900001) {
                entidad = "DF";
            }

            

        }else{
            entidad = "Error en la longitud del número de crédito. No más de 9 digitos.";
        }
        
        return entidad;
    }

    public List<CNotario> consultarListaDeNotariosAdai(String entidad) {
        List<CNotario> notarios;
        notarios = new ArrayList<CNotario>();


        if (entidad.matches("[^(A-ZA-ZA-Z)]")) {

            CNotario n1 = new CNotario();
            n1.setCodigoRespuesta("Ingresar una entidad. O puede que no sea la clave correcta de la entidad");
            notarios.add(n1);
        } else {

            CNotario n1 = new CNotario();
            n1.setCodigoNotario(134 + (int) Math.random());
            n1.setNombreNotario("Teodoro Ortíz");
            n1.setNotariaNumero("Notaria 100");
            n1.setCEntidad(entidad);
            n1.setConvenioInfonavit(1);
            n1.setEmail("tortiz@notaria100.com");
            n1.setTelefono("7687699876");
            n1.setCodigoRespuesta("LN00");

            notarios.add(n1);

            CNotario n2 = new CNotario();
            n2.setCodigoNotario(864);
            n2.setNombreNotario("Daniel Mejía");
            n2.setNotariaNumero("Notaria 65");
            n2.setCEntidad(entidad);
            n2.setConvenioInfonavit(1);
            n2.setEmail("dmejia@notaria65.com");
            n2.setTelefono("7687699876");
            n2.setCodigoRespuesta("LN00");

            notarios.add(n2);

            CNotario n3 = new CNotario();
            n3.setCodigoNotario(434);
            n3.setNombreNotario("Danilo Torres");
            n3.setNotariaNumero("Notaria 223");
            n3.setCEntidad(entidad);
            n3.setConvenioInfonavit(1);
            n3.setEmail("dtorres@notaria23.com");
            n3.setTelefono("4444843576");

            notarios.add(n3);
        }
        
        
    	//ListRequest lr = new ListRequest();
        //ListResponse<NotarioInfo> list = service.list(lr);
        return notarios;
    }

    
}
