package com.growthbeat.analytics.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.growthbeat.Context;
import com.growthbeat.analytics.query.datapoint.DataPointQuery;
import com.growthbeat.constants.Constants;
import com.growthbeat.model.Model;
import com.growthbeat.utils.DateUtils;
import com.growthbeat.utils.JsonUtils;

public class DataPoint extends Model {

	private String category;
	private String value;

	public static List<DataPoint> findByDataPointQuery(DataPointQuery dataPointQuery, Date begin, Date end, boolean cacheable, boolean realtime, Context context) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dataPointQuery", JsonUtils.serialize(dataPointQuery));
		if (begin != null)
			params.put("begin", DateUtils.dateTimeStringFromDateWithFormat(begin, Constants.ISO_8601_DATETIME_FORMAT));
		if (end != null)
			params.put("end", DateUtils.dateTimeStringFromDateWithFormat(end, Constants.ISO_8601_DATETIME_FORMAT));
		params.put("cacheable", cacheable);
		params.put("realtime", realtime);

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
