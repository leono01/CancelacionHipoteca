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

import com.gisnet.cancelacion.events.Info;
import java.util.Date;

/**
 *
 * @author marco-g8
 */
public class BitacoraRPPInfo implements Info {
    
    private long id;
    private Date fechaActualizacion;
    private long statusRPPId;
    private long proyectoRPPId;

    public BitacoraRPPInfo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public long getStatusRPPId() {
        return statusRPPId;
    }

    public void setStatusRPPId(long statusRPPId) {
        this.statusRPPId = statusRPPId;
    }

    public long getProyectoRPPId() {
        return proyectoRPPId;
    }

    public void setProyectoRPPId(long proyectoRPPId) {
        this.proyectoRPPId = proyectoRPPId;
    }
    
    
    
}
