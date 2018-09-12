package br.com.fws.profiles.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Users")
public class UserDetails extends User {

	private String id;
	private String name;
	private String email;
	private String birthDate;

	@DynamoDBAttribute(attributeName = "birthDate")
	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	@DynamoDBAttribute(attributeName = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@DynamoDBHashKey(attributeName = "id")
	public String getId() {
		return (super.getUserId().isEmpty() ? id : super.getUserId());
	}

	public void setId(String id) {
		this.id = id;
	}

	@DynamoDBAttribute(attributeName = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
