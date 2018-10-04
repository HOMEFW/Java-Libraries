package appointments;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.Test;

import br.com.fws.appointements.resource.ClientService;
import br.com.fws.appointments.models.Client;

public class ClientTest {
	final UUID uuid = UUID.randomUUID();

	@Test
	public void addClient() throws IllegalArgumentException, Exception {
		ClientService service = new ClientService();
		Client data = new Client();
		data.setClientName("client" + uuid.toString());
		data.setActive(true);
		assertTrue(service.doRegister(data));
	}

	@Test
	public void duplicateClient() throws IllegalArgumentException, Exception {
		ClientService service = new ClientService();
		Client data = new Client();
		data.setClientName("client" + uuid.toString());
		data.setActive(true);
		assertFalse(service.doRegister(data));

	}

	@Test
	public void getAllClients() throws Exception {
		ClientService service = new ClientService();
		List<Client> result = service.getAllClients();
		assertTrue(result != null && !result.isEmpty());
	}

}
