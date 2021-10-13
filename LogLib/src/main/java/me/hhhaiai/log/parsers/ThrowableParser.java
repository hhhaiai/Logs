package me.hhhaiai.log.parsers;

import android.util.Log;

import java.lang.reflect.Method;

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
    public EParserType getTypeName() {
        return EParserType.T_THROWABLE;
    }

    @Override
    public String format(String args) {
        return args;
    }
}