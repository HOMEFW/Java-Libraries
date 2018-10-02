package br.com.fws.profiles.data;

import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;

public interface BaseContract<T> {
	T scanResultItemToObejct(Map<String, AttributeValue> item);
}
