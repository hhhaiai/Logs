package me.hhhaiai.logs;

import me.hhhaiai.logs.utils.LogLevel;
import me.hhhaiai.logs.utils.Ref;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Locale;

class LoggerNative {

    public static void println(int priority, String tag, String msg) {
        if (msg == null) {
            msg = "null";
        }
        if (msg.equals("")) {
            msg = "";
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
        String tagProcessed = converyPriorityToString(priorty, LogLevel.TYPE_TAG, tag);
        String msgProcessed = converyPriorityToString(priorty, LogLevel.TYPE_MSG, msg);
        ppp(String.format("%s%s", tagProcessed, msgProcessed));
    }

    private static final String PLANA = "\u001b[%d;%dm%s\u001b[0m";
    private static final String PLANB = "\033[%d;%dm%s\u001b[0m";

    private static String converyPriorityToString(int priorty, int type, String msg) {
        switch (priorty) {
            case LogLevel.VERBOSE:
                if (type == LogLevel.TYPE_TAG) {
                    return String.format(Locale.getDefault(), PLANA, 97, 1, "V/" + msg + ": ");
                } else {
                    return String.format(Locale.getDefault(), PLANA, 97, 2, msg);
                }
//                return "V";
            case LogLevel.DEBUG:
                if (type == LogLevel.TYPE_TAG) {
                    return String.format(Locale.getDefault(), PLANA, 36, 1, "D/" + msg + ": ");
                } else {
                    return String.format(Locale.getDefault(), PLANA, 36, 2, msg);
                }
//                return "D";
            case LogLevel.INFO:

                if (type == LogLevel.TYPE_TAG) {
                    return String.format(Locale.getDefault(), PLANA, 32, 1, "I/" + msg + ": ");
                } else {
                    return String.format(Locale.getDefault(), PLANA, 32, 2, msg);
                }
//                return "I";
            case LogLevel.WARN:
                if (type == LogLevel.TYPE_TAG) {
                    return String.format(Locale.getDefault(), PLANA, 33, 1, "W/" + msg + ": ");
                } else {
                    return String.format(Locale.getDefault(), PLANA, 33, 2, msg);
                }
//                return "W";
            case LogLevel.ERROR:
                if (type == LogLevel.TYPE_TAG) {
                    return String.format(Locale.getDefault(), PLANA, 91, 1, "E/" + msg + ": ");
                } else {
                    return String.format(Locale.getDefault(), PLANA, 91, 2, msg);
                }

//                return "E";
            case LogLevel.ASSERT:
                if (type == LogLevel.TYPE_TAG) {
                    return String.format(Locale.getDefault(), PLANA, 31, 1, "A/" + msg + ": ");
                } else {
                    return String.format(Locale.getDefault(), PLANA, 31, 2, msg);
                }
//                return "A";
            default:
                return "";
        }
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
                Method println = Ref.getMethod(logClass, "println", int.class, String.class, String.class);
                if (println != null) {
                    println.invoke(null, priority, tag, msg);
                }
            }
        } catch (Throwable e) {
        }
    }

    public static boolean isAndroidPlatform() {
        try {
            Class<?> log = Ref.getClass("android.util.Log");
            if (log != null) {
                return true;
            }
        } catch (Throwable e) {
        }
        return false;
    }

    //    public static native boolean isLoggable(String tag, int level);
    public static boolean isLoggable(String tag, int level) {
        try {
            Class<?> log = Ref.getClass("android.util.Log");
            if (log == null) {
                return false;
            }
            Method isLoggable = log.getDeclaredMethod("isLoggable", String.class, int.class);
            if (isLoggable != null) {
                if (!isLoggable.isAccessible()) {
                    isLoggable.setAccessible(true);
                }
                Object obj = isLoggable.invoke(null, tag, level);
                if (obj != null && (obj instanceof Boolean
                        || obj.getClass().equals(boolean.class)
                        || obj.getClass().equals(Boolean.class))) {
                    return (Boolean) obj;
                }
            }
        } catch (Throwable e) {
        }
        return false;
    }


    private static <T> boolean isStartupFromJar(Class<T> clazz) {
        File file = new File(clazz.getProtectionDomain().getCodeSource().getLocation().getPath());
        return file.isFile();
    }

}
