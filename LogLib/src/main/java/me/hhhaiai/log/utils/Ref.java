package me.hhhaiai.log.utils;

import java.lang.reflect.Field;

/**
 * @Copyright © 2021 sanbo Inc. All rights reserved.
 * @Description: TODO
 * @Version: 1.0
 * @Create: 2021/10/21 3:10 下午
 * @author: sanbo
 */
public class Ref {
    public static void setFinalFieldReadable(Field field, int modify) throws NoSuchFieldException, IllegalAccessException {
        if (java.lang.reflect.Modifier.isFinal(modify)) {
            Field modifiersField = getField("modifiers");
            if (modifiersField == null) {
                modifiersField = getField("accessFlags");
            }
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, modify & ~java.lang.reflect.Modifier.FINAL);
        }
    }

    private static Field getField(String accessFlags) {
        try {
            return Field.class.getDeclaredField(accessFlags);
        } catch (Throwable e) {
//            e.printStackTrace();
        }
        return null;
    }
}
