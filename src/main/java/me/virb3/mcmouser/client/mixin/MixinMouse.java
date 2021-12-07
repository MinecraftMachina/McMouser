package me.virb3.mcmouser.client.mixin;

import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MouseHandler.class)
public class MixinMouse {
    @Redirect(method = "onPress", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;ON_OSX:Z"))
    private boolean onPress() {
        return false;
    }
}
