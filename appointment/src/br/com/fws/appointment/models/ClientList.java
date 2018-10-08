package br.com.fws.appointment.models;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ClientList {

	@XmlElement(name = "Client")
	private List<Client> clientList;

	public ClientList(List<Client> clientList) {
		this.clientList = clientList;
	}

	ClientList() {

	}

	public List<Client> getClientList() {
		return clientList;
	}
}
