package com.halotroop.omnimod.tags;

import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.ApiStatus;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

import static com.halotroop.omnimod.Omnimod.id;

public class OmnimodBlockTags implements ModInitializer {
	public static final TagKey<Block> BASE_STONE_END = create("base_stone_end");

	@Override
	public void onInitialize(ModContainer mod) {
	}

	/**
	 * @apiNote Public only for access by Quilt Loader.
	 */
	@ApiStatus.Internal
	public OmnimodBlockTags() {
	}

	private static TagKey<Block> create(String name) {
		return TagKey.create(Registry.BLOCK_REGISTRY, id(name));
	}
}
