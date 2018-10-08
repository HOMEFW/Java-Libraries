package br.com.fws.appointment.resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.fws.appointment.dao.ClientDao;
import br.com.fws.appointment.dictionnary.Message;
import br.com.fws.appointment.exceptions.AuthorizationException;
import br.com.fws.appointment.models.Client;
import br.com.fws.appointment.models.ClientList;

public class ClientResource {

	public ClientResource() {
	}

	public Boolean doRegister(Client data) throws AuthorizationException, IllegalArgumentException, Exception {

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

	public Client getClient(Client data) throws Exception {
		Client result = null;
		ClientDao dao = new ClientDao();
		result = dao.getClient(data);
		return result;
	}

	public ClientList getAllClients() throws Exception {

		ClientDao dao = new ClientDao();
		List<Client> clientList = dao.getAllClients();

		return new ClientList(clientList);
	}

	public ClientList getClientsByFilter(Client data) throws Exception {

		ClientDao dao = new ClientDao();
		List<Client> clientList = dao.getClientsByFilter(data);

		return new ClientList(clientList);
	}

}
