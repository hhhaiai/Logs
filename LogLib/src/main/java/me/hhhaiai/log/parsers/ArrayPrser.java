package me.hhhaiai.log.parsers;

import android.util.Pair;

import java.util.Arrays;
import java.util.List;

public class ArrayPrser implements IParser {
    @Override
    public Pair<String, String> parserObject(Object args, boolean isFormat, boolean isWrapper) {
        if (args == null) {
            return null;
        }
        List list = Arrays.asList(args);
        return IterablePrser.getStringStringPair(isFormat, isWrapper, list);
    }


}
