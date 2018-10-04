package br.com.fws.config;

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
import com.amazonaws.services.dynamodbv2.model.TableDescription;
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
			createTable(usersConfig("Users"));
		}

		if (!getTable("Appointments")) {
			createTable(appointmentsConfig("Appointments"));
		}

		if (!getTable("Client")) {
			createTable(clientConfig("Client"));
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

			TableDescription tableDescription = dynamoDB.getTable(tableName).describe();

			System.out.println("Table " + tableName + " exists already! Status : " + tableDescription.getTableStatus());
			return true;

		} catch (Exception ase) {
			System.out.println("Table " + tableName + " not found!");
			return false;
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

	private CreateTableRequest usersConfig(String tableName) {

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

	private CreateTableRequest appointmentsConfig(String tableName) {

		ArrayList<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
		keySchema.add(new KeySchemaElement().withAttributeName("appointmentsId").withKeyType(KeyType.HASH));

		ArrayList<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
		attributeDefinitions.add(
				new AttributeDefinition().withAttributeName("appointmentsId").withAttributeType(ScalarAttributeType.S));
		attributeDefinitions
				.add(new AttributeDefinition().withAttributeName("userId").withAttributeType(ScalarAttributeType.S));
		attributeDefinitions.add(new AttributeDefinition().withAttributeName("year").withAttributeType("N"));
		attributeDefinitions.add(new AttributeDefinition().withAttributeName("month").withAttributeType("N"));

		ProvisionedThroughput ptIndex = new ProvisionedThroughput().withReadCapacityUnits(1L)
				.withWriteCapacityUnits(1L);

		GlobalSecondaryIndex yearIndex = new GlobalSecondaryIndex().withIndexName("year_index")
				.withProvisionedThroughput(ptIndex)
				.withKeySchema(new KeySchemaElement().withAttributeName("year").withKeyType(KeyType.HASH), // Partition
																											// key
						new KeySchemaElement().withAttributeName("month").withKeyType(KeyType.RANGE)) // Sort
																										// key
				.withProjection(new Projection().withProjectionType("KEYS_ONLY"));

		GlobalSecondaryIndex userIdIndex = new GlobalSecondaryIndex().withIndexName("userId_index")
				.withProvisionedThroughput(ptIndex)
				.withKeySchema(new KeySchemaElement().withAttributeName("userId").withKeyType(KeyType.HASH)) // Partition
																												// key
				.withProjection(new Projection().withProjectionType("KEYS_ONLY"));

		CreateTableRequest createTableRequest = new CreateTableRequest().withTableName(tableName)
				.withKeySchema(keySchema).withAttributeDefinitions(attributeDefinitions)
				.withProvisionedThroughput(new ProvisionedThroughput(1L, 1L))
				.withGlobalSecondaryIndexes(yearIndex, userIdIndex);
		return createTableRequest;
	}

	private CreateTableRequest clientConfig(String tableName) {

		ArrayList<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
		keySchema.add(new KeySchemaElement().withAttributeName("clientId").withKeyType(KeyType.HASH));

		ArrayList<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
		attributeDefinitions
				.add(new AttributeDefinition().withAttributeName("clientId").withAttributeType(ScalarAttributeType.S));

		CreateTableRequest createTableRequest = new CreateTableRequest().withTableName(tableName)
				.withKeySchema(keySchema).withAttributeDefinitions(attributeDefinitions)
				.withProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
		return createTableRequest;
	}

}
