package me.virb3.mcmouser.mixin;

import net.minecraft.client.MouseHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MouseHelper.class)
public class MixinMouse {
    @Redirect(method = "onPress", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;ON_OSX:Z"))
    private boolean onPress() {
        return false;
    }
}
