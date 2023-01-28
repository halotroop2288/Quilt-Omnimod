package com.halotroop.omnimod.server;

import com.halotroop.omnimod.Omnimod;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.server.DedicatedServerModInitializer;

public final class OmnimodServer implements DedicatedServerModInitializer {
	@Override
	public void onInitializeServer(ModContainer mod) {
		Omnimod.info("Loading {} on server!", mod.metadata().name());
	}
}
