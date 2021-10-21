package me.hhhaiai.log.parsers;

import android.util.Pair;

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
            // 2. 数据处理，格式化和包裹
            // 返回值: 处理前,处理后，方便使用加工数据(可以直接上传或者保存)
            Pair<String, String> processedData = parser.parserObject(o, LContent.isDefFormatInfo, LContent.isDefWarpperInfo);

            // 3. 获取加工后数据
            if (processedData == null) {
                return null;
            }
            String processData = processedData.second;
            // 4. 按照能打印大小进行切割
            return Supervision.split(processData);
        }
        return null;
    }


}
