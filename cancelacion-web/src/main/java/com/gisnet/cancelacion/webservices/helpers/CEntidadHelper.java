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

import com.gisnet.cancelacion.webservices.dto.CEntidad;
//import com.gisnet.cancelacion.util.CHibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author leonel
 */
public class CEntidadHelper {

    public CEntidadHelper() {

    }

    public CEntidad queryConsultaEntidad(String entidad) {
        CEntidad e = new CEntidad();

        if (entidad.equalsIgnoreCase("CHI")) {
            e.setNombre("CHIHUAHUA");
            e.setClave("CHI");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("SON")) {
            e.setNombre("SONORA");
            e.setClave("SON");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("COA")) {
            e.setNombre("COAHUILA");
            e.setClave("COA");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("DUR")) {
            e.setNombre("DURANGO");
            e.setClave("DUR");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("OAX")) {
            e.setNombre("OAXACA");
            e.setClave("OAX");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("TAM")) {
            e.setNombre("TAMAULIPAS");
            e.setClave("TAM");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("JAL")) {
            e.setNombre("JALISCO");
            e.setClave("JAL");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("ZAC")) {
            e.setNombre("ZACATECAS");
            e.setClave("ZAC");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("BCS")) {
            e.setNombre("BAJA CALIFORNIA SUR");
            e.setClave("BCS");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("CHP")) {
            e.setNombre("CHIAPAS");
            e.setClave("CHP");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("VER")) {
            e.setNombre("VERACRUZ");
            e.setClave("VER");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("BCL")) {
            e.setNombre("BAJA CALIFORNIA");
            e.setClave("BCL");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("NLN")) {
            e.setNombre("NUEVO LEÓN");
            e.setClave("NLN");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("GUE")) {
            e.setNombre("GUERRERO");
            e.setClave("GUE");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("SLN")) {
            e.setNombre("SAN LUIS POTOSÍ");
            e.setClave("SLN");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("MCH")) {
            e.setNombre("MICHOACÁN");
            e.setClave("MCH");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("CAM")) {
            e.setNombre("CAMPECHE");
            e.setClave("CAM");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("SIN")) {
            e.setNombre("SINALOA");
            e.setClave("SIN");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("QUI")) {
            e.setNombre("QUINTANA ROO");
            e.setClave("QUI");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("YUC")) {
            e.setNombre("YUCATÁN");
            e.setClave("YUC");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("PUE")) {
            e.setNombre("PUEBLA");
            e.setClave("PUE");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("GUA")) {
            e.setNombre("GUANAJUATO");
            e.setClave("GUA");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("NAY")) {
            e.setNombre("NAYARIT");
            e.setClave("NAY");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("TAB")) {
            e.setNombre("TABASCO");
            e.setClave("TAB");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("MEX")) {
            e.setNombre("MÉXICO");
            e.setClave("MEX");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("HID")) {
            e.setNombre("HIDALGO");
            e.setClave("HID");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("QUE")) {
            e.setNombre("QUERÉTARO");
            e.setClave("QUE");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("COL")) {
            e.setNombre("COLIMA");
            e.setClave("COL");
            e.setConvenio(Boolean.TRUE);
        }
        if (entidad.equalsIgnoreCase("AGU")) {
            e.setNombre("AGUASCALIENTES");
            e.setClave("AGU");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("MOR")) {
            e.setNombre("MORELOS");
            e.setClave("MOR");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("TLA")) {
            e.setNombre("TLAXCALA");
            e.setClave("TLA");
            e.setConvenio(Boolean.FALSE);
        }
        if (entidad.equalsIgnoreCase("DIF")) {
            e.setNombre("DISTRITO FEDERAL");
            e.setClave("DIF");
            e.setConvenio(Boolean.FALSE);
        }

        return e;
    }

    public String buscaNumeroDeCredito(int numeroDeCredito) {
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
}
