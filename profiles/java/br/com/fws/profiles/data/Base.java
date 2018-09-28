package br.com.fws.profiles.data;

import java.util.List;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

public class Base<T> {

	static AmazonDynamoDB client;
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
		mapper = new DynamoDBMapper(client);
	}

	protected List<T> getListByScan(DynamoDBScanExpression scanExpression, Class<T> T) {

		List<T> replies = mapper.scan(T, scanExpression);
		return replies;
	}

	protected T getItem(DynamoDBScanExpression scanExpression, Class<T> T) {
		try {
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

	protected void saveItem(T item) {
		// DynamoDBMapper mapper = new DynamoDBMapper(client);
		try {
			mapper.generateCreateTableRequest(item.getClass());
			mapper.save(item);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void deleteItem(T item) {
		// DynamoDBMapper mapper = new DynamoDBMapper(client);
		mapper.delete(item);
	}
}
