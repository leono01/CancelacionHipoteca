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
import javax.persistence.Lob;
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
    
    private long id;
    private String codigoCarta;
    private byte[] pdf;
    private byte[] xml;
    private Date fechaEmisionCarta;

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
    @Lob
    public byte[] getPdf() {
        return pdf;
    }

    public void setPdf(byte[] pdf) {
        this.pdf = pdf;
    }

    @Column(name = "XML", columnDefinition = "VARBINARY(MAX)")
    @Lob
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
    
    @Override
    public CartaCancelacionInfo asInfo() {
        CartaCancelacionInfo info = new CartaCancelacionInfo();
        info.setId(id);
        info.setCodigoCarta(codigoCarta);
        info.setPdf(pdf);
        info.setXml(xml);
        return info;
    }
}
