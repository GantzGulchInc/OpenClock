package com.gantzgulch.openclock.swt.app.config;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FontConfig {

	@JsonProperty("name")
	private String name;

	@JsonProperty("height")
	private int height = 12;

	@JsonProperty("bold")
	private boolean bold = false;
	
	@JsonProperty("italic")
	private boolean italic = false;

	public String getName() {
		return StringUtils.defaultIfBlank(name, "Arial");
	}
	
	public int getHeight() {
		return height;
	}
	
	public boolean isBold() {
		return bold;
	}
	
	public boolean isItalic() {
		return italic;
	}
	
	private int getStyles() {
		return (isBold() ? SWT.BOLD : SWT.NONE) | ( isItalic() ? SWT.ITALIC : SWT.NONE); 
	}

	public Font getFont() {
		return new Font(Display.getCurrent(), getName(), getHeight(), getStyles());
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
