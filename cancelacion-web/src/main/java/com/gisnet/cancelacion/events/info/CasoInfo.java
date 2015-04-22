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
public class CasoInfo implements Info {
    
    private long id;
    private String numeroCredito;
    private String numeroCaso;
    private Date fechaCreacion;
    private Date fechaCierre;
    private String nombreAcreditado;
    
    private long notarioId;
    private long proyectoCancelacionId;
    private long cartaCancelacionId;
    private long proyectoRPPId;
    private long statusCasoId;

    public CasoInfo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumeroCredito() {
        return numeroCredito;
    }

    public void setNumeroCredito(String numeroCredito) {
        this.numeroCredito = numeroCredito;
    }

    public String getNumeroCaso() {
        return numeroCaso;
    }

    public void setNumeroCaso(String numeroCaso) {
        this.numeroCaso = numeroCaso;
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

    public String getNombreAcreditado() {
        return nombreAcreditado;
    }

    public void setNombreAcreditado(String nombreAcreditado) {
        this.nombreAcreditado = nombreAcreditado;
    }

    public long getNotarioId() {
        return notarioId;
    }

    public void setNotarioId(long notarioId) {
        this.notarioId = notarioId;
    }

    public long getProyectoCancelacionId() {
        return proyectoCancelacionId;
    }

    public void setProyectoCancelacionId(long proyectoCancelacionId) {
        this.proyectoCancelacionId = proyectoCancelacionId;
    }

    public long getCartaCancelacionId() {
        return cartaCancelacionId;
    }

    public void setCartaCancelacionId(long cartaCancelacionId) {
        this.cartaCancelacionId = cartaCancelacionId;
    }

    public long getProyectoRPPId() {
        return proyectoRPPId;
    }

    public void setProyectoRPPId(long proyectoRPPId) {
        this.proyectoRPPId = proyectoRPPId;
    }

    public long getStatusCasoId() {
        return statusCasoId;
    }

    public void setStatusCasoId(long statusCasoId) {
        this.statusCasoId = statusCasoId;
    }
    
    
    
}
