package me.hhhaiai.logs.parsers;

import android.util.Pair;

import org.json.JSONArray;

import java.util.Iterator;

import me.hhhaiai.logs.proces.LinesPorcesser;


/**
 * @Copyright © 2021 sanbo Inc. All rights reserved.
 * @Description: 列表支持, List-
 * @Version: 1.0
 * @Create: 2021/12/355 16:07:16
 * @author: sanbo
 */
public class IterablePrser implements IParser {
    @Override
    public Pair<String, String> parserObject(Object args, boolean isFormat, boolean isWrapper) {
        if (args == null) {
            return null;
        }
        Iterable iterable = (Iterable) args;
        return getStringStringPair(isFormat, isWrapper, iterable);
    }

    protected static Pair<String, String> getStringStringPair(boolean isFormat, boolean isWrapper, Iterable iterable) {
        Iterator it = iterable.iterator();
        JSONArray arr = new JSONArray();
        while (it.hasNext()) {
            arr.put(it.next());
        }
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


}
