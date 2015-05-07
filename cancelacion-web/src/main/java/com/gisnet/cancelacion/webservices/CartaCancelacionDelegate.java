package com.gisnet.cancelacion.webservices;

import com.gisnet.cancelacion.webservices.dto.CCarta;
import com.gisnet.cancelacion.webservices.dto.InfoDeActualizacion;
import com.gisnet.cancelacion.webservices.dto.StatusOperacion;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;


@WebService (targetNamespace="http://webservices.cancelacion.gisnet.com/", serviceName="CartaCancelacionService", portName="CartaCancelacionPort", wsdlLocation="WEB-INF/wsdl/CartaCancelacionService.wsdl")
public class CartaCancelacionDelegate{

    com.gisnet.cancelacion.webservices.CartaCancelacion _cartaCancelacion = null;

    public StatusOperacion registraCartaCancelacion (@WebParam(name="codigoCarta") String codigoCarta, @WebParam(name="pdfCarta") File pdfCarta, @WebParam(name="xmlCarta") File xmlCarta) {
        return _cartaCancelacion.registraCartaCancelacion(codigoCarta,pdfCarta,xmlCarta);
    }

    public StatusOperacion actualizaCartaCancelacion (@WebParam(name="codigoCarta") String codigoCarta, @WebParam(name="pdfCarta") File pdfCarta, @WebParam(name="xmlCarta") File xmlCarta) {
        return _cartaCancelacion.actualizaCartaCancelacion(codigoCarta,pdfCarta,xmlCarta);
    }

    public List<CCarta> consultaCartaCancelacion (@WebParam(name="codigoCarta") int codigoCarta) {
        return _cartaCancelacion.consultaCartaCancelacion(codigoCarta);
    }

    public CartaCancelacionDelegate() {
        _cartaCancelacion = new com.gisnet.cancelacion.webservices.CartaCancelacion(); }

}