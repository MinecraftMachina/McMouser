package me.virb3.mcmouser;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Locale;

public class CoreMod implements PreLaunchEntrypoint {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void onPreLaunch() {
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
}
