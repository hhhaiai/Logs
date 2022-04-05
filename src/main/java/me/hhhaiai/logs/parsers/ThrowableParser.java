package me.hhhaiai.logs.parsers;

import me.hhhaiai.logs.utils.Pair;

import me.hhhaiai.logs.proces.LinesPorcesser;
import me.hhhaiai.logs.utils.Utils;

public class ThrowableParser implements IParser {
    @Override
    public Pair<String, String> parserObject(Object args, boolean isFormat, boolean isWrapper) {
        if (args == null) {
            return null;
        }
        Throwable th = (Throwable) args;

        String source = Utils.getStackTraceString(th);
        String tartget = null;
        if (isFormat) {
            tartget = Supervision.format(source);
        }
        if (isWrapper) {
            tartget = LinesPorcesser.wrapper(source);
            return new Pair<String, String>(source, tartget);
        } else {
            return new Pair<String, String>(source, source);
        }
    }


}