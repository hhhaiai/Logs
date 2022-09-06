package demo;

public class Model {
    private static final String _private_static_final_string = "private_static_final_string";
    private static String _private_static_string = "private_static_string";

    public static final String _public_static_final_string = "public_static_final_string";
    public static String _public_static_string = "public_static_string";

    static final String _static_final_string = "static_final_string";
    static String _static_string = "static_string";

    final String _final_string = "final_string";
    String _string = "string";


    private static final int _private_static_final_int = 1;
    private static int _private_static_int = 2;

    public static final int _public_static_final_int = 3;
    public static int _public_static_int = 4;

    static final int _static_final_int = 5;
    static int _static_int = 6;

    final int _final_int = 7;
    int _int = 8;

    private static String mName = null;
    private static int iAge = -1;

    public Model(String name, int age) {
        this.mName = name;
        this.iAge = age;
    }

    public static String b(String xx) {
        return b(xx, "hello");
    }

    private static String b(String xx, String hello) {
        return xx + "-" + hello;
    }
}
