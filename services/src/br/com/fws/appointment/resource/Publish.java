package br.com.fws.appointment.resource;

import javax.xml.ws.Endpoint;

public class Publish {

	public static void main(String[] args) {

		ClientResource service = new ClientResource();
		String url = "http://localhost:8080/clients";

		System.out.println("startin");
		Endpoint.publish(url, service);

		System.out.println("waiting");
	}

}
