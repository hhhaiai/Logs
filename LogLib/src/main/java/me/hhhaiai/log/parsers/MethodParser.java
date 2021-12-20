package me.hhhaiai.log.parsers;

import android.util.Pair;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class MethodParser implements IParser {
    @Override
    public Pair<String, String> parserObject(Object args, boolean isFormat, boolean isWrapper) {
        if (args == null) {
            return null;
        }
        String source = parserMethod((Method) args);
        String tartget = null;
        if (isFormat) {
            tartget = Supervision.format(source);
        }
        if (isWrapper) {
            tartget = Supervision.wrapper(tartget);
        }
        return new Pair<String, String>(source, tartget);
    }

    public static String parserMethod(Method method) {
        if (method == null) {
            return null;
        }
        method.setAccessible(true);
        StringBuffer line = new StringBuffer();

        Annotation[] as = method.getDeclaredAnnotations();
        if (as != null && as.length > 0) {
            for (Annotation a : as) {
                line.append(a.toString()).append("\r\n");
            }
        }
        line.append(method.toGenericString());
        if (!line.toString().endsWith(";")) {
            return line.toString() + ";";
        }

        return null;
    }
}