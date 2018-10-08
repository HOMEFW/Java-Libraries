
package br.com.fws.appointment.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de GetClient complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="GetClient">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ClientFilter" type="{http://resource.appointment.fws.com.br/}client" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetClient", propOrder = {
    "clientFilter"
})
public class GetClient {

    @XmlElement(name = "ClientFilter")
    protected Client clientFilter;

    /**
     * Obtém o valor da propriedade clientFilter.
     * 
     * @return
     *     possible object is
     *     {@link Client }
     *     
     */
    public Client getClientFilter() {
        return clientFilter;
    }

    /**
     * Define o valor da propriedade clientFilter.
     * 
     * @param value
     *     allowed object is
     *     {@link Client }
     *     
     */
    public void setClientFilter(Client value) {
        this.clientFilter = value;
    }

}
