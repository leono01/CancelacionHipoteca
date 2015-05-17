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
package com.gisnet.cancelacion.events.info;

import java.util.Date;

import com.gisnet.cancelacion.events.Info;

/**
 *
 * @author marco-g8
 */
public class CartaCancelacionInfo implements Info {
    
    private long 	id;
    private String 	codigoCarta;
    private byte[] 	pdf;
    private byte[] 	xml;
    private Date 	fechaEmisionCarta;
    private String 	folio;
    private String 	entidad;
	private String	nombreAcreditado;
	private String	nombreNotario;
	private String 	notaria;
	private String	direccion;
	private String	folioEscritura;
	private String	fojaEscritura;
	private String	libroEscritura;

	public CartaCancelacionInfo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigoCarta() {
        return codigoCarta;
    }

    public void setCodigoCarta(String codigoCarta) {
        this.codigoCarta = codigoCarta;
    }

    public byte[] getPdf() {
        return pdf;
    }

    public void setPdf(byte[] pdf) {
        this.pdf = pdf;
    }

    public byte[] getXml() {
        return xml;
    }

    public void setXml(byte[] xml) {
        this.xml = xml;
    }
    

	public Date getFechaEmisionCarta() {
		return fechaEmisionCarta;
	}

	public void setFechaEmisionCarta(Date fechaEmisionCarta) {
		this.fechaEmisionCarta = fechaEmisionCarta;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}
	
	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getNombreAcreditado() {
		return nombreAcreditado;
	}

	public void setNombreAcreditado(String nombreAcreditado) {
		this.nombreAcreditado = nombreAcreditado;
	}

	public String getNombreNotario() {
		return nombreNotario;
	}

	public void setNombreNotario(String nombreNotario) {
		this.nombreNotario = nombreNotario;
	}

	public String getNotaria() {
		return notaria;
	}

	public void setNotaria(String notaria) {
		this.notaria = notaria;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getFolioEscritura() {
		return folioEscritura;
	}

	public void setFolioEscritura(String folioEscritura) {
		this.folioEscritura = folioEscritura;
	}

	public String getFojaEscritura() {
		return fojaEscritura;
	}

	public void setFojaEscritura(String fojaEscritura) {
		this.fojaEscritura = fojaEscritura;
	}

	public String getLibroEscritura() {
		return libroEscritura;
	}

	public void setLibroEscritura(String libroEscritura) {
		this.libroEscritura = libroEscritura;
	}
    
}
