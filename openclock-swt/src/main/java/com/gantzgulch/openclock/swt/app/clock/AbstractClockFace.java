package com.gantzgulch.openclock.swt.app.clock;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;

import com.gantzgulch.openclock.swt.app.config.ClockFaceConfig;

public abstract class AbstractClockFace extends Composite implements ClockFace {

	public AbstractClockFace(final Composite parent, final ClockFaceConfig clockFaceConfig) {
		super(parent, SWT.NONE);
		
		setBackground( clockFaceConfig.getParameters().getBackground());
	}

	@Override
	public void update() {
		try {
			getDisplay().asyncExec(this::updateInternal);
		}catch(final SWTException e ) {
			// This happens when we close.
		}
	}
	
	protected abstract void updateInternal();

}
