package me.hhhaiai.logs.parsers;

import me.hhhaiai.logs.proces.LinesPorcesser;
import me.hhhaiai.logs.utils.Pair;
import me.hhhaiai.logs.utils.Ref;
import me.hhhaiai.logs.utils.Utils;

import java.lang.reflect.Field;

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
        StringBuffer sb = new StringBuffer();
        sb.append("// ").append(args.getClass()).append("@").append(Integer.toHexString(args.hashCode())).append("\r\n")
                .append("Object ").append(args.getClass().getName()).append("{").append("\r\n")
                .append(getObjectInfo(args)).append("\r\n")
                .append("}");
        String source = sb.toString();
        String tartget = null;

        if (isFormat) {
            tartget = Supervision.format(source);
        }
        if (isWrapper) {
            tartget = LinesPorcesser.wrapper(tartget);
            return new Pair<String, String>(source, tartget);
        } else {
            return new Pair<String, String>(source, source);
        }
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
            if (!Utils.isEmpty(line)) {
                // 增加空格
                res.append("\t").append(line).append("\r\n");
            }
        }
        return res.toString();
    }


}