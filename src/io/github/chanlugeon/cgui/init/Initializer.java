package io.github.chanlugeon.cgui.init;

import java.lang.reflect.Field;
import java.nio.charset.Charset;

public class Initializer {
    public Initializer() {
        new AssertionError();
    }

    public static void init() {
        System.setProperty("file.encoding", "UTF-8");
        try {
            final Field charset = Charset.class.getDeclaredField("defaultCharset");
            charset.setAccessible(true);
            charset.set(null, null);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
