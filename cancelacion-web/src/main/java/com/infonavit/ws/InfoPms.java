//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package com.infonavit.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for infoPms complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="infoPms">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigoNotario" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="convenioInfonavit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="direccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="entidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreNotario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="notaria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroDeCaso" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numeroDeCredito" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="procede" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="telefono" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "infoPms", propOrder = {
    "codigoNotario",
    "convenioInfonavit",
    "descripcion",
    "direccion",
    "email",
    "entidad",
    "nombreNotario",
    "notaria",
    "numeroDeCaso",
    "numeroDeCredito",
    "procede",
    "status",
    "telefono"
})
public class InfoPms {

    protected int codigoNotario;
    protected String convenioInfonavit;
    protected String descripcion;
    protected String direccion;
    protected String email;
    protected String entidad;
    protected String nombreNotario;
    protected String notaria;
    protected int numeroDeCaso;
    protected int numeroDeCredito;
    protected boolean procede;
    protected int status;
    protected String telefono;

    /**
     * Gets the value of the codigoNotario property.
     * 
     */
    public int getCodigoNotario() {
        return codigoNotario;
    }

    /**
     * Sets the value of the codigoNotario property.
     * 
     */
    public void setCodigoNotario(int value) {
        this.codigoNotario = value;
    }

    /**
     * Gets the value of the convenioInfonavit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConvenioInfonavit() {
        return convenioInfonavit;
    }

    /**
     * Sets the value of the convenioInfonavit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConvenioInfonavit(String value) {
        this.convenioInfonavit = value;
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
     * Gets the value of the direccion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Sets the value of the direccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireccion(String value) {
        this.direccion = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
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
     * Gets the value of the nombreNotario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreNotario() {
        return nombreNotario;
    }

    /**
     * Sets the value of the nombreNotario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreNotario(String value) {
        this.nombreNotario = value;
    }

    /**
     * Gets the value of the notaria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotaria() {
        return notaria;
    }

    /**
     * Sets the value of the notaria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotaria(String value) {
        this.notaria = value;
    }

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

    /**
     * Gets the value of the numeroDeCredito property.
     * 
     */
    public int getNumeroDeCredito() {
        return numeroDeCredito;
    }

    /**
     * Sets the value of the numeroDeCredito property.
     * 
     */
    public void setNumeroDeCredito(int value) {
        this.numeroDeCredito = value;
    }

    /**
     * Gets the value of the procede property.
     * 
     */
    public boolean isProcede() {
        return procede;
    }

    /**
     * Sets the value of the procede property.
     * 
     */
    public void setProcede(boolean value) {
        this.procede = value;
    }

    /**
     * Gets the value of the status property.
     * 
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     */
    public void setStatus(int value) {
        this.status = value;
    }

    /**
     * Gets the value of the telefono property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Sets the value of the telefono property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefono(String value) {
        this.telefono = value;
    }

}
