package com.gisnet.cancelacion.webservices;

public class ValidarCredito {
	
	public String validaCredito(int numeroDeCaso){
		String si = "";
		if(numeroDeCaso > 0 && numeroDeCaso <= 555555555){
			si = "PROCEDENTE";
		}
		else{
			if(numeroDeCaso > 555555555 && numeroDeCaso <= 99999999){
				si = "NO PROCEDENTE";
			}
		}
		return si;
	}
	

}
