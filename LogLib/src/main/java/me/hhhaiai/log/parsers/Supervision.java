package me.hhhaiai.log.parsers;

import android.text.TextUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import me.hhhaiai.log.CtlCheck;

class Supervision {

    /**
     * @param info
     * @return
     * @TODO
     */
    static String wrapper(String info) {
        if (TextUtils.isEmpty(info)) {
            return null;
        }
        return null;
    }

    /**
     * @param info
     * @return
     * @TODO 按个体切割
     */
    static List<String> split(String info) {
        if (TextUtils.isEmpty(info)) {
            return null;
        }
        return null;
    }

    static IParser getParser(Object o) {
        if (o instanceof String) {
            String temp = (String) o;
            if (CtlCheck.isJsonArray(temp)) {
                return new JsonArrayParser();
            } else if (CtlCheck.isJsonObject(temp)) {
                return new JsonObjParser();
            } else if (CtlCheck.isXml(temp)) {
                return new XmlParser();
            } else {
                return new StringPrser();
            }
        } else if (o instanceof Number) {
            return new NumberParser();
        } else if (o instanceof Class) {
            return new ClassParser();
        } else if (o instanceof Method) {
            return new MethodParser();
        } else if (o instanceof Field) {
            return new FieldParser();
        } else if (o instanceof Object) {
            return new ObjectParer();
        }
        return null;
    }
}
