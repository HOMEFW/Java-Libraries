package br.com.fws.core.profiles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;

import br.com.fws.core.data.Base;
import br.com.fws.entities.User;

public class Core extends Base<User> {

	public List<User> getAllUsers(int pageSize, Map<String, AttributeValue> startKey) {
		DynamoDBScanExpression scan = new DynamoDBScanExpression();
		super.pageSize = pageSize;
		List<User> results = super.getListByScanResultPage(scan, User.class, startKey);
		return results;
	}

	public List<User> getUserByFirstOrLastName(String name) {

		Map<String, AttributeValue> map = new HashMap<String, AttributeValue>();
		map.put(":first", new AttributeValue().withS(name));
		map.put(":last", new AttributeValue().withS(name));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression(" contains(details.firstName, :first) OR contains(details.lastName, :last) ")
				.withExpressionAttributeValues(map);

		List<User> results = super.getListByScan(scanExpression, User.class);
		return results;
	}

	public List<User> getUsersByEmail(String email) throws Exception {
		try {
			HashMap<String, Condition> scan = new HashMap<String, Condition>();

			Condition condition = new Condition().withComparisonOperator(ComparisonOperator.CONTAINS.toString())
					.withAttributeValueList(new AttributeValue().withS(email));
			scan.put("email", condition);

			return super.scanResultToListObject(scan, User.class);
		} catch (Exception e) {
			throw e;
		}
	}

	public User getUserByEmail(String email) throws Exception {
		try {
			HashMap<String, Condition> scan = new HashMap<String, Condition>();

			Condition condition = new Condition().withComparisonOperator(ComparisonOperator.BEGINS_WITH.toString())
					.withAttributeValueList(new AttributeValue().withS(email));
			scan.put("email", condition);

			return super.scanResultItemToObject(scan, User.class);
		} catch (Exception e) {
			throw e;
		}
	}

}
