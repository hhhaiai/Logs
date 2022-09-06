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
            } else {
                // 对象
                if (obj != null) {
                    Object value = field.get(obj);
                    if (value != null) {
                        line.append(" = ").append(Helper.wrpper(value));
                    }
                } else {
                    //已经增加了响应的变量
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
    private static void setFinalFieldReadable(Field field, int modify) {
        try {
            if (Modifier.isFinal(modify)) {
                Field modifiersField = getField(Field.class, "modifiers");
                if (modifiersField == null) {
                    modifiersField = getField(Field.class, "accessFlags");
                }
                modifiersField.setAccessible(true);
                modifiersField.setInt(field, modify & ~Modifier.FINAL);
            }
        } catch (Throwable e) {
        }
    }

    public static Field getField(Class clazz, String fieldName) {
        if (clazz == null || Utils.isEmpty(fieldName)) {
            return null;
        }
        Field field = null;
        while (clazz != Object.class) {
            try {
                field = clazz.getDeclaredField(fieldName);
                if (field != null) {
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    return field;
                }
            } catch (Exception e) {
            }
            clazz = clazz.getSuperclass();
        }
        return field;
    }

    public static Class<?> getClass(String className) {
        if (Utils.isEmpty(className)) {
            return null;
        }
        Class c = null;
        try {
            c = Class.forName(className);
        } catch (Throwable e) {
        }
        if (c == null) {

            try {
                c = ClassLoader.getSystemClassLoader().loadClass(className);
            } catch (Throwable e) {
            }
        }
        return c;
    }

    // 基础方法，不能使用其他内部反射
    public static Method getMethod(Class<?> clazz, String methodName, Class<?>... types) {
        if (clazz == null || Utils.isEmpty(methodName)) {
            return null;
        }
        Method method = null;
        while (clazz != Object.class) {
            try {
                method = clazz.getDeclaredMethod(methodName, types);
                if (method != null) {
                    if (!method.isAccessible()) {
                        method.setAccessible(true);
                    }
                    return method;
                }
            } catch (Throwable igone) {
            }
            clazz = clazz.getSuperclass();
        }
        return method;
    }

}
