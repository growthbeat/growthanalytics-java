package com.growthbeat.analytics.query.metric;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.growthbeat.utils.JsonUtils;

public class JsonMetricQueryDeserializer extends JsonDeserializer<MetricQuery> {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public MetricQuery deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

		ObjectCodec objectCodec = jp.getCodec();
		Map<String, Object> map = objectCodec.readValue(jp, new TypeReference<Map<String, Object>>() {
		});
		String json = JsonUtils.serialize(map);

		if (!map.containsKey("type") || map.get("type") == null)
			return null;

		MetricQueryType type = null;
		try {
			type = MetricQueryType.valueOf((String) map.get("type"));
		} catch (IllegalArgumentException e) {
			logger.warn("Invalid query type: " + e.getMessage());
			return null;
		}

		switch (type) {
		case constant:
			return JsonUtils.deserialize(json, ConstantMetricQuery.class);
		case event:
			return JsonUtils.deserialize(json, EventMetricQuery.class);
		case metric:
			return JsonUtils.deserialize(json, MetricMetricQuery.class);
		case add:
			return JsonUtils.deserialize(json, AddMetricQuery.class);
		case multiply:
			return JsonUtils.deserialize(json, MultiplyMetricQuery.class);
		case subtract:
			return JsonUtils.deserialize(json, SubtractMetricQuery.class);
		case divide:
			return JsonUtils.deserialize(json, DivideMetricQuery.class);
		default:
			logger.warn("Invalid query type: null");
			return null;
		}

	}

}