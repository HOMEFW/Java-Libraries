package br.com.fws.appointements.resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import br.com.fws.appointements.Dao.ClientDao;
import br.com.fws.appointements.dictionary.Message;
import br.com.fws.appointments.models.Client;

@WebService
public class ClientService {

	public Boolean doRegister(Client data) throws Exception, IllegalArgumentException {
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

	public Client getClient(Client data) throws Exception {
		Client result = null;
		ClientDao dao = new ClientDao();
		result = dao.getClient(data);
		return result;
	}

	public List<Client> getAllClients() throws Exception {
		ClientDao dao = new ClientDao();
		List<Client> result = null;
		result = dao.getAllClients();
		return result;
	}
}
