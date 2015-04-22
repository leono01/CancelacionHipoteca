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
@Table(name = "C_BITACORA_RPP")
public class BitacoraRPP implements Serializable {
    
    private long id;
    private Date fechaActualizacion;
    private StatusRPP statusRPP;
    private ProyectoRPP proyectoRPP;

    public BitacoraRPP() {
    }

    @Id
    @Column(name = "BITACORA_RPP_ID")
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
    @JoinColumn(name = "STATUS_RPP")
    public StatusRPP getStatusRPP() {
        return statusRPP;
    }

    public void setStatusRPP(StatusRPP statusRPP) {
        this.statusRPP = statusRPP;
    }

    @ManyToOne
    @JoinColumn(name = "PROYECTO_RPP")
    public ProyectoRPP getProyectoRPP() {
        return proyectoRPP;
    }

    public void setProyectoRPP(ProyectoRPP proyectoRPP) {
        this.proyectoRPP = proyectoRPP;
    }
    
    
    
}
