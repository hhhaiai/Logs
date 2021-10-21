package me.hhhaiai.log.parsers;

import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;

public class ThrowableParser implements IParser {
    @Override
    public Pair<String, String> parserObject(Object args, boolean isFormat, boolean isWrapper) {
        if (args == null) {
            return null;
        }
        Throwable th = (Throwable) args;

        String source = Log.getStackTraceString(th);
        String tartget = null;
        if (isFormat) {
            tartget = Supervision.format(source);
        }
        if (isWrapper) {
            tartget = Supervision.wrapper(source);
        }
        return new Pair<String, String>(source, tartget);
    }


}