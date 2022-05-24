package com.gantzgulch.openclock.swt.app.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.tools.json.GGJsonReaders;

public class Config {

	private static final GGLogger LOG = GGLogger.getLogger(Config.class);
	
	@JsonProperty("display")
	private ClockDisplayConfig displayConfig;
	
	@JsonProperty("clockFaces")
	private List<ClockFaceConfig> clockFaceConfigs;
	
	public ClockDisplayConfig getDisplayConfig() {
		return displayConfig;
	}
	
	public List<ClockFaceConfig> getClockConfigs() {
		return clockFaceConfigs;
	}
	
	public static Config load() {
		
		try( final InputStream is = Config.class.getResourceAsStream("/clocks.json") ) {
			
			return GGJsonReaders.STRICT.read(is, Config.class);
			
		} catch (final IOException e) {
			LOG.warn(e, "load: Error loading config: %s", e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
