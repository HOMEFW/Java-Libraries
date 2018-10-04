package br.com.fws.profiles.resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;

import br.com.fws.commom.Encryption;
import br.com.fws.data.Base;
import br.com.fws.profile.models.User;
import br.com.fws.profile.models.UserDetails;;

public class Register extends Base<User> {

	public Register() {
		super();
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

			super.saveItem(data);

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

			User user = super.getItem(scanExpression, User.class);
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

			User user = super.getItem(scanExpression, User.class);
			if (user != null)
				throw new IllegalArgumentException("Email already exists;");
		}

	}

	public Boolean doUpdate(String userId, UserDetails details) throws Exception, IllegalArgumentException {
		try {

			if (userId == null || details == null) {
				throw new IllegalAccessError("dados inválidos!");
			}

			UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("userId", userId)
					.withUpdateExpression(
							"set details.firstName = :firstName, details.birthDate = :birthDate, details.lastName = :lastName")
					.withValueMap(new ValueMap().withString(":firstName", details.getFirstName())
							.withString(":birthDate", details.getBirthDate())
							.withString(":lastName", details.getLastName())
					// .withList(":a", Arrays.asList("Larry", "Moe", "Curly"))
					).withReturnValues(ReturnValue.UPDATED_NEW);

			super.updateItem("Users", updateItemSpec);

			// UpdateItemOutcome outcome = super.updateItem("Users",
			// updateItemSpec);

			return true;
		} catch (Exception e) {
			throw e;
		}
	}

}
