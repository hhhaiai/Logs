package me.hhhaiai.log.utils;

import android.os.Build;

import java.io.Closeable;

public class Closer {

    public static void close(Object... args) {
        if (args == null || args.length == 0) {
            return;
        }
        for (Object o : args) {
            if (o == null) {
                continue;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (o instanceof AutoCloseable) {
                    try {
                        ((AutoCloseable) o).close();
                    } catch (Exception e) {
                    }
                }
            }
            if (o instanceof Closeable) {
                Closeable co = (Closeable) o;
                try {
                    co.close();
                } catch (Exception e) {
                }

            }
        }
    }
}
