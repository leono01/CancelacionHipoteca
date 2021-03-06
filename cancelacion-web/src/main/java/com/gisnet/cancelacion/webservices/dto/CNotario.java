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
package com.gisnet.cancelacion.webservices.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * CNotario generated by hbm2java
 */
public class CNotario  implements java.io.Serializable {


     
     
     private String   	entidad;      
     private String 	nombreNotario;
     private String		codigoNotario;
     private String 	notariaNumero;     
     private String		convenioInfonavit;
     private String 	email;
     private String 	telefono;
     private int		codigo;
     private String		descripcion;     
     private long		identificadorUnicoNotario;
     
     

    public long getIdentificadorUnicoNotario() {
		return identificadorUnicoNotario;
	}


	public void setIdentificadorUnicoNotario(long identificadorUnicoNotario) {
		this.identificadorUnicoNotario = identificadorUnicoNotario;
	}


	public CNotario() {
    }

	
    public CNotario(String nombreNotario, 
    				String codigoNotario, 
    				String notariaNumero, 
    				String convenioInfonavit,
    				String email,
    				String telefono,
    				String entidad
    				) {
       

       this.nombreNotario 		= nombreNotario;
       this.codigoNotario 		= codigoNotario;
       this.notariaNumero 		= notariaNumero;
       this.convenioInfonavit 	= convenioInfonavit;
       this.email 				= email;
       this.telefono 			= telefono;
       this.entidad 			= entidad;

    }
   

    public String getNombreNotario() {
        return this.nombreNotario;
    }
    
    public void setNombreNotario(String nombreNotario) {
        this.nombreNotario = nombreNotario;
    }
    public String getCodigoNotario() {
        return this.codigoNotario;
    }
    
    public void setCodigoNotario(String codigoNotario) {
        this.codigoNotario = codigoNotario;
    }
    public String getNotariaNumero() {
        return this.notariaNumero;
    }
    
    public void setNotariaNumero(String notariaNumero) {
        this.notariaNumero = notariaNumero;
    }
    public String getConvenioInfonavit() {
        return this.convenioInfonavit;
    }
    
    public void setConvenioInfonavit(String convenioInfonavit) {
        this.convenioInfonavit = convenioInfonavit;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
    

    public String getCEntidad() {
        return entidad;
    }

    public void setCEntidad(String entidad) {
        this.entidad = entidad;
    }
    
    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }


	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}


