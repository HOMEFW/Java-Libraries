package br.com.fws.appointment.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import br.com.fws.appointment.exceptions.AuthorizationException;
import br.com.fws.appointment.models.Client;
import br.com.fws.appointment.models.ClientList;
import br.com.fws.appointment.resource.ClientResource;

@WebService(endpointInterface = "br.com.fws.appointment.service")
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL, parameterStyle = ParameterStyle.WRAPPED)
public class ClientService extends ClientResource {

	public ClientService() {
	}

	@WebMethod(operationName = "Register")
	@WebResult(name = "SuccessConfirm")
	public Boolean doRegister(@WebParam(name = "Token", header = true) String token,
			@WebParam(name = "ClientData") Client data)
			throws AuthorizationException, IllegalArgumentException, Exception {

		if (!token.equals("1")) {
			throw new AuthorizationException("invalid token");
		}

		return super.doRegister(data);
	}

	@WebMethod(operationName = "GetClient")
	@WebResult(name = "Client")
	public Client getClient(@WebParam(name = "ClientFilter") Client data) throws Exception {
		return super.getClient(data);
	}

	@WebMethod(operationName = "GetAllClients")
	@WebResult(name = "Clients")
	public ClientList getAllClients() throws Exception {
		return super.getAllClients();
	}

	@WebMethod(operationName = "GetClientByFilter")
	@WebResult(name = "Clients")
	public ClientList getClientsByFilter(@WebParam(name = "ClientData") Client data) throws Exception {

		return super.getClientsByFilter(data);
	}

}
