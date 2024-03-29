package me.hhhaiai.logs.parsers;


import me.hhhaiai.logs.proces.LinesPorcesser;
import me.hhhaiai.logs.utils.Pair;
import me.hhhaiai.logs.utils.Ref;
import me.hhhaiai.logs.utils.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class ClassParser implements IParser {

    @Override
    public Pair<String, String> parserObject(Object args, boolean isFormat, boolean isWrapper) {

        if (args == null) {
            return null;
        }
        Class<?> clz = (Class) args;
        StringBuilder source = new StringBuilder();
        StringBuilder target = new StringBuilder();
        // 获取自己类的所有变量
        try {

            String fieldInfo = parserFieldByClass(clz);
            String methodInfo = parserMethodByClass(clz);
            source.append(Supervision.format(fieldInfo))
                    .append("\r\n")
                    .append(Supervision.format(methodInfo));
            // 暂时格式化部分不增加其他的，后续可增加类关系等
//            if (isFormat) {
//            }
            // 生成 class xxx{
            // //...
            // }
            target.append("// ").append(clz.getName()).append("\r\n")
                    .append("class ").append(clz.getName()).append("{").append("\r\n")
                    .append(source.toString()).append("\r\n")
                    .append("}")
            ;
            if (isWrapper) {
                target.append("// ").append(clz.getName()).append("\r\n")
                .append("class ").append(clz.getName()).append("{").append("\r\n")
                .append(source.toString()).append("\r\n")
                .append("}")
                ;
                return new Pair<String, String>(source.toString(), LinesPorcesser.wrapper(target.toString()));
            } else {

                return new Pair<String, String>(source.toString(), target.toString());
            }

        } catch (Throwable e) {
            e.printStackTrace();
        }
        return new Pair<String, String>(null, null);
    }

    public static String parserMethodByClass(Class<?> clz) {
        if (clz == null) {
            return null;
        }
        Method[] methods = clz.getDeclaredMethods();
        if (methods == null || methods.length == 0) {
            return null;
        }
        StringBuilder res = new StringBuilder();
        for (Method method : methods) {
            String line = MethodParser.parserMethod(method);
            if (!Utils.isEmpty(line)) {
                res.append("\t").append(line).append("\r\n");
            }
        }
        return res.toString();
    }

    public static String parserFieldByClass(Class<?> clz) throws NoSuchFieldException, IllegalAccessException {
        if (clz == null) {
            return null;
        }
        Field[] fields = clz.getDeclaredFields();
        if (fields == null || fields.length == 0) {
            return null;
        }
        StringBuilder res = new StringBuilder();
        for (Field f : fields) {
            String line = Ref.getField(null, f);
            if (!Utils.isEmpty(line)) {
                res.append("\t").append(line).append("\r\n");
            }
        }
        return res.toString();
    }
}
