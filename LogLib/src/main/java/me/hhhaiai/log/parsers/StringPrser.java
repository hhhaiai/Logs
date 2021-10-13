package me.hhhaiai.log.parsers;

import android.util.Pair;

import me.hhhaiai.log.CtlCheck;

public class StringPrser implements IParser {
    @Override
    public String parserObject(Object args) {
        if (args == null) {
            return null;
        }
        return (String) args;
    }

    @Override
    public EParserType getTypeName() {
        return EParserType.T_STRING;
    }

    @Override
    public String format(String args) {
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
        return args;
    }
}
