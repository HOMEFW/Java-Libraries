package br.com.fws.profiles;

import br.com.fws.profiles.entities.User;
import br.com.fws.profiles.entities.UserDetails;

/**
 * Hello world
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		Login login = new Login();
		Register register = new Register();

		try {
			User user = new User();
			UserDetails userDetails = new UserDetails();
			user.setLogin("5677");
			user.setPassword("pass");
			user.setEmail("5677@email.com");

			// userDetails.setBirthDate("01/01/2012");
			// userDetails.setFirstName("new User");
			// userDetails.setLastName("new User");
			// userDetails.setActive(false);
			// userDetails.setBlocked(true);
			// user.setDetails(userDetails);
			//
			// register.doRegister(user);

			user.setPassword("pass");
			login.doLogin(user);
			String userId = login.doLogin(user);
			System.out.println("User " + userId + ", your're Logged in!");

			userDetails.setFirstName("First");
			userDetails.setLastName("Last");
			userDetails.setBirthDate("01/09/2018");
			Boolean retorno = register.doUpdate(userId, userDetails);
			System.out.println(retorno);

		} catch (IllegalArgumentException ia) {
			// ia.printStackTrace();
			System.out.println(ia.getMessage());
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}
}
