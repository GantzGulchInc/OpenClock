package com.gantzgulch.openclock.swt.app.clock.digital;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.openclock.swt.app.config.ClockFaceConfig;
import com.gantzgulch.openclock.swt.app.util.FontUtil;

public class DigitalSegments extends Composite {

	private static final GGLogger LOG = GGLogger.getLogger(DigitalSegments.class);
	
	private Font font;
	private Color color;
	private Color shadowColor;
	
	private Label label;
	private Label shadowLabel;

	public DigitalSegments(final Composite parent, //
			final ClockFaceConfig clockFaceConfig, //
			final String labelText, //
			final String shadowLabelText) {

		super(parent, SWT.NONE);
		
		this.setBackground( clockFaceConfig.getParameters().getBackground() );
		
		this.font = clockFaceConfig.getParameters().getClockFont().getFont();
		this.color = clockFaceConfig.getParameters().getClockFont().getColor();
		this.shadowColor = clockFaceConfig.getParameters().getClockFont().getShadowColor();
		
		this.label = new Label(this, SWT.TRANSPARENT);
		this.label.setFont(this.font);
		this.label.setText(labelText);
		this.label.setForeground( color );
		// this.label.setBackground(clockFaceConfig.getParameters().getBackground());
		
		this.shadowLabel = new Label(this, SWT.TRANSPARENT);
		this.shadowLabel.setFont(this.font);
		this.shadowLabel.setText(shadowLabelText);
		this.shadowLabel.setForeground(shadowColor);
		// this.shadowLabel.setBackground(clockFaceConfig.getParameters().getBackground());
		
		this.label.moveAbove(this.shadowLabel);
		
		final Point p = FontUtil.computeExtents(this.shadowLabel, shadowLabelText);
		
		this.label.setBounds(0, 0, p.x + 6, p.y + 6);
		this.shadowLabel.setBounds(0, 0, p.x + 6, p.y + 6);
		
		// this.label.setBounds(0, 0, p.x, p.y);
		// this.shadowLabel.setBounds(0, 0, p.x, p.y);
	}

	public void setText(final String text) {
		this.label.setText(text);
	}
}
