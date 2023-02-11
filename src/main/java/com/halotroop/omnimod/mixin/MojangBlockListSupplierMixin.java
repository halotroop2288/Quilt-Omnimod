/*
 * Copyright (C) 2023 halotroop2288
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.halotroop.omnimod.mixin;

import com.halotroop.omnimod.Omnimod;
import com.mojang.patchy.BlockedServers;
import com.mojang.patchy.MojangBlockListSupplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

/**
 * @author halotroop2288
 * @see MojangBlockListSupplier
 * @see BlockedServers
 */
@Mixin(MojangBlockListSupplier.class)
public class MojangBlockListSupplierMixin {
	/**
	 * @reason disables the Mojang server blocking feature in Patchy entirely.
	 */
	@Inject(method = "createBlockList", at = @At("HEAD"), cancellable = true)
	private void preventServerBlocking(CallbackInfoReturnable<Predicate<String>> cir) {
		if (Omnimod.config.disable_server_blocklist) {
			cir.setReturnValue(null);
		}
	}
}
