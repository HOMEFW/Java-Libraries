package br.com.fws.profile.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;

public class UserDetails {

	private boolean active;
	private int accessCounter;
	private String birthDate;
	private boolean blocked;
	private String firstName;
	private String lastName;
	private String lastAccess;

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
	@DynamoDBAttribute(attributeName = "firstName")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@DynamoDBTyped(DynamoDBAttributeType.S)
	@DynamoDBAttribute(attributeName = "lastAccess")
	public String getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(String lastAccess) {
		this.lastAccess = lastAccess;
	}

	@DynamoDBTyped(DynamoDBAttributeType.S)
	@DynamoDBAttribute(attributeName = "lastName")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
