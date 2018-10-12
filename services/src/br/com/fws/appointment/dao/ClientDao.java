package br.com.fws.appointment.dao;

import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;

import br.com.fws.appointment.models.Client;
import br.com.fws.data.Base;

public class ClientDao extends Base<Client> {

	public final Client getClient(Client data) throws Exception {
		// Map<String, AttributeValue> map = new HashMap<String,
		// AttributeValue>();
		// map.put(":clientId", new AttributeValue().withS((data.getClientId()
		// == null ? "" : data.getClientId())));
		// map.put(":clientName", new
		// AttributeValue().withS(data.getClientName()));
		//
		// DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
		// .withFilterExpression(" clientId = :clientId OR clientName =
		// :clientName ")
		// .withExpressionAttributeValues(map);
		// Client result = super.getItem(scanExpression, Client.class);

		HashMap<String, Condition> scanCondition = new HashMap<String, Condition>();

		if (data.getClientId() != null && !data.getClientId().isEmpty()) {
			Condition condition = new Condition().withComparisonOperator(ComparisonOperator.EQ.toString())
					.withAttributeValueList(new AttributeValue().withS(data.getClientId()));
			scanCondition.put("clientId", condition);
		}

		if (data.getClientName() != null && !data.getClientName().isEmpty()) {
			Condition condition = new Condition().withComparisonOperator(ComparisonOperator.EQ.toString())
					.withAttributeValueList(new AttributeValue().withS(data.getClientName()));
			scanCondition.put("clientName", condition);
		}
		Client result = super.scanResultItemToObject(scanCondition, Client.class);
		return result;
	}

	public final List<Client> getAllClients() throws Exception {
		DynamoDBScanExpression scan = new DynamoDBScanExpression();
		super.pageSize = 100;
		List<Client> results = (List<Client>) super.getListByScanResultPage(scan, Client.class, null);
		return results;
	}

	public final Boolean save(Client data) throws Exception {
		return super.saveItem(data);
	}

	public List<Client> getClientsByFilter(Client data) {
		HashMap<String, Condition> scanCondition = new HashMap<String, Condition>();

		if (data.getClientId() != null && !data.getClientId().isEmpty()) {
			Condition condition = new Condition().withComparisonOperator(ComparisonOperator.EQ.toString())
					.withAttributeValueList(new AttributeValue().withS(data.getClientId()));
			scanCondition.put("clientId", condition);
		}

		if (data.getClientName() != null && !data.getClientName().isEmpty()) {
			Condition condition = new Condition().withComparisonOperator(ComparisonOperator.CONTAINS.toString())
					.withAttributeValueList(new AttributeValue().withS(data.getClientName()));
			scanCondition.put("clientName", condition);
		}

		if (data.getActive() != null) {
			Condition condition = new Condition().withComparisonOperator(ComparisonOperator.EQ)
					.withAttributeValueList(new AttributeValue().withBOOL(data.getActive()));
			scanCondition.put("active", condition);
		}

		List<Client> results = super.scanResultToListObject(scanCondition, Client.class);
		return results;
	}

}
