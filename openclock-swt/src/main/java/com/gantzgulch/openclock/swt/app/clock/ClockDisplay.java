package com.gantzgulch.openclock.swt.app.clock;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class ClockDisplay extends Composite {

	private Font font;
	private Label label;
	private Label shadowLabel;

	public ClockDisplay(final Composite parent, //
			final Font font, //
			final String labelText, //
			final Color labelColor, //
			final String shadowLabelText, //
			final Color shadowLabelColor) {

		super(parent, SWT.TRANSPARENT | SWT.NO_BACKGROUND);
		
		this.font = font;
		
		// this.setBackground(getDisplay().getSystemColor(SWT.COLOR_BLACK));

		this.label = new Label(this, SWT.NONE);
		this.label.setFont(this.font);
		this.label.setText(labelText);
		this.label.setForeground(labelColor);
		this.label.setBackground(null);
		
		
		this.shadowLabel = new Label(this, SWT.NONE);
		this.shadowLabel.setFont(this.font);
		this.shadowLabel.setText(shadowLabelText);
		this.shadowLabel.setForeground(shadowLabelColor);
		this.shadowLabel.setBackground(null);
		
		this.label.moveAbove(this.shadowLabel);
		
		final GC gc = new GC(this.shadowLabel);
		
		final Point p = gc.textExtent(shadowLabelText);

		this.label.setBounds(0, 0, p.x, p.y);
		this.shadowLabel.setBounds(0, 0, p.x, p.y);
		
//		final Display display = Display.getCurrent();
//		
//		final Color shadowColor = new Color(display, 0, 30, 0);
//		final Color foreground = new Color(display, 0, 255, 0);
//		
//		this.setBackground(getDisplay().getSystemColor(SWT.COLOR_BLACK));
//	
//		this.dateTimeLabelFont = new Font(getDisplay(), "DSEG14Modern", 48, SWT.BOLD);
//		
//		final FontData[] fontData = this.dateTimeLabelFont.getFontData();
//		
//		final int fontHeight = fontData[0].getHeight() + 20;
//		
//		System.out.println("FontHeight: " + fontHeight);
//		
//		this.timeShadowLabel = new Label(this, SWT.NONE);
//		this.timeShadowLabel.setText("~~ ~~ ~~");
//		this.timeShadowLabel.setFont(dateTimeLabelFont);
//		this.timeShadowLabel.setForeground(shadowColor);
//		this.timeShadowLabel.setBackground(null);
//		this.timeShadowLabel.setBounds(0, 0, 600, fontHeight);
//		
//		this.timeLabel = new Label(this, SWT.NONE);
//		this.timeLabel.setText("~~ ~~ ~~");
//		this.timeLabel.setFont(dateTimeLabelFont);
//		this.timeLabel.setForeground(foreground);
//		this.timeLabel.setBackground(null);
//		this.timeLabel.setBounds(0, 0, 600, fontHeight);
//		this.timeLabel.moveAbove(this.timeShadowLabel);
//
//		this.dateShadowLabel = new Label(this, SWT.NONE);
//		this.dateShadowLabel.setText("~~~~~~~~~~");
//		this.dateShadowLabel.setFont(this.dateTimeLabelFont);
//		this.dateShadowLabel.setForeground(shadowColor);
//		this.dateShadowLabel.setBackground(null);
//		this.dateShadowLabel.setBounds(0, fontHeight, 600, fontHeight);
//
//		this.dateLabel = new Label(this, SWT.NONE);
//		this.dateLabel.setFont(this.dateTimeLabelFont);
//		this.dateLabel.setForeground(foreground);
//		this.dateLabel.setBackground(null);
//		this.dateLabel.setBounds(0, fontHeight, 600, fontHeight);
//		this.dateLabel.moveAbove(this.dateShadowLabel);

	}


	public void setText(final String text) {
		this.label.setText(text);
	}
}
