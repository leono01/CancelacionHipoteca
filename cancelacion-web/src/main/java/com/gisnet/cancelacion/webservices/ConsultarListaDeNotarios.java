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

import com.gisnet.cancelacion.webservices.dto.CNotario;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author leonel
 */
public class ConsultarListaDeNotarios {

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
        
        
        Connection conn = null;

        try {

            String dbURL = "jdbc:sqlserver://192.168.15.18:1433;databaseName=CANCELACION";
            String usuario = "cancelacion_user";
            String pwd = "cancelacion";
            conn = DriverManager.getConnection(dbURL, usuario, pwd);
            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());                

                Statement statement = conn.createStatement();
                String queryString = "select * from dbo.c_notario where numeroDeCredito=1";
                ResultSet rs = statement.executeQuery(queryString);
                
                    while (rs.next()) {
                    System.out.println(rs.getRow());
                    System.out.println(rs.getString("NOMBRE_NOTARIO"));

                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
    
        
        
      
        /*try {
         org.hibernate.Transaction tx = session.beginTransaction();
         Query q = session.createQuery("from CNotario as cn where cn.notarioId=" + numeroDeCredito);
         cn = (List<CNotario>) q.list();
         } catch (Exception e) {
         e.printStackTrace();
         }*/
        //CCaso caso = new CCaso();
        
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

 
        return notarios;
    }
        
    public String entidad(int numeroDeCredito) {

        String entidad = "";

        if (numeroDeCredito > 0 && numeroDeCredito <= 999999999) {

            if (numeroDeCredito >= 0 && numeroDeCredito <= 30000) {
                entidad = "CHI";
            }
            if (numeroDeCredito >= 30001 && numeroDeCredito <= 60000) {
                entidad = "SON";
            }
            if (numeroDeCredito >= 60001 && numeroDeCredito <= 90000) {
                entidad = "COA";
            }
            if (numeroDeCredito >= 90001 && numeroDeCredito <= 120000) {
                entidad = "DUR";
            }
            if (numeroDeCredito >= 120001 && numeroDeCredito <= 150000) {
                entidad = "OAX";
            }
            if (numeroDeCredito >= 150001 && numeroDeCredito <= 180000) {
                entidad = "TAM";
            }
            if (numeroDeCredito >= 180001 && numeroDeCredito <= 210000) {
                entidad = "JAL";
            }
            if (numeroDeCredito >= 210001 && numeroDeCredito <= 240000) {
                entidad = "ZAC";
            }
            if (numeroDeCredito >= 240001 && numeroDeCredito <= 270000) {
                entidad = "BCS";
            }
            if (numeroDeCredito >= 270001 && numeroDeCredito <= 300000) {
                entidad = "CHP";
            }
            if (numeroDeCredito >= 300001 && numeroDeCredito <= 330000) {
                entidad = "VER";
            }
            if (numeroDeCredito >= 330001 && numeroDeCredito <= 360000) {
                entidad = "BCL";
            }
            if (numeroDeCredito >= 360001 && numeroDeCredito <= 390000) {
                entidad = "NLN";
            }
            if (numeroDeCredito >= 390001 && numeroDeCredito <= 410000) {
                entidad = "GUE";
            }
            if (numeroDeCredito >= 410001 && numeroDeCredito <= 430000) {
                entidad = "SLN";
            }
            if (numeroDeCredito >= 430001 && numeroDeCredito <= 450000) {
                entidad = "MCH";
            }
            if (numeroDeCredito >= 450001 && numeroDeCredito <= 480000) {
                entidad = "CAM";
            }
            if (numeroDeCredito >= 480001 && numeroDeCredito <= 510000) {
                entidad = "SIN";
            }
            if (numeroDeCredito >= 510001 && numeroDeCredito <= 540000) {
                entidad = "QUI";
            }
            if (numeroDeCredito >= 540001 && numeroDeCredito <= 570000) {
                entidad = "YUC";
            }
            if (numeroDeCredito >= 570001 && numeroDeCredito <= 600000) {
                entidad = "PUE";
            }
            if (numeroDeCredito >= 600001 && numeroDeCredito <= 630000) {
                entidad = "GUA";
            }
            if (numeroDeCredito >= 630001 && numeroDeCredito <= 660000) {
                entidad = "NAY";
            }
            if (numeroDeCredito >= 660001 && numeroDeCredito <= 690000) {
                entidad = "TAB";
            }
            if (numeroDeCredito >= 690001 && numeroDeCredito <= 720000) {
                entidad = "MEX";
            }
            if (numeroDeCredito >= 720001 && numeroDeCredito <= 750000) {
                entidad = "HID";
            }
            if (numeroDeCredito >= 750001 && numeroDeCredito <= 780000) {
                entidad = "QUE";
            }
            if (numeroDeCredito >= 780001 && numeroDeCredito <= 810000) {
                entidad = "COL";
            }
            if (numeroDeCredito >= 810001 && numeroDeCredito <= 840000) {
                entidad = "AGU";
            }
            if (numeroDeCredito >= 840001 && numeroDeCredito <= 870000) {
                entidad = "MOR";
            }
            if (numeroDeCredito >= 870001 && numeroDeCredito <= 900000) {
                entidad = "TLA";
            }
            if (numeroDeCredito >= 900001) {
                entidad = "DIF";
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
        
        
        
        return notarios;
    }

    
}
