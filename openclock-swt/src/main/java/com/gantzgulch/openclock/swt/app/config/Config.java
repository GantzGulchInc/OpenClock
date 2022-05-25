package com.gantzgulch.openclock.swt.app.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

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
	
		final Path homeConfigPath = getHome().resolve(".openclock").resolve("config.json");
		
		final Optional<Config> homeConfig = tryLoad(homeConfigPath);
		
		if( homeConfig.isPresent() ) {
			return homeConfig.get();
		}
		
		try( final InputStream is = Config.class.getResourceAsStream("/clocks.json") ) {
			
			return GGJsonReaders.STRICT.read(is, Config.class);
			
		} catch (final IOException e) {
			LOG.warn(e, "load: Error loading config: %s", e.getMessage());
			throw new RuntimeException(e);
		}
	}
	
	private static Path getHome() {
		final String homeDirectory = System.getProperty("user.home");
		return Paths.get(homeDirectory);
	}
	
	private static Optional<Config> tryLoad(final Path configPath) {

		LOG.info("tryLoad: Trying: %s", configPath);
		
		try(final InputStream is = Files.newInputStream(configPath)) {
			
			return Optional.of(GGJsonReaders.STRICT.read(is, Config.class));
			
		} catch (final RuntimeException | IOException e) {
			LOG.info("tryLoad: Failed: %s", e.getMessage());
			return Optional.empty();
		}
		
	}
}
