package com.growthbeat.analytics.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.growthbeat.Context;
import com.growthbeat.model.Model;

public class ClientTag extends Model {

	private String clientId;
	private String tagId;
	private String value;
	private Date updated;

	public static ClientTag findByClientIdAndTagId(String clientId, String tagId, Context context) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clientId", clientId);
		params.put("tagId", tagId);

		return get(context, "/1/client_tags", params, ClientTag.class);
	}

	public static List<ClientTag> findByClientId(String clientId, Context context) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clientId", clientId);

		return get(context, "/1/client_tags", params, new TypeReference<List<ClientTag>>() {
		});
	}

	public static List<ClientTag> findByTagId(String tagId, Interger limit, Context context) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tagId", tagId);
		if (limit != null)
			params.put("limit", limit);

		return get(context, "/1/client_tags", params, new TypeReference<List<ClientTag>>() {
		});
	}

	public static ClientTag create(String clientId, String tagId, String value, Context context) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clientId", clientId);
		params.put("tagId", tagId);
		if (value != null)
			params.put("value", value);
		return post(context, "/1/client_tags", params, ClientTag.class);
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}
