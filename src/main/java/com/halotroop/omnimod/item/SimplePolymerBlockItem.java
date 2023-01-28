package com.halotroop.omnimod.item;

import eu.pb4.polymer.api.item.PolymerItem;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SimplePolymerBlockItem extends BlockItem implements PolymerItem {
	@NotNull
	private final Item virtualItem;

	public SimplePolymerBlockItem(@NotNull Block block, @NotNull Properties properties, @NotNull Item virtualItem) {
		super(block, properties);
		this.virtualItem = virtualItem;
	}

	public SimplePolymerBlockItem(@NotNull Block block, Properties properties, @NotNull Block virtualBlock) {
		this(block, properties, virtualBlock.asItem());
	}

	@Override
	public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayer player) {
		return virtualItem;
	}
}
