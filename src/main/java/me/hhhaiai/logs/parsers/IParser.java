package me.hhhaiai.logs.parsers;



import me.hhhaiai.logs.utils.Pair;

/**
 * @Copyright © 2021 sanbo Inc. All rights reserved.
 * @Description: 解析器接口
 * @Version: 1.0
 * @Create: 2021/10/286 11:17:45
 * @author: sanbo
 */
interface IParser {

    /**
     * 将对象转换成有效识别的字符串
     *
     * @param args      源对象
     * @param isFormat  是否格式化
     * @param isWrapper 是否包裹
     * @return Pair<String, String> : 未处理字符串，处理后字符串
     */
    public abstract Pair<String, String> parserObject(Object args, boolean isFormat, boolean isWrapper);

}
