package com.gantzgulch.openclock.swt.app.clock;

import org.eclipse.swt.widgets.Shell;

import com.gantzgulch.openclock.swt.app.clock.digital.ClockFace12HourDigital;
import com.gantzgulch.openclock.swt.app.clock.digital.ClockFace24HourDigital;
import com.gantzgulch.openclock.swt.app.config.ClockFaceConfig;

public class ClockFaceFactory {

	public static AbstractClockFace createClockFace(final Shell shell, final ClockFaceConfig clockFaceConfig) {

		switch (clockFaceConfig.getType()) {

		case "Digital24Hour":
			return new ClockFace24HourDigital(shell, clockFaceConfig);

		case "Digital12Hour":
			return new ClockFace12HourDigital(shell, clockFaceConfig);

		default:
			throw new RuntimeException("Unknown clock face type: " + clockFaceConfig.getType());
		}
	}

}
