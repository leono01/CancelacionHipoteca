//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.gisnet.cancelacion.wsclient.autenticacion;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(name = "LDAPConnectionJAASService", targetNamespace = "http://infonavit.ejemplo.com", wsdlLocation = "WEB-INF/wsdl/Autentificar.wsdl")
public class LDAPConnectionJAASService
    extends Service
{

    private final static URL LDAPCONNECTIONJAASSERVICE_WSDL_LOCATION;
    private final static WebServiceException LDAPCONNECTIONJAASSERVICE_EXCEPTION;
    private final static QName LDAPCONNECTIONJAASSERVICE_QNAME = new QName("http://infonavit.ejemplo.com", "LDAPConnectionJAASService");

    static {
            LDAPCONNECTIONJAASSERVICE_WSDL_LOCATION = com.gisnet.cancelacion.wsclient.autenticacion.LDAPConnectionJAASService.class.getResource("/WEB-INF/wsdl/Autentificar.wsdl");
        WebServiceException e = null;
        if (LDAPCONNECTIONJAASSERVICE_WSDL_LOCATION == null) {
            e = new WebServiceException("Cannot find 'WEB-INF/wsdl/Autentificar.wsdl' wsdl. Place the resource correctly in the classpath.");
        }
        LDAPCONNECTIONJAASSERVICE_EXCEPTION = e;
    }

    public LDAPConnectionJAASService() {
        super(__getWsdlLocation(), LDAPCONNECTIONJAASSERVICE_QNAME);
    }

    public LDAPConnectionJAASService(WebServiceFeature... features) {
        super(__getWsdlLocation(), LDAPCONNECTIONJAASSERVICE_QNAME, features);
    }

    public LDAPConnectionJAASService(URL wsdlLocation) {
        super(wsdlLocation, LDAPCONNECTIONJAASSERVICE_QNAME);
    }

    public LDAPConnectionJAASService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, LDAPCONNECTIONJAASSERVICE_QNAME, features);
    }

    public LDAPConnectionJAASService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public LDAPConnectionJAASService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns LDAPConnectionJAAS
     */
    @WebEndpoint(name = "LDAPConnectionJAAS")
    public LDAPConnectionJAAS getLDAPConnectionJAAS() {
        return super.getPort(new QName("http://infonavit.ejemplo.com", "LDAPConnectionJAAS"), LDAPConnectionJAAS.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns LDAPConnectionJAAS
     */
    @WebEndpoint(name = "LDAPConnectionJAAS")
    public LDAPConnectionJAAS getLDAPConnectionJAAS(WebServiceFeature... features) {
        return super.getPort(new QName("http://infonavit.ejemplo.com", "LDAPConnectionJAAS"), LDAPConnectionJAAS.class, features);
    }

    private static URL __getWsdlLocation() {
        if (LDAPCONNECTIONJAASSERVICE_EXCEPTION!= null) {
            throw LDAPCONNECTIONJAASSERVICE_EXCEPTION;
        }
        return LDAPCONNECTIONJAASSERVICE_WSDL_LOCATION;
    }

}
