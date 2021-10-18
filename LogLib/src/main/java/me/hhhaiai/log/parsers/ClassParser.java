package me.hhhaiai.log.parsers;

import android.text.TextUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassParser implements IParser {
    @Override
    public String parserObject(Object args) {
        if (args == null) {
            return null;
        }
        Class<?> clz = (Class) args;

        // 获取自己类的所有变量
        Field[] fields=  clz.getDeclaredFields();

        Method[]  methods= clz.getDeclaredMethods();


        return null;
    }

    @Override
    public String process(String args, boolean isFormat, boolean isWrapper) {
        if (TextUtils.isEmpty(args)) {
            return null;
        }
        if (isFormat) {
            args = format(args);
        }
        return null;
    }


    @Override
    public String format(String args) {
        return args;
    }

    @Override
    public String wrapper(String args) {

        return null;
    }
}
