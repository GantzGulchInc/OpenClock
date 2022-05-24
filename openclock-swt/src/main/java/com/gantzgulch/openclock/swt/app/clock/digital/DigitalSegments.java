package com.gantzgulch.openclock.swt.app.clock.digital;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.gantzgulch.openclock.swt.app.config.ClockFaceConfig;
import com.gantzgulch.openclock.swt.app.util.FontUtil;

public class DigitalSegments extends Composite {

	private Font font;
	private Label label;
	private Label shadowLabel;

	public DigitalSegments(final Composite parent, //
			final ClockFaceConfig clockFaceConfig, //
			final Font font, //
			final String labelText, //
			final Color labelColor, //
			final String shadowLabelText, //
			final Color shadowLabelColor) {

		super(parent, SWT.NONE);
		
		this.setBackground( clockFaceConfig.getParameters().getBackground() );
		
		this.font = font;
		
		this.label = new Label(this, SWT.NONE);
		this.label.setFont(this.font);
		this.label.setText(labelText);
		this.label.setForeground(labelColor);
		this.label.setBackground( clockFaceConfig.getParameters().getBackground() );
		
		this.shadowLabel = new Label(this, SWT.NONE);
		this.shadowLabel.setFont(this.font);
		this.shadowLabel.setText(shadowLabelText);
		this.shadowLabel.setForeground(shadowLabelColor);
		this.shadowLabel.setBackground( clockFaceConfig.getParameters().getBackground() );
		
		this.label.moveAbove(this.shadowLabel);
		
		final Point p = FontUtil.computeExtents(this.shadowLabel, shadowLabelText);
		
		this.label.setBounds(0, 0, p.x + 6, p.y + 6);
		this.shadowLabel.setBounds(0, 0, p.x + 6, p.y + 6);
	}

	public void setText(final String text) {
		this.label.setText(text);
	}
}
