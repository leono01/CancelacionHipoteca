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

import com.gisnet.cancelacion.events.info.EmpleadoInfo;
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
@Table(name = "C_EMPLEADO")
public class Empleado implements Infoable<EmpleadoInfo>, Serializable {
    
    private long id;
    private String clave;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String subdireccion;
    private String delegacion;
    private Usuario usuario;

    public Empleado() {
    }

    @Id
    @Column(name = "EMPLEADO_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "CLAVE_EMPLEADO")
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Column(name = "NOMBRE_EMPLEADO")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "APELLIDO_PATERNO_EMPLEADO")
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    @Column(name = "APELLIDO_MATERNO_EMPLEADO")
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    @Column(name = "SUBDIRECCION")
    public String getSubdireccion() {
        return subdireccion;
    }

    public void setSubdireccion(String subdireccion) {
        this.subdireccion = subdireccion;
    }

    @Column(name = "DELEGACION")
    public String getDelegacion() {
        return delegacion;
    }

    public void setDelegacion(String delegacion) {
        this.delegacion = delegacion;
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
    public EmpleadoInfo asInfo() {
        EmpleadoInfo info = new EmpleadoInfo();
        info.setId(id);
        info.setNombre(nombre);
        info.setApellidoPaterno(apellidoPaterno);
        info.setApellidoMaterno(apellidoMaterno);
        info.setSubdireccion(subdireccion);
        info.setDelegacion(delegacion);
        if (usuario != null)
            info.setUsuarioId(usuario.getId());
        return info;
    }
    
}
