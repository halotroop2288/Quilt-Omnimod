package com.halotroop.omnimod.block;

import eu.pb4.polymer.api.block.PolymerBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class SimplePolymerOreBlock extends OreBlock implements PolymerBlock {
	@NotNull
	private final Block virtualBlock;

	public SimplePolymerOreBlock(Properties properties, @NotNull Block virtualBlock) {
		super(properties);
		this.virtualBlock = virtualBlock;
	}

	public SimplePolymerOreBlock(@NotNull Block propertiesOf, @NotNull Block virtualBlock) {
		this(BlockBehaviour.Properties.copy(virtualBlock), virtualBlock);
	}

	public SimplePolymerOreBlock(@NotNull Block virtualBlock) {
		this(virtualBlock, virtualBlock);
	}

	@Override
	public Block getPolymerBlock(BlockState state) {
		return virtualBlock;
	}
}
