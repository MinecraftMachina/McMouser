package me.virb3.mcmouser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Mouse;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.Locale;

/** Expands LWJGL 2's macOS mouse state from three buttons to five. */
final class LwjglMousePatch {
    private static final Logger LOGGER = LogManager.getLogger("McMouser");
    private static final int BUTTON_COUNT = 5;

    private LwjglMousePatch() {
    }

    static void apply() {
        if (!System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("mac")) {
            return;
        }

        if (!Mouse.isCreated()) {
            LOGGER.error("Cannot enable mouse side buttons because LWJGL's mouse has not been created");
            return;
        }

        try {
            Field implementationField = field(Mouse.class, "implementation");
            Object implementation = implementationField.get(null);
            if (!"org.lwjgl.opengl.MacOSXDisplay".equals(implementation.getClass().getName())) {
                LOGGER.error("Cannot enable mouse side buttons for LWJGL implementation {}",
                        implementation.getClass().getName());
                return;
            }

            Field nativeMouseField = field(implementation.getClass(), "mouse");
            Object nativeMouse = nativeMouseField.get(implementation);
            if (nativeMouse == null) {
                LOGGER.error("Cannot enable mouse side buttons outside LWJGL's native display mode");
                return;
            }

            Field nativeButtonsField = field(nativeMouse.getClass(), "buttons");
            byte[] nativeButtons = (byte[]) nativeButtonsField.get(nativeMouse);
            nativeButtonsField.set(nativeMouse, copyOf(nativeButtons, BUTTON_COUNT));

            Field buttonsField = field(Mouse.class, "buttons");
            ByteBuffer buttons = (ByteBuffer) buttonsField.get(null);
            buttonsField.set(null, copyOf(buttons, BUTTON_COUNT));

            Field buttonCountField = field(Mouse.class, "buttonCount");
            buttonCountField.setInt(null, BUTTON_COUNT);
            LOGGER.info("Enabled LWJGL mouse buttons 4 and 5");
        } catch (ReflectiveOperationException | RuntimeException e) {
            LOGGER.error("Failed to enable LWJGL mouse buttons 4 and 5", e);
        }
    }

    private static Field field(Class<?> owner, String name) throws NoSuchFieldException {
        Field field = owner.getDeclaredField(name);
        field.setAccessible(true);
        return field;
    }

    private static byte[] copyOf(byte[] source, int length) {
        byte[] copy = new byte[length];
        System.arraycopy(source, 0, copy, 0, Math.min(source.length, length));
        return copy;
    }

    private static ByteBuffer copyOf(ByteBuffer source, int capacity) {
        ByteBuffer copy = BufferUtils.createByteBuffer(capacity);
        for (int i = 0; i < Math.min(source.capacity(), capacity); i++) {
            copy.put(i, source.get(i));
        }
        return copy;
    }
}
