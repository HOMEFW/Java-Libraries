package br.com.fws.appointment.web;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import br.com.fws.appointment.client.ClientList;
import br.com.fws.appointment.client.ClientService;
import br.com.fws.appointment.client.Exception_Exception;

public class Cliente {

	URL url = null;
	QName qname = null;
	Service service = null;
	ClientService client = null;

	public Cliente() {
		try {
			url = new URL("http://localhost:8080/service-0.0.1-SNAPSHOT/ClientService?wsdl");
			qname = new QName("http://service.appointment.fws.com.br/", "ClientServiceService");
			service = Service.create(url, qname);
			client = service.getPort(ClientService.class);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ClientList getClients() throws Exception_Exception {
		return client.getAllClients();
	}

}
