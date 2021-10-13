package me.hhhaiai.log.parsers;

import java.lang.reflect.Field;

public class FieldParser implements IParser {
    @Override
    public String parserObject(Object args) {

        if (args == null) {
            return null;
        }
        Field filed = (Field) args;
        return null;
    }

    @Override
    public EParserType getTypeName() {
        return EParserType.T_FIELD;
    }

    @Override
    public String format(String args) {
        return args;
    }
}