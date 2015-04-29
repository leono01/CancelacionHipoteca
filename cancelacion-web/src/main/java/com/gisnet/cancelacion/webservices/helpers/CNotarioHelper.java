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
package com.gisnet.cancelacion.webservices.helpers;

import com.gisnet.cancelacion.webservices.dto.CNotario;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author leonel
 */
public class CNotarioHelper {

    public CNotarioHelper() {

    }

    public List<CNotario> queryConsultarListaDeNotarios(int numeroDeCredito, String entidad) {

        List<CNotario> cn = new ArrayList<CNotario>();

        /*try {
         org.hibernate.Transaction tx = session.beginTransaction();
         Query q = session.createQuery("from CNotario as cn where cn.notarioId=" + numeroDeCredito);
         cn = (List<CNotario>) q.list();
         } catch (Exception e) {
         e.printStackTrace();
         }*/
        //CCaso caso = new CCaso();
        CEntidadHelper e = new CEntidadHelper();
        
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

            cn.add(n3);
            
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

                cn.add(n1);
                cn.add(n2);
            
            
            
        } else {
            if (esNumero == false || entidad.equals("[^(A-ZA-ZA-Z)]")) {
                CNotario n1 = new CNotario();
                n1.setCodigoRespuesta("LN03");
                cn.add(n1);
                System.out.println("***** No fue numero o no fue clave");
            } else {

                
            }
        }

        // Hace la búsqueda en la BD
        return cn;
    }

    public List<CNotario> queryConsultarListaDeNotariosAdai(String entidad) {

        List<CNotario> cn = new ArrayList<CNotario>();

        /*try {
         org.hibernate.Transaction tx = session.beginTransaction();
         Query q = session.createQuery("from CNotario as cn where cn.notarioId=" + entidad);
         cn = (List<CNotario>) q.list();
         } catch (Exception e) {
         e.printStackTrace();
         }*/
        if (entidad.matches("[^(A-ZA-ZA-Z)]")) {

            CNotario n1 = new CNotario();
            n1.setCodigoRespuesta("Ingresar una entidad. O puede que no sea la clave correcta de la entidad");
            cn.add(n1);
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

            cn.add(n1);

            CNotario n2 = new CNotario();
            n2.setCodigoNotario(864);
            n2.setNombreNotario("Daniel Mejía");
            n2.setNotariaNumero("Notaria 65");
            n2.setCEntidad(entidad);
            n2.setConvenioInfonavit(1);
            n2.setEmail("dmejia@notaria65.com");
            n2.setTelefono("7687699876");
            n2.setCodigoRespuesta("LN00");

            cn.add(n2);

            CNotario n3 = new CNotario();
            n3.setCodigoNotario(434);
            n3.setNombreNotario("Danilo Torres");
            n3.setNotariaNumero("Notaria 223");
            n3.setCEntidad(entidad);
            n3.setConvenioInfonavit(1);
            n3.setEmail("dtorres@notaria23.com");
            n3.setTelefono("4444843576");

            cn.add(n3);
        }

        // Hace la búsqueda en la BD
        return cn;
    }
}
