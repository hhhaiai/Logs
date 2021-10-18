package me.hhhaiai.log.parsers;

import android.text.TextUtils;
import android.util.Pair;

import me.hhhaiai.log.CtlCheck;

public class StringPrser implements IParser {
    @Override
    public String parserObject(Object args) {
        if (args == null) {
            return null;
        }
        try {
            return (String) args;
        } catch (Throwable e) {
            return args.toString();
        }
    }


    @Override
    public String format(String args) {
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

    @Override
    public String wrapper(String args) {
        if (TextUtils.isEmpty(args)) {
            return null;
        }
        return null;
    }
}
