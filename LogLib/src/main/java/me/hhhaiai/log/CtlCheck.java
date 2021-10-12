package me.hhhaiai.log;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;


public class CtlCheck {
    /**
     * shell不允许打印该级别日志打印
     *
     * @param level
     * @return
     */
    static boolean isShellLimit(int level) {
        if (LContent.isDefShellControl && !Log.isLoggable(LContent.DefTAG, level)) {
            LContent.getLogLevelName(level);
            Log.println(level, LContent.DefTAG, LContent.getShellErrorInfo(level));
            return true;
        }
        return false;
    }

    /**
     * 参数是否有效
     *
     * @param args
     * @return true 有效
     * false 无效
     */
    public static boolean isValid(Object[] args) {
        if (args == null || args.length == 0) {
            return false;
        }
        return true;
    }

    /**
     * 参数是否有效
     *
     * @param args
     * @return true 有效
     * false 无效
     */
    public static boolean isValid(List<String> args) {
        if (args == null || args.size() == 0) {
            return false;
        }
        return true;
    }


    public static boolean isJsonArray(String args) {
        try {
            new JSONArray(args);
            return true;
        } catch (Throwable e) {
        }
        return false;
    }

    public static boolean isJsonObject(String args) {
        try {
            new JSONObject(args);
            return true;
        } catch (Throwable e) {
        }
        return false;
    }

    public static boolean isXml(String temp) {
        return false;
    }
}
