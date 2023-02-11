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

import blue.endless.jankson.Comment;
import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.api.SyntaxError;
import org.quiltmc.loader.api.QuiltLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * For Omnimod to be desirable to the average user, we allow all features to be toggleable,
 * because your taste in mod features may vary.
 *
 * @author halotroop2288
 * @see Jankson
 */
public final class OmnimodConfig {
	private static final Jankson jankson = Jankson.builder().build();
	private static final File config_file = new File(QuiltLoader.getConfigDir().toFile(), "omnimod.json5");

	@Comment("Register end variants of overworld ores?")
	public boolean register_end_ores = true;
	@Comment("Spawn end ores in The End biomes? (Useful in superflat survival)")
	public boolean spawn_end_ores = true;

	@Comment("Disable the Mojang server block list?")
	public boolean disable_server_blocklist = true;
	@Comment("Removes the Realms button from the title screen")
	public boolean remove_realms = true;

	@Override
	public String toString() {
		return String.format(
				"""
						Register End Ores: %s
						Spawn End Ores: %s
						Disable Server Blocklist: %s""",
				register_end_ores, spawn_end_ores,
				disable_server_blocklist
		);
	}

	//region save/load
	public static OmnimodConfig load() {
		if (!config_file.exists()) return new OmnimodConfig().save();

		JsonObject configJson;
		try {
			configJson = jankson.load(config_file);
		} catch (IOException | SyntaxError e) {
			throw new RuntimeException("Failed to load config file.", e);
		}

		return jankson.fromJson(configJson, OmnimodConfig.class);
	}

	public OmnimodConfig save() {
		final String result = jankson.toJson(this).toJson(true, true);
		try {
			if (!config_file.exists() || config_file.createNewFile()) {
				FileOutputStream out = new FileOutputStream(config_file, false);

				out.write(result.getBytes());
				out.flush();
				out.close();
			}
		} catch (IOException e) {
			Omnimod.error("Failed to save config! {}", e);
		}

		return this;
	}

	/**
	 * Use {@link #load()}.
	 */
	private OmnimodConfig() {
	}
//endregion
}
