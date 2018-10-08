package br.com.fws.appointment.resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import br.com.fws.appointment.dao.ClientDao;
import br.com.fws.appointment.dictionnary.Message;
import br.com.fws.appointment.exceptions.AuthorizationException;
import br.com.fws.appointment.models.Client;
import br.com.fws.appointment.models.ClientList;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL, parameterStyle = ParameterStyle.WRAPPED)
public class ClientService {

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

		if (isValid(data)) {
			String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			data.setCreatedDate(sdf.format(new Date()));
			ClientDao dao = new ClientDao();
			return dao.save(data);
		} else {
			return false;
		}
	}

	private boolean isValid(Client data) throws Exception, IllegalArgumentException {
		if (data.getClientName() == null || data.getClientName().isEmpty()) {
			throw new IllegalArgumentException(Message.INVALIDNAME);
		}

		if (getClient(data) != null) {
			throw new IllegalArgumentException(Message.ALREADYEXISTS);
		}
		return true;
	}

	@WebMethod(operationName = "GetClient")
	@WebResult(name = "Client")
	public Client getClient(@WebParam(name = "ClientFilter") Client data) throws Exception {
		Client result = null;
		ClientDao dao = new ClientDao();
		result = dao.getClient(data);
		return result;
	}

	@WebMethod(operationName = "ClientList")
	@WebResult(name = "Clients")
	public ClientList getAllClients() throws Exception {

		ClientDao dao = new ClientDao();
		List<Client> clientList = dao.getAllClients();

		return new ClientList(clientList);
	}

	@WebMethod(operationName = "ClientSearch")
	@WebResult(name = "Clients")
	public ClientList getClientsByFilter(@WebParam(name = "ClientData") Client data) throws Exception {

		ClientDao dao = new ClientDao();
		List<Client> clientList = dao.getClientsByFilter(data);

		return new ClientList(clientList);
	}

}
