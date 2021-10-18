package me.hhhaiai.log.parsers;

import android.text.TextUtils;

import java.lang.reflect.Field;

public class FieldParser implements IParser {
    @Override
    public String parserObject(Object args) {

        if (args == null) {
            return null;
        }
        Field filed = (Field) args;
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