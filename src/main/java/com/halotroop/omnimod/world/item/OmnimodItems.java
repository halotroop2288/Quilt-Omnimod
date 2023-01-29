/*
 * Copyright (C) 2023 halotroop2288
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.halotroop.omnimod.world.item;

import com.halotroop.omnimod.Omnimod;
import com.halotroop.omnimod.world.level.block.OmnimodBlocks;
import eu.pb4.polymer.api.item.SimplePolymerItem;
import net.minecraft.core.Registry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

import static com.halotroop.omnimod.Omnimod.id;
import static com.halotroop.omnimod.world.level.block.OmnimodBlocks.ORE_NAMES;
import static net.minecraft.world.item.CreativeModeTab.TAB_BUILDING_BLOCKS;

/**
 * Handles creation and registry of {@link Item items} for the mod.
 * @see Items
 * @author halotroop2288
 */
public final class OmnimodItems implements ModInitializer {

	private static final Item[] END_ORE;
	public static final BlockItem
			END_COAL_ORE, END_COPPER_ORE, END_DIAMOND_ORE, END_EMERALD_ORE,
			END_GOLD_ORE, END_IRON_ORE, END_LAPIS_ORE, END_REDSTONE_ORE;

	static {
	//region End Ore
		END_COAL_ORE = createSimple(OmnimodBlocks.END_COAL_ORE,
				TAB_BUILDING_BLOCKS, Items.DEEPSLATE_COAL_ORE);
		END_COPPER_ORE = createSimple(OmnimodBlocks.END_COPPER_ORE,
				TAB_BUILDING_BLOCKS, Items.DEEPSLATE_COPPER_ORE);
		END_DIAMOND_ORE = createSimple(OmnimodBlocks.END_DIAMOND_ORE,
				TAB_BUILDING_BLOCKS, Items.DEEPSLATE_DIAMOND_ORE);
		END_EMERALD_ORE = createSimple(OmnimodBlocks.END_EMERALD_ORE,
				TAB_BUILDING_BLOCKS, Items.DEEPSLATE_EMERALD_ORE);
		END_GOLD_ORE = createSimple(OmnimodBlocks.END_GOLD_ORE,
				TAB_BUILDING_BLOCKS, Items.DEEPSLATE_GOLD_ORE);
		END_IRON_ORE = createSimple(OmnimodBlocks.END_IRON_ORE,
				TAB_BUILDING_BLOCKS, Items.DEEPSLATE_IRON_ORE);
		END_LAPIS_ORE = createSimple(OmnimodBlocks.END_LAPIS_ORE,
				TAB_BUILDING_BLOCKS, Items.DEEPSLATE_LAPIS_ORE);
		END_REDSTONE_ORE = createSimple(OmnimodBlocks.END_REDSTONE_ORE,
				TAB_BUILDING_BLOCKS, Items.DEEPSLATE_REDSTONE_ORE);
		END_ORE = new BlockItem[] {
				END_COAL_ORE, END_COPPER_ORE, END_DIAMOND_ORE, END_EMERALD_ORE,
				END_GOLD_ORE, END_IRON_ORE, END_LAPIS_ORE, END_REDSTONE_ORE
		};
	//endregion
	}

	@Override
	public void onInitialize(ModContainer mod) {
		Omnimod.spewRegistration("items");

		if (Omnimod.config().server_required.register_end_ores) {
			register(Omnimod.mod_id, "end_%s_ore", ORE_NAMES, END_ORE);
		}
	}

	/**
	 * Registers the item provided using the key provided.
	 * @param modId Mod ID to register under
	 * @param key registry key for the item
	 * @param value the item to register
	 */
	private void register(String modId, String key, Item value) {
		Omnimod.spewRegistration("item", key);
		Registry.register(Registry.ITEM, id(key), value);
	}

	/**
	 * Registers each item provided using the formatted keys provided.
	 * @param modId Mod ID to register under
	 * @param format how to name the entries, use %s as a placeholder for a key
	 * @param keys an array of strings to format as provided to make the registry key
	 * @param values an array of values to register
	 */
	private void register(String modId, String format, String[] keys, Item[] values) {
		if (keys.length != values.length) {
			Omnimod.error("Supplied incorrect amount of keys for Item registry of format: " + format);
			return;
		}

		for (int index = 0; index < keys.length; index++) {
			register(modId, String.format(format, keys[index]), values[index]);
		}
	}

	private static SimplePolymerItem createSimple(CreativeModeTab tab, Item virtualItem) {
		return new SimplePolymerItem(new Item.Properties().tab(tab), virtualItem);
	}

	private static BlockItem createSimple(Block block, CreativeModeTab tab, Block virtualBlock) {
		return new SimplePolymerBlockItem(block, new Item.Properties().tab(tab), virtualBlock);
	}

	private static BlockItem createSimple(Block block, CreativeModeTab tab, Item virtualItem) {
		return new SimplePolymerBlockItem(block, new Item.Properties().tab(tab), virtualItem);
	}
}
