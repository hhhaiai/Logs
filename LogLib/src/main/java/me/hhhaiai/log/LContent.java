package me.hhhaiai.log;

import android.util.Log;

import java.util.Locale;

 class LContent {
    //打印日志
    static boolean isDefLoged = false;

    static boolean isDefShellControl = true;
    static boolean isDefWarpperInfo = false;
    static boolean isDefPrintCallStackInfo = false;
    static boolean isDefFormatInfo = false;
    protected static String DefTAG = LContent.PRE_TAG;
    // 默认tag
    static final String PRE_TAG = "sanbo";
//    static List<Logsx> LOGS = new ArrayList<Logsx>();

    static String WRNNING_SHELL_CTL = "Wranning....不够打印级别,请在命令行设置指令后重新尝试打印,命令行指令: adb shell setprop log.tag.%s %s";


    static String getLogLevelName(int level) {
        if (Log.VERBOSE == level) {
            return "VERBOSE";
        } else if (Log.DEBUG == level) {
            return "DEBUG";
        } else if (Log.INFO == level) {
            return "INFO";
        } else if (Log.WARN == level) {
            return "WARN";
        } else if (Log.ERROR == level) {
            return "ERROR";
        } else if (Log.ASSERT == level) {
            return "ASSERT";
        } else {
            return "VERBOSE";
        }
    }

    public static String getShellErrorInfo(int level) {
        return String.format(Locale.getDefault(), LContent.WRNNING_SHELL_CTL, DefTAG, LContent.getLogLevelName(level));
    }
}
