/*
 * Copyright (C) 2023 halotroop2288
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.halotroop.omnimod.world.level.block;

import com.halotroop.omnimod.Omnimod;
import eu.pb4.polymer.api.block.SimplePolymerBlock;
import eu.pb4.polymer.ext.blocks.api.PolymerBlockModel;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

import static com.halotroop.omnimod.Omnimod.id;
import static net.minecraft.world.level.block.Blocks.*;

/**
 * Handles creation and registry of {@link Block blocks} for the mod.
 * @see Blocks
 * @author halotroop2288
 */
public final class OmnimodBlocks implements ModInitializer {
	public static final String[] ORE_NAMES = new String[] {
			"coal", "copper", "diamond", "emerald", "gold", "iron", "lapis", "redstone"
	};

	public static final Block[] END_ORE;
	public static final Block
			END_COAL_ORE, END_COPPER_ORE, END_DIAMOND_ORE, END_EMERALD_ORE,
			END_GOLD_ORE, END_IRON_ORE, END_LAPIS_ORE, END_REDSTONE_ORE;

	static {
	//region End Ore
		END_COAL_ORE = new SimplePolymerOreBlock(END_STONE, DEEPSLATE_COAL_ORE);
		END_COPPER_ORE = new SimplePolymerOreBlock(END_STONE, DEEPSLATE_COPPER_ORE);
		END_DIAMOND_ORE = new SimplePolymerOreBlock(END_STONE, DEEPSLATE_DIAMOND_ORE);
		END_EMERALD_ORE = new SimplePolymerOreBlock(END_STONE, DEEPSLATE_EMERALD_ORE);
		END_GOLD_ORE = new SimplePolymerOreBlock(END_STONE, DEEPSLATE_GOLD_ORE);
		END_IRON_ORE = new SimplePolymerOreBlock(END_STONE, DEEPSLATE_IRON_ORE);
		END_LAPIS_ORE = new SimplePolymerOreBlock(END_STONE, DEEPSLATE_LAPIS_ORE);
		END_REDSTONE_ORE = new PolymerRedStoneOreBlock(END_STONE, DEEPSLATE_REDSTONE_ORE);
		END_ORE = new Block[] {
				END_COAL_ORE, END_COPPER_ORE, END_DIAMOND_ORE, END_EMERALD_ORE,
				END_GOLD_ORE, END_IRON_ORE, END_LAPIS_ORE, END_REDSTONE_ORE
		};
	//endregion
	}

	@Override
	public void onInitialize(ModContainer mod) {
		Omnimod.spewRegistration("blocks");

		if (Omnimod.config().server_required.register_end_ores) {
			register(Omnimod.mod_id, "end_%s_ore", ORE_NAMES, END_ORE);
		}
	}

	private void register(String modId, String key, Block value) {
		Omnimod.spewRegistration("block", key);
		ResourceLocation id = id(key);
		Registry.register(Registry.BLOCK, id, value);
		PolymerBlockModel.of(id);
	}

	private void register(String modId, String format, String[] keys, Block[] values) {
		if (keys.length != values.length) {
			Omnimod.error("Supplied incorrect amount of keys for Block registry of format: " + format);
			return;
		}

		for (int index = 0; index < keys.length; index++) {
			register(modId, String.format(format, keys[index]), values[index]);
		}
	}

	private static SimplePolymerBlock createSimple(Block virtualBlock) {
		return new SimplePolymerBlock(BlockBehaviour.Properties.copy(virtualBlock), virtualBlock);
	}
}
