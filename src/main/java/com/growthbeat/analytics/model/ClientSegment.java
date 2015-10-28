package com.growthbeat.analytics.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.growthbeat.Context;
import com.growthbeat.model.Model;

public class ClientSegment extends Model {

	private String clientId;
	private String segmentId;

	public static ClientSegment findByClientIdAndSegmentId(String clientId, String segmentId, 
			Date begin, Date end, boolean cacheable, Context context) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clientId", clientId);
		params.put("segmentId", segmentId);
		params.put("begin", begin);
		params.put("end", end);
		params.put("cacheable", cacheable);
		return get(context, "/1/client_segments", params, ClientSegment.class);
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getSegmentId() {
		return segmentId;
	}

	public void setSegmentId(String segmentId) {
		this.segmentId = segmentId;
	}

}
