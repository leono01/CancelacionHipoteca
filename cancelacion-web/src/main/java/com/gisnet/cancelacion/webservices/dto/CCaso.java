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


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CCaso generated by hbm2java
 */
public class CCaso  implements java.io.Serializable {


     private long casoId;     
     private CNotario CNotario;
     private int numeroDeCredito;
     private int numeroDeCaso;
     private Date fechaDeCreacion;
     private Date fechaDeCierre;
     private Long proyectoDeCancelacion;
     private String nombreAcreditado;
     private String entidad;
     private Date	fechaActualizacion;

	public CCaso() {
    }

	
    public CCaso(long casoId) {
        this.casoId = casoId;
    }
    public CCaso(long casoId, CNotario CNotario, int numeroDeCredito, int numeroDeCaso, Date fechaDeCreacion, Date fechaDeCierre, Long proyectoDeCancelacion, String nombreAcreditado) {
       this.casoId = casoId;
       
       this.CNotario = CNotario;
       
       this.numeroDeCredito = numeroDeCredito;
       this.numeroDeCaso = numeroDeCaso;
       this.fechaDeCreacion = fechaDeCreacion;
       this.fechaDeCierre = fechaDeCierre;
       this.proyectoDeCancelacion = proyectoDeCancelacion;
       this.nombreAcreditado = nombreAcreditado;
       //this.CBitacoraCasos = CBitacoraCasos;
    }
   
    public long getCasoId() {
        return this.casoId;
    }
    
    public void setCasoId(long casoId) {
        this.casoId = casoId;
    }
    
    public CNotario getCNotario() {
        return this.CNotario;
    }
    
    public void setCNotario(CNotario CNotario) {
        this.CNotario = CNotario;
    }
    
    public int getNumeroDeCredito() {
        return this.numeroDeCredito;
    }
    
    public void setNumeroDeCredito(int numeroDeCredito) {
        this.numeroDeCredito = numeroDeCredito;
    }
    public int getNumeroDeCaso() {
        return this.numeroDeCaso;
    }
    
    public void setNumeroDeCaso(int numeroDeCaso) {
        this.numeroDeCaso = numeroDeCaso;
    }
    public Serializable getFechaDeCreacion() {
        return this.fechaDeCreacion;
    }
    
    public void setFechaDeCreacion(Date fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }
    public Serializable getFechaDeCierre() {
        return this.fechaDeCierre;
    }
    
    public void setFechaDeCierre(Date fechaDeCierre) {
        this.fechaDeCierre = fechaDeCierre;
    }
    public Long getProyectoDeCancelacion() {
        return this.proyectoDeCancelacion;
    }
    
    public void setProyectoDeCancelacion(Long proyectoDeCancelacion) {
        this.proyectoDeCancelacion = proyectoDeCancelacion;
    }
    public String getNombreAcreditado() {
        return this.nombreAcreditado;
    }
    
    public void setNombreAcreditado(String nombreAcreditado) {
        this.nombreAcreditado = nombreAcreditado;
    }
    
    public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
    
}


