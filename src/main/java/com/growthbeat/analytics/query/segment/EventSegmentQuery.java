package com.growthbeat.analytics.query.segment;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.growthbeat.analytics.query.Operator;
import com.growthbeat.analytics.query.calculation.CalculationQuery;
import com.growthbeat.analytics.query.filter.FilterQuery;
import com.growthbeat.analytics.query.time.TimeQuery;

@JsonDeserialize(using = JsonDeserializer.None.class)
public class EventSegmentQuery extends SegmentQuery {

	private static final long serialVersionUID = 1L;

	private String eventId;

	private TimeQuery begin;

	private TimeQuery end;

	private CalculationQuery calculationQuery;

	private Operator operator;

	private double value;

	private FilterQuery filterQuery;

	public EventSegmentQuery() {
		super();
		setType(SegmentQueryType.event);
	}

	public EventSegmentQuery(String eventId, TimeQuery begin, TimeQuery end, CalculationQuery calculationQuery, Operator operator,
			double value, FilterQuery filterQuery) {
		this();
		setEventId(eventId);
		setBegin(begin);
		setEnd(end);
		setCalculationQuery(calculationQuery);
		setOperator(operator);
		setValue(value);
		setFilterQuery(filterQuery);
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public TimeQuery getBegin() {
		return begin;
	}

	public void setBegin(TimeQuery begin) {
		this.begin = begin;
	}

	public TimeQuery getEnd() {
		return end;
	}

	public void setEnd(TimeQuery end) {
		this.end = end;
	}

	public CalculationQuery getCalculationQuery() {
		return calculationQuery;
	}

	public void setCalculationQuery(CalculationQuery calculationQuery) {
		this.calculationQuery = calculationQuery;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public FilterQuery getFilterQuery() {
		return filterQuery;
	}

	public void setFilterQuery(FilterQuery filterQuery) {
		this.filterQuery = filterQuery;
	}

}
