package com.gisnet.cancelacion.webservices;

import javax.jws.WebService;
import javax.jws.WebParam;


@WebService (targetNamespace="http://webservices.cancelacion.gisnet.com/", serviceName="ValidarCreditoService", portName="ValidarCreditoPort", wsdlLocation="WEB-INF/wsdl/ValidarCreditoService.wsdl")
public class ValidarCreditoDelegate{

    com.gisnet.cancelacion.webservices.ValidarCredito _validarCredito = null;

    public String validaCredito (@WebParam(name="numeroDeCaso") int numeroDeCaso) {
        return _validarCredito.validaCredito(numeroDeCaso);
    }

    public ValidarCreditoDelegate() {
        _validarCredito = new com.gisnet.cancelacion.webservices.ValidarCredito(); }

}