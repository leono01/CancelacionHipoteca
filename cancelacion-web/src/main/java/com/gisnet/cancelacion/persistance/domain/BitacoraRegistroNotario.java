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

import com.gisnet.cancelacion.events.Info;
import com.gisnet.cancelacion.events.info.BitacoraRegistroNotarioInfo;
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

/**
 *
 * @author marco-g8
 */
@Entity
@Table(name = "C_BITACORA_REGISTRO_NOTARIO")
public class BitacoraRegistroNotario implements Infoable<BitacoraRegistroNotarioInfo>, Serializable {
    
    private long id;
    private Date fechaActualizacion;
    private Notario notario;
    private StatusNotario statusNotario;
    private String usuario;

    public BitacoraRegistroNotario() {
    }

    @Id
    @Column(name = "BITACORA_REGISTRO_NOTARIO_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @ManyToOne
    @JoinColumn(name = "NOTARIO")
    public Notario getNotario() {
        return notario;
    }

    public void setNotario(Notario notario) {
        this.notario = notario;
    }

    @ManyToOne
    @JoinColumn(name = "STATUS_NOTARIO")
    public StatusNotario getStatusNotario() {
        return statusNotario;
    }

    public void setStatusNotario(StatusNotario statusNotario) {
        this.statusNotario = statusNotario;
    }

    @Column(name = "USUARIO")
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public BitacoraRegistroNotarioInfo asInfo() {
        BitacoraRegistroNotarioInfo info = new BitacoraRegistroNotarioInfo();
        info.setId(id);
        info.setFechaActualizacion(fechaActualizacion);
        if (notario != null) {
            info.setNotarioId(notario.getId());
        }
        info.setStatusNotario(statusNotario.asInfo());
        info.setUsuario(usuario);
        return info;
    }
    
    
    
}
