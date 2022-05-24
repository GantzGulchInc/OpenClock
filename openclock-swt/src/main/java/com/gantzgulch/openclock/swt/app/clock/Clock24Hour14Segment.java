package com.gantzgulch.openclock.swt.app.clock;

import java.util.TimeZone;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class Clock24Hour14Segment extends Clock14Segment {

	private static Font font = new Font(Display.getCurrent(), "DSEG14Modern", 48, SWT.BOLD);
	private static Color color = new Color(Display.getCurrent(), 0, 200, 0);
	private static Color shadowColor = new Color(Display.getCurrent(), 0, 20, 0);

	private static String timeFormat = "HH:mm:ss";
	private static String timeShadow = "~~:~~:~~";

	private static String dateFormat = "yyyy/MM/dd";
	private static String dateShadow = "~~~~~~~~~~";

	public Clock24Hour14Segment(final Composite parent, final String title, final TimeZone timezone) {
		super(parent, title, font, color, shadowColor, timezone, timeFormat, timeShadow, dateFormat, dateShadow);
	}

}
