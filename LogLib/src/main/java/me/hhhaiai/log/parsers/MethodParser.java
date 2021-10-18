package me.hhhaiai.log.parsers;

import android.text.TextUtils;

import java.lang.reflect.Method;

public class MethodParser implements IParser {
    @Override
    public String parserObject(Object args) {
        if (args == null) {
            return null;
        }
        Method method = (Method) args;
        return null;
    }

    @Override
    public String process(String args, boolean isFormat, boolean isWrapper) {
        return null;
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
        return null;
    }
}