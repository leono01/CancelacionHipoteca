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

import com.gisnet.cancelacion.events.info.CasoInfo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author marco-g8
 */
@Entity
@Table(name = "C_CASO")
public class Caso implements Infoable<CasoInfo>, Serializable {
    
    private long 				id;
    private String 				numeroCredito;
    private String 				numeroCaso;
    private	Date 				fechaCreacion;
    private Date 				fechaCierre;
    private String 				nombreAcreditado;
    
    private Notario 			notario;
    private ProyectoCancelacion proyectoCancelacion;
    private CartaCancelacion 	cartaCancelacion;
    private ProyectoRPP 		proyectoRPP;
    private StatusCaso 			statusCaso;
    
    private Date 				fechaActualizacion;
    private String 				procedeCredito;
    private String 				entidad;
    
    private String 				descripcionCredito;
	private Double				saldoCredito;
	private String				fechaLiquidacionCredito;
    
	private String 				saldoCreditoVSM;

    public Caso() {
    }

    @Id
    @Column(name = "CASO_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "NUMERO_DE_CREDITO")
    public String getNumeroCredito() {
        return numeroCredito;
    }

    public void setNumeroCredito(String numeroCredito) {
        this.numeroCredito = numeroCredito;
    }

    @Column(name = "NUMERO_DE_CASO")
    public String getNumeroCaso() {
        return numeroCaso;
    }

    public void setNumeroCaso(String numeroCaso) {
        this.numeroCaso = numeroCaso;
    }

    @Column(name = "FECHA_DE_CREACION")
    @Temporal(TemporalType.DATE)
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Column(name = "FECHA_DE_CIERRE")
    @Temporal(TemporalType.DATE)
    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    @Column(name = "NOMBRE_DE_ACREDITADO")
    public String getNombreAcreditado() {
        return nombreAcreditado;
    }

    public void setNombreAcreditado(String nombreAcreditado) {
        this.nombreAcreditado = nombreAcreditado;
    }

    @ManyToOne
    @JoinColumn(name = "NOTARIO")
    public Notario getNotario() {
        return notario;
    }

    public void setNotario(Notario notario) {
        this.notario = notario;
    }

    @ManyToOne
    @JoinColumn(name = "PROYECTO_DE_CANCELACION")
    public ProyectoCancelacion getProyectoCancelacion() {
        return proyectoCancelacion;
    }

    public void setProyectoCancelacion(ProyectoCancelacion proyectoCancelacion) {
        this.proyectoCancelacion = proyectoCancelacion;
    }

    @ManyToOne
    @JoinColumn(name = "CARTA_DE_CANCELACION")
    public CartaCancelacion getCartaCancelacion() {
        return cartaCancelacion;
    }

    public void setCartaCancelacion(CartaCancelacion cartaCancelacion) {
        this.cartaCancelacion = cartaCancelacion;
    }

    @ManyToOne
    @JoinColumn(name = "PROYECTO_RPP")
    public ProyectoRPP getProyectoRPP() {
        return proyectoRPP;
    }

    public void setProyectoRPP(ProyectoRPP proyectoRPP) {
        this.proyectoRPP = proyectoRPP;
    }

    @ManyToOne
    @JoinColumn(name = "STATUS_DEL_CASO")
    public StatusCaso getStatusCaso() {
        return statusCaso;
    }

    public void setStatusCaso(StatusCaso statusCaso) {
        this.statusCaso = statusCaso;
    }

    @Column(name = "FECHA_DE_ACTUALIZACION")
    @Temporal(TemporalType.DATE)
    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @Column(name = "PROCEDE_CREDITO")
    public String getProcedeCredito() {
        return procedeCredito;
    }

    public void setProcedeCredito(String procedeCredito) {
        this.procedeCredito = procedeCredito;
    }

    @Column(name = "ENTIDAD")
    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }
    
    @Column(name = "DESCRIPCION_CREDITO")
    public String getDescripcionCredito() {
		return descripcionCredito;
	}

	public void setDescripcionCredito(String descripcionCredito) {
		this.descripcionCredito = descripcionCredito;
	}

	@Column(name = "SALDO_CREDITO")
	public Double getSaldoCredito() {
		return saldoCredito;
	}

	public void setSaldoCredito(Double saldoCredito) {
		this.saldoCredito = saldoCredito;
	}

	@Column(name = "FECHA_LIQUIDACION_CREDITO")
	public String getFechaLiquidacionCredito() {
		return fechaLiquidacionCredito;
	}

	public void setFechaLiquidacionCredito(String fechaLiquidacionCredito) {
		this.fechaLiquidacionCredito = fechaLiquidacionCredito;
	}
	
    @Column(name = "SALDO_CREDITO_VSM")
	public String getSaldoCreditoVSM() {
		return saldoCreditoVSM;
	}

	public void setSaldoCreditoVSM(String saldoCreditoVSM) {
		this.saldoCreditoVSM = saldoCreditoVSM;
	}
    
    @Override
    public CasoInfo asInfo() {
        CasoInfo info = new CasoInfo();
        info.setId(id);
        info.setNumeroCredito(numeroCredito);
        info.setNumeroCaso(numeroCaso);
        info.setFechaCreacion(fechaCreacion);
        info.setFechaCierre(fechaCierre);
        info.setNombreAcreditado(nombreAcreditado);
        if (notario != null)
            info.setNotarioId(notario.getId());
        if (proyectoCancelacion != null)
            info.setProyectoCancelacionId(proyectoCancelacion.getId());
        if (cartaCancelacion != null)
            info.setCartaCancelacionId(cartaCancelacion.getId());
        if (proyectoRPP != null)
            info.setProyectoRPPId(proyectoRPP.getId());
        if (statusCaso != null)
            info.setStatusCaso(statusCaso.asInfo());
        info.setFechaActualizacion(fechaActualizacion);
        info.setProcedeCredito(procedeCredito);
        info.setEntidad(entidad);
        info.setDescripcionCredito(descripcionCredito);
        info.setSaldoCredito(saldoCredito);
        info.setFechaLiquidacionCredito(fechaLiquidacionCredito);
        info.setSaldoCreditoVSM(saldoCreditoVSM);
        return info;
    }

}
