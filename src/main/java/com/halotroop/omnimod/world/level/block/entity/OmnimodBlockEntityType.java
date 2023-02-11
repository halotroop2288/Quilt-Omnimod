/*
 * Copyright (C) 2023 halotroop2288
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.halotroop.omnimod.world.level.block.entity;

import com.halotroop.omnimod.Omnimod;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.ApiStatus;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

import static com.halotroop.omnimod.Omnimod.id;

/**
 * Handles creation and registry of {@link BlockEntityType block entity types} for the mod.
 *
 * @author halotroop2288
 * @see BlockEntityType
 */
public class OmnimodBlockEntityType implements ModInitializer {
	@Override
	public void onInitialize(ModContainer mod) {
		Omnimod.spewRegistration("block entity types");
	}

	/**
	 * @apiNote Public only for access by Quilt Loader.
	 */
	@ApiStatus.Internal
	public OmnimodBlockEntityType() {
	}

	/**
	 * Registers the block entity type provided using the key provided.
	 *
	 * @param modId Mod ID to register under
	 * @param key   registry key for the item
	 * @param value the item to register
	 */
	private void register(String modId, String key, EntityType<?> value) {
		Omnimod.spewRegistration("block entity type", key);
		Registry.register(Registry.ENTITY_TYPE, id(key), value);
	}

	/**
	 * Registers each block entity type provided using the formatted keys provided.
	 *
	 * @param modId  Mod ID to register under
	 * @param format how to name the entries, use %s as a placeholder for a key
	 * @param keys   an array of strings to format as provided to make the registry key
	 * @param values an array of values to register
	 */
	private void register(String modId, String format, String[] keys, EntityType<?>[] values) {
		if (keys.length != values.length) {
			Omnimod.error("Supplied incorrect amount of keys for Item registry of format: " + format);
			return;
		}

		for (int index = 0; index < keys.length; index++) {
			register(modId, String.format(format, keys[index]), values[index]);
		}
	}
}
