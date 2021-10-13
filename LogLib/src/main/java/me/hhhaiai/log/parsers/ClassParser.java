package me.hhhaiai.log.parsers;

public class ClassParser implements IParser {
    @Override
    public String parserObject(Object args) {
        if (args == null) {
            return null;
        }
        Class<?> clz = (Class) args;

        return null;
    }

    @Override
    public EParserType getTypeName() {
        return EParserType.T_CLASS;
    }

    @Override
    public String format(String args) {
        return args;
    }
}
