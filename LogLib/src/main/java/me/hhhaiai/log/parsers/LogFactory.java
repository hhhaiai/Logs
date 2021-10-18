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
            // 未序列化的字符串可以直接上传、保存
            // 3. 数据处理，格式化和包裹
            String processedData = parser.process(source,LContent.isDefFormatInfo, LContent.isDefWarpperInfo);
            // 处理后的字符串可以直接上传、保存
            // 4. 检测大小和切割处理
            return Supervision.split(processedData);
        }
        return null;
    }


}
