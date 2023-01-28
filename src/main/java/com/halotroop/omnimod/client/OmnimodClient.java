package com.halotroop.omnimod.client;

import com.halotroop.omnimod.Omnimod;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public final class OmnimodClient implements ClientModInitializer {
	@Override
	public void onInitializeClient(ModContainer mod) {
		Omnimod.info("Loading {} on client!", mod.metadata().name());
	}
}
