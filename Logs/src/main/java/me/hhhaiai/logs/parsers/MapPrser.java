package me.hhhaiai.logs.parsers;

import me.hhhaiai.logs.utils.Pair;

import org.json.JSONObject;

import java.util.Map;

import me.hhhaiai.logs.proces.LinesPorcesser;

public class MapPrser implements IParser {
    @Override
    public Pair<String, String> parserObject(Object args, boolean isFormat, boolean isWrapper) {
        if (args == null) {
            return null;
        }
        Map<?, ?> map = (Map) args;
        // JSON BUG: don't support  int key
        JSONObject obj = new JSONObject();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            try {
                Object key = entry.getKey();
                Object value = entry.getValue();
                if (key instanceof Number) {
                    obj.put(String.valueOf(key), value);
                } else if (key instanceof String) {
                    obj.put((String) key, value);
                } else {
                    if (key != null) {
                        obj.put(key.toString(), value);
                    } else {
                        obj.put("", value);
                    }

                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        String source = obj.toString();
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


}
