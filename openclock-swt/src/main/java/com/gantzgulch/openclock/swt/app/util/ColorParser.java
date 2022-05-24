package com.gantzgulch.openclock.swt.app.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

import com.gantzgulch.logging.core.GGLogger;

public class ColorParser {

	private static final GGLogger LOG = GGLogger.getLogger(ColorParser.class);
	
	private static final String COLOR_24 = "^#(\\p{XDigit}\\p{XDigit})(\\p{XDigit}\\p{XDigit})(\\p{XDigit}\\p{XDigit})$";
	private static final String COLOR_12 = "^#(\\p{XDigit})(\\p{XDigit})(\\p{XDigit})$";
	
	private static final Pattern COLOR_24_PATTERN = Pattern.compile(COLOR_24, Pattern.CASE_INSENSITIVE);
	private static final Pattern COLOR_12_PATTERN = Pattern.compile(COLOR_12, Pattern.CASE_INSENSITIVE);
	
	public static Color parse(final String colorText, final Color defaultColor) {
		
		if( StringUtils.isBlank(colorText) ) {
			return defaultColor;
		}
		
		final Matcher m24 = COLOR_24_PATTERN.matcher(colorText);
		
		if( m24.matches() ) {
			final RGB rgb = toRGB(m24.group(1), m24.group(2), m24.group(3));
			LOG.info("parse: rgb: %s", rgb);
			return new Color(rgb);
		}
		
		return defaultColor;
	}
	
	public static RGB toRGB(final String redHex, final String greenHex, final String blueHex) {
		
		final int red = Integer.parseInt(redHex, 16);
		final int green = Integer.parseInt(greenHex, 16); 
		final int blue = Integer.parseInt(blueHex, 16);
		
		return new RGB(red, green, blue);
	}
}
