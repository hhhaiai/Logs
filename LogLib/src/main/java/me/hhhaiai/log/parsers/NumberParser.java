package me.hhhaiai.log.parsers;

import android.text.TextUtils;

public class NumberParser implements IParser {
    @Override
    public String parserObject(Object args) {
        if (args == null) {
            return null;
        }
        return args.toString();
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
