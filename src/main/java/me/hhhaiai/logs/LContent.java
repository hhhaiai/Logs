package me.hhhaiai.logs;

import me.hhhaiai.logs.utils.LogLevel;

import java.util.Locale;

/**
 * @Copyright © 2021 sanbo Inc. All rights reserved.
 * @Description: 内部变量
 * @Version: 1.0
 * @Create: 2021/10/286 14:47:02
 * @author: sanbo
 */
public class LContent {
    //打印日志
    public static boolean isDefLoged = false;

    public static boolean isDefShellControl = true;
    public static boolean isDefWarpperInfo = false;
    public static boolean isDefPrintCallStackInfo = false;
    public static boolean isDefFormatInfo = false;
    public static String DefTAG = LContent.PRE_TAG;
    // 默认tag
    static final String PRE_TAG = "sanbo";

    static final int JSON_INDENT = 2;
    static String WRNNING_SHELL_CTL = "Wranning....不够打印级别,请在命令行设置指令后重新尝试打印,命令行指令: adb shell setprop log.tag.%s %s";

    static String getLogLevelName(int level) {
        if (LogLevel.VERBOSE == level) {
            return "VERBOSE";
        } else if (LogLevel.DEBUG == level) {
            return "DEBUG";
        } else if (LogLevel.INFO == level) {
            return "INFO";
        } else if (LogLevel.WARN == level) {
            return "WARN";
        } else if (LogLevel.ERROR == level) {
            return "ERROR";
        } else if (LogLevel.ASSERT == level) {
            return "ASSERT";
        } else {
            return "VERBOSE";
        }
    }

    public static String getShellErrorInfo(int level) {
        return String.format(Locale.getDefault(), LContent.WRNNING_SHELL_CTL, DefTAG, LContent.getLogLevelName(level));
    }
}
