package br.com.fws.profiles.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;

public class UserDetails {

	private boolean active;
	private int accessCounter;
	private String birthDate;
	private boolean blocked;
	private String name;
	private String ultimoAcesso;

	@DynamoDBTyped(DynamoDBAttributeType.N)
	@DynamoDBAttribute(attributeName = "accessCounter")
	public int getAccessCounter() {
		return accessCounter;
	}

	public void setAccessCounter(int accessCounter) {
		this.accessCounter = accessCounter;
	}

	@DynamoDBTyped(DynamoDBAttributeType.BOOL)
	@DynamoDBAttribute(attributeName = "active")
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@DynamoDBTyped(DynamoDBAttributeType.S)
	@DynamoDBAttribute(attributeName = "birthDate")
	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	@DynamoDBTyped(DynamoDBAttributeType.BOOL)
	@DynamoDBAttribute(attributeName = "blocked")
	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	@DynamoDBTyped(DynamoDBAttributeType.S)
	@DynamoDBAttribute(attributeName = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@DynamoDBTyped(DynamoDBAttributeType.S)
	@DynamoDBAttribute(attributeName = "lastAccess")
	public String getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(String ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}

}
