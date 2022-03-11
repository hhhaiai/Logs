package me.hhhaiai.logs.proces;


import android.util.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
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

        StringBuilder sb = new StringBuilder();
        sb.append(content_title_info_log).append("\r\n");
        if (TextUtils.isEmpty(info)) {
            sb.append(CONTENT_LINE);
            return String.valueOf(sb);
        }

        preprocess(info, sb, "\r");
        preprocess(info, sb, "\n");
        preprocess(info, sb, "\r\n");
        preprocess(info, sb, "\n\r");
        preprocess(info, sb, "");
        sb.append(content_title_end);
        return String.valueOf(sb);
    }

    private static void preprocess(String info, StringBuilder sb, String sps) {
        if (TextUtils.isEmpty(sps)) {
            if (!TextUtils.isEmpty(info)
                    && !info.startsWith(CONTENT_A)
                    && !info.startsWith(CONTENT_B)
                    && !info.startsWith(CONTENT_C)
                    && !info.startsWith(CONTENT_D)
                    && !info.startsWith(CONTENT_E)) {
                sb.append(CONTENT_LINE);
            }
            sb.append(info);
            return;
        }

        if (info.contains(sps)) {
            String[] ss = info.split(sps);
            String temp = null;
            if (ss.length > 0) {
                sb = new StringBuilder();
                for (int i = 0; i < ss.length; i++) {
                    temp = ss[i];
                    if (
                            !TextUtils.isEmpty(info)
                                    && !info.startsWith(CONTENT_A)
                                    && !info.startsWith(CONTENT_B)
                                    && !info.startsWith(CONTENT_C)
                                    && !info.startsWith(CONTENT_D)
                                    && !info.startsWith(CONTENT_E)
                    ) {
                        sb.append(CONTENT_LINE);
                    }
                    sb.append(temp);

                    if (i != ss.length - 1) {
                        sb.append("\n");
                    }
                }
            }

        }
    }

    // 规定每段显示的长度.每行最大日志长度 (Android Studio3.1最多2902字符，忘记来源，好像是测试来的)
    private static int LOG_MAXLENGTH = 2900;
    // 格式化时，行首封闭符
    private static String CONTENT_LINE = "║ ";

    //    private static String content_title_begin =
//            "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════";
//    private static String content_title_info_callstack =
//            "╔══════════════════════════════════════════════════════════════调用详情══════════════════════════════════════════════════════════════";
    private static String content_title_info_log =
            "╔══════════════════════════════════════════════════════════════日志详情══════════════════════════════════════════════════════════════";
    //    private static String content_title_info_error =
//            "╔══════════════════════════════════════════════════════════════异常详情══════════════════════════════════════════════════════════════";
//    private static String content_title_info_type =
//            "╔════════════════════════════════════════════════════「%s"
//                    + "」════════════════════════════════════════════════════";
    private static String content_title_end =
            "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════";
    /**
     * 行首为该符号时，不增加行首封闭符
     */
    private static String CONTENT_A = CONTENT_LINE;
    private static String CONTENT_B = "╔";
    private static String CONTENT_C = "╚";
    private static String CONTENT_D = " ╔";
    private static String CONTENT_E = " ╚";
//    private static Character FORMATER = '%';

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
                // 预加计算，超标，
                if (sb.length() + line.length() >= LOG_MAXLENGTH) {
                    result.add(sb.toString());
                    sb = new StringBuilder();
//                    if (i != splitStr.size() - 1) {
//                        sb.append("\n");
//                    }
                } else {
                    sb.append(line).append("\r\n");
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
        if (TextUtils.isEmpty(msg)) {
            return result;
        }
        // split to lines
        List<String> lines = parserToLines(msg);
        // makesure less than max size in line.
        if (lines.size() < 1) {
            lines.add(msg);
        }
        for (String line : lines) {
            // 单行都超过最大长度，直接按照字符串分别来做
            processLine(maxLen, result, line.trim());
        }

        return result;
    }

    private static List<String> parserToLines(String msg) {
        List<String> lines = new ArrayList<String>();
        parserToLines(lines, msg, "\r");
        parserToLines(lines, msg, "\n");
        parserToLines(lines, msg, "\r\n");
        parserToLines(lines, msg, "\n\r");
        if (lines.size() == 0) {
            //has any thing
            lines.add(msg);
        }
        return lines;
    }

    private static void parserToLines(List<String> lines, String msg, String splitKey) {
        if (msg.contains(splitKey)) {
            String[] tempss = msg.split(splitKey);
            if (tempss != null) {
                // makesure can update
                lines.addAll(Arrays.asList(tempss));
            }
        }
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
