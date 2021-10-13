package me.hhhaiai.log.parsers;

interface IParser {
    public abstract String parserObject(Object obj);
    public abstract EParserType getTypeName();
}
