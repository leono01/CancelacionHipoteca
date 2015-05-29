//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.gisnet.cancelacion.wsclient.microflujo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Input complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Input">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numeroCredito" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroCaso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoOperacion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="estatus" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="entidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Carta" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="nombreAcreditado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaEmision" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="md5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Input", namespace = "http://www.sap.com/caf/sap.com/home.infonavit_po_ch.bl.caf/modeled", propOrder = {
    "numeroCredito",
    "numeroCaso",
    "tipoOperacion",
    "estatus",
    "descripcion",
    "entidad",
    "carta",
    "nombreAcreditado",
    "fechaEmision",
    "md5"
})
public class Input {

    @XmlElement(required = true)
    protected String numeroCredito;
    protected String numeroCaso;
    protected int tipoOperacion;
    protected Integer estatus;
    protected String descripcion;
    protected String entidad;
    @XmlElement(name = "Carta")
    protected byte[] carta;
    protected String nombreAcreditado;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaEmision;
    protected String md5;

    /**
     * Gets the value of the numeroCredito property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCredito() {
        return numeroCredito;
    }

    /**
     * Sets the value of the numeroCredito property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCredito(String value) {
        this.numeroCredito = value;
    }

    /**
     * Gets the value of the numeroCaso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCaso() {
        return numeroCaso;
    }

    /**
     * Sets the value of the numeroCaso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCaso(String value) {
        this.numeroCaso = value;
    }

    /**
     * Gets the value of the tipoOperacion property.
     * 
     */
    public int getTipoOperacion() {
        return tipoOperacion;
    }

    /**
     * Sets the value of the tipoOperacion property.
     * 
     */
    public void setTipoOperacion(int value) {
        this.tipoOperacion = value;
    }

    /**
     * Gets the value of the estatus property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEstatus() {
        return estatus;
    }

    /**
     * Sets the value of the estatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEstatus(Integer value) {
        this.estatus = value;
    }

    /**
     * Gets the value of the descripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the value of the descripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Gets the value of the entidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntidad() {
        return entidad;
    }

    /**
     * Sets the value of the entidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntidad(String value) {
        this.entidad = value;
    }

    /**
     * Gets the value of the carta property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getCarta() {
        return carta;
    }

    /**
     * Sets the value of the carta property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setCarta(byte[] value) {
        this.carta = ((byte[]) value);
    }

    /**
     * Gets the value of the nombreAcreditado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreAcreditado() {
        return nombreAcreditado;
    }

    /**
     * Sets the value of the nombreAcreditado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreAcreditado(String value) {
        this.nombreAcreditado = value;
    }

    /**
     * Gets the value of the fechaEmision property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Sets the value of the fechaEmision property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaEmision(XMLGregorianCalendar value) {
        this.fechaEmision = value;
    }

    /**
     * Gets the value of the md5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMd5() {
        return md5;
    }

    /**
     * Sets the value of the md5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMd5(String value) {
        this.md5 = value;
    }

}
