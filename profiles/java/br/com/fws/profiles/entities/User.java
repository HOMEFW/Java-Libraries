package br.com.fws.profiles.entities;

public class User {

	private boolean active;
	private int accessCounter;
	private boolean blocked;
	private String login;
	private String password;
	private String ultimoAcesso;
	private String userId;

	public int getAccessCounter() {
		return accessCounter;
	}

	public void setAccessCounter(int accessCounter) {
		this.accessCounter = accessCounter;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(String ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
