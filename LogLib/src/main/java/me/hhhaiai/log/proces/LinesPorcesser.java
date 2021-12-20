package me.hhhaiai.log.proces;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Copyright © 2021 sanbo Inc. All rights reserved.
 * @Description: 字符处理
 * @Version: 1.0
 * @Create: 2021/12/20 4:59 PM
 * @author: sanbo
 */
public class LinesPorcesser {

    /**
     * 包裹字符串
     * @param info
     * @return
     */
    public static String wrapper(String info) {

        return null;
    }

    // 规定每段显示的长度.每行最大日志长度 (Android Studio3.1最多2902字符，忘记来源，好像是测试来的)
    private static int LOG_MAXLENGTH = 2900;

    /**
     * @param info
     * @return
     * @TODO 按个体切割
     */
    public static List<String> split(String info) {
        if (TextUtils.isEmpty(info)) {
            return null;
        }
        List<String> result = new ArrayList<String>();
        if (info.length() > LOG_MAXLENGTH) {
            // 切割分行,含单行超长处理
            List<String> splitStr = getStringBysplitLine(info, LOG_MAXLENGTH);
            StringBuilder sb = null;
            for (int i = 0; i < splitStr.size(); i++) {
                String line = splitStr.get(i);
                if (sb == null) {
                    sb = new StringBuilder();
                }

                if (sb.length() + line.length() >= LOG_MAXLENGTH) {
                    result.add(sb.toString());
                    sb = new StringBuilder();
                    if (i != splitStr.size() - 1) {
                        sb.append("\n");
                    }
                } else {
                    sb.append(line);
                }
            }
            if (sb != null) {
                result.add(sb.toString());

            }
        } else {
            result.add(info);
        }
        return result;
    }

    /**
     * 按行分割字符串
     *
     * @param msg
     * @param maxLen
     * @return
     */
    private static List<String> getStringBysplitLine(String msg, int maxLen) {
        List<String> result = new ArrayList<String>();
        String[] lines = msg.split("\n");
        if (lines.length == 1) {
            lines = msg.split("\r");
            if (lines.length == 1) {
                lines = msg.split("\r\n");
                if (lines.length == 1) {
                    lines = msg.split("\n\r");
                }
            }
        }
        if (lines.length > 1) {
            for (int i = 0; i < lines.length; i++) {
                String line = lines[i];
                // 单行都超过最大长度，直接按照字符串分别来做
                processLine(maxLen, result, line);
            }
        } else {
            processLine(maxLen, result, msg);
        }

        return result;
    }

    /**
     * 处理单行超长处理
     *
     * @param maxLen
     * @param result
     * @param line
     */
    private static void processLine(int maxLen, List<String> result, String line) {
        if (line.length() > maxLen) {
            int current = 0;
            String str;
            while (true) {
                try {
                    str = line.substring(current, current + maxLen);
                    result.add(str);
                    current += maxLen;
                } catch (StringIndexOutOfBoundsException e) {
                    str = line.substring(current, line.length());
                    result.add(str);
                    break;
                }
            }
        } else {
            result.add(line);
        }
    }

}
