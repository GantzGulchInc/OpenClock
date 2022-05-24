package com.gantzgulch.openclock.swt.app.config;

import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClockFaceConfig {

	@JsonProperty("title")
	private String title;
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("timeZone")
	private String timeZone;

	@JsonProperty("parameters")
	private ClockFaceParameters parameters;

	public String getTitle() {
		return title;
	}
	
	public String getType() {
		return type;
	}
	
	public TimeZone getTimeZone() {
		return StringUtils.isNotBlank(timeZone) ? TimeZone.getTimeZone(timeZone) : TimeZone.getTimeZone("utc");
	}
	
	public ClockFaceParameters getParameters() {
		
		if( parameters == null ) {
			parameters = new ClockFaceParameters();
		}
		
		return parameters;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
