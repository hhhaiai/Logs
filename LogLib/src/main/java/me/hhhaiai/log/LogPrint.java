package me.hhhaiai.log;

import android.util.Log;

import java.util.List;

import me.hhhaiai.log.parsers.LogFactory;

class LogPrint {
    public static void print(int level, Object... args) {
        if (!CtlCheck.isValid(args)) {
            return;
        }
        List<String> list = LogFactory.getInfo(args);
        if (!CtlCheck.isValid(list)) {
            return;
        }
        // support java and android
//        boolean isAndroid = isAndroidByLog();
//        for (String info : list) {
//            if (isAndroid) {
//                Log.println(level, LContent.DefTAG, info);
//            } else {
//                System.out.println(info);
//            }
//        }


        for (String line : list) {
            Log.println(level, LContent.DefTAG, line);
        }
    }

    private static boolean isAndroidByLog() {
        try {
            Class.forName("android.util.Log");
            return true;
        } catch (Throwable e) {
        }
        return false;
    }
}
