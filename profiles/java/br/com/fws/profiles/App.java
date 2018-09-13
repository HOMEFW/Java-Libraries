package br.com.fws.profiles;

import br.com.fws.profiles.entities.User;
import br.com.fws.profiles.entities.UserDetails;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		User user = new User();
		UserDetails userDetails = new UserDetails();
		user.setLogin("123");
		user.setPassword("pass");
		user.setEmail("email@email.com");

		userDetails.setBirthDate("01/01/2012");
		userDetails.setName("new User");
		userDetails.setActive(false);
		userDetails.setBlocked(true);
		user.setDetails(userDetails);

		Login login = new Login();
		System.out.println(login.doRegister(user));

		System.out.println(login.doLogin(user));

	}
}
