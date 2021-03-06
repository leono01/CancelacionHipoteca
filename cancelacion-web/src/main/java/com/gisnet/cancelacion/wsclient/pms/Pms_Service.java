//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.gisnet.cancelacion.wsclient.pms;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(name = "Pms", targetNamespace = "http://ws.infonavit.com/", wsdlLocation = "WEB-INF/wsdl/Pms.wsdl")
public class Pms_Service
    extends Service
{

    private final static URL PMS_WSDL_LOCATION;
    private final static WebServiceException PMS_EXCEPTION;
    private final static QName PMS_QNAME = new QName("http://ws.infonavit.com/", "Pms");

    static {
            PMS_WSDL_LOCATION = com.gisnet.cancelacion.wsclient.pms.Pms_Service.class.getResource("/WEB-INF/wsdl/Pms.wsdl");
        WebServiceException e = null;
        if (PMS_WSDL_LOCATION == null) {
            e = new WebServiceException("Cannot find 'WEB-INF/wsdl/Pms.wsdl' wsdl. Place the resource correctly in the classpath.");
        }
        PMS_EXCEPTION = e;
    }

    public Pms_Service() {
        super(__getWsdlLocation(), PMS_QNAME);
    }

    public Pms_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), PMS_QNAME, features);
    }

    public Pms_Service(URL wsdlLocation) {
        super(wsdlLocation, PMS_QNAME);
    }

    public Pms_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, PMS_QNAME, features);
    }

    public Pms_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Pms_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Pms
     */
    @WebEndpoint(name = "PmsPort")
    public Pms getPmsPort() {
        return super.getPort(new QName("http://ws.infonavit.com/", "PmsPort"), Pms.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Pms
     */
    @WebEndpoint(name = "PmsPort")
    public Pms getPmsPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.infonavit.com/", "PmsPort"), Pms.class, features);
    }

    private static URL __getWsdlLocation() {
        if (PMS_EXCEPTION!= null) {
            throw PMS_EXCEPTION;
        }
        return PMS_WSDL_LOCATION;
    }

}
