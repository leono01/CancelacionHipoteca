package com.gisnet.cancelacion.webservices;

import com.gisnet.cancelacion.core.services.NotarioService;
import com.gisnet.cancelacion.events.ListRequest;
import com.gisnet.cancelacion.events.ListResponse;
import com.gisnet.cancelacion.events.SaveRequest;
import com.gisnet.cancelacion.events.SaveResponse;
import com.gisnet.cancelacion.events.info.NotarioInfo;
import com.gisnet.cancelacion.web.domain.NotarioForm;
import com.gisnet.cancelacion.webservices.dto.CNotario;
import java.security.Principal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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