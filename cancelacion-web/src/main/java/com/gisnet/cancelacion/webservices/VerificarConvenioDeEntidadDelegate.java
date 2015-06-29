package com.gisnet.cancelacion.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import com.gisnet.cancelacion.core.services.EntidadService;
import com.gisnet.cancelacion.events.FindByRequest;
import com.gisnet.cancelacion.events.FindResponse;
import com.gisnet.cancelacion.events.info.EntidadInfo;
import com.gisnet.cancelacion.webservices.dto.InfoValidarConvenio;
import javax.jws.WebService;
import javax.jws.WebParam;


@WebService (targetNamespace="http://webservices.cancelacion.gisnet.com/", serviceName="VerificarConvenioDeEntidadService", portName="VerificarConvenioDeEntidadPort", wsdlLocation="WEB-INF/wsdl/VerificarConvenioDeEntidadService.wsdl")
public class VerificarConvenioDeEntidadDelegate{

    com.gisnet.cancelacion.webservices.VerificarConvenioDeEntidad _verificarConvenioDeEntidad = null;

    public InfoValidarConvenio verificarConvenioDeEntidad (@WebParam(name="entidad") String entidad) {
        return _verificarConvenioDeEntidad.verificarConvenioDeEntidad(entidad);
    }

    public VerificarConvenioDeEntidadDelegate() {
        _verificarConvenioDeEntidad = new com.gisnet.cancelacion.webservices.VerificarConvenioDeEntidad(); }

}