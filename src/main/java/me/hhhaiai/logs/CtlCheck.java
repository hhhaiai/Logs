package me.hhhaiai.logs;


import me.hhhaiai.logs.utils.Closer;
import me.hhhaiai.logs.utils.Pair;
import me.hhhaiai.logs.utils.Utils;
import me.hhhaiai.logs.utils.org.json.JSONArray;
import me.hhhaiai.logs.utils.org.json.JSONObject;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;


public class CtlCheck {
    /**
     * shell不允许打印该级别日志打印
     *
     * @param priorty
     * @return
     */
    static boolean isShellLimit(int priorty) {

        if (!LoggerNative.isAndroidPlatform()) {
            return false;
        } else {
            // android 平台才接受shell控制
            if (LContent.isDefShellControl) {
                return LoggerNative.isLoggable(LContent.DefTAG, priorty);
            }
        }
        return false;
    }

    /**
     * 参数是否有效
     *
     * @param args
     * @return true 有效
     * false 无效
     */
    public static boolean isValid(Object[] args) {
        if (args == null || args.length == 0) {
            return false;
        }
        return true;
    }

    /**
     * 参数是否有效
     *
     * @param args
     * @return true 有效
     * false 无效
     */
    public static boolean isValid(List<String> args) {
        if (args == null || args.size() == 0) {
            return false;
        }
        return true;
    }


    public static Pair<Boolean, String> tryGetJsonArray(String args) {
        try {
            JSONArray arr = new JSONArray(args);
            return new Pair<Boolean, String>(true, arr.toString(LContent.JSON_INDENT));
        } catch (Throwable e) {
        }
        return new Pair<Boolean, String>(false, "");
    }

    public static Pair<Boolean, String> tryGetJsonObject(String args) {
        try {
            // @TODO android 自带的JSON格式化有问题，多种其他语言的类型会出现转换的问题
            //  {"a":false,"b":1,"c":"c1","d":["xx",1,"w1",@(YES)],"e":False,"f":TRUE,"g":YES}
            JSONObject obj = new JSONObject(args);
            return new Pair<Boolean, String>(true, obj.toString(LContent.JSON_INDENT));
        } catch (Throwable e) {
        }
        return new Pair<Boolean, String>(false, "");
    }


    public static Pair<Boolean, String> getFormatXml(String temp) {
        StringReader t = null;
        StringWriter sw = null;
        try {
            t = new StringReader(temp);
            Source xmlInput = new StreamSource(t);

            sw = new StringWriter();
            StreamResult xmlOutput = new StreamResult(sw);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
                    String.valueOf(LContent.JSON_INDENT));
            transformer.transform(xmlInput, xmlOutput);
            String sp = System.getProperty("line.separator");
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//                sp = System.lineSeparator();
//            }
            String result = xmlOutput.getWriter().toString().replaceFirst(">", ">" + sp);
            if (!Utils.isEmpty(result)) {
                return new Pair<Boolean, String>(true, result);
            }
        } catch (Throwable e) {
        } finally {
            Closer.close(t, sw);
        }
        return new Pair<Boolean, String>(false, "");
    }
}
