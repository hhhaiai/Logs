package me.hhhaiai.log.parsers;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import me.hhhaiai.log.CtlCheck;
import me.hhhaiai.log.LContent;

public class LogFactory {

    public static List<String> getInfo(Object... args) {
        CopyOnWriteArrayList result = new CopyOnWriteArrayList();
        for (Object o : args) {
            // 按照类型分割
            List<String> sp1 = getFianlContent(o);
            if (!CtlCheck.isValid(sp1)) {
                continue;
            } else {
                result.add(sp1);
            }
        }
        return result;
    }

    private static List<String> getFianlContent(Object o) {
        // 1. 获取解析器
        IParser parser = Supervision.getParser(o);
        if (parser != null) {
            // 2. 解析成可读性高的字符串
            String source = parser.parserObject(o);
            // 3. 格式化数据
            String formatData = source;
            if (LContent.isDefFormatInfo) {
                formatData = parser.format(source);
            }
            // 4. 再次加工数据
            String wrapContent = Supervision.wrapper(parser.getTypeName(), formatData);
            // 4.+ 如需要序列化保存文件，或者上报，可在此处另行处理
            // 5. 检测大小和切割处理
            return Supervision.split(wrapContent);
        }
        return null;
    }


}
