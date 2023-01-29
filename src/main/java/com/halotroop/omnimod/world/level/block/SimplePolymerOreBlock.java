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

import eu.pb4.polymer.ext.blocks.api.PolymerTexturedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * A simple implementation of {@link PolymerTexturedBlock} over {@link OreBlock}.
 * @see OreBlock
 * @author halotroop2288
 */
public class SimplePolymerOreBlock extends OreBlock implements PolymerTexturedBlock {
	@NotNull
	private final Block virtualBlock;

	public SimplePolymerOreBlock(Properties properties, @NotNull Block virtualBlock) {
		super(properties);
		this.virtualBlock = virtualBlock;
	}

	public SimplePolymerOreBlock(@NotNull Block propertiesOf, @NotNull Block virtualBlock) {
		this(Properties.copy(propertiesOf), virtualBlock);
	}

	public SimplePolymerOreBlock(@NotNull Block virtualBlock) {
		this(virtualBlock, virtualBlock);
	}

	@Override
	public Block getPolymerBlock(BlockState state) {
		return this.virtualBlock;
	}
}
