package me.hhhaiai.log.parsers;

import android.util.Pair;

import org.json.JSONArray;
import org.json.JSONException;

import me.hhhaiai.log.proces.LinesPorcesser;

public class ArrayPrser implements IParser {
    @Override
    public Pair<String, String> parserObject(Object args, boolean isFormat, boolean isWrapper) {
        if (args == null) {
            return null;
        }
        // 基本类型转换有问题
        String type = args.toString();
        JSONArray arr = parserArrays(args, type);

        String source = arr.toString();
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

    private JSONArray parserArrays(Object args, String type) {
        JSONArray arr = new JSONArray();
        // 版本1 支持一维数组
        if (type.startsWith(type_int)) {
            int[] ints = (int[]) args;
            if (ints.length > 0) {
                for (int i = 0; i < ints.length; i++) {
                    arr.put(ints[i]);
                }
            }
        } else if (type.startsWith(type_boolean)) {
            boolean[] booleans = (boolean[]) args;
            if (booleans.length > 0) {
                for (int i = 0; i < booleans.length; i++) {
                    arr.put(booleans[i]);
                }
            }
        } else if (type.startsWith(type_byte)) {
            byte[] bytes = (byte[]) args;
            if (bytes.length > 0) {
                for (int i = 0; i < bytes.length; i++) {
                    arr.put(bytes[i]);
                }
            }
        } else if (type.startsWith(type_short)) {
            short[] shorts = (short[]) args;
            if (shorts.length > 0) {
                for (int i = 0; i < shorts.length; i++) {
                    arr.put(shorts[i]);
                }
            }
        } else if (type.startsWith(type_long)) {
            long[] longs = (long[]) args;
            if (longs.length > 0) {
                for (int i = 0; i < longs.length; i++) {
                    arr.put(longs[i]);
                }
            }
        } else if (type.startsWith(type_float)) {
            float[] floats = (float[]) args;
            if (floats.length > 0) {
                for (int i = 0; i < floats.length; i++) {
                    try {
                        arr.put(floats[i]);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (type.startsWith(type_double)) {
            double[] doubles = (double[]) args;
            if (doubles.length > 0) {
                for (int i = 0; i < doubles.length; i++) {
                    try {
                        arr.put(doubles[i]);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (type.startsWith(type_char)) {
            char[] chars = (char[]) args;
            if (chars.length > 0) {
                for (int i = 0; i < chars.length; i++) {
                    arr.put(chars[i]);
                }
            }
        } else if (type.startsWith(type_oject)) {
            Object[] ojects = (Object[]) args;
            if (ojects.length > 0) {
                for (int i = 0; i < ojects.length; i++) {
                    arr.put(ojects[i]);
                }
            }
        } else {
            // 多位数组或者其他
            arr.put(args.toString());
        }
        return arr;
    }

    private String type_int = "[I";
    private String type_boolean = "[Z";
    private String type_byte = "[B";
    private String type_short = "[S";
    private String type_long = "[J";
    private String type_float = "[F";
    private String type_double = "[D";
    private String type_char = "[C";
    private String type_oject = "[L";

}
