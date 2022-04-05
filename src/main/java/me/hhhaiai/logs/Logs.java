package me.hhhaiai.logs;

import me.hhhaiai.logs.utils.LogLevel;

public class Logs {
    private Logs() {
    }

    /**
     * 初始化接口
     *
     * @param showLog           是否展示log，默认true,展示
     * @param shellControl      是否使用shell控制log动态打印.默认不使用. 如LogContent.TAG为sanbo,此时shell设置方式：`setprop log.LogContent.TAG.sanbo INFO`
     *                          最后一个参数为log等级,可选项目：VERBOSE/DEBUG/INFO/WARN/ERROR/ASSERT. 默认true
     * @param needWarpper       每个日志输出,是否含外部包裹. 默认不包裹
     * @param needCallStackInfo 是否需要打印详细的堆栈调用信息. 设置true打印具体的调用堆栈
     * @param format            格式化json/xml等情况下，是否需要格式化.默认fasle
     * @param tag               android logcat的LogContent.TAG. 默认的LogContent.TAG为"sanbo"
     */
    public static void build(boolean showLog, boolean shellControl, boolean needWarpper, boolean needCallStackInfo, boolean format, String tag) {
        LContent.isDefLoged = showLog;
        LContent.isDefShellControl = shellControl;
        LContent.isDefWarpperInfo = needWarpper;
        LContent.isDefPrintCallStackInfo = needCallStackInfo;
        LContent.isDefFormatInfo = format;
        LContent.DefTAG = tag;
    }

    public static void v(Object... args) {
        base(LogLevel.VERBOSE, args);
    }


    public static void d(Object... args) {
        base(LogLevel.DEBUG, args);
    }

    public static void i(Object... args) {
        base(LogLevel.INFO, args);
    }

    public static void w(Object... args) {
        base(LogLevel.WARN, args);
    }


    public static void e(Object... args) {
        base(LogLevel.ERROR, args);
    }


    public static void wtf(Object... args) {
        base(LogLevel.ASSERT, args);
    }

    private static void base(int level, Object... args) {
        if (CtlCheck.isShellLimit(level)) {
            return;
        }
        LogPrint.print(level, args);
    }

}
