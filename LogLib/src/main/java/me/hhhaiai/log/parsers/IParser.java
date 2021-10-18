package me.hhhaiai.log.parsers;

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
     * @param args
     * @return
     */
    public abstract String parserObject(Object args);


    /**
     * 格式化字符串
     *
     * @param args
     * @return
     */
    public abstract String format(String args);

    /**
     * 包裹
     *
     * @param args
     * @return
     */
    public abstract String wrapper(String args);
}
