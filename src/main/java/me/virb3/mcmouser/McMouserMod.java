package me.virb3.mcmouser;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.relauncher.Side;

/**
 * FML mod container for McMouser.
 *
 * The actual native-library setup must remain in {@link CoreMod}, because it
 * needs to run before LWJGL loads. This container makes the coremod visible to
 * normal FML discovery and to the Mods screen.
 */
@Mod(
        modid = McMouserMod.MOD_ID,
        name = "McMouser",
        useMetadata = true,
        acceptedMinecraftVersions = "[1.7.10]",
        acceptableRemoteVersions = "*"
)
public final class McMouserMod {
    public static final String MOD_ID = "mcmouser";

    public McMouserMod() {
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            LwjglMousePatch.apply();
        }
    }
}
