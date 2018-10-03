package br.com.fws.core.client;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import br.com.fws.core.data.Base;
import br.com.fws.dictionary.Message;

public class Client extends Base<br.com.fws.entities.Client> {

	public void doRegister(br.com.fws.entities.Client data) throws Exception {
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

	private boolean isValid(br.com.fws.entities.Client data) {
		if (data.getClientName().isEmpty()) {
			throw new IllegalArgumentException(Message.INVALIDCLIENTNAME);
		}
		return true;
	}

	public List<br.com.fws.entities.Client> getAllClients() {
		DynamoDBScanExpression scan = new DynamoDBScanExpression();
		super.pageSize = 100;
		List<br.com.fws.entities.Client> results = super.getListByScanResultPage(scan, br.com.fws.entities.Client.class,
				null);
		return results;
	}
}
