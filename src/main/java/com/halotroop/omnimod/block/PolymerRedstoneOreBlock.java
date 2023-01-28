package com.halotroop.omnimod.block;

import eu.pb4.polymer.api.block.PolymerBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedStoneOreBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class PolymerRedstoneOreBlock extends RedStoneOreBlock implements PolymerBlock {
	@NotNull
	private final Block virtualBlock;

	public PolymerRedstoneOreBlock(Properties properties, @NotNull Block virtualBlock) {
		super(properties);
		this.virtualBlock = virtualBlock;
	}

	public PolymerRedstoneOreBlock(@NotNull Block propertiesOf, @NotNull Block virtualBlock) {
		this(BlockBehaviour.Properties.copy(virtualBlock), virtualBlock);
	}

	public PolymerRedstoneOreBlock(@NotNull Block virtualBlock) {
		this(virtualBlock, virtualBlock);
	}

	@NotNull
	@Override
	public Block getPolymerBlock(BlockState state) {
		return virtualBlock;
	}
}
