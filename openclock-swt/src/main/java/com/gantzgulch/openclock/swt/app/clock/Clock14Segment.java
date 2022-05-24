package com.gantzgulch.openclock.swt.app.clock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class Clock14Segment extends BaseClock {

	private String title;
	
	private Font font;
	private Color color;
	private Color shadowColor;

	private TimeZone tz;

	private String timeFormat;
	private String timeShadow;
	private SimpleDateFormat timeFormatter;

	private String dateFormat;
	private String dateShadow;
	private SimpleDateFormat dateFormatter;

	private Label titleLabel;
	private ClockDisplay timeDisplay;
	private ClockDisplay dateDisplay;

	public Clock14Segment(//
			final Composite parent, //
			final String title, //
			final Font font, //
			final Color color, //
			final Color shadowColor, //
			final TimeZone timezone, //
			final String timeFormat, //
			final String timeShadow, //
			final String dateFormat, //
			final String dateShadow) {

		super(parent);

		this.title = title;
		
		this.font = font;
		this.color = color;
		this.shadowColor = shadowColor;

		this.tz = timezone;

		this.timeFormat = timeFormat;
		this.timeShadow = timeShadow;
		this.timeFormatter = new SimpleDateFormat(this.timeFormat);
		this.timeFormatter.setTimeZone(this.tz);

		this.dateFormat = dateFormat;
		this.dateShadow = dateShadow;
		this.dateFormatter = new SimpleDateFormat(this.dateFormat);
		this.dateFormatter.setTimeZone(this.tz);

		createUi();
	}

	private void createUi() {

		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		gridLayout.makeColumnsEqualWidth = true;
		gridLayout.marginHeight = 20;
		gridLayout.marginWidth = 20;

		this.setLayout(gridLayout);

		this.titleLabel = new Label(this, SWT.NONE);
		this.titleLabel.setText(this.title);
		this.titleLabel.setFont( updateFont(getDisplay().getSystemFont(), 24));
		this.titleLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));
		
		this.timeDisplay = new ClockDisplay(this, this.font, timeShadow, color, timeShadow, shadowColor);
		this.timeDisplay.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));

		this.dateDisplay = new ClockDisplay(this, this.font, dateShadow, color, dateShadow, shadowColor);
		this.dateDisplay.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));
	}

	protected void updateInternal() {

		if (!getDisplay().isDisposed()) {
			
			final Date now = new Date();

			final String timeString = this.timeFormatter.format(now);
			final String dateString = this.dateFormatter.format(now);

			timeDisplay.setText(timeString);
			dateDisplay.setText(dateString);
		}
	}

}
