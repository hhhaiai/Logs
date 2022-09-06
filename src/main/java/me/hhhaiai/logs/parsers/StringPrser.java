package me.hhhaiai.logs.parsers;

import me.hhhaiai.logs.proces.LinesPorcesser;
import me.hhhaiai.logs.utils.Pair;


public class StringPrser implements IParser {
    @Override
    public Pair<String, String> parserObject(Object args, boolean isFormat, boolean isWrapper) {
        if (args == null) {
            args = "null";
        }
        String source = args.toString();
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
