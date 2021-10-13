package me.hhhaiai.log.parsers;

public class StringPrser implements IParser {
    @Override
    public String parserObject(Object obj) {
        if (obj==null){
            return null;
        }
        return (String) obj;
    }
    @Override
    public EParserType getTypeName() {
        return EParserType.T_STRING;
    }
}
