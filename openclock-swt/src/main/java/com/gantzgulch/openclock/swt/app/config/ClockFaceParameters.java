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
	
	@JsonProperty("color1")
	private String color1;
	
	@JsonProperty("color2")
	private String color2;
	
	@JsonProperty("color3")
	private String color3;

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
	
	public Color getColor1(final Color defaultColor) {
		return ColorParser.parse(color1, defaultColor);
	}
	
	public Color getColor2(final Color defaultColor) {
		return ColorParser.parse(color2, defaultColor);
	}
	public Color getColor3(final Color defaultColor) {
		return ColorParser.parse(color3, defaultColor);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
