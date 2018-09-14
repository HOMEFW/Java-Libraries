package br.com.fws.profiles;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import br.com.fws.profiles.data.Base;
import br.com.fws.profiles.entities.User;

public class Login {

	public String doLogin(User data) {
		String message = "";
		Base<User> base = new Base<User>();

		try {

			Map<String, AttributeValue> map = new HashMap<String, AttributeValue>();
			map.put(":login", new AttributeValue().withS(data.getLogin()));

			DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("login = :login")
					.withExpressionAttributeValues(map);

			User user = base.getItem(scanExpression);

			base = null;

			if (user.getPassword().equals(data.getPassword())) {
				message = "you´re in";
			} else {
				message = "error";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return message;
	}

	public String doRegister(User data) {

		Base<User> base = new Base<User>();

		if (data.getUserId() == null) {
			UUID uuid = UUID.randomUUID();
			data.setUserId(uuid.toString().replaceAll("-", ""));
		}

		base.saveItem(data);

		base = null;

		return "registered";
	}
}
