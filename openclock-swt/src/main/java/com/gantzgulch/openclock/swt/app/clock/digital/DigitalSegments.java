package com.gantzgulch.openclock.swt.app.clock.digital;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.gantzgulch.openclock.swt.app.util.FontUtil;

public class DigitalSegments extends Composite {

	private Font font;
	private Label label;
	private Label shadowLabel;

	public DigitalSegments(final Composite parent, //
			final Font font, //
			final String labelText, //
			final Color labelColor, //
			final String shadowLabelText, //
			final Color shadowLabelColor) {

		super(parent, SWT.TRANSPARENT | SWT.NO_BACKGROUND);
		
		this.font = font;
		
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
		
		final Point p = FontUtil.computeExtents(this.shadowLabel, shadowLabelText);
		
		this.label.setBounds(0, 0, p.x, p.y);
		this.shadowLabel.setBounds(0, 0, p.x, p.y);
	}

	public void setText(final String text) {
		this.label.setText(text);
	}
}
