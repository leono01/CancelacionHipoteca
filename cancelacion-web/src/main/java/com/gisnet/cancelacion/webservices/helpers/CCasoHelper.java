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

import com.gisnet.cancelacion.webservices.dto.CCaso;
import com.gisnet.cancelacion.webservices.dto.InfoDeActualizacion;
import com.gisnet.cancelacion.webservices.dto.InfoDeConsulta;
import com.gisnet.cancelacion.webservices.dto.StatusOperacion;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leonel
 */
public class CCasoHelper {

    public CCasoHelper() {

    }

    public StatusOperacion queryRegistraCaso(int numeroDeCredito, int numeroDeCaso, String nombreAcreditado) {

        StatusOperacion so = new StatusOperacion();

        /*Date fechaDeCierre = null;
        
         try {
         org.hibernate.Transaction tx = session.beginTransaction();
         Query q = session.createQuery("INSERT INTO CCaso (casoId,numeroDeCredito,numeroDeCaso,fechaDeCreacion,fechaDeCierre,CNotario,CProyectoDeCancelacion,CCartaDeCancelacion,CProyectoRpp,CStatusDelCaso,nombreAcreditado) VALUES(1,:credito,:caso,:fechaCreacion,:fechaCierre,null,null,null,null,null,:nombreAcreditado)");
         q.setParameter("credito", numeroDeCredito);
         q.setParameter("caso", numeroDeCaso);
         q.setParameter("fechaCreacion", new Date());
         q.setParameter("fechaCierre", fechaDeCierre);
         q.setParameter("nombreAcreditado", nombreAcreditado);
         int casosCreados = q.executeUpdate();
         System.out.println("Casos creados en la operación: " + casosCreados);
         so.setCodigo(10);
         so.setDescripcion("El caso se creo exitosamente.");
         } catch (Exception e) {
         so.setCodigo(20);
         so.setDescripcion("El caso no se creo exitosamente.");
         e.printStackTrace();
         }*/
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

    public InfoDeActualizacion queryActualizaCaso(int numeroDeCredito, int numeroDeCaso, Date fecha, int status, String cartaDeCancelacionPdf, Date fechaEmisionCarta, String numeroDeFolio) {

        InfoDeActualizacion ida = new InfoDeActualizacion();

        /*Date fechaDeCierre = null;
        
         try {
         org.hibernate.Transaction tx = session.beginTransaction();
         Query q = session.createQuery("update dbo.c_caso set fechaDeCreacion = :fecha where numeroDeCaso = :caso");
         q.setParameter("fecha", fechaDeCreacion);
         q.setParameter("caso", numeroDeCaso);
         int casosCreados = q.executeUpdate();
         System.out.println("Casos afectados por los cambios: " + casosCreados);
         so.setCodigo(10);
         so.setDescripcion("El caso se creo exitosamente.");
         } catch (Exception e) {
         so.setCodigo(20);
         so.setDescripcion("El caso no se creo exitosamente.");
         e.printStackTrace();
         }*/
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
        return ida;
    }

    /*public List<CCaso> queryConsultaCaso(String numeroDeCredito,String numeroDeCaso) {

     List <CCaso> casos = null;
        
     try {
     org.hibernate.Transaction tx = session.beginTransaction();
     //Query q = session.createQuery("from dbo.c_caso as cc where cc.numeroDeCaso = :caso and cc.numeroDeCredito = :credito");
     Query q = session.createQuery("from dbo.c_caso as cc where cc.numeroDeCaso = :caso");
     q.setParameter("caso", numeroDeCaso);
     //q.setParameter("credito", numeroDeCredito);
     casos = (List<CCaso>)q.list();
        
        
     } catch (Exception e) {
        
     e.printStackTrace();
     }
     return casos;
     }*/
    public InfoDeConsulta queryConsultaCaso(int numeroDeCredito, int numeroDeCaso) {

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
        } catch (ParseException ex) {
            Logger.getLogger(CCasoHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return idc;
    }
}
