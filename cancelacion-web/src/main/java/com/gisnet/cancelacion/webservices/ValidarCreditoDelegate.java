package com.gisnet.cancelacion.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import com.gisnet.cancelacion.core.services.CasoService;
import com.gisnet.cancelacion.events.FindByRequest;
import com.gisnet.cancelacion.events.FindResponse;
import com.gisnet.cancelacion.events.info.CasoInfo;
import com.gisnet.cancelacion.webservices.dto.StatusOperacion;
import javax.jws.WebService;
import javax.jws.WebParam;


@WebService (targetNamespace="http://webservices.cancelacion.gisnet.com/", serviceName="ValidarCreditoService", portName="ValidarCreditoPort", wsdlLocation="WEB-INF/wsdl/ValidarCreditoService.wsdl")
public class ValidarCreditoDelegate{

    com.gisnet.cancelacion.webservices.ValidarCredito _validarCredito = null;

    public StatusOperacion validaCredito (@WebParam(name="numeroDeCredito") String numeroDeCredito) {
        return _validarCredito.validaCredito(numeroDeCredito);
    }

    public ValidarCreditoDelegate() {
        _validarCredito = new com.gisnet.cancelacion.webservices.ValidarCredito(); }

}