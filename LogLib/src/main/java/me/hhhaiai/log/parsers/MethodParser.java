package me.hhhaiai.log.parsers;

import java.lang.reflect.Method;

public class MethodParser implements IParser {
    @Override
    public String parserObject(Object args) {
        if (args == null) {
            return null;
        }
        Method method = (Method) args;
        return null;
    }

    @Override
    public EParserType getTypeName() {
        return EParserType.T_METHOD;
    }

    @Override
    public String format(String args) {
        return args;
    }
}