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

import java.util.Date;

/**
 *
 * @author leonel
 */
public class InfoDeConsulta {
    
    int numeroDeCredito;
    int numeroDeCaso;
    Date fecha;
    int status;
    String cartaDeCancelacion;
    Date fechaEmisionCarta;
    String numeroDeFolio;

    public int getNumeroDeCredito() {
        return numeroDeCredito;
    }

    public void setNumeroDeCredito(int numeroDeCredito) {
        this.numeroDeCredito = numeroDeCredito;
    }

    public int getNumeroDeCaso() {
        return numeroDeCaso;
    }

    public void setNumeroDeCaso(int numeroDeCaso) {
        this.numeroDeCaso = numeroDeCaso;
    }

    public Date getFechaDeActualizacion() {
        return fecha;
    }

    public void setFechaDeActualizacion(Date fechaDeActualizacion) {
        this.fecha = fechaDeActualizacion;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCartaDeCancelacion() {
        return cartaDeCancelacion;
    }

    public void setCartaDeCancelacion(String cartaDeCancelacion) {
        this.cartaDeCancelacion = cartaDeCancelacion;
    }

    public Date getFechaDeEmisionDeCarta() {
        return fechaEmisionCarta;
    }

    public void setFechaDeEmisionDeCarta(Date fechaEmisionCarta) {
        this.fechaEmisionCarta = fechaEmisionCarta;
    }

    public String getNumeroDeFolio() {
        return numeroDeFolio;
    }

    public void setNumeroDeFolio(String numeroDeFolio) {
        this.numeroDeFolio = numeroDeFolio;
    }
    
}

