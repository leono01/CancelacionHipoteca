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

import com.gisnet.cancelacion.events.info.MunicipioInfo;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author marco-g8
 */
@Entity
@Table(name = "C_MUNICIPIO")
public class Municipio implements Infoable<MunicipioInfo>, Serializable {
    
    private long id;
    private String nombre;
    private Entidad entidad;

    public Municipio() {
    }

    @Id
    @Column(name = "MUNICIPIO_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "NOMBRE")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @ManyToOne
    @JoinColumn(name = "ENTIDAD")
    public Entidad getEntidad() {
        return entidad;
    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }
    
    @Override
    public MunicipioInfo asInfo() {
        MunicipioInfo info = new MunicipioInfo();
        info.setId(id);
        info.setNombre(nombre);
        if (entidad != null) {
            info.setEntidadId(entidad.getId());
            info.setEntidadNombre(entidad.getNombre());
        }
        return info;
    }
    
}
