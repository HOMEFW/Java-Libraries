package br.com.fws.profiles;

import java.util.UUID;

import br.com.fws.profiles.data.Base;
import br.com.fws.profiles.entities.User;

public class Login {

	public String doLogin(User data) {
		return "";

	}

	public String doRegister(User data) {

		Base<User> base = new Base<User>();

		if (data.getUserId() == null) {
			UUID uuid = UUID.randomUUID();
			data.setUserId(uuid.toString().replaceAll("-", ""));
		}

		base.saveItem(data);

		base = null;

		return "something";
	}
}
