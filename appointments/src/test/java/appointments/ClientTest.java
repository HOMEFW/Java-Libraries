package appointments;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import br.com.fws.appointment.models.Client;
import br.com.fws.appointment.models.ClientList;
import br.com.fws.appointment.resource.ClientService;

public class ClientTest {
	final UUID uuid = UUID.randomUUID();

	@Test
	public void addClient() throws IllegalArgumentException, Exception {
		ClientService service = new ClientService();
		Client data = new Client();
		data.setClientName("client" + uuid.toString());
		data.setActive(null);
		assertTrue(service.doRegister("", data));
	}

	@Test
	public void duplicateClient() throws IllegalArgumentException, Exception {
		ClientService service = new ClientService();
		Client data = new Client();
		data.setClientName("client" + uuid.toString());
		data.setActive(null);
		assertFalse(service.doRegister("", data));

	}

	@Test
	public void getAllClients() throws Exception {
		ClientService service = new ClientService();
		ClientList result = service.getAllClients();
		assertTrue(result != null && result.getClientList() != null && !result.getClientList().isEmpty());
	}

}