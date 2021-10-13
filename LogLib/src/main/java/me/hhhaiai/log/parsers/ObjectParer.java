package me.hhhaiai.log.parsers;

public class ObjectParer implements IParser {
    @Override
    public String parserObject(Object args) {
        if (args == null) {
            return null;
        }
        return null;
    }

    @Override
    public EParserType getTypeName() {
        return EParserType.T_OBJECT;
    }

    @Override
    public String format(String args) {
        return args;
    }
}