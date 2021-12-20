package me.hhhaiai.log.parsers;

import android.util.Pair;

import java.lang.reflect.Field;

import me.hhhaiai.log.utils.Ref;

public class FieldParser implements IParser {
    @Override
    public Pair<String, String> parserObject(Object args, boolean isFormat, boolean isWrapper) {

        if (args == null) {
            return null;
        }
        Field filed = (Field) args;
        try {
            String source = Ref.getField(null, filed);
            String tartget = null;
            if (isFormat) {
                tartget = Supervision.format(source);
            }
            if (isWrapper) {
                tartget = Supervision.wrapper(tartget);
            }
            return new Pair<String, String>(source, tartget);

        } catch (Throwable e) {
            e.printStackTrace();
        }
        return new Pair<String, String>(null, null);
    }


}