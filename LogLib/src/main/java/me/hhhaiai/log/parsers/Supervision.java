package me.hhhaiai.log.parsers;

import android.text.TextUtils;
import android.util.Pair;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import me.hhhaiai.log.CtlCheck;

class Supervision {


    /**
     * 格式化
     * @param args
     * @return
     */
    static String format(String args) {
        if (TextUtils.isEmpty(args)) {
            return null;
        }
        // 1.try parser JSONArray
        Pair<Boolean, String> pair = CtlCheck.tryGetJsonArray(args);
        if (pair.first) {
            return pair.second;
        }
        // 2.try parser JSONObject
        pair = CtlCheck.tryGetJsonObject(args);
        if (pair.first) {
            return pair.second;
        }
        // 3.try parser XML
        pair = CtlCheck.getFormatXml(args);
        if (pair.first) {
            return pair.second;
        }
        // 4. other case. igone
        return args;
    }



    public static Object priWrep(Object res) {
        if (res instanceof Long) {
            return (Long) res + "L";
        } else if (res instanceof Float) {
            return (Float) res + "F";
        } else if (res instanceof Double) {
            return (Double) res + "D";
        } else if (res instanceof Number) {
            return res;
        } else if (res instanceof String) {
            return "\"" + res.toString() + "\"";
        } else if (res instanceof String[]) {
            return toString((String[]) res);
        }

        return null;
    }


    public static String toString(String[] a) {
        if (a == null || a.length == 0) {
            return "new String[]{}";
        }
        StringBuilder b = new StringBuilder();
        b.append("new String[]{");
        for (int i = 0; i < a.length; i++) {
            if (i != 0) {
                b.append(",");
            }
            b.append("\"").append(a[i]).append("\"");
        }
        return b.append('}').toString();
    }

    static IParser getParser(Object o) {
        if (o instanceof String) {
            return new StringPrser();
        } else if (o instanceof Number) {
            return new NumberParser();
        } else if (o instanceof Class) {
            return new ClassParser();
        } else if (o instanceof Method) {
            return new MethodParser();
        } else if (o instanceof Field) {
            return new FieldParser();
        } else if (o instanceof Map) {
            return new MapPrser();
        } else if (o instanceof java.lang.Iterable) {
            return new IterablePrser();
        } else if (o.getClass().isArray()) {
            return new ArrayPrser();
        } else if (o instanceof Object) {
            return new ObjectParer();
        } else {
            return new StringPrser();
        }
    }


}
