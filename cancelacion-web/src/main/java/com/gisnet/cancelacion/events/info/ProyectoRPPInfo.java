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
public class ProyectoRPPInfo implements Info {
    
    private long id;
    private byte[] folioRealElectronico;
    private Date fechaIngreso;
    private String folioTramiteRPP;
    private String libro;
    private String tomo;
    private String volumen;
    private String partida;
    private String foja;
    private String observaciones;
    
    private long statusRPPId;
    private long entidadId;
    private long usuarioId;

    public ProyectoRPPInfo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getFolioRealElectronico() {
        return folioRealElectronico;
    }

    public void setFolioRealElectronico(byte[] folioRealElectronico) {
        this.folioRealElectronico = folioRealElectronico;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFolioTramiteRPP() {
        return folioTramiteRPP;
    }

    public void setFolioTramiteRPP(String folioTramiteRPP) {
        this.folioTramiteRPP = folioTramiteRPP;
    }

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    public String getTomo() {
        return tomo;
    }

    public void setTomo(String tomo) {
        this.tomo = tomo;
    }

    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public String getFoja() {
        return foja;
    }

    public void setFoja(String foja) {
        this.foja = foja;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public long getStatusRPPId() {
        return statusRPPId;
    }

    public void setStatusRPPId(long statusRPPId) {
        this.statusRPPId = statusRPPId;
    }

    public long getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(long entidadId) {
        this.entidadId = entidadId;
    }

    public long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }
    
    
    
}
