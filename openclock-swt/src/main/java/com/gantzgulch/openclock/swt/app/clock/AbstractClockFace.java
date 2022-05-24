package com.gantzgulch.openclock.swt.app.clock;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;

public abstract class AbstractClockFace extends Composite implements ClockFace {

	public AbstractClockFace(final Composite parent) {
		super(parent, SWT.TRANSPARENT | SWT.NO_BACKGROUND);
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
