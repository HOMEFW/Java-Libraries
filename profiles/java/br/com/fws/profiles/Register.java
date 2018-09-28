package br.com.fws.profiles;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;

import br.com.fws.commom.Encryption;
import br.com.fws.profiles.data.Base;
import br.com.fws.profiles.entities.User;
import br.com.fws.profiles.entities.UserDetails;;

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

			DynamoDB dynamoDB = new DynamoDB(client);
			Table table = dynamoDB.getTable("User");

			UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("userId", userId)
					.withUpdateExpression("set details.name=:name, details.birthDate=:birthDate")
					.withValueMap(new ValueMap().withString(":name", details.getName()).withString(":birthDate",
							details.getBirthDate())
					// .withList(":a", Arrays.asList("Larry", "Moe", "Curly"))
					).withReturnValues(ReturnValue.UPDATED_NEW);

			// UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
			table.updateItem(updateItemSpec);

			// Map<String, AttributeValue> map = new HashMap<String,
			// AttributeValue>();
			// map.put(":userId", new AttributeValue().withS(userId));
			//
			// DynamoDBScanExpression scanExpression = new
			// DynamoDBScanExpression()
			// .withFilterExpression("userId =
			// :userId").withExpressionAttributeValues(map);
			//
			// User user = super.getItem(scanExpression, User.class);
			//
			// if (user == null) {
			// throw new IllegalAccessError("usuário não encontrado!");
			// }
			//
			// UserDetails userDetails = (user.getDetails() == null) ? new
			// UserDetails() : user.getDetails();
			// userDetails.setBirthDate(details.getBirthDate());
			// userDetails.setName(details.getName());
			//
			// user.setDetails(userDetails);
			//
			// super.saveItem(user);
			//
			// return "success!";
			return true;
		} catch (Exception e) {
			throw e;
		}
	}
}
