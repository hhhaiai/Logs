package me.hhhaiai.log;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import me.hhhaiai.log.utils.Closer;


public class CtlCheck {
    /**
     * shell不允许打印该级别日志打印
     *
     * @param level
     * @return
     */
    static boolean isShellLimit(int level) {
        if (LContent.isDefShellControl && !Log.isLoggable(LContent.DefTAG, level)) {
            LContent.getLogLevelName(level);
            Log.println(level, LContent.DefTAG, LContent.getShellErrorInfo(level));
            return true;
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


    public static boolean isJsonArray(String args) {
        try {
            new JSONArray(args);
            return true;
        } catch (Throwable e) {
        }
        return false;
    }

    public static boolean isJsonObject(String args) {
        try {
            new JSONObject(args);
            return true;
        } catch (Throwable e) {
        }
        return false;
    }

    public static boolean isXml(String temp) {
        if (TextUtils.isEmpty(getFormatXml(temp))) {
            return false;
        }
        return true;
    }

    public static String getFormatXml(String temp) {
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
                    String.valueOf(4));
            transformer.transform(xmlInput, xmlOutput);
            String sp = System.getProperty("line.separator");
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                sp = System.lineSeparator();
            }
            String result = xmlOutput.getWriter().toString().replaceFirst(">", ">" + sp);
            if (TextUtils.isEmpty(result)) {
                return null;
            } else {
                return result;
            }
        } catch (Throwable e) {
        } finally {
            Closer.close(t, sw);
        }
        return null;
    }
}
