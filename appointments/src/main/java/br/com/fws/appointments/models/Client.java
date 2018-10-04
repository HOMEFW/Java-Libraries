package br.com.fws.appointments.models;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonRootName("Client")
@DynamoDBTable(tableName = "Client")
@XmlRootElement(name = "Client")
public class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5330934980470652048L;

	private String clientId;
	private String clientName;
	private Boolean active;
	private String createdDate;

	@DynamoDBTyped(DynamoDBAttributeType.BOOL)
	@DynamoDBAttribute(attributeName = "active")
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@DynamoDBTyped(DynamoDBAttributeType.S)
	@DynamoDBHashKey(attributeName = "clientId")
	@DynamoDBAutoGeneratedKey
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@DynamoDBTyped(DynamoDBAttributeType.S)
	@DynamoDBAttribute(attributeName = "clientName")
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	@DynamoDBTyped(DynamoDBAttributeType.S)
	@DynamoDBAttribute(attributeName = "createdDate")
	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
}
