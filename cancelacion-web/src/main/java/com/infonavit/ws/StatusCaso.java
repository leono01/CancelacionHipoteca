//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.infonavit.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for statusCaso complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="statusCaso">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numeroDeCaso" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "statusCaso", propOrder = {
    "numeroDeCaso"
})
public class StatusCaso {

    protected int numeroDeCaso;

    /**
     * Gets the value of the numeroDeCaso property.
     * 
     */
    public int getNumeroDeCaso() {
        return numeroDeCaso;
    }

    /**
     * Sets the value of the numeroDeCaso property.
     * 
     */
    public void setNumeroDeCaso(int value) {
        this.numeroDeCaso = value;
    }

}
