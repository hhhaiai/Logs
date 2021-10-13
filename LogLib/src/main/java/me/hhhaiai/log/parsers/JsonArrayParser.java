package me.hhhaiai.log.parsers;

public class JsonArrayParser implements IParser {
    @Override
    public String parserObject(Object obj) {
        if (obj == null) {
            return null;
        }
        String base = (String) obj;
        return null;
    }

    @Override
    public EParserType getTypeName() {
        return EParserType.T_JSONARRAY;
    }
}
