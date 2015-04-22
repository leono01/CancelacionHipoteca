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

import com.gisnet.cancelacion.events.info.NotarioInfo;
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
@Table(name = "C_NOTARIO")
public class Notario implements Serializable {

    private long id;
    private String nombre;
    private int codigo;
    private String notariaNumero;
    private boolean convenioInfonavit;
    private String email;
    private String telefono;
    private String calleNotaria;
    private String numeroCalle;
    private String coloniaNotaria;
    private String codigoPostalNotaria;
    
    private Usuario usuario;
    private Municipio municipio;
    private Entidad entidad;
    
    public Notario() {
    }

    @Id
    @Column(name = "NOTARIO_ID")
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
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @ManyToOne
    @JoinColumn(name = "USUARIO")
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @ManyToOne
    @JoinColumn(name = "MUNICIPIO")
    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
    
    @ManyToOne
    @JoinColumn(name = "ENTIDAD")
    public Entidad getEntidad() {
        return entidad;
    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }
    
    public NotarioInfo asInfo() {
        NotarioInfo info = new NotarioInfo();
        info.setId(this.id);
        info.setNombre(this.nombre);
        info.setCodigo(this.codigo);
        info.setNotariaNumero(this.notariaNumero);
        info.setEmail(this.email);
        info.setTelefono(this.telefono);
        info.setCalleNotaria(this.calleNotaria);
        info.setNumeroCalle(this.numeroCalle);
        info.setColoniaNotaria(this.coloniaNotaria);
        info.setCodigoPostalNotaria(this.codigoPostalNotaria);
        
        info.setUsuarioId(0l);
        info.setMunicipioId(0l);
        info.setEntidadId(0l);
        
        return info;
    }
    
}

