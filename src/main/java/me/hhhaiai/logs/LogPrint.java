package me.hhhaiai.logs;

import me.hhhaiai.logs.parsers.LogFactory;

import java.util.List;

class LogPrint {
    public static void print(int level, Object... args) {
        if (!CtlCheck.isValid(args)) {
            return;
        }
        List<String> list = LogFactory.getInfo(args);
        if (!CtlCheck.isValid(list)) {
            return;
        }

        for (String line : list) {
            LoggerNative.println(level, LContent.DefTAG, line);
        }
    }

}
