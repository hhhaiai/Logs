package me.hhhaiai.logs;

import me.hhhaiai.logs.utils.LogLevel;
import me.hhhaiai.logs.utils.Ref;
import me.hhhaiai.logs.utils.Utils;

import java.io.File;
import java.lang.reflect.Method;

class LoggerNative {
    public static void println(int priority, String tag, String msg) {
        if (Utils.isEmpty(msg)) {
            return;
        }
        // 2. check platform
        if (isAndroidPlatform()) {
            printFormAndroidPlatform(priority, tag, msg);
        } else {
            printForJava(priority, tag, msg);
        }
    }

    /**
     * java print
     *
     * @param priorty
     * @param tag
     * @param msg
     */
    private static void printForJava(int priorty, String tag, String msg) {
        // @TODO can update other color
        String header = "", end = "";
        String strPriority = converyPriorityToString(priorty);
        ppp(String.format("%s%s[%s] %s%s", header, strPriority, tag, msg, end));
    }


    private static void ppp(String formatString) {
        System.out.println(formatString);
    }


    /**
     * android print
     * android.util.Log.println(int priority, String tag, String msg)
     *
     * @param priority
     * @param tag
     * @param msg
     */
    private static void printFormAndroidPlatform(int priority, String tag, String msg) {
        try {
            Class<?> logClass = Ref.getClass("android.util.Log");
            if (logClass != null) {
                Method println = Ref.getMethod(logClass, "println"
                        , int.class, String.class, String.class);
                if (println != null) {
                    println.invoke(null, priority, tag, msg);
                }
            }
        } catch (Throwable e) {
        }
    }

    private static boolean isAndroidPlatform() {
        try {
            Class<?> log = Ref.getClass("android.util.Log");
            if (log != null) {
                return true;
            }
        } catch (ClassNotFoundException e) {
        }
        return false;
    }

    private static <T> boolean isStartupFromJar(Class<T> clazz) {
        File file = new File(clazz.getProtectionDomain().getCodeSource().getLocation().getPath());
        return file.isFile();
    }

    private static String converyPriorityToString(int priorty) {
        switch (priorty) {
            case LogLevel.VERBOSE:
                return "V";
            case LogLevel.DEBUG:
                return "D";
            case LogLevel.INFO:
                return "I";
            case LogLevel.WARN:
                return "W";
            case LogLevel.ERROR:
                return "E";
            case LogLevel.ASSERT:
                return "A";
            default:
                return "";
        }
    }
}
