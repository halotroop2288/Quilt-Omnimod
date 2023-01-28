package com.halotroop.omnimod.block;

import com.halotroop.omnimod.Omnimod;
import eu.pb4.polymer.api.block.SimplePolymerBlock;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public final class OmnimodBlocks implements ModInitializer {
	public static final String[] ORE_NAMES = new String[] {
			"coal", "copper", "diamond", "emerald", "gold", "iron", "lapis", "redstone"
	};

//	public static final Block TEST;

	public static final Block[] END_ORE;
	public static final Block
			END_COAL_ORE, END_COPPER_ORE, END_DIAMOND_ORE, END_EMERALD_ORE,
			END_GOLD_ORE, END_IRON_ORE, END_LAPIS_ORE, END_REDSTONE_ORE;

	static {
//		TEST = createSimple(Blocks.BEDROCK);

		{
			END_COAL_ORE = new SimplePolymerOreBlock(Blocks.END_STONE, Blocks.DEEPSLATE_COAL_ORE);
			END_COPPER_ORE = new SimplePolymerOreBlock(Blocks.END_STONE, Blocks.DEEPSLATE_COPPER_ORE);
			END_DIAMOND_ORE = new SimplePolymerOreBlock(Blocks.END_STONE, Blocks.DEEPSLATE_DIAMOND_ORE);
			END_EMERALD_ORE = new SimplePolymerOreBlock(Blocks.END_STONE, Blocks.DEEPSLATE_EMERALD_ORE);
			END_GOLD_ORE = new SimplePolymerOreBlock(Blocks.END_STONE, Blocks.DEEPSLATE_GOLD_ORE);
			END_IRON_ORE = new SimplePolymerOreBlock(Blocks.END_STONE, Blocks.DEEPSLATE_IRON_ORE);
			END_LAPIS_ORE = new SimplePolymerOreBlock(Blocks.END_STONE, Blocks.DEEPSLATE_LAPIS_ORE);
			END_REDSTONE_ORE = new PolymerRedstoneOreBlock(Blocks.END_STONE, Blocks.DEEPSLATE_REDSTONE_ORE);
			END_ORE = new Block[] {
					END_COAL_ORE, END_COPPER_ORE, END_DIAMOND_ORE, END_EMERALD_ORE,
					END_GOLD_ORE, END_IRON_ORE, END_LAPIS_ORE, END_REDSTONE_ORE
			};
		}
	}

	@Override
	public void onInitialize(ModContainer mod) {
		Omnimod.info("Registering blocks...");
		String modId = mod.metadata().id();

//		register(modId, "test", TEST);
		register(modId, "end_%s_ore", ORE_NAMES, END_ORE);
	}

	private void register(String modId, String key, Block value) {
		Omnimod.debug("Registering {}", key);
		Registry.register(Registry.BLOCK, new ResourceLocation(modId, key), value);
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
