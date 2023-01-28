package com.halotroop.omnimod.item;

import com.halotroop.omnimod.Omnimod;
import com.halotroop.omnimod.block.OmnimodBlocks;
import eu.pb4.polymer.api.item.SimplePolymerItem;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

import static net.minecraft.world.item.CreativeModeTab.TAB_BUILDING_BLOCKS;

public final class OmnimodItems implements ModInitializer {
//	public static final Item TEST;

	private static final Item[] END_ORE;
	public static final BlockItem
			END_COAL_ORE, END_COPPER_ORE, END_DIAMOND_ORE, END_EMERALD_ORE,
			END_GOLD_ORE, END_IRON_ORE, END_LAPIS_ORE, END_REDSTONE_ORE;

	static {
//		TEST = createSimple(CreativeModeTab.TAB_MISC, Items.BEDROCK);

		{
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
		}
	}

	@Override
	public void onInitialize(ModContainer mod) {
		Omnimod.info("Registering items...");
		String modId = mod.metadata().id();

//		register(modId, "test", TEST);
		register(modId, "end_%s_ore", OmnimodBlocks.ORE_NAMES, END_ORE);
	}

	/**
	 * Registers the item provided using the key provided.
	 * @param modId Mod ID to register under
	 * @param key registry key for the item
	 * @param value the item to register
	 */
	private void register(String modId, String key, Item value) {
		Omnimod.debug("Registering {}", key);
		Registry.register(Registry.ITEM, new ResourceLocation(modId, key), value);
	}

	/**
	 * Registers each value provided using the formatted keys provided.
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
