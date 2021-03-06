
package br.com.fws.appointment.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de GetClientByFilter complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="GetClientByFilter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ClientData" type="{http://service.appointment.fws.com.br/}client" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetClientByFilter", propOrder = {
    "clientData"
})
public class GetClientByFilter {

    @XmlElement(name = "ClientData")
    protected Client clientData;

    /**
     * Obt�m o valor da propriedade clientData.
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
