package com.growthbeat.analytics;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.growthbeat.Context;
import com.growthbeat.analytics.model.Application;
import com.growthbeat.analytics.model.ClientEvent;
import com.growthbeat.analytics.model.ClientSegment;
import com.growthbeat.analytics.model.ClientTag;
import com.growthbeat.analytics.model.DataPoint;
import com.growthbeat.analytics.model.Event;
import com.growthbeat.analytics.model.Metric;
import com.growthbeat.analytics.model.Segment;
import com.growthbeat.analytics.model.Tag;
import com.growthbeat.analytics.query.datapoint.DataPointQuery;
import com.growthbeat.analytics.query.filter.FilterQuery;
import com.growthbeat.analytics.query.metric.MetricQuery;
import com.growthbeat.analytics.query.segment.SegmentQuery;
import com.growthbeat.model.Order;

public class GrowthAnalytics {

	private static final String BASE_URL = "https://api.analytics.growthbeat.com";
	private Context context = new Context();

	public GrowthAnalytics() {
		super();
		context.getGrowthbeatHttpClient().setBaseUrl(BASE_URL);
	}

	public GrowthAnalytics(String credentialId) {
		this();
		context.setCredentialId(credentialId);
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public com.growthbeat.model.Application initialize(String applicationId) {
		return Application.initialize(applicationId, context);
	}

	public List<ClientEvent> findClientEventsByEventId(String eventId, Date begin, Date end, String exclusiveId, Order order,
			FilterQuery filterQuery, Integer limit) {
		return ClientEvent.findByEventId(eventId, begin, end, exclusiveId, order, filterQuery, limit, context);
	}

	public List<ClientEvent> findClientEventsByClientIdAndEventId(String clientId, String eventId, Date begin, Date end,
			String exclusiveId, Order order, Integer limit) {
		return ClientEvent.findByClientIdAndEventId(clientId, eventId, begin, end, exclusiveId, order, limit, context);
	}

	public ClientEvent createClientEvent(String clientId, String eventId, Map<String, String> properties) {
		return ClientEvent.create(clientId, eventId, properties, context);
	}

	public ClientSegment findClientSegmentByClientIdAndSegmentId(String clientId, String segmentId) {
		return ClientSegment.findByClientIdAndSegmentId(clientId, segmentId, context);
	}

	public ClientTag findClientTagByClientIdAndTagId(String clientId, String tagId) {
		return ClientTag.findByClientIdAndTagId(clientId, tagId, context);
	}

	public List<ClientTag> findClientTagsByClientId(String clientId) {
		return ClientTag.findByClientId(clientId, context);
	}

	public List<ClientTag> findClientTagsByTagId(String tagId, Interger limit) {
		return ClientTag.findByTagId(tagId, limit, context);
	}

	public ClientTag createClientTag(String clientId, String tagId, String value) {
		return ClientTag.create(clientId, tagId, value, context);
	}

	public List<DataPoint> findDataPointsByDataPointQuery(DataPointQuery dataPointQuery, Date begin, Date end, boolean cacheable) {
		return DataPoint.findByDataPointQuery(dataPointQuery, begin, end, cacheable, context);
	}

	public Event findEventById(String id) {
		return Event.findById(id, context);
	}

	public List<Event> findEventsByParentEventId(String parentEventId, Order order, Integer page, Integer limit, Boolean onlyLeaf) {
		return Event.findByParentEventId(parentEventId, order, page, limit, onlyLeaf, context);
	}

	public Event updateEvent(String id, String name, String description) {
		return Event.update(id, name, description, context);
	}

	public void deleteEventById(String id) {
		Event.deleteById(id, context);
	}

	public Metric findMetricById(String id) {
		return Metric.findById(id, context);
	}

	public List<Metric> findMetricsByParentMetricId(String parentMetricId, Order order, Integer page, Integer limit, Boolean onlyLeaf) {
		return Metric.findByParentMetricId(parentMetricId, order, page, limit, onlyLeaf, context);
	}

	public Metric updateMetric(String id, String name, String description, MetricQuery query, Integer color) {
		return Metric.update(id, name, description, query, color, context);
	}

	public void deleteMetricById(String id) {
		Metric.deleteById(id, context);
	}

	public Segment findSegmentById(String id) {
		return Segment.findById(id, context);
	}

	public List<Segment> findSegmentsByParentSegmentId(String parentSegmentId, Order order, Integer page, Integer limit, Boolean onlyLeaf) {
		return Segment.findByParentSegmentId(parentSegmentId, order, page, limit, onlyLeaf, context);
	}

	public Set<String> findClientIdsBySegmentQuery(String applicationId, SegmentQuery segmentQuery) {
		return Segment.findClientIdsBySegmentQuery(applicationId, segmentQuery, context);
	}

	public Segment updateSegment(String id, String name, String description, SegmentQuery query) {
		return Segment.update(id, name, description, query, context);
	}

	public Segment updateSegmentSize(String id) {
		return Segment.updateSize(id, context);
	}

	public void deleteSegmentById(String id) {
		Segment.deleteById(id, context);
	}

	public Tag findTagById(String id) {
		return Tag.findById(id, context);
	}

	public List<Tag> findTagsByParentTagId(String parentTagId, Order order, Integer page, Integer limit, Boolean onlyLeaf) {
		return Tag.findByParentTagId(parentTagId, order, page, limit, onlyLeaf, context);
	}

	public Tag updateTag(String id, String name, String description) {
		return Tag.update(id, name, description, context);
	}

	public void deleteTagById(String id) {
		Tag.deleteById(id, context);
	}

}
