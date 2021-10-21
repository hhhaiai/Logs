package me.hhhaiai.log.parsers;

import android.text.TextUtils;
import android.util.Pair;

import me.hhhaiai.log.CtlCheck;

public class StringPrser implements IParser {
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
            tartget = Supervision.wrapper(source);
        }
        return new Pair<String, String>(source, tartget);
    }


}
