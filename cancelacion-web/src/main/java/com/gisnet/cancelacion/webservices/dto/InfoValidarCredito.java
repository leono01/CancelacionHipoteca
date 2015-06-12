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
