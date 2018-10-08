
package br.com.fws.appointment.resource;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.com.fws.appointment.resource package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Client_QNAME = new QName("http://resource.appointment.fws.com.br/", "client");
    private final static QName _GetAllClientsResponse_QNAME = new QName("http://resource.appointment.fws.com.br/", "GetAllClientsResponse");
    private final static QName _ClientList_QNAME = new QName("http://resource.appointment.fws.com.br/", "clientList");
    private final static QName _GetClient_QNAME = new QName("http://resource.appointment.fws.com.br/", "GetClient");
    private final static QName _GetClientByFilterResponse_QNAME = new QName("http://resource.appointment.fws.com.br/", "GetClientByFilterResponse");
    private final static QName _Authorization_QNAME = new QName("http://resource.appointment.fws.com.br/", "Authorization");
    private final static QName _RegisterResponse_QNAME = new QName("http://resource.appointment.fws.com.br/", "RegisterResponse");
    private final static QName _Register_QNAME = new QName("http://resource.appointment.fws.com.br/", "Register");
    private final static QName _GetAllClients_QNAME = new QName("http://resource.appointment.fws.com.br/", "GetAllClients");
    private final static QName _Token_QNAME = new QName("http://resource.appointment.fws.com.br/", "Token");
    private final static QName _GetClientByFilter_QNAME = new QName("http://resource.appointment.fws.com.br/", "GetClientByFilter");
    private final static QName _Exception_QNAME = new QName("http://resource.appointment.fws.com.br/", "Exception");
    private final static QName _GetClientResponse_QNAME = new QName("http://resource.appointment.fws.com.br/", "GetClientResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.com.fws.appointment.resource
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetClientResponse }
     * 
     */
    public GetClientResponse createGetClientResponse() {
        return new GetClientResponse();
    }

    /**
     * Create an instance of {@link GetAllClients }
     * 
     */
    public GetAllClients createGetAllClients() {
        return new GetAllClients();
    }

    /**
     * Create an instance of {@link GetClientByFilter }
     * 
     */
    public GetClientByFilter createGetClientByFilter() {
        return new GetClientByFilter();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link RegisterResponse }
     * 
     */
    public RegisterResponse createRegisterResponse() {
        return new RegisterResponse();
    }

    /**
     * Create an instance of {@link Register }
     * 
     */
    public Register createRegister() {
        return new Register();
    }

    /**
     * Create an instance of {@link Client }
     * 
     */
    public Client createClient() {
        return new Client();
    }

    /**
     * Create an instance of {@link GetAllClientsResponse }
     * 
     */
    public GetAllClientsResponse createGetAllClientsResponse() {
        return new GetAllClientsResponse();
    }

    /**
     * Create an instance of {@link ClientList }
     * 
     */
    public ClientList createClientList() {
        return new ClientList();
    }

    /**
     * Create an instance of {@link GetClient }
     * 
     */
    public GetClient createGetClient() {
        return new GetClient();
    }

    /**
     * Create an instance of {@link GetClientByFilterResponse }
     * 
     */
    public GetClientByFilterResponse createGetClientByFilterResponse() {
        return new GetClientByFilterResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Client }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resource.appointment.fws.com.br/", name = "client")
    public JAXBElement<Client> createClient(Client value) {
        return new JAXBElement<Client>(_Client_QNAME, Client.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllClientsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resource.appointment.fws.com.br/", name = "GetAllClientsResponse")
    public JAXBElement<GetAllClientsResponse> createGetAllClientsResponse(GetAllClientsResponse value) {
        return new JAXBElement<GetAllClientsResponse>(_GetAllClientsResponse_QNAME, GetAllClientsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClientList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resource.appointment.fws.com.br/", name = "clientList")
    public JAXBElement<ClientList> createClientList(ClientList value) {
        return new JAXBElement<ClientList>(_ClientList_QNAME, ClientList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClient }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resource.appointment.fws.com.br/", name = "GetClient")
    public JAXBElement<GetClient> createGetClient(GetClient value) {
        return new JAXBElement<GetClient>(_GetClient_QNAME, GetClient.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClientByFilterResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resource.appointment.fws.com.br/", name = "GetClientByFilterResponse")
    public JAXBElement<GetClientByFilterResponse> createGetClientByFilterResponse(GetClientByFilterResponse value) {
        return new JAXBElement<GetClientByFilterResponse>(_GetClientByFilterResponse_QNAME, GetClientByFilterResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resource.appointment.fws.com.br/", name = "Authorization")
    public JAXBElement<String> createAuthorization(String value) {
        return new JAXBElement<String>(_Authorization_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resource.appointment.fws.com.br/", name = "RegisterResponse")
    public JAXBElement<RegisterResponse> createRegisterResponse(RegisterResponse value) {
        return new JAXBElement<RegisterResponse>(_RegisterResponse_QNAME, RegisterResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Register }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resource.appointment.fws.com.br/", name = "Register")
    public JAXBElement<Register> createRegister(Register value) {
        return new JAXBElement<Register>(_Register_QNAME, Register.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllClients }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resource.appointment.fws.com.br/", name = "GetAllClients")
    public JAXBElement<GetAllClients> createGetAllClients(GetAllClients value) {
        return new JAXBElement<GetAllClients>(_GetAllClients_QNAME, GetAllClients.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resource.appointment.fws.com.br/", name = "Token")
    public JAXBElement<String> createToken(String value) {
        return new JAXBElement<String>(_Token_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClientByFilter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resource.appointment.fws.com.br/", name = "GetClientByFilter")
    public JAXBElement<GetClientByFilter> createGetClientByFilter(GetClientByFilter value) {
        return new JAXBElement<GetClientByFilter>(_GetClientByFilter_QNAME, GetClientByFilter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resource.appointment.fws.com.br/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClientResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resource.appointment.fws.com.br/", name = "GetClientResponse")
    public JAXBElement<GetClientResponse> createGetClientResponse(GetClientResponse value) {
        return new JAXBElement<GetClientResponse>(_GetClientResponse_QNAME, GetClientResponse.class, null, value);
    }

}
