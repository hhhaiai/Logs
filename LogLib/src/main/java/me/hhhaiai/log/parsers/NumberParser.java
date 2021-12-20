package me.hhhaiai.log.parsers;

import android.util.Pair;

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
            tartget = Supervision.wrapper(tartget);
        }
        return new Pair<String, String>(source, tartget);
    }

}
