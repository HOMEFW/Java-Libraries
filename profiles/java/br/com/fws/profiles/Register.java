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
import br.com.fws.profiles.entities.User;
import br.com.fws.profiles.entities.UserDetails;;

public class Register extends Base<User> {

	public Register() {
		super();
	}

	/**
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public String doRegister(User data) throws Exception {
		try {

			if (data.getLogin() == null && data.getEmail() == null) {
				return Mensagens.DADOSINVALIDOS;
			}

			if (loginExists(data)) {
				return Mensagens.LOGINEXISTE;
			}

			if (emailExists(data)) {
				return Mensagens.EMAILEXISTE;
			}

			if (data.getUserId() == null) {
				UUID uuid = UUID.randomUUID();
				data.setUserId(uuid.toString().replaceAll("-", ""));
				data.setPassword(Encryption.Generate(data.getPassword()));
				String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
				data.setRegisterDate(sdf.format(new Date()));
			}

			super.saveItem(data);

			return Mensagens.COMSUCESSO;
		} catch (Exception e) {
			return Mensagens.SEMSUCESSO;
			// throw e;
		}
	}

	private Boolean loginExists(User data) {
		Boolean valid = false;
		if (data.getLogin() != null) {
			Map<String, AttributeValue> map = new HashMap<String, AttributeValue>();
			map.put(":login", new AttributeValue().withS(data.getLogin()));

			DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("login = :login")
					.withExpressionAttributeValues(map);

			User user = super.getItem(scanExpression, User.class);
			valid = (user != null);
		}
		return valid;
	}

	private Boolean emailExists(User data) {
		Boolean valid = false;

		if (data.getEmail() != null) {
			Map<String, AttributeValue> map = new HashMap<String, AttributeValue>();
			map.put(":email", new AttributeValue().withS(data.getEmail()));

			DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("email = :email")
					.withExpressionAttributeValues(map);

			User user = super.getItem(scanExpression, User.class);

			valid = (user != null);
		}

		return valid;
	}

	public String doUpdate(String userId, UserDetails details) throws Exception, IllegalArgumentException {
		try {

			if (userId == null || details == null) {
				return Mensagens.DADOSINVALIDOS;
			}

			Map<String, AttributeValue> map = new HashMap<String, AttributeValue>();
			map.put(":userId", new AttributeValue().withS(userId));

			DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
					.withFilterExpression("userId = :userId").withExpressionAttributeValues(map);

			User user = super.getItem(scanExpression, User.class);

			if (user == null) {
				return Mensagens.USUARIONAOENCONTRADO;
			}

			UserDetails userDetails = (user.getDetails() == null) ? new UserDetails() : user.getDetails();
			userDetails.setBirthDate(details.getBirthDate());
			userDetails.setName(details.getName());

			user.setDetails(userDetails);

			super.saveItem(user);

			return Mensagens.COMSUCESSO;

		} catch (Exception e) {
			return Mensagens.SEMSUCESSO;
			// throw e;
		}
	}
}
