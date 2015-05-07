package com.gisnet.cancelacion.webservices;

import com.gisnet.cancelacion.webservices.dto.CNotario;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebParam;


@WebService (targetNamespace="http://webservices.cancelacion.gisnet.com/", serviceName="ConsultarListaDeNotariosService", portName="ConsultarListaDeNotariosPort", wsdlLocation="WEB-INF/wsdl/ConsultarListaDeNotariosService.wsdl")
public class ConsultarListaDeNotariosDelegate{

    com.gisnet.cancelacion.webservices.ConsultarListaDeNotarios _consultarListaDeNotarios = null;

    public List<CNotario> consultarListaDeNotarios (@WebParam(name="numeroDeCredito") int numeroDeCredito, @WebParam(name="entidad") String entidad) {
        return _consultarListaDeNotarios.consultarListaDeNotarios(numeroDeCredito,entidad);
    }

    public String entidad (@WebParam(name="numeroDeCredito") int numeroDeCredito) {
        return _consultarListaDeNotarios.entidad(numeroDeCredito);
    }

    public List<CNotario> consultarListaDeNotariosAdai (@WebParam(name="entidad") String entidad) {
        return _consultarListaDeNotarios.consultarListaDeNotariosAdai(entidad);
    }

    public ConsultarListaDeNotariosDelegate() {
        _consultarListaDeNotarios = new com.gisnet.cancelacion.webservices.ConsultarListaDeNotarios(); }

}