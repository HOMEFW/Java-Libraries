package br.com.fws.profiles;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import br.com.fws.profiles.data.Base;
import br.com.fws.profiles.entities.User;

public class Login {
	Base<User> base = null;

	public Login() {
		base = new Base<User>();
	}

	public void doLogin(User data) throws Exception {

		try {

			Map<String, AttributeValue> map = new HashMap<String, AttributeValue>();
			map.put(":login", new AttributeValue().withS(data.getLogin()));

			DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("login = :login")
					.withExpressionAttributeValues(map);

			User user = base.getItem(scanExpression, User.class);

			if (!user.getPassword().equals(data.getPassword())) {
				throw new IllegalArgumentException("Invalid user or password!");
			}

		} catch (Exception e) {
			throw e;

		}
	}

	public void doRegister(User data) throws Exception {
		try {
			String message = "";

			if (data.getLogin() == null && data.getEmail() == null) {
				throw new IllegalArgumentException("Invalid data");
			}

			message = checkLogin(data);

			message = checkEmail(data);

			if (!message.isEmpty()) {
				throw new IllegalArgumentException(message);
			}

			if (data.getUserId() == null) {
				UUID uuid = UUID.randomUUID();
				data.setUserId(uuid.toString().replaceAll("-", ""));
			}

			base.saveItem(data);

		} catch (Exception e) {
			throw e;
		}
	}

	private String checkLogin(User data) {
		String message = "";
		if (data.getLogin() != null) {
			Map<String, AttributeValue> map = new HashMap<String, AttributeValue>();
			map.put(":login", new AttributeValue().withS(data.getLogin()));

			DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("login = :login")
					.withExpressionAttributeValues(map);

			User user = base.getItem(scanExpression, User.class);
			if (user != null)
				message = "Login already exists;";
		}

		return message;
	}

	private String checkEmail(User data) {
		String message = "";
		if (data.getEmail() != null) {
			Map<String, AttributeValue> map = new HashMap<String, AttributeValue>();
			map.put(":email", new AttributeValue().withS(data.getEmail()));

			DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("email = :email")
					.withExpressionAttributeValues(map);

			User user = base.getItem(scanExpression, User.class);
			if (user != null)
				message = "Email already exists;";
		}
		return message;
	}
}
