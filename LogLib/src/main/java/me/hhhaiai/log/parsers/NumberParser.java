package me.hhhaiai.log.parsers;

import android.util.Pair;

import me.hhhaiai.log.proces.LinesPorcesser;

public class NumberParser implements IParser {
    @Override
    public Pair<String, String> parserObject(Object args, boolean isFormat, boolean isWrapper) {
        if (args == null) {
            return null;
        }
        String source = args.toString();
        String tartget = null;
        if (isFormat) {
            tartget = Supervision.format(source);
        }
        if (isWrapper) {
            tartget = LinesPorcesser.wrapper(tartget);
            return new Pair<String, String>(source, tartget);
        }else {
            return new Pair<String, String>(source, source);
        }

    }

}
