package com.growthbeat.analytics.query.datapoint;

import java.util.List;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.growthbeat.analytics.model.DataPoint;

@JsonDeserialize(using = JsonDeserializer.None.class)
public class ConstantDataPointQuery extends DataPointQuery {

	private static final long serialVersionUID = 1L;

	List<DataPoint> dataPoints;

	public ConstantDataPointQuery() {
		super();
		setType(DataPointQueryType.constant);
	}

	public ConstantDataPointQuery(List<DataPoint> dataPoints) {
		this();
		setDataPoints(dataPoints);
	}

	public List<DataPoint> getDataPoints() {
		return dataPoints;
	}

	public void setDataPoints(List<DataPoint> dataPoints) {
		this.dataPoints = dataPoints;
	}

}
