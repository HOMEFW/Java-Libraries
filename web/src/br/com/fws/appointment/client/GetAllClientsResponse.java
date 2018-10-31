
package br.com.fws.appointment.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de GetAllClientsResponse complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="GetAllClientsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Clients" type="{http://service.appointment.fws.com.br/}clientList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAllClientsResponse", propOrder = {
    "clients"
})
public class GetAllClientsResponse {

    @XmlElement(name = "Clients")
    protected ClientList clients;

    /**
     * Obt�m o valor da propriedade clients.
     * 
     * @return
     *     possible object is
     *     {@link ClientList }
     *     
     */
    public ClientList getClients() {
        return clients;
    }

    /**
     * Define o valor da propriedade clients.
     * 
     * @param value
     *     allowed object is
     *     {@link ClientList }
     *     
     */
    public void setClients(ClientList value) {
        this.clients = value;
    }

}
