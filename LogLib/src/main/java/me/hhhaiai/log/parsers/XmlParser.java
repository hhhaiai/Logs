package me.hhhaiai.log.parsers;

import me.hhhaiai.log.CtlCheck;

public class XmlParser implements IParser {
    @Override
    public String parserObject(Object obj) {
        if (obj == null) {
            return null;
        }
        String base = (String) obj;
        return CtlCheck.getFormatXml(base);
    }

    @Override
    public EParserType getTypeName() {
        return EParserType.T_XML;
    }
}
