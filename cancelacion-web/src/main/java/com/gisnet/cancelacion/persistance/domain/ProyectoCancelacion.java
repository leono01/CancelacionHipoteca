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
@Table(name = "C_PROYECTO_DE_CANCELACION")
public class ProyectoCancelacion implements Serializable {
    
    private long id;
    private Date fechaCreacion;
    private Date fechaCierre;
    private String motivoRechazo;
    private Date fechaRevision;
    private boolean autorizado;
    private Date fechaAutorizacion;
    
    private Empleado empleado;
    private StatusProyecto statusProyecto;

    public ProyectoCancelacion() {
    }

    @Id
    @Column(name = "PROYECTO_DE_CANCELACION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "FECHA_DE_CREACION")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Column(name = "FECHA_DE_CIERRE")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    @Column(name = "MOTIVO_RECHAZO")
    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    @Column(name = "FECHA_REVISION")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(Date fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    @Column(name = "SE_AUTORIZA")
    public boolean isAutorizado() {
        return autorizado;
    }

    public void setAutorizado(boolean autorizado) {
        this.autorizado = autorizado;
    }

    @Column(name = "FECHA_AUTORIZACION")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(Date fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    @ManyToOne
    @JoinColumn(name = "EMPLEADO")
    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @ManyToOne
    @JoinColumn(name = "STATUS_PROYECTO")
    public StatusProyecto getStatusProyecto() {
        return statusProyecto;
    }

    public void setStatusProyecto(StatusProyecto statusProyecto) {
        this.statusProyecto = statusProyecto;
    }
    
    
}
