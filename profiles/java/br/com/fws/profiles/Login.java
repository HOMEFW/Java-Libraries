package br.com.fws.profiles;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import br.com.fws.commom.Encryption;
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

			if (user == null || !user.getPassword().equals(Encryption.Generate(data.getPassword()))) {
				throw new IllegalArgumentException("Invalid user or password!");
			}

		} catch (Exception e) {
			throw e;
		}
	}

}
