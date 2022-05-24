package com.gantzgulch.openclock.swt.app.config;

import org.eclipse.swt.graphics.Color;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gantzgulch.openclock.swt.app.util.ColorParser;

public class ClockDisplayConfig {

	@JsonProperty("columns")
	private int columns = 2;
	
	@JsonProperty("background")
	private String background;
	
	public int getColumns() {
		return columns;
	}
	
	public Color getBackground(final Color defaultColor) {
		return ColorParser.parse(background, defaultColor);
	}
}
