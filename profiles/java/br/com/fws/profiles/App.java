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
		user.setLogin("5676");
		user.setPassword("pass");
		user.setEmail("5676@email.com");

		userDetails.setBirthDate("01/01/2012");
		userDetails.setName("new User");
		userDetails.setActive(false);
		userDetails.setBlocked(true);
		user.setDetails(userDetails);

		Login login = new Login();
		Register register = new Register();

		try {
			register.doRegister(user);

			user.setPassword("pass");
			login.doLogin(user);

			System.out.println("Logged in");

		} catch (IllegalArgumentException ia) {
			// ia.printStackTrace();
			System.out.println(ia.getMessage());
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}
}
