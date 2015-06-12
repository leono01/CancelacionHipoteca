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
    
    String 	numeroDeCredito;
    String 	numeroDeCaso;
    Date 	fecha;
    int 	status;
    byte[] 	cartaDeCancelacion;
    Date 	fechaEmisionCarta;
    String 	numeroDeFolio;
    String	nombreAcreditado;
    String 	entidad;
    int		infoConsulta;
    String 	descripcionConsulta;
    String	procedeCredito;
    String 	descripcionCredito;
	Double	saldoCredito;
	String	fechaLiquidacionCredito;
    
    public int getInfoConsulta() {
		return infoConsulta;
	}

	public void setInfoConsulta(int infoConsulta) {
		this.infoConsulta = infoConsulta;
	}

	public String getDescripcionConsulta() {
		return descripcionConsulta;
	}

	public void setDescripcionConsulta(String descripcionConsulta) {
		this.descripcionConsulta = descripcionConsulta;
	}

    public String getNumeroDeCredito() {
        return numeroDeCredito;
    }

    public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFechaEmisionCarta() {
		return fechaEmisionCarta;
	}

	public void setFechaEmisionCarta(Date fechaEmisionCarta) {
		this.fechaEmisionCarta = fechaEmisionCarta;
	}

	public String getNombreAcreditado() {
		return nombreAcreditado;
	}

	public void setNombreAcreditado(String nombreAcreditado) {
		this.nombreAcreditado = nombreAcreditado;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
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

    public byte[] getCartaDeCancelacion() {
        return cartaDeCancelacion;
    }

    public void setCartaDeCancelacion(byte[] cartaDeCancelacion) {
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

	public String getProcedeCredito() {
		return procedeCredito;
	}

	public void setProcedeCredito(String procedeCredito) {
		this.procedeCredito = procedeCredito;
	}

	public String getDescripcionCredito() {
		return descripcionCredito;
	}

	public void setDescripcionCredito(String descripcionCredito) {
		this.descripcionCredito = descripcionCredito;
	}

	public Double getSaldoCredito() {
		return saldoCredito;
	}

	public void setSaldoCredito(Double saldoCredito) {
		this.saldoCredito = saldoCredito;
	}

	public String getFechaLiquidacionCredito() {
		return fechaLiquidacionCredito;
	}

	public void setFechaLiquidacionCredito(String fechaLiquidacionCredito) {
		this.fechaLiquidacionCredito = fechaLiquidacionCredito;
	}
    
}

