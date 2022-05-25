package com.gantzgulch.openclock.swt.app.config;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gantzgulch.openclock.swt.app.util.ColorParser;

public class ClockFaceParameters {

	@JsonProperty("titleFont")
	private FontConfig titleFont;

	@JsonProperty("clockFont")
	private FontConfig clockFont;

	@JsonProperty("background")
	private String background;
	
	public FontConfig getTitleFont() {
		
		if( titleFont == null ) {
			titleFont = new FontConfig();
		}
		
		return titleFont;
	}
	
	
	public FontConfig getClockFont() {
		
		if( clockFont == null ) {
			clockFont = new FontConfig();
		}
		
		return clockFont;
	}
	
	public Color getBackground() {
		return ColorParser.parse(background, Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
