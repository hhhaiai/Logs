package android.util;

public class TextUtils {


    /**
     * Returns true if the string is null or 0-length.
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static String nullIfEmpty(String str) {
        return isEmpty(str) ? null : str;
    }

    public static String emptyIfNull(String str) {
        return str == null ? "" : str;
    }


}
