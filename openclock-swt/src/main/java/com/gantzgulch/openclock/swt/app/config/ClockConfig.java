package com.gantzgulch.openclock.swt.app.config;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClockConfig {

	@JsonProperty("title")
	private String title;
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("timeZone")
	private String timeZone;

	public String getTitle() {
		return title;
	}
	
	public String getType() {
		return type;
	}
	
	public String getTimeZone() {
		return timeZone;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
