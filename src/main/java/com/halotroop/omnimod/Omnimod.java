package com.halotroop.omnimod;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Omnimod implements ModInitializer {
	private static final Logger LOGGER = LoggerFactory.getLogger(Omnimod.class);

	@Override
	public void onInitialize(ModContainer mod) {
		info("Loading {}!", mod.metadata().name());
	}

	/**
	 * Prints the formatted message in white.
	 */
	public static void info(String message, Object... params) {
		LOGGER.info(message, params);
	}

	/**
	 * Prints the formatted message in yellow.
	 */
	public static void warning(String message, Object... params) {
		LOGGER.warn(message, params);
	}

	/**
	 * Prints the formatted message in red.
	 */
	public static void error(String message, Object... params) {
		LOGGER.error(message, params);
	}

	public static void debug(String message, Object... params) {
		if (QuiltLoader.isDevelopmentEnvironment()) {
			LOGGER.info(message, params);
		}
	}
}
