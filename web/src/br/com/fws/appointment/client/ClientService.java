
package br.com.fws.appointment.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ClientService", targetNamespace = "http://service.appointment.fws.com.br/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ClientService {


    /**
     * 
     * @param parameters
     * @param token
     * @return
     *     returns br.com.fws.appointment.client.RegisterResponse
     * @throws IllegalArgumentException_Exception
     * @throws AuthorizationException
     * @throws Exception_Exception
     */
    @WebMethod(operationName = "Register")
    @WebResult(name = "RegisterResponse", targetNamespace = "http://service.appointment.fws.com.br/", partName = "parameters")
    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    public RegisterResponse register(
        @WebParam(name = "Register", targetNamespace = "http://service.appointment.fws.com.br/", partName = "parameters")
        Register parameters,
        @WebParam(name = "Token", targetNamespace = "http://service.appointment.fws.com.br/", header = true, partName = "Token")
        String token)
        throws AuthorizationException, Exception_Exception, IllegalArgumentException_Exception
    ;

    /**
     * 
     * @param clientFilter
     * @return
     *     returns br.com.fws.appointment.client.Client
     * @throws Exception_Exception
     */
    @WebMethod(operationName = "GetClient")
    @WebResult(name = "Client", targetNamespace = "")
    @RequestWrapper(localName = "GetClient", targetNamespace = "http://service.appointment.fws.com.br/", className = "br.com.fws.appointment.client.GetClient")
    @ResponseWrapper(localName = "GetClientResponse", targetNamespace = "http://service.appointment.fws.com.br/", className = "br.com.fws.appointment.client.GetClientResponse")
    public Client getClient(
        @WebParam(name = "ClientFilter", targetNamespace = "")
        Client clientFilter)
        throws Exception_Exception
    ;

    /**
     * 
     * @param clientData
     * @return
     *     returns br.com.fws.appointment.client.ClientList
     * @throws Exception_Exception
     */
    @WebMethod(operationName = "GetClientByFilter")
    @WebResult(name = "Clients", targetNamespace = "")
    @RequestWrapper(localName = "GetClientByFilter", targetNamespace = "http://service.appointment.fws.com.br/", className = "br.com.fws.appointment.client.GetClientByFilter")
    @ResponseWrapper(localName = "GetClientByFilterResponse", targetNamespace = "http://service.appointment.fws.com.br/", className = "br.com.fws.appointment.client.GetClientByFilterResponse")
    public ClientList getClientByFilter(
        @WebParam(name = "ClientData", targetNamespace = "")
        Client clientData)
        throws Exception_Exception
    ;

    /**
     * 
     * @return
     *     returns br.com.fws.appointment.client.ClientList
     * @throws Exception_Exception
     */
    @WebMethod(operationName = "GetAllClients")
    @WebResult(name = "Clients", targetNamespace = "")
    @RequestWrapper(localName = "GetAllClients", targetNamespace = "http://service.appointment.fws.com.br/", className = "br.com.fws.appointment.client.GetAllClients")
    @ResponseWrapper(localName = "GetAllClientsResponse", targetNamespace = "http://service.appointment.fws.com.br/", className = "br.com.fws.appointment.client.GetAllClientsResponse")
    public ClientList getAllClients()
        throws Exception_Exception
    ;

}
