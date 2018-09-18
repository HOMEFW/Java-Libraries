package br.com.fws.profiles;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import br.com.fws.commom.Encryption;
import br.com.fws.profiles.data.Base;
import br.com.fws.profiles.entities.User;;

public class Register {

	Base<User> base = null;

	public Register() {
		base = new Base<User>();
	}

	public void doRegister(User data) throws Exception {
		try {

			if (data.getLogin() == null && data.getEmail() == null) {
				throw new IllegalArgumentException("Invalid data");
			}

			checkLogin(data);

			checkEmail(data);

			if (data.getUserId() == null) {
				UUID uuid = UUID.randomUUID();
				data.setUserId(uuid.toString().replaceAll("-", ""));
				data.setPassword(Encryption.Generate(data.getPassword()));
				String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
				data.setRegisterDate(sdf.format(new Date()));
			}

			base.saveItem(data);

		} catch (IllegalArgumentException a) {
			throw a;
		} catch (Exception e) {
			throw e;
		}
	}

	private void checkLogin(User data) {
		if (data.getLogin() != null) {
			Map<String, AttributeValue> map = new HashMap<String, AttributeValue>();
			map.put(":login", new AttributeValue().withS(data.getLogin()));

			DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("login = :login")
					.withExpressionAttributeValues(map);

			User user = base.getItem(scanExpression, User.class);
			if (user != null)
				throw new IllegalArgumentException("Login already exists;");
		}
	}

	private void checkEmail(User data) {

		if (data.getEmail() != null) {
			Map<String, AttributeValue> map = new HashMap<String, AttributeValue>();
			map.put(":email", new AttributeValue().withS(data.getEmail()));

			DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("email = :email")
					.withExpressionAttributeValues(map);

			User user = base.getItem(scanExpression, User.class);
			if (user != null)
				throw new IllegalArgumentException("Email already exists;");
		}

	}

}
