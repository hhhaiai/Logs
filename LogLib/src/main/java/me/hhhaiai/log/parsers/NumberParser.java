package me.hhhaiai.log.parsers;

public class NumberParser implements IParser {
    @Override
    public String parserObject(Object args) {
        if (args == null) {
            return null;
        }
        return args.toString();
    }

    @Override
    public EParserType getTypeName() {
        return EParserType.T_NUMBER;
    }

    @Override
    public String format(String args) {
        return args;
    }
}
