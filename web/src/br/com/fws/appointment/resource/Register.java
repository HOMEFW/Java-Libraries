
package br.com.fws.appointment.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de Register complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="Register">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ClientData" type="{http://resource.appointment.fws.com.br/}client" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Register", propOrder = {
    "clientData"
})
public class Register {

    @XmlElement(name = "ClientData")
    protected Client clientData;

    /**
     * Obtém o valor da propriedade clientData.
     * 
     * @return
     *     possible object is
     *     {@link Client }
     *     
     */
    public Client getClientData() {
        return clientData;
    }

    /**
     * Define o valor da propriedade clientData.
     * 
     * @param value
     *     allowed object is
     *     {@link Client }
     *     
     */
    public void setClientData(Client value) {
        this.clientData = value;
    }

}
