package br.com.fws.appointment.exceptions;

import javax.xml.ws.WebFault;

@WebFault(name = "Authorization")
public class AuthorizationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthorizationException(String message) {
		super(message);
	}

	public String getFaultInfo() {
		return "invalid token";
	}
}
