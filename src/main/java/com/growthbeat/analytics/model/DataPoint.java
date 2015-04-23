package com.growthbeat.analytics.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.utils.DateUtils;
import org.codehaus.jackson.type.TypeReference;

import com.growthbeat.Context;
import com.growthbeat.model.Model;

public class DataPoint extends Model {

	private String category;
	private String value;

	public static List<DataPoint> findByDataPointQuery(String dataPointQuery, Date begin, Date end, Context context) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dataPointQuery", dataPointQuery);
		if (begin != null)
			params.put("begin", DateUtils.formatDate(begin, ISO_8601_DATETIME_FORMAT));
		if (end != null)
			params.put("end", DateUtils.formatDate(end, ISO_8601_DATETIME_FORMAT));

		return get(context, "/1/data_points", params, new TypeReference<List<DataPoint>>() {
		});
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
