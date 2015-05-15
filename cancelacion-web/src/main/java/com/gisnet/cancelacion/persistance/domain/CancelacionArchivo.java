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

import com.gisnet.cancelacion.events.info.CancelacionArchivoInfo;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author marco-g8
 */
@Entity
@Table(name = "C_CANCELACION_ARCHIVO")
public class CancelacionArchivo implements Infoable<CancelacionArchivoInfo>, Serializable {
    
    private long id;
    private byte[] archivo;
    private String nombre;
    private ProyectoCancelacion proyectoCancelacion;
    
    private String mimetype;

    public CancelacionArchivo() {
    }

    @Id
    @Column(name = "ARCHIVO_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "ARCHIVO", columnDefinition = "VARBINARY(MAX)")
    @Lob
    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    @Column(name = "NOMBRE")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @ManyToOne
    @JoinColumn(name = "PROYECTO_DE_CANCELACION")
    public ProyectoCancelacion getProyectoCancelacion() {
        return proyectoCancelacion;
    }

    public void setProyectoCancelacion(ProyectoCancelacion proyectoCancelacion) {
        this.proyectoCancelacion = proyectoCancelacion;
    }

    @Column(name = "MIMETYPE")
    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }
    
    @Override
    public CancelacionArchivoInfo asInfo() {
        CancelacionArchivoInfo info = new CancelacionArchivoInfo();
        info.setId(id);
        info.setArchivo(archivo);
        info.setNombre(nombre);
        if (proyectoCancelacion != null)
            info.setProyectoCancelacionId(proyectoCancelacion.getId());
        info.setMimetype(mimetype);
        return info;
    }
    
}
