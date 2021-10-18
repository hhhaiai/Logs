package me.hhhaiai.log.parsers;

import android.text.TextUtils;
import android.util.Log;

public class ThrowableParser implements IParser {
    @Override
    public String parserObject(Object args) {
        if (args == null) {
            return null;
        }
        Throwable th = (Throwable) args;
        return Log.getStackTraceString(th);
    }


    @Override
    public String format(String args) {
        if (TextUtils.isEmpty(args)) {
            return null;
        }
        return args;
    }

    @Override
    public String wrapper(String args) {
        if (TextUtils.isEmpty(args)) {
            return null;
        }
        if (TextUtils.isEmpty(args)) {
            return null;
        }
        return null;
    }
}