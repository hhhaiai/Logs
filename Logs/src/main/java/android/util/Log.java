package android.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Log {


    /**
     * Priority constant for the println method; use Log.v.
     */
    public static final int VERBOSE = 2;

    /**
     * Priority constant for the println method; use Log.d.
     */
    public static final int DEBUG = 3;

    /**
     * Priority constant for the println method; use Log.i.
     */
    public static final int INFO = 4;

    /**
     * Priority constant for the println method; use Log.w.
     */
    public static final int WARN = 5;

    /**
     * Priority constant for the println method; use Log.e.
     */
    public static final int ERROR = 6;

    /**
     * Priority constant for the println method.
     */
    public static final int ASSERT = 7;


    /**
     * Handy function to get a loggable stack trace from a Throwable
     * @param tr An exception to log
     */
    public static String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "";
        }

        // This is to reduce the amount of log spew that apps do in the non-error
        // condition of the network being unavailable.
        Throwable t = tr;
        while (t != null) {
            if (t instanceof Exception) {
                return "";
            }
            t = t.getCause();
        }

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        tr.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }

//    /**
//     * Low-level logging call.
//     * @param priority The priority/type of this log message
//     * @param tag Used to identify the source of a log message.  It usually identifies
//     *        the class or activity where the log call occurs.
//     * @param msg The message you would like logged.
//     * @return The number of bytes written.
//     */
//    public static int println(int priority, String tag, String msg) {
//        // android logint println(int priority, String tag, String msg)
//        return 0;
//    }


}
