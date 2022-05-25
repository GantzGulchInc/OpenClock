package com.gantzgulch.openclock.swt.app;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.gantzgulch.openclock.swt.app.config.Config;

public class Main implements Runnable {

	// private static final GGLogger LOG = GGLogger.getLogger(Main.class);
	
	private Application app;

	public Main(final Config config) {
		this.app = new Application(config);
	}

	@Override
	public void run() {

		this.app.run();
		
	}

	public static void main(final String[] args) {

		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		
		final Config config = Config.load();
		
		final Main main = new Main(config);

		main.run();

	}

}
