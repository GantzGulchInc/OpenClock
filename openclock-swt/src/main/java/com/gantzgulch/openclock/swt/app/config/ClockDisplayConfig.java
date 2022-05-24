package com.gantzgulch.openclock.swt.app.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClockDisplayConfig {

	@JsonProperty("columns")
	private int columns = 2;
	
	public int getColumns() {
		return columns;
	}
}
