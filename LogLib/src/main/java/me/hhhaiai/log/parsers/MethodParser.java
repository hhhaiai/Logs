package me.hhhaiai.log.parsers;

public class MethodParser implements IParser{
    @Override
    public String parserObject(Object obj) {
        return null;
    }

    @Override
    public EParserType getTypeName() {
        return EParserType.T_METHOD;
    }
}