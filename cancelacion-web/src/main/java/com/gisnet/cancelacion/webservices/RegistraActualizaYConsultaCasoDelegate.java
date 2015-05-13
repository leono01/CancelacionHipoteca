package com.gisnet.cancelacion.webservices;

import com.gisnet.cancelacion.webservices.dto.CCaso;
import com.gisnet.cancelacion.webservices.dto.InfoDeActualizacion;
import com.gisnet.cancelacion.webservices.dto.InfoDeConsulta;
import com.gisnet.cancelacion.webservices.dto.StatusCaso;
import com.gisnet.cancelacion.webservices.dto.StatusOperacion;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebParam;


@WebService (targetNamespace="http://webservices.cancelacion.gisnet.com/", serviceName="RegistraActualizaYConsultaCasoService", portName="RegistraActualizaYConsultaCasoPort", wsdlLocation="WEB-INF/wsdl/RegistraActualizaYConsultaCasoService.wsdl")
public class RegistraActualizaYConsultaCasoDelegate{

    com.gisnet.cancelacion.webservices.RegistraActualizaYConsultaCaso _registraActualizaYConsultaCaso = null;

    public StatusOperacion registraCaso (@WebParam(name="numeroDeCredito") int numeroDeCredito, @WebParam(name="numeroDeCaso") int numeroDeCaso, @WebParam(name="nombreAcreditado") String nombreAcreditado) {
        return _registraActualizaYConsultaCaso.registraCaso(numeroDeCredito,numeroDeCaso,nombreAcreditado);
    }

    public InfoDeActualizacion actualizaCaso (@WebParam(name="numeroDeCredito") int numeroDeCredito, @WebParam(name="numeroDeCaso") int numeroDeCaso, @WebParam(name="fecha") Date fecha, @WebParam(name="status") int status, @WebParam(name="cartaDeCancelacionPdf") String cartaDeCancelacionPdf, @WebParam(name="fechaEmisionCarta") Date fechaEmisionCarta, @WebParam(name="numeroDeFolio") String numeroDeFolio) {
        return _registraActualizaYConsultaCaso.actualizaCaso(numeroDeCredito,numeroDeCaso,fecha,status,cartaDeCancelacionPdf,fechaEmisionCarta,numeroDeFolio);
    }

    public InfoDeConsulta consultaCaso (@WebParam(name="numeroDeCredito") int numeroDeCredito, @WebParam(name="numeroDeCaso") int numeroDeCaso) {
        return _registraActualizaYConsultaCaso.consultaCaso(numeroDeCredito,numeroDeCaso);
    }

    public StatusCaso estadoDelCaso (@WebParam(name="numeroDeCaso") int numeroDeCaso) {
        return _registraActualizaYConsultaCaso.estadoDelCaso(numeroDeCaso);
    }

    public RegistraActualizaYConsultaCasoDelegate() {
        _registraActualizaYConsultaCaso = new com.gisnet.cancelacion.webservices.RegistraActualizaYConsultaCaso(); }

}