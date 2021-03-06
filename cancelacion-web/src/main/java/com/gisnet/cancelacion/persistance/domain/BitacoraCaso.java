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

import com.gisnet.cancelacion.events.info.BitacoraCasoInfo;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author marco-g8
 */
@Entity
@Table(name = "C_BITACORA_CASO")
public class BitacoraCaso implements Infoable<BitacoraCasoInfo>, Serializable {
    
    private long id;
    private Date fechaActualizacion;
    private StatusCaso statusCaso;
    private Caso bitacoraCaso;
    
    private String usuario;

    public BitacoraCaso() {
    }

    @Id
    @Column(name = "BITACORA_CASO_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.DATE)
    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @ManyToOne
    @JoinColumn(name = "STATUS_DEL_CASO")
    public StatusCaso getStatusCaso() {
        return statusCaso;
    }

    public void setStatusCaso(StatusCaso statusCaso) {
        this.statusCaso = statusCaso;
    }

    @ManyToOne
    @JoinColumn(name = "BITACORA_CASO")
    public Caso getBitacoraCaso() {
        return bitacoraCaso;
    }

    public void setBitacoraCaso(Caso bitacoraCaso) {
        this.bitacoraCaso = bitacoraCaso;
    }

    @Column(name = "USUARIO")
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public BitacoraCasoInfo asInfo() {
        BitacoraCasoInfo info = new BitacoraCasoInfo();
        info.setId(id);
        info.setFechaActualizacion(fechaActualizacion);
        if (statusCaso != null)
            info.setStatusCaso(statusCaso.asInfo());
        if (bitacoraCaso != null)
            info.setBitacoraCasoId(bitacoraCaso.getId());
        info.setUsuario(usuario);
        return info;
    }
    
}
