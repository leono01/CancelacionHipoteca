package com.gisnet.cancelacion.webservices;

import com.gisnet.cancelacion.core.services.CasoService;
import com.gisnet.cancelacion.core.services.CartaCancelacionService;
import com.gisnet.cancelacion.core.services.StatusCasoService;
import com.gisnet.cancelacion.events.FindByRequest;
import com.gisnet.cancelacion.events.FindResponse;
import com.gisnet.cancelacion.events.SaveRequest;
import com.gisnet.cancelacion.events.SaveResponse;
import com.gisnet.cancelacion.events.UpdateRequest;
import com.gisnet.cancelacion.events.info.CasoInfo;
import com.gisnet.cancelacion.events.info.CartaCancelacionInfo;
import com.gisnet.cancelacion.events.info.StatusCasoInfo;
import com.gisnet.cancelacion.webservices.dto.InfoDeActualizacion;
import com.gisnet.cancelacion.webservices.dto.InfoDeConsulta;
import com.gisnet.cancelacion.webservices.dto.StatusCarta;
import com.gisnet.cancelacion.webservices.dto.StatusCaso;
import com.gisnet.cancelacion.webservices.dto.StatusOperacion;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import javax.jws.WebService;
import javax.jws.WebParam;


@WebService (targetNamespace="http://webservices.cancelacion.gisnet.com/", serviceName="RegistraActualizaYConsultaCasoService", portName="RegistraActualizaYConsultaCasoPort", wsdlLocation="WEB-INF/wsdl/RegistraActualizaYConsultaCasoService.wsdl")
public class RegistraActualizaYConsultaCasoDelegate{

    com.gisnet.cancelacion.webservices.RegistraActualizaYConsultaCaso _registraActualizaYConsultaCaso = null;

    public StatusOperacion registraCaso (@WebParam(name="numeroDeCredito") String numeroDeCredito, @WebParam(name="numeroDeCaso") String numeroDeCaso, @WebParam(name="nombreAcreditado") String nombreAcreditado, @WebParam(name="entidad") String entidad, @WebParam(name="procedeCredito") String procedeCredito, @WebParam(name="descripcionCredito") String descripcionCredito, @WebParam(name="saldoCredito") Double saldoCredito, @WebParam(name="fechaLiquidacionCredito") String fechaLiquidacionCredito, @WebParam(name="saldoCreditoVSM") Double saldoCreditoVSM) {
        return _registraActualizaYConsultaCaso.registraCaso(numeroDeCredito,numeroDeCaso,nombreAcreditado,entidad,procedeCredito,descripcionCredito,saldoCredito,fechaLiquidacionCredito,saldoCreditoVSM);
    }

    public InfoDeActualizacion actualizaCaso (@WebParam(name="numeroDeCredito") String numeroDeCredito, @WebParam(name="numeroDeCaso") String numeroDeCaso, @WebParam(name="fecha") Date fecha, @WebParam(name="status") int status, @WebParam(name="cartaDeCancelacionPdf") byte[] cartaDeCancelacionPdf, @WebParam(name="fechaEmisionCarta") Date fechaEmisionCarta, @WebParam(name="numeroDeFolio") String numeroDeFolio, @WebParam(name="md5") String md5) {
        return _registraActualizaYConsultaCaso.actualizaCaso(numeroDeCredito,numeroDeCaso,fecha,status,cartaDeCancelacionPdf,fechaEmisionCarta,numeroDeFolio,md5);
    }

    public InfoDeConsulta consultaCaso (@WebParam(name="numeroDeCaso") String numeroDeCaso) {
        return _registraActualizaYConsultaCaso.consultaCaso(numeroDeCaso);
    }

    public StatusCaso estadoDelCaso (@WebParam(name="numeroDeCaso") int numeroDeCaso) {
        return _registraActualizaYConsultaCaso.estadoDelCaso(numeroDeCaso);
    }

    public StatusCarta registrarCarta (@WebParam(name="numeroDeCaso") int numeroDeCaso, @WebParam(name="codigoCarta") String codigoCarta, @WebParam(name="base64") byte[] base64, @WebParam(name="md5") String md5, @WebParam(name="folio") String folio, @WebParam(name="entidad") String entidad, @WebParam(name="fechaEmision") Date fechaEmision, @WebParam(name="nombreAcreditado") String nombreAcreditado, @WebParam(name="nombreNotario") String nombreNotario, @WebParam(name="notaria") String notaria, @WebParam(name="direccion") String direccion, @WebParam(name="folioEscritura") String folioEscritura, @WebParam(name="fojaEscritura") String fojaEscritura, @WebParam(name="libroEscritura") String libroEscritura) {
        return _registraActualizaYConsultaCaso.registrarCarta(numeroDeCaso,codigoCarta,base64,md5,folio,entidad,fechaEmision,nombreAcreditado,nombreNotario,notaria,direccion,folioEscritura,fojaEscritura,libroEscritura);
    }

    public StatusCarta actualizarCarta (@WebParam(name="numeroDeCaso") int numeroDeCaso, @WebParam(name="codigoCarta") String codigoCarta, @WebParam(name="base64") byte[] base64, @WebParam(name="md5") String md5, @WebParam(name="folio") String folio, @WebParam(name="entidad") String entidad, @WebParam(name="fechaEmision") Date fechaEmision, @WebParam(name="nombreAcreditado") String nombreAcreditado, @WebParam(name="nombreNotario") String nombreNotario, @WebParam(name="notaria") String notaria, @WebParam(name="direccion") String direccion, @WebParam(name="folioEscritura") String folioEscritura, @WebParam(name="fojaEscritura") String fojaEscritura, @WebParam(name="libroEscritura") String libroEscritura) {
        return _registraActualizaYConsultaCaso.actualizarCarta(numeroDeCaso,codigoCarta,base64,md5,folio,entidad,fechaEmision,nombreAcreditado,nombreNotario,notaria,direccion,folioEscritura,fojaEscritura,libroEscritura);
    }

    public CartaCancelacionInfo consultaCartaCancelacion (@WebParam(name="numeroDeCaso") int numeroDeCaso) {
        return _registraActualizaYConsultaCaso.consultaCartaCancelacion(numeroDeCaso);
    }

    public RegistraActualizaYConsultaCasoDelegate() {
        _registraActualizaYConsultaCaso = new com.gisnet.cancelacion.webservices.RegistraActualizaYConsultaCaso(); }

}