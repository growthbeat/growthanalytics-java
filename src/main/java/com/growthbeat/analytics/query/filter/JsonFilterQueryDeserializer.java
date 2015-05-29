package com.growthbeat.analytics.query.filter;

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

public class JsonFilterQueryDeserializer extends JsonDeserializer<FilterQuery> {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public FilterQuery deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

		ObjectCodec objectCodec = jp.getCodec();
		Map<String, Object> map = objectCodec.readValue(jp, new TypeReference<Map<String, Object>>() {
		});
		String json = JsonUtils.serialize(map);

		if (!map.containsKey("type") || map.get("type") == null)
			return null;

		FilterQueryType type = null;
		try {
			type = FilterQueryType.valueOf((String) map.get("type"));
		} catch (IllegalArgumentException e) {
			logger.warn("Invalid query type: " + e.getMessage());
			return null;
		}

		switch (type) {
		case segment:
			return JsonUtils.deserialize(json, SegmentFilterQuery.class);
		case property:
			return JsonUtils.deserialize(json, PropertyFilterQuery.class);
		case filter:
			return JsonUtils.deserialize(json, FilterFilterQuery.class);
		case and:
			return JsonUtils.deserialize(json, AndFilterQuery.class);
		case or:
			return JsonUtils.deserialize(json, OrFilterQuery.class);
		case not:
			return JsonUtils.deserialize(json, NotFilterQuery.class);
		default:
			logger.warn("Invalid query type: null");
			return null;
		}

	}

}