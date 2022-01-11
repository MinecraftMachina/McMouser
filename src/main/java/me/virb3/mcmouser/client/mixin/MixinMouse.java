package me.virb3.mcmouser.client.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public class MixinMouse {
    private double deltaX;

    @Redirect(method = "onPress", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;ON_OSX:Z"))
    private boolean onPress() {
        return false;
    }

    @Inject(method = "onScroll", at = @At(value = "HEAD"))
    private void onScroll(long windowHandle, double deltaX, double deltaY, CallbackInfo ci) {
        this.deltaX = deltaX;
    }

    @ModifyVariable(method = "onScroll", ordinal = 1, at = @At(value = "LOAD"), argsOnly = true)
    private double onScroll_deltaX(double deltaY) {
        if (Minecraft.ON_OSX && deltaY == 0) {
            return deltaX;
        }
        return deltaY;
    }
}
