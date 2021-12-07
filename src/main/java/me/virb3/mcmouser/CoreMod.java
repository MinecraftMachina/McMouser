package me.virb3.mcmouser;

import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Locale;
import java.util.Map;

@IFMLLoadingPlugin.MCVersion(ForgeVersion.mcVersion)
@IFMLLoadingPlugin.Name("McMouser")
public class CoreMod implements IFMLLoadingPlugin {
    private static final Logger LOGGER = LogManager.getLogger();

    public CoreMod() {
        if (!System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("mac")) {
            LOGGER.info("Not running on macOS, disabling");
            return;
        }
        try {
            File tempDir = Files.createTempDirectory("mcmouser").toFile();
            tempDir.deleteOnExit();
            Path tempFile = tempDir.toPath().resolve("liblwjgl.dylib");
            try (InputStream is = CoreMod.class.getResourceAsStream("/liblwjgl.dylib")) {
                Files.copy(is, tempFile, StandardCopyOption.REPLACE_EXISTING);
            }
            System.setProperty("org.lwjgl.librarypath", tempDir.getAbsolutePath());
            LOGGER.info("Everything loaded");
        } catch (Exception e) {
            LOGGER.error("Failed to load patched liblwjgl.dylib, throwing exception");
            throw new RuntimeException(e);
        }
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
