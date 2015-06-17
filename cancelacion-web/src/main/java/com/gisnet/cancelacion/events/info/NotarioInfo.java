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

/**
 *
 * @author marco-g8
 */
public class NotarioInfo implements Info {
    
    private long 		id;
    private String 		nombre;
    private String 		codigoNotario;
    private String 		notariaNumero;
    private String 		email;
    private String 		telefono;
    private String 		calleNotaria;
    private String 		numeroCalle;
    private String 		coloniaNotaria;
    private String 		codigoPostalNotaria;
    
    private long 		usuarioId;
    private long 		municipioId;
    private long 		entidadId;
    
    private String 		rfc;
    private String 		curp;
    private boolean 	habilitado;
    private String 		convenio;
    private String 		entidad2;

    public NotarioInfo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoNotario() {
        return codigoNotario;
    }

    public void setCodigoNotario(String codigoNotario) {
        this.codigoNotario = codigoNotario;
    }

    public String getNotariaNumero() {
        return notariaNumero;
    }

    public void setNotariaNumero(String notariaNumero) {
        this.notariaNumero = notariaNumero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCalleNotaria() {
        return calleNotaria;
    }

    public void setCalleNotaria(String calleNotaria) {
        this.calleNotaria = calleNotaria;
    }

    public String getNumeroCalle() {
        return numeroCalle;
    }

    public void setNumeroCalle(String numeroCalle) {
        this.numeroCalle = numeroCalle;
    }

    public String getColoniaNotaria() {
        return coloniaNotaria;
    }

    public void setColoniaNotaria(String coloniaNotaria) {
        this.coloniaNotaria = coloniaNotaria;
    }

    public String getCodigoPostalNotaria() {
        return codigoPostalNotaria;
    }

    public void setCodigoPostalNotaria(String codigoPostalNotaria) {
        this.codigoPostalNotaria = codigoPostalNotaria;
    }

    public long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public long getMunicipioId() {
        return municipioId;
    }

    public void setMunicipioId(long municipioId) {
        this.municipioId = municipioId;
    }

    public long getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(long entidadId) {
        this.entidadId = entidadId;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public String getEntidad2() {
        return entidad2;
    }

    public void setEntidad2(String entidad2) {
        this.entidad2 = entidad2;
    }


}

