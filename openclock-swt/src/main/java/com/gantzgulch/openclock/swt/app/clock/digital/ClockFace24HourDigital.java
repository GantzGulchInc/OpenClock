package com.gantzgulch.openclock.swt.app.clock.digital;

import org.eclipse.swt.widgets.Composite;

import com.gantzgulch.openclock.swt.app.config.ClockFaceConfig;

public class ClockFace24HourDigital extends ClockFaceDigital {

	private static String timeFormat = "HH:mm:ss";
	private static String timeShadow = "88:88:88";

	private static String dateFormat = "yyyy-MM-dd";
	private static String dateShadow = "8888-88-88";

	public ClockFace24HourDigital(final Composite parent, final ClockFaceConfig clockFaceConfig) {
		super(parent, clockFaceConfig,  timeFormat, timeShadow, dateFormat, dateShadow);
	}

}
