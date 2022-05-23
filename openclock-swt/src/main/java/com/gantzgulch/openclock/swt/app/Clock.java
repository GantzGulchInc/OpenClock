package com.gantzgulch.openclock.swt.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

public class Clock extends Composite {

	private Font dateTimeLabelFont;
	
	private Label timeLabel;
	private Label timeShadowLabel;
	private Label dateLabel;
	private Label dateShadowLabel;

	private TimeZone tz;
	private String timeFormat = "HH:mm:ss";
	private SimpleDateFormat timeFormatter = new SimpleDateFormat(this.timeFormat);

	private String dateFormat = "yyyy/MM/dd";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(this.dateFormat);

	public Clock(final Composite parent, final String timezone) {

		super(parent, SWT.NONE);

		this.tz = TimeZone.getTimeZone(timezone);
		this.timeFormatter.setTimeZone(this.tz);
		this.dateFormatter.setTimeZone(this.tz);
		
		final Display display = Display.getCurrent();
		
		final Color shadowColor = new Color(display, 0, 30, 0);
		final Color foreground = new Color(display, 0, 255, 0);
		
		// this.setLayout(new FillLayout());
		this.setBackground(getDisplay().getSystemColor(SWT.COLOR_BLACK));
	
		this.dateTimeLabelFont = new Font(getDisplay(), "DSEG14Modern", 48, SWT.BOLD);
		
		final FontData[] fontData = this.dateTimeLabelFont.getFontData();
		
		final int fontHeight = fontData[0].getHeight() + 20;
		
		System.out.println("FontHeight: " + fontHeight);
		
		this.timeShadowLabel = new Label(this, SWT.NONE);
		this.timeShadowLabel.setText("~~ ~~ ~~");
		this.timeShadowLabel.setFont(dateTimeLabelFont);
		this.timeShadowLabel.setForeground(shadowColor);
		this.timeShadowLabel.setBackground(null);
		this.timeShadowLabel.setBounds(0, 0, 600, fontHeight);
		
		this.timeLabel = new Label(this, SWT.NONE);
		this.timeLabel.setText("~~ ~~ ~~");
		this.timeLabel.setFont(dateTimeLabelFont);
		this.timeLabel.setForeground(foreground);
		this.timeLabel.setBackground(null);
		this.timeLabel.setBounds(0, 0, 600, fontHeight);
		this.timeLabel.moveAbove(this.timeShadowLabel);

		this.dateShadowLabel = new Label(this, SWT.NONE);
		this.dateShadowLabel.setText("~~~~~~~~~~");
		this.dateShadowLabel.setFont(this.dateTimeLabelFont);
		this.dateShadowLabel.setForeground(shadowColor);
		this.dateShadowLabel.setBackground(null);
		this.dateShadowLabel.setBounds(0, fontHeight, 600, fontHeight);

		this.dateLabel = new Label(this, SWT.NONE);
		this.dateLabel.setFont(this.dateTimeLabelFont);
		this.dateLabel.setForeground(foreground);
		this.dateLabel.setBackground(null);
		this.dateLabel.setBounds(0, fontHeight, 600, fontHeight);
		this.dateLabel.moveAbove(this.dateShadowLabel);

	}

	public void update() {
		
		getDisplay().asyncExec(this::updateInternal);
	}

	private void updateInternal() {
		
		final Date now = new Date();
		
		final String timeString = this.timeFormatter.format(now);
		final String dateString = this.dateFormatter.format(now);

		timeLabel.setText(timeString);
		dateLabel.setText(dateString);
	}

}
