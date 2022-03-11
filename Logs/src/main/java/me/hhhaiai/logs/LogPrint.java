package me.hhhaiai.logs;

import java.util.List;

import me.hhhaiai.logs.parsers.LogFactory;

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
            Logger.println(level, LContent.DefTAG, line);
        }
    }

}
