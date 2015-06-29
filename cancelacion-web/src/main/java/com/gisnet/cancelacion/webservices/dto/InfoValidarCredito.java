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

package com.gisnet.cancelacion.webservices.dto;

public class InfoValidarCredito {
	
	String     	numeroDeCredito;
    String     	numeroDeCaso;
    int     	codigo;
    String  	descripcion; 
    
    public String getNumeroDeCredito() {
        return numeroDeCredito;
    }

    public void setNumeroDeCredito(String numeroDeCredito) {
        this.numeroDeCredito = numeroDeCredito;
    }

    public String getNumeroDeCaso() {
        return numeroDeCaso;
    }

    public void setNumeroDeCaso(String numeroDeCaso) {
        this.numeroDeCaso = numeroDeCaso;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
