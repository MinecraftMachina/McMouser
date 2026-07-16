package me.virb3.mcmouser.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "org.lwjgl.opengl.MacOSXDisplay", remap = false)
abstract class MacOSXDisplayMixin {
    @Inject(method = "getButtonCount", at = @At("HEAD"), cancellable = true)
    private void mcmouser$reportFiveButtons(CallbackInfoReturnable<Integer> callbackInfo) {
        callbackInfo.setReturnValue(5);
    }
}
