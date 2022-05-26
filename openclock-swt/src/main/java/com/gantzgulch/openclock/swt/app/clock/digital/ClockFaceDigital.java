package com.gantzgulch.openclock.swt.app.clock.digital;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.gantzgulch.openclock.swt.app.clock.AbstractClockFace;
import com.gantzgulch.openclock.swt.app.config.ClockFaceConfig;

public class ClockFaceDigital extends AbstractClockFace {

	private final ClockFaceConfig clockFaceConfig;
	
	private String timeFormat;
	private String timeShadow;
	private SimpleDateFormat timeFormatter;

	private String dateFormat;
	private String dateShadow;
	private SimpleDateFormat dateFormatter;

	private Label titleLabel;
	private LedDisplay timeDisplay;
	private LedDisplay dateDisplay;

	public ClockFaceDigital(//
			final Composite parent, //
			final ClockFaceConfig clockFaceConfig, //
			final String timeFormat, //
			final String timeShadow, //
			final String dateFormat, //
			final String dateShadow) {

		super(parent, clockFaceConfig);
		
		this.clockFaceConfig = clockFaceConfig;

		this.timeFormat = timeFormat;
		this.timeShadow = timeShadow;
		this.timeFormatter = new SimpleDateFormat(this.timeFormat);
		this.timeFormatter.setTimeZone(this.clockFaceConfig.getTimeZone());

		this.dateFormat = dateFormat;
		this.dateShadow = dateShadow;
		this.dateFormatter = new SimpleDateFormat(this.dateFormat);
		this.dateFormatter.setTimeZone(this.clockFaceConfig.getTimeZone());

		createUi();
	}

	private void createUi() {

		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		gridLayout.makeColumnsEqualWidth = true;
		gridLayout.marginHeight = 20;
		gridLayout.marginWidth = 20;

		this.setLayout(gridLayout);

		//
		// Title
		//
		this.titleLabel = new Label(this, SWT.NONE);
		this.titleLabel.setText(this.clockFaceConfig.getTitle());
		this.titleLabel.setFont( clockFaceConfig.getParameters().getTitleFont().getFont() );
		this.titleLabel.setBackground( clockFaceConfig.getParameters().getBackground() );
		this.titleLabel.setForeground( clockFaceConfig.getParameters().getTitleFont().getColor());
		this.titleLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));

		//
		// Time Display
		//
		this.timeDisplay = new LedDisplay(this, clockFaceConfig, timeShadow, timeShadow);
		this.timeDisplay.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));

		//
		// Date Display
		//
		this.dateDisplay = new LedDisplay(this, clockFaceConfig, dateShadow, dateShadow);
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
