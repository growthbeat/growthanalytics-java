package com.growthbeat.analytics.query.filter;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.growthbeat.analytics.query.segment.SegmentQuery;

@JsonDeserialize(using = JsonDeserializer.None.class)
public class SegmentFilterQuery extends FilterQuery {

	private static final long serialVersionUID = 1L;

	private SegmentQuery segmentQuery;

	public SegmentFilterQuery() {
		super();
		setType(FilterQueryType.segment);
	}

	public SegmentFilterQuery(SegmentQuery segmentQuery) {
		this();
		setSegmentQuery(segmentQuery);
	}

	public SegmentQuery getSegmentQuery() {
		return segmentQuery;
	}

	public void setSegmentQuery(SegmentQuery segmentQuery) {
		this.segmentQuery = segmentQuery;
	}

}
