/*
 * Copyright (C) 2015 leonel
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
package com.gisnet.cancelacion.webservices.dto;

public class CEntidad  implements java.io.Serializable {


     private long entidadId;
     private String nombre;
     private String clave;
     private Boolean convenio;
     

    public CEntidad() {
    }

	
    public CEntidad(long entidadId) {
        this.entidadId = entidadId;
    }
    public CEntidad(long entidadId, String nombre, String clave, Boolean convenio) {
       this.entidadId = entidadId;
       this.nombre = nombre;
       this.clave = clave;
       this.convenio = convenio;

    }
   
    public long getEntidadId() {
        return this.entidadId;
    }
    
    public void setEntidadId(long entidadId) {
        this.entidadId = entidadId;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getClave() {
        return this.clave;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }
    public Boolean getConvenio() {
        return this.convenio;
    }
    
    public void setConvenio(Boolean convenio) {
        this.convenio = convenio;
    }
    
}


