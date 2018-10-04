package br.com.fws.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.ScanResultPage;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.Condition;

public class Base<T> {

	protected int pageSize = 10;

	public static AmazonDynamoDB client;
	DynamoDBMapper mapper;
	ProfileCredentialsProvider credentialsProvider;

	public Base() {

		try {
			credentialsProvider = new ProfileCredentialsProvider();
		} catch (Exception e) {
			throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
					+ "Please make sure that your credentials file is at the correct "
					+ "location (~/.aws/credentials), and is in valid format.", e);
		}
		client = AmazonDynamoDBClientBuilder.standard().withCredentials(credentialsProvider).withRegion("us-east-2")
				.build();
	}

	protected List<T> getListByScan(DynamoDBScanExpression scanExpression, Class<T> T) {
		mapper = new DynamoDBMapper(client);
		scanExpression.withLimit(pageSize);
		List<T> replies = mapper.scan(T, scanExpression);
		return replies;
	}

	protected List<T> getListByScanResultPage(DynamoDBScanExpression scanExpression, Class<T> T,
			Map<String, AttributeValue> startKey) {

		mapper = new DynamoDBMapper(client);

		scanExpression.withLimit(pageSize);

		if (startKey != null) {
			scanExpression.withExclusiveStartKey(startKey);
		}

		ScanResultPage<T> scanPage = mapper.scanPage(T, scanExpression);

		return scanPage.getResults();
	}

	protected T getItem(DynamoDBScanExpression scanExpression, Class<T> T) {
		try {
			mapper = new DynamoDBMapper(client);
			List<T> replies = mapper.scan(T, scanExpression);
			if (replies.size() > 0) {
				return replies.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	protected final T scanResultItemToObject(HashMap<String, Condition> scan, Class<T> T) {

		List<T> list = scanResultToListObject(scan, T);
		if (list != null && list.size() > 0)
			return list.get(0);

		return null;
	};

	protected final List<T> scanResultToListObject(HashMap<String, Condition> scan, Class<T> T) {

		mapper = new DynamoDBMapper(client);
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		scanExpression.setScanFilter(scan);
		List<T> replies = mapper.scan(T, scanExpression);
		return replies;

	};

	protected void saveItem(T item) {
		try {
			mapper = new DynamoDBMapper(client);
			mapper.generateCreateTableRequest(item.getClass());
			mapper.save(item);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void deleteItem(T item) {
		mapper = new DynamoDBMapper(client);
		mapper.delete(item);
	}

	/**
	 * @param tableName
	 * @param updateItemSpec
	 * @return updated items
	 * @throws Exception
	 */
	protected UpdateItemOutcome updateItem(String tableName, UpdateItemSpec updateItemSpec) throws Exception {
		DynamoDB dynamoDB = new DynamoDB(client);
		Table table = dynamoDB.getTable(tableName);
		return table.updateItem(updateItemSpec);

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
	}
}
