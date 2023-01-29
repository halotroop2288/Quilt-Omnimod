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

import eu.pb4.polymer.api.item.PolymerItem;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A simple implementation of {@link PolymerItem} over {@link BlockItem}
 * @see BlockItem
 * @see PolymerItem
 * @author halotroop2288
 */
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
		return this.virtualItem;
	}
}
