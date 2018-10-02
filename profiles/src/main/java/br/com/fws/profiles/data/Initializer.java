package br.com.fws.profiles.data;

import java.util.ArrayList;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.GlobalSecondaryIndex;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.Projection;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.amazonaws.services.dynamodbv2.util.TableUtils.TableNeverTransitionedToStateException;

public class Initializer {

	protected AmazonDynamoDB client;
	protected DynamoDB dynamoDB;
	protected Table table = null;
	protected ProfileCredentialsProvider credentialsProvider;

	protected final void init() throws AmazonServiceException, AmazonClientException {
		try {
			credentialsProvider = new ProfileCredentialsProvider();
		} catch (Exception e) {
			throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
					+ "Please make sure that your credentials file is at the correct "
					+ "location (~/.aws/credentials), and is in valid format.", e);
		}
	}

	public Initializer() {
		init();
	}

	public void initializeDataBase() {

		if (!getTable("Users")) {
			createTable(UsersConfig("Users"));
		}
	}

	protected Boolean getTable(String tableName) {
		if (tableName.isEmpty()) {
			throw new IllegalArgumentException("Table name is empty!");
		}

		try {
			client = AmazonDynamoDBClientBuilder.standard().withCredentials(credentialsProvider).withRegion("us-east-2")
					.build();
			dynamoDB = new DynamoDB(client);

			Table table = dynamoDB.getTable(tableName);

			if (table != null) {
				System.out.println("Table " + tableName + " exists already!");
				return true;
			} else {
				System.out.println("Table " + tableName + " not found!");
				return false;
			}

		} catch (AmazonServiceException ase) {
			throw new AmazonServiceException("Caught an AmazonServiceException, which means your request made it "
					+ "to AWS, but was rejected with an error response for some reason." + "HTTP Status Code: "
					+ ase.getStatusCode() + "AWS Error Code: " + ase.getErrorCode() + "Error Type: "
					+ ase.getErrorType() + "Request ID: " + ase.getRequestId() + "Error Message: " + ase.getMessage());
		} catch (AmazonClientException ace) {
			throw new AmazonClientException("Caught an AmazonClientException, which means the client encountered "
					+ "a serious internal problem while trying to communicate with AWS, "
					+ "such as not being able to access the network.  Error Message: " + ace.getMessage());
		}
	}

	private void createTable(CreateTableRequest createTableRequest) {

		try {
			TableUtils.createTableIfNotExists(client, createTableRequest);
			TableUtils.waitUntilActive(client, createTableRequest.getTableName());

			System.out.println("Table " + createTableRequest.getTableName() + " created");

		} catch (TableNeverTransitionedToStateException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private CreateTableRequest UsersConfig(String tableName) {

		ArrayList<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
		keySchema.add(new KeySchemaElement().withAttributeName("userId").withKeyType(KeyType.HASH));

		ArrayList<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
		attributeDefinitions
				.add(new AttributeDefinition().withAttributeName("userId").withAttributeType(ScalarAttributeType.S));

		attributeDefinitions.add(new AttributeDefinition().withAttributeName("login").withAttributeType("S"));

		// Initial provisioned throughput settings for the indexes
		ProvisionedThroughput ptIndex = new ProvisionedThroughput().withReadCapacityUnits(1L)
				.withWriteCapacityUnits(1L);

		GlobalSecondaryIndex loginIndex = new GlobalSecondaryIndex().withIndexName("login_index")
				.withProvisionedThroughput(ptIndex)
				.withKeySchema(new KeySchemaElement().withAttributeName("login").withKeyType(KeyType.HASH)) // Sort
				.withProjection(new Projection().withProjectionType("KEYS_ONLY"));

		CreateTableRequest createTableRequest = new CreateTableRequest().withTableName(tableName)
				.withKeySchema(keySchema).withAttributeDefinitions(attributeDefinitions)
				.withProvisionedThroughput(new ProvisionedThroughput(1L, 1L)).withGlobalSecondaryIndexes(loginIndex);

		return createTableRequest;
	}

}
