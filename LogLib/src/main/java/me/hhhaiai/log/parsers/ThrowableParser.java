package me.hhhaiai.log.parsers;

import android.util.Log;

public class ThrowableParser implements IParser {
    @Override
    public String parserObject(Object obj) {
        if (obj == null) {
            return null;
        }
        return Log.getStackTraceString((Throwable) obj);
    }

    @Override
    public EParserType getTypeName() {
        return EParserType.T_THROWABLE;
    }
}