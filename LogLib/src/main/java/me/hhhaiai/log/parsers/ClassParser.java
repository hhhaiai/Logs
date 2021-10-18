package me.hhhaiai.log.parsers;

import android.text.TextUtils;

public class ClassParser implements IParser {
    @Override
    public String parserObject(Object args) {
        if (args == null) {
            return null;
        }
        Class<?> clz = (Class) args;

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
