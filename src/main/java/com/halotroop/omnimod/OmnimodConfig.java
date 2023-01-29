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
 * @author halotroop2288
 */
public final class OmnimodConfig {
	private static final Jankson jankson = Jankson.builder().build();
	private static final File config_file = new File(QuiltLoader.getConfigDir().toFile(), "omnimod.json5");

	@Comment("""
			These options affect features that only required the mod to be present on the server side.
			This includes on single-player clients.""")
	public final ServerRequired server_required = new ServerRequired();

	@Comment("These options affect features that only required the mod to be present on the client side.")
	public final ClientRequired client_required = new ClientRequired();

	@Comment("""
			These options affect features which require the mod to be installed on both client and server
			Ideally, leave these settings false to be compatible with vanilla clients.""")
	public final BothRequired both_required = new BothRequired();

	@Comment("""
			The rest of these options affect features that only require the mod to be installed on one side.
			Only the side the mod is installed on will be affected.""")
	public final EitherSide either_side = new EitherSide();

	public static class ServerRequired {
		@Comment("Register end variants of overworld ores?")
		public boolean register_end_ores = true;
		@Comment("Spawn end ores in The End biomes? (Useful in superflat survival)")
		public boolean spawn_end_ores = true;

		@Override
		public String toString() {
			return String.format("""
					Register End Ores - %b
					Spawn End Ores - %b""",
					register_end_ores,
					spawn_end_ores
			);
		}
	}

	public static class ClientRequired {
		@Override
		public String toString() {
			return String.format("""
					"""
			);
		}
	}

	public static class BothRequired {
		@Override
		public String toString() {
			return String.format("""
					"""
			);
		}
	}

	public static class EitherSide {
		@Comment("Disables reading the Mojang blocked server list.")
		public boolean disable_server_blocklist = false;

		@Override
		public String toString() {
			return String.format("""
					"""
			);
		}
	}

	public static OmnimodConfig load() {
		JsonObject configJson;
		try {
			configJson = jankson.load(config_file);
		} catch (IOException e) {
			return new OmnimodConfig();
		} catch (SyntaxError e) {
			throw new RuntimeException("Impossible error.", e);
		}

		return jankson.fromJson(configJson, OmnimodConfig.class);
	}

	public void save() {
		final String result = jankson.toJson(new OmnimodConfig()) // -> JsonObject
				.toJson(true, true); // -> String
		//in this case, preserving comments and pretty-printing with newlines
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
	}

	@Override
	public String toString() {
		return String.format("""
				Server Required:
				%s

				Client Required:
				%s

				Both Required:
				%s

				Either Side:
				%s""",
				server_required, client_required, both_required, either_side
		);
	}

	/**
	 * Use {@link #load()}.
	 */
	private OmnimodConfig() {}
}
