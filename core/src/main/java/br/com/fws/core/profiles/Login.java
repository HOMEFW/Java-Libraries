package br.com.fws.core.profiles;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import br.com.fws.commom.Encryption;
import br.com.fws.core.data.Base;
import br.com.fws.entities.User;

public class Login extends Base<User> {

	public Login() {
	}

	/**
	 * @param data
	 *            - login & password obrigatórios
	 * @return UserId em caso de sucesso
	 * @throws IllegalArgumentException
	 *             Em caso de erro na informação
	 * @throws Exception
	 *             for any other exception
	 */
	public String doLogin(User data) throws Exception {
		try {

			if (data.getLogin() != null && data.getLogin().length() <= 0)
				throw new IllegalArgumentException("please fill user information!");

			if (data.getPassword() != null && data.getPassword().length() <= 0)
				throw new IllegalArgumentException("please fill password information!");

			Map<String, AttributeValue> map = new HashMap<String, AttributeValue>();
			map.put(":login", new AttributeValue().withS(data.getLogin()));

			DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("login = :login")
					.withExpressionAttributeValues(map);

			User user = super.getItem(scanExpression, User.class);

			if (user == null || !user.getPassword().equals(Encryption.Generate(data.getPassword()))) {
				throw new IllegalArgumentException("Invalid user or password!");
			} else {
				return user.getUserId();
			}
		} catch (Exception e) {
			throw e;
		}
	}
}
