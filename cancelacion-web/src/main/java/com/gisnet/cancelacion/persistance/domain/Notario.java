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
public class Notario implements Infoable<NotarioInfo>, Serializable {

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
    
    private Usuario 	usuario;
    private Municipio 	municipio;
    private Entidad 	entidad; //renombrar
    
    private String 		rfc;
    private String 		curp;
    private boolean 	habilitado;
    private String 		convenio;
    private String 		entidad2;
    
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
    public String getCodigoNotario() {
        return codigoNotario;
    }

    public void setCodigoNotario(String codigoNotario) {
        this.codigoNotario = codigoNotario;
    }

    @Column(name = "NOTARIA_NUMERO")
    public String getNotariaNumero() {
        return notariaNumero;
    }

    public void setNotariaNumero(String notariaNumero) {
        this.notariaNumero = notariaNumero;
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

    @Column(name = "RFC")
    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    @Column(name = "CURP")
    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    @Column(name = "HABILITADO")
    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    @Column(name = "CONVENIO_INFONAVIT")
    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    @Column(name = "ENTIDAD2")
    public String getEntidad2() {
        return entidad2;
    }

    public void setEntidad2(String entidad2) {
        this.entidad2 = entidad2;
    }
    
    @Override
    public NotarioInfo asInfo() {
        NotarioInfo info = new NotarioInfo();
        info.setId(id);
        info.setNombre(nombre);
        info.setCodigoNotario(codigoNotario);
        info.setNotariaNumero(notariaNumero);
        info.setEmail(email);
        info.setTelefono(telefono);
        info.setCalleNotaria(calleNotaria);
        info.setNumeroCalle(numeroCalle);
        info.setColoniaNotaria(coloniaNotaria);
        info.setCodigoPostalNotaria(codigoPostalNotaria);
        if (usuario != null)
            info.setUsuarioId(usuario.getId());
        if (municipio != null)
            info.setMunicipioId(municipio.getId());
        if (entidad != null)
            info.setEntidadId(entidad.getId());
        info.setRfc(rfc);
        info.setCurp(curp);
        info.setHabilitado(habilitado);
        info.setConvenio(convenio);
        info.setEntidad2(entidad2);
        return info;
    }
    
}

