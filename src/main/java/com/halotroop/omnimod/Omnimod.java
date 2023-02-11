/*
 * Copyright (C) 2023 halotroop2288
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.halotroop.omnimod;

import me.luligabi.noindium.NoIndium;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.ApiStatus;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.LoggerFactory;

/**
 * Holds common utilities for mod development
 * and manages the most basic initialization of the mod.<br/>
 * To keep the order of operations clear,
 * all registration is handled by its own {@link ModInitializer mod initializers}.
 *
 * @author halotroop2288
 * @see com.halotroop.omnimod.server.OmnimodServer
 * @see com.halotroop.omnimod.client.OmnimodClient
 * @see com.halotroop.omnimod.world.item.OmnimodItems
 * @see com.halotroop.omnimod.world.level.block.OmnimodBlocks
 */
public final class Omnimod implements ModInitializer {
	public static String mod_name = "Omnimod";
	public static String mod_id = "omnimod";

	@ApiStatus.Internal
	public static final OmnimodConfig config = OmnimodConfig.load();

	/**
	 * @apiNote Public only for access by Quilt Loader.
	 */
	@ApiStatus.Internal
	public Omnimod() {
	}

	@Override
	public void onInitialize(ModContainer mod) {
		// In case the mod metadata is changed by the end-user...
		mod_id = mod.metadata().id();
		mod_name = mod.metadata().name();

		info("Loading {}...", mod_name);
		NoIndium.config.allowToProceed = false; // Sodium may break rendering without Indium
	}

//region Logging

	/**
	 * Prints the formatted message in white.
	 */
	public static void info(String message, Object... params) {
		LoggerFactory.getLogger(mod_name).info(message, params);
	}

	/**
	 * Prints the formatted message in white only if in development mode.
	 *
	 * @apiNote Allows the developer(s) to print messages without spamming the console for end-users.
	 */
	public static void debug(String message, Object... params) {
		if (QuiltLoader.isDevelopmentEnvironment()) {
			info(message, params);
		}
	}

	/**
	 * Prints the formatted message in yellow.
	 */
	public static void warning(String message, Object... params) {
		LoggerFactory.getLogger(mod_name).warn(message, params);
	}

	/**
	 * Prints the formatted message in red.
	 */
	public static void error(String message, Object... params) {
		LoggerFactory.getLogger(mod_name).error(message, params);
	}

	/**
	 * A print that is duplicated several times, for each registry.
	 *
	 * @param type The lower-case plural name of the object type that gets registered. Ex: "items"
	 */
	public static void spewRegistration(String type) {
		info("Registering {} {}...", mod_name, type);
	}

	/**
	 * A print that is duplicated several times, for each registry.
	 *
	 * @param type The lower-case plural name of the object type that gets registered. Ex: "items"
	 * @param key  The registry key being used to register the object. Ex: "stick"
	 */
	public static void spewRegistration(String type, String key) {
		debug("Registering {}: {}", type, key);
	}
//endregion

	public static ResourceLocation id(String key) {
		return new ResourceLocation(mod_id, key);
	}
}
