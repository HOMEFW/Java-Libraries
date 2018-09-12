package br.com.fws.profiles;

import br.com.fws.profiles.entities.User;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		User user = new User();

		user.setLogin("123");
		user.setPassword("pass");
		user.setActive(false);

		Login login = new Login();

		login.doRegister(user);

	}
}
