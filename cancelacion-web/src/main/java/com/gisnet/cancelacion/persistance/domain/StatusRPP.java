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

import com.gisnet.cancelacion.events.info.StatusRPPInfo;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author marco-g8
 */
@Entity
@Table(name = "C_STATUS_RPP")
public class StatusRPP implements Infoable<StatusRPPInfo>, Serializable {
    
    private long id;
    private int clave;
    private String nombre;
    private String descripcion;

    public StatusRPP() {
    }

    @Id
    @Column(name = "STATUS_RPP_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "CLAVE")
    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    @Column(name = "NOMBRE_STATUS_CASO")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "DESCRIPCION_STATUS")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public StatusRPPInfo asInfo() {
        StatusRPPInfo info = new StatusRPPInfo();
        info.setId(id);
        info.setClave(clave);
        info.setNombre(nombre);
        info.setDescripcion(descripcion);
        return info;
    }
    
}
