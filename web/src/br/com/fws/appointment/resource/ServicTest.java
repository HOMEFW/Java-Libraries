package br.com.fws.appointment.resource;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class ServicTest {

	public static void main(String[] args) throws Exception_Exception, MalformedURLException {

		// ClientService client = new
		// ClientServiceService().getClientServicePort();
		//
		// ClientList lista = client.getAllClients();
		//
		// for (Client item : lista.getClient()) {
		// System.out.println(item.clientName);
		// }

		URL url = new URL("http://localhost:8080/estoque?wsdl");
		QName qname = new QName("http://resource.appointment.fes.com.br/", "ClientService");

		Service service = Service.create(url, qname);

		ClientService client = service.getPort(ClientService.class);

		ClientList lista = client.getAllClients();

		for (Client item : lista.getClient()) {
			System.out.println(item.clientName);
		}

	}

}
