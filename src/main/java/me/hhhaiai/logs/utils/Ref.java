package me.hhhaiai.logs.utils;

import me.hhhaiai.logs.parsers.Helper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


/**
 * @Copyright © 2021 sanbo Inc. All rights reserved.
 * @Description: TODO
 * @Version: 1.0
 * @Create: 2021/10/21 3:10 下午
 * @author: sanbo
 */
public class Ref {

    // 获取变量
    public static String getField(Object obj, Field field) {
        StringBuffer line = new StringBuffer();
        try {
            if (field != null) {
                field.setAccessible(true);
            }
            //解析类注释
            Annotation[] as = field.getDeclaredAnnotations();
            if (as != null && as.length > 0) {
                for (Annotation a : as) {
                    line.append(a.toString()).append("\r\n");
                }
            }
            line.append(field.toGenericString());

            int modify = field.getModifiers();
            setFinalFieldReadable(field, modify);
            // 解析静态变量.也可以理解成获取默认值
            if (Modifier.isStatic(modify)) {
                try {
                    Object resFieldValue = field.get(null);
                    if (resFieldValue != null) {
                        line.append(" = ").append(Helper.wrpper(resFieldValue));
                    }
                } catch (Throwable e) {
                }
            }
            if (obj != null) {
                Object value = field.get(obj);
                if (value != null) {
                    line.append(" = ").append(Helper.wrpper(value));
                }
            }
            if (!line.toString().endsWith(";")) {
                line.append(";");
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return line.toString();
    }

    // support android and java
    private static void setFinalFieldReadable(Field field, int modify) throws NoSuchFieldException, IllegalAccessException {
        if (Modifier.isFinal(modify)) {
            Field modifiersField = getField("modifiers");
            if (modifiersField == null) {
                modifiersField = getField("accessFlags");
            }
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, modify & ~Modifier.FINAL);
        }
    }

    private static Field getField(String accessFlags) {
        try {
            return Field.class.getDeclaredField(accessFlags);
        } catch (Throwable e) {
        }
        return null;
    }

    public static Class<?> getClass(String className) throws ClassNotFoundException {
        Class c = null;
        try {
            c = Class.forName(className);
        } catch (Throwable e) {
        }
        if (c == null) {
            c = Ref.class.getClassLoader().loadClass(className);
        }
        return c;
    }

    public static Method getMethod(Class<?> cls, String methodName, Class<?>... parameterTypes) {
        Method m = null;
        try {
            m = cls.getDeclaredMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
        }
        if (m != null) {
            return m;
        }
        try {
            m = cls.getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
        }
        return m;
    }

}
