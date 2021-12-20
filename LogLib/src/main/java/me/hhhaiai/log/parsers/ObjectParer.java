package me.hhhaiai.log.parsers;

import android.text.TextUtils;
import android.util.Pair;

import java.lang.reflect.Field;

import me.hhhaiai.log.utils.Ref;

/**
 * @Copyright © 2021 sanbo Inc. All rights reserved.
 * @Description: 对象解析, 可用于内存对象解析
 * @Version: 1.0
 * @Create: 2021/12/354 16:08:56
 * @author: sanbo
 */
public class ObjectParer implements IParser {
    @Override
    public Pair<String, String> parserObject(Object args, boolean isFormat, boolean isWrapper) {
        if (args == null) {
            return null;
        }
        String source = getObjectInfo(args);
        String tartget = null;
        if (isFormat) {
            tartget = Supervision.format(source);
        }
        if (isWrapper) {
            tartget = Supervision.wrapper(tartget);
        }
        return new Pair<String, String>(source, tartget);
    }


    // 仅获取变量,不获取方法
    // 后续计划：不带参数的方法，也可以直接调用
    private String getObjectInfo(Object args) {
        String field = getObjectField(args);
        String methodInfo = ClassParser.parserMethodByClass(args.getClass());
        return new StringBuffer().append(field).append("\r\n").append(methodInfo).toString();
    }

    private String getObjectField(Object obj) {
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if (fields == null || fields.length == 0) {
            return null;
        }
        StringBuilder res = new StringBuilder();
        for (Field f : fields) {
            String line = Ref.getField(obj, f);
            if (!TextUtils.isEmpty(line)) {
                res.append(line).append("\r\n");
            }
        }
        return res.toString();
    }


}