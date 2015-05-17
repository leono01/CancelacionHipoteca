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
package com.gisnet.cancelacion.persistance.domain;

import com.gisnet.cancelacion.events.info.CartaCancelacionInfo;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author marco-g8
 */
@Entity
@Table(name = "C_CARTA_DE_CANCELACION")
public class CartaCancelacion implements Infoable<CartaCancelacionInfo>, Serializable {
    
    private long 	id;
    private String 	codigoCarta;
    private byte[] 	pdf;
    private byte[] 	xml;
    private Date 	fechaEmisionCarta;
    private String 	folio;
    private String	entidad;
	private String	nombreAcreditado;
	private String	nombreNotario;
	private String 	notaria;
	private String	direccion;
	private String	folioEscritura;
	private String	fojaEscritura;
	private String	libroEscritura;

    public CartaCancelacion() {
    }

    @Id
    @Column(name = "CARTA_DE_CANCELACION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "CODIGO_CARTA")
    public String getCodigoCarta() {
        return codigoCarta;
    }

    public void setCodigoCarta(String codigoCarta) {
        this.codigoCarta = codigoCarta;
    }

    @Column(name = "PDF", columnDefinition = "VARBINARY(MAX)")
    public byte[] getPdf() {
        return pdf;
    }

    public void setPdf(byte[] pdf) {
        this.pdf = pdf;
    }

    @Column(name = "XML", columnDefinition = "VARBINARY(MAX)")
    public byte[] getXml() {
        return xml;
    }

    public void setXml(byte[] xml) {
        this.xml = xml;
    }
    
    @Column(name = "FECHA_EMISION_CARTA")
    @Temporal(TemporalType.DATE)
    public Date getFechaEmisionCarta() {
        return fechaEmisionCarta;
    }

    public void setFechaEmisionCarta(Date fechaEmisionCarta) {
        this.fechaEmisionCarta = fechaEmisionCarta;
    }
    
    @Column(name = "FOLIO")
    public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}
    
	@Column(name = "ENTIDAD")
    public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	@Column(name = "NOMBRE_ACREDITADO")
    public String getNombreAcreditado() {
		return nombreAcreditado;
	}

	public void setNombreAcreditado(String nombreAcreditado) {
		this.nombreAcreditado = nombreAcreditado;
	}

	@Column(name = "NOMBRE_NOTARIO")
	public String getNombreNotario() {
		return nombreNotario;
	}

	public void setNombreNotario(String nombreNotario) {
		this.nombreNotario = nombreNotario;
	}

	@Column(name = "NOTARIA")
	public String getNotaria() {
		return notaria;
	}

	public void setNotaria(String notaria) {
		this.notaria = notaria;
	}

	@Column(name = "DIRECCION")
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Column(name = "FOLIO_ESCRITURA")
	public String getFolioEscritura() {
		return folioEscritura;
	}

	public void setFolioEscritura(String folioEscritura) {
		this.folioEscritura = folioEscritura;
	}

	@Column(name = "FOJA_ESCRITURA")
	public String getFojaEscritura() {
		return fojaEscritura;
	}

	public void setFojaEscritura(String fojaEscritura) {
		this.fojaEscritura = fojaEscritura;
	}

	@Column(name = "LIBRO_ESCRITURA")
	public String getLibroEscritura() {
		return libroEscritura;
	}

	public void setLibroEscritura(String libroEscritura) {
		this.libroEscritura = libroEscritura;
	}

    
    
    @Override
    public CartaCancelacionInfo asInfo() {
        CartaCancelacionInfo info = new CartaCancelacionInfo();
        info.setId(id);
        info.setCodigoCarta(codigoCarta);
        info.setPdf(pdf);
        info.setXml(xml);
        info.setFechaEmisionCarta(fechaEmisionCarta);
        info.setFolio(folio);
        info.setEntidad(entidad);
        info.setNombreAcreditado(nombreAcreditado);
        info.setNombreNotario(nombreNotario);
        info.setNotaria(notaria);
        info.setDireccion(direccion);
        info.setFolioEscritura(folioEscritura);
        info.setFojaEscritura(fojaEscritura);
        info.setLibroEscritura(libroEscritura);
        return info;
    }
}
