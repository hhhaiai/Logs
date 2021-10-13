package me.hhhaiai.log.parsers;

public class NumberParser implements IParser {
    @Override
    public String parserObject(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    @Override
    public EParserType getTypeName() {
        return EParserType.T_NUMBER;
    }
}
