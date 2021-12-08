package net.piinut.sp.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import net.piinut.sp.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class SPMixin {
	@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info) {
		Main.LOGGER.info("This line is printed by mod mixin!");
	}
}
