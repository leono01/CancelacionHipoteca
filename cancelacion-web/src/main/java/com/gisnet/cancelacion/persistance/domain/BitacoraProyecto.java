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

import com.gisnet.cancelacion.events.info.BitacoraProyectoInfo;
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
@Table(name = "C_BITACORA_PROYECTO")
public class BitacoraProyecto implements Infoable<BitacoraProyectoInfo>, Serializable {
    
    private long id;
    private Date fechaActualizacion;
    private StatusProyecto statusProyecto;
    private ProyectoCancelacion proyectoCancelacion;
    
    private String usuario;

    public BitacoraProyecto() {
    }

    @Id
    @Column(name = "BITACORA_PROYECTO_ID")
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
    @JoinColumn(name = "STATUS_PROYECTO")
    public StatusProyecto getStatusProyecto() {
        return statusProyecto;
    }

    public void setStatusProyecto(StatusProyecto statusProyecto) {
        this.statusProyecto = statusProyecto;
    }

    @ManyToOne
    @JoinColumn(name = "PROYECTO_DE_CANCELACION")
    public ProyectoCancelacion getProyectoCancelacion() {
        return proyectoCancelacion;
    }

    public void setProyectoCancelacion(ProyectoCancelacion proyectoCancelacion) {
        this.proyectoCancelacion = proyectoCancelacion;
    }

    @Column(name = "USUARIO")
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public BitacoraProyectoInfo asInfo() {
        BitacoraProyectoInfo info = new BitacoraProyectoInfo();
        info.setId(this.id);
        info.setFechaActualizacion(fechaActualizacion);
        if (statusProyecto != null)
            info.setStatusProyecto(statusProyecto.asInfo());
        if (proyectoCancelacion != null)
            info.setProyectoCancelacionId(proyectoCancelacion.getId());
        info.setUsuario(this.usuario);
        return info;
    }
    
}
