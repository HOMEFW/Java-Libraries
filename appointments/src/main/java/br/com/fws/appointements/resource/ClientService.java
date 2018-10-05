package br.com.fws.appointements.resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.com.fws.appointements.dao.ClientDao;
import br.com.fws.appointements.dictionary.Message;
import br.com.fws.appointements.exceptions.AuthorizationException;
import br.com.fws.appointments.models.Client;
import br.com.fws.appointments.models.ClientList;

@WebService
public class ClientService {

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
			throw new IllegalArgumentException(Message.INVALIDCLIENTNAME);
		}

		if (getClient(data) != null) {
			throw new IllegalArgumentException(Message.CLIENTNAMEALREADYEXISTS);
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
