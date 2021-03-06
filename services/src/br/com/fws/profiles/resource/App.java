package br.com.fws.profiles.resource;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import br.com.fws.config.Initializer;
import br.com.fws.profile.models.User;
import br.com.fws.profile.models.UserDetails;

/**
 * Hello world
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		Initializer init = new Initializer();
		init.initializeDataBase();

		Login login = new Login();
		Register register = new Register();
		Core core = new Core();
		// Client client = new Client();

		try {
			User user = new User();
			UserDetails userDetails = new UserDetails();

			user.setLogin("5678");
			user.setPassword("pass");
			user.setEmail("5678@email.com");

			userDetails.setBirthDate("10/01/2017");
			userDetails.setFirstName("new User");
			userDetails.setLastName("new User");
			userDetails.setActive(false);
			userDetails.setBlocked(true);
			user.setDetails(userDetails);

			register.doRegister(user);

			// br.com.fws.models.Client dataClient = new
			// br.com.fws.models.Client();
			// dataClient.setClientName("Teste Client 1");
			// dataClient.setActive(true);
			// client.doRegister(dataClient);

			user.setPassword("pass");
			login.doLogin(user);
			String userId = login.doLogin(user);
			System.out.println("User " + userId + ", your're Logged in!");

			userDetails.setFirstName("First 2");
			userDetails.setLastName("Last 2");
			userDetails.setBirthDate("02/09/2018");
			Boolean retorno = register.doUpdate(userId, userDetails);

			System.out.println(retorno);
			String user_Id = "";
			for (User item : core.getAllUsers(1, null)) {
				user_Id = item.getUserId();
				System.out.println(user_Id);
			}

			System.out.println("Another");
			Map<String, AttributeValue> startKey = new HashMap<String, AttributeValue>();
			startKey.put("userId", new AttributeValue().withS(user_Id));
			for (User item : core.getAllUsers(1, startKey)) {
				System.out.println(item.getUserId());
			}

			System.out.println("Filter");
			for (User item : core.getUserByFirstOrLastName("new")) {
				System.out.println(item.getUserId());
			}

			System.out.println("Email");
			for (User item : core.getUsersByEmail("email")) {
				System.out.println(item.getUserId());
			}

			System.out.println("Begins");
			User item = core.getUserByEmail("5676");
			System.out.println(item.getUserId());

		} catch (IllegalArgumentException ia) {
			// ia.printStackTrace();
			System.out.println(ia.getMessage());
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}
}
