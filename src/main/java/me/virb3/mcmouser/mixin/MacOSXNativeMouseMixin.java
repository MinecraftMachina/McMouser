package me.virb3.mcmouser.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(targets = "org.lwjgl.opengl.MacOSXNativeMouse", remap = false)
abstract class MacOSXNativeMouseMixin {
    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 3))
    private int mcmouser$expandButtonState(int originalButtonCount) {
        return 5;
    }
}
