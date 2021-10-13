package me.hhhaiai.log.parsers;

public class ObjectParer implements IParser{
    @Override
    public String parserObject(Object obj) {
        return null;
    }

    @Override
    public EParserType getTypeName() {
        return EParserType.T_OBJECT;
    }
}