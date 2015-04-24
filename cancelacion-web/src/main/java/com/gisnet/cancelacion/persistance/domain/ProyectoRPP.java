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

import com.gisnet.cancelacion.events.info.ProyectoRPPInfo;
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
@Table(name = "C_PROYECTO_RPP")
public class ProyectoRPP implements Infoable<ProyectoRPPInfo>, Serializable {
    
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
    
    private StatusRPP statusRPP;
    private Entidad entidad;
    private Usuario usuario;

    public ProyectoRPP() {
    }

    @Id
    @Column(name = "PROYECTO_RPP_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "FOLIO_REAL_ELECTRONICO")
    public byte[] getFolioRealElectronico() {
        return folioRealElectronico;
    }

    public void setFolioRealElectronico(byte[] folioRealElectronico) {
        this.folioRealElectronico = folioRealElectronico;
    }

    @Column(name = "FECHA_INGRESO")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Column(name = "FOLIO_TRAMITE_RPP")
    public String getFolioTramiteRPP() {
        return folioTramiteRPP;
    }

    public void setFolioTramiteRPP(String folioTramiteRPP) {
        this.folioTramiteRPP = folioTramiteRPP;
    }

    @Column(name = "LIBRO")
    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    @Column(name = "TOMO")
    public String getTomo() {
        return tomo;
    }

    public void setTomo(String tomo) {
        this.tomo = tomo;
    }

    @Column(name = "VOLUMEN")
    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }

    @Column(name = "PARTIDA")
    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    @Column(name = "FOJA")
    public String getFoja() {
        return foja;
    }

    public void setFoja(String foja) {
        this.foja = foja;
    }

    @Column(name = "OBSERVACIONES")
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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
    @JoinColumn(name = "ENTIDAD")
    public Entidad getEntidad() {
        return entidad;
    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }

    @ManyToOne
    @JoinColumn(name = "USUARIO")
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public ProyectoRPPInfo asInfo() {
        ProyectoRPPInfo info = new ProyectoRPPInfo();
        info.setId(id);
        info.setFolioRealElectronico(folioRealElectronico);
        info.setFechaIngreso(fechaIngreso);
        info.setFolioTramiteRPP(folioTramiteRPP);
        info.setLibro(libro);
        info.setTomo(tomo);
        info.setVolumen(volumen);
        info.setPartida(partida);
        info.setFoja(foja);
        info.setObservaciones(observaciones);
        if (statusRPP != null)
            info.setStatusRPPId(statusRPP.getId());
        if (entidad != null)
            info.setEntidadId(entidad.getId());
        if (usuario != null)
            info.setUsuarioId(usuario.getId());
        return info;
    }
    
}
