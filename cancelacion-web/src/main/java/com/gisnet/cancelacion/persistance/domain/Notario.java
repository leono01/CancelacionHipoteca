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
@Table(name = "C_NOTARIO")
public class Notario implements Serializable {
    
/*
     BIGINT PRIMARY KEY,
     VARCHAR,
     NUMERIC(5),
     VARCHAR,
     NUMERIC(1),
     VARCHAR,
    TELEFONO VARCHAR,
     VARCHAR,
     VARCHAR,
     NUMERIC(5),
    USUARIO BIGINT,
    MUNICIPIO BIGINT);
    */
    
    
    private long id;
    private String nombre;
    private int codigo;
    private String notariaNumero;
    private boolean convenioInfonavit;
    private String correoElectronico;
    private String telefono;
    private String calleNotaria;
    private String numeroCalle;
    private String coloniaNotaria;
    private String codigoPostalNotaria;
    
    private String usuario;
    private String municipio;
    
    public Notario() {
    }

    @Id
//    @Column(name = "NOTARIO_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "NOMBRE_NOTARIO")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "CODIGO_NOTARIO")
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Column(name = "NOTARIA_NUMERO")
    public String getNotariaNumero() {
        return notariaNumero;
    }

    public void setNotariaNumero(String notariaNumero) {
        this.notariaNumero = notariaNumero;
    }

    @Column(name = "CONVENIO_INFONAVIT")
    public boolean isConvenioInfonavit() {
        return convenioInfonavit;
    }

    public void setConvenioInfonavit(boolean convenioInfonavit) {
        this.convenioInfonavit = convenioInfonavit;
    }

    @Column(name = "EMAIL")
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @Column(name = "TELEFONO")
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Column(name = "CALLE_NOTARIA")
    public String getCalleNotaria() {
        return calleNotaria;
    }

    public void setCalleNotaria(String calleNotaria) {
        this.calleNotaria = calleNotaria;
    }

    @Column(name = "NUMERO_CALLE")
    public String getNumeroCalle() {
        return numeroCalle;
    }

    public void setNumeroCalle(String numeroCalle) {
        this.numeroCalle = numeroCalle;
    }

    @Column(name = "COLONIA_NOTARIA")
    public String getColoniaNotaria() {
        return coloniaNotaria;
    }

    public void setColoniaNotaria(String coloniaNotaria) {
        this.coloniaNotaria = coloniaNotaria;
    }

    @Column(name = "CODIGO_POSTAL_NOTARIA")
    public String getCodigoPostalNotaria() {
        return codigoPostalNotaria;
    }

    public void setCodigoPostalNotaria(String codigoPostalNotaria) {
        this.codigoPostalNotaria = codigoPostalNotaria;
    }

    @Column(name = "USUARIO")
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Column(name = "MUNICIPIO")
    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    
}

