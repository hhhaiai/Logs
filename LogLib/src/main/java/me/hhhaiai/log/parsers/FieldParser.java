package me.hhhaiai.log.parsers;

import android.util.Pair;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import me.hhhaiai.log.utils.Ref;

public class FieldParser implements IParser {
    @Override
    public Pair<String, String> parserObject(Object args, boolean isFormat, boolean isWrapper) {

        if (args == null) {
            return null;
        }
        Field filed = (Field) args;
        try {
            String source = parserField(filed);
            String tartget = null;
            if (isFormat) {
                tartget = Supervision.format(source);
            }
            if (isWrapper) {
                tartget = Supervision.wrapper(source);
            }
            return new Pair<String, String>(source, tartget);

        } catch (Throwable e) {
            e.printStackTrace();
        }
        return new Pair<String, String>(null, null);
    }


    public static String parserField(Field field) throws IllegalAccessException, NoSuchFieldException {

        if (field == null) {
            return null;
        }
        field.setAccessible(true);
        StringBuffer line = new StringBuffer();

        Annotation[] as = field.getDeclaredAnnotations();
        if (as != null && as.length > 0) {
            for (Annotation a : as) {
                line.append(a.toString()).append("\r\n");
            }
        }
        line.append(field.toGenericString());


        int modify = field.getModifiers();
        Ref.setFinalFieldReadable(field, modify);
        // 获取默认值
        if (Modifier.isStatic(modify)) {
            try {
                Object resFieldValue = field.get(null);
                if (resFieldValue != null) {
                    line.append(" = ").append(Supervision.wrpper(resFieldValue));
                }
            } catch (Throwable e) {
//                System.err.println("|field|"+field.toGenericString());
                e.printStackTrace();
            }
        }

        if (!line.toString().endsWith(";")) {
            return line.toString() + ";";
        }
        return line.toString();
    }


}