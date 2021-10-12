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
        for (String info : list) {
            Log.println(level, LContent.DefTAG, info);
        }
    }
}
