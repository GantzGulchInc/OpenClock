package com.gantzgulch.openclock.swt.app.clock.digital;

import org.eclipse.swt.widgets.Composite;

import com.gantzgulch.openclock.swt.app.config.ClockFaceConfig;

public class ClockFace12HourDigital extends ClockFaceDigital {

	private static String timeFormat = "hh:mm:ss aa";
	private static String timeShadow = "~~:~~:~~ ~~";

	private static String dateFormat = "EEE MMM dd yyyy";
	private static String dateShadow = "~~~ ~~~ ~~ ~~~~";

	public ClockFace12HourDigital(final Composite parent, final ClockFaceConfig clockFaceConfig) {

		super(parent, clockFaceConfig, timeFormat, timeShadow, dateFormat, dateShadow);

	}

}
