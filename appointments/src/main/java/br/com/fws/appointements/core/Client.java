package br.com.fws.appointements.core;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import br.com.fws.data.Base;
import br.com.fws.profile.dictionary.Message;

public class Client extends Base<br.com.fws.appointments.models.Client> {

	public void doRegister(br.com.fws.appointments.models.Client data) throws Exception {
		try {
			if (isValid(data)) {
				String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
				data.setCreatedDate(sdf.format(new Date()));
				super.saveItem(data);
			}
		} catch (IllegalArgumentException a) {
			throw a;
		} catch (Exception e) {
			throw e;
		}
	}

	private boolean isValid(br.com.fws.appointments.models.Client data) {
		if (data.getClientName() == null || data.getClientName().isEmpty()) {
			throw new IllegalArgumentException(Message.INVALIDCLIENTNAME);
		}

		if (getClient(data) != null) {
			throw new IllegalArgumentException(Message.CLIENTNAMEALREADYEXISTS);
		}

		return true;
	}

	public br.com.fws.appointments.models.Client getClient(br.com.fws.appointments.models.Client data) {

		Map<String, AttributeValue> map = new HashMap<String, AttributeValue>();
		map.put(":clientId", new AttributeValue().withS((data.getClientId() == null ? "" : data.getClientId())));
		map.put(":clientName", new AttributeValue().withS(data.getClientName()));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression(" clientId = :clientId OR clientName = :clientName ")
				.withExpressionAttributeValues(map);
		br.com.fws.appointments.models.Client result = super.getItem(scanExpression, br.com.fws.appointments.models.Client.class);

		return result;
	}

	public br.com.fws.appointments.models.Client[] getAllClients() {
		DynamoDBScanExpression scan = new DynamoDBScanExpression();
		super.pageSize = 100;
		br.com.fws.appointments.models.Client[] results = (br.com.fws.appointments.models.Client[]) super.getListByScanResultPage(scan,
				br.com.fws.appointments.models.Client.class, null).toArray();
		return results;
	}
}
