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
public class ProyectoCancelacionInfo implements Info {
    
    private long id;
    private Date fechaCreacion;
    private Date fechaCierre;
    private String motivoRechazo;
    private Date fechaRevision;
    private boolean autorizado;
    private Date fechaAutorizacion;
    
    private long empleadoId;
    private StatusProyectoInfo statusProyecto;
    private Date fechaAsignadaParaFirma;
    private Date fechaFirmaNotario;

    public ProyectoCancelacionInfo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    public Date getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(Date fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    public boolean isAutorizado() {
        return autorizado;
    }

    public void setAutorizado(boolean autorizado) {
        this.autorizado = autorizado;
    }

    public Date getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(Date fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public long getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(long empleadoId) {
        this.empleadoId = empleadoId;
    }

    public StatusProyectoInfo getStatusProyecto() {
        return statusProyecto;
    }

    public void setStatusProyecto(StatusProyectoInfo statusProyecto) {
        this.statusProyecto = statusProyecto;
    }

    public Date getFechaAsignadaParaFirma() {
        return fechaAsignadaParaFirma;
    }

    public void setFechaAsignadaParaFirma(Date fechaAsignadaParaFirma) {
        this.fechaAsignadaParaFirma = fechaAsignadaParaFirma;
    }

    public Date getFechaFirmaNotario() {
        return fechaFirmaNotario;
    }

    public void setFechaFirmaNotario(Date fechaFirmaNotario) {
        this.fechaFirmaNotario = fechaFirmaNotario;
    }

}
