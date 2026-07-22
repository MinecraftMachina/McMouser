package me.virb3.mcmouser;

import net.minecraftforge.fml.common.Mod;

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
        clientSideOnly = true,
        acceptedMinecraftVersions = "[1.10.2]",
        acceptableRemoteVersions = "*"
)
public final class McMouserMod {
    public static final String MOD_ID = "mcmouser";

    public McMouserMod() {
        LwjglMousePatch.apply();
    }
}
