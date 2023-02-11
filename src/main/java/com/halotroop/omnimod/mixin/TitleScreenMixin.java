package com.halotroop.omnimod.mixin;

import com.halotroop.omnimod.Omnimod;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
	private static final Component REALMS_BUTTON_LABEL = new TranslatableComponent("menu.online");

	protected TitleScreenMixin(Component component) {
		super(component);
	}

	/**
	 * @reason disable Realms button
	 */
	@Inject(method = "createNormalMenuOptions", at = @At(value = "TAIL"))
	private void createNormalMenuOptions_end(int y, int rowHeight, CallbackInfo ci) {
		if (Omnimod.config.remove_realms) removeRealms();
	}

	private void removeRealms() {
		((ScreenAccessor)this).getChildren().forEach(child -> {
			if (!(child instanceof Button button) || !button.isActive()) return;
			if (!button.getMessage().equals(REALMS_BUTTON_LABEL)) return;
			button.active = false;
			button.visible = false;
		});
	}
}
