package demo;

import me.hhhaiai.logs.Logs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ax {
    public static void main(String[] args) {
//        Logs.i("字符串打印:" + a());
//        Logs.i(new Model("名字",99));
//        Logs.i(Model.class);
//
//        testMap();
//        testList();
//        testNull();
        System.out.println("原本颜色字符串打印:" + a());
        System.out.println("原本颜色字符串打印:" + a());
        System.out.println("原本颜色字符串打印:" + a());
        System.out.println("原本颜色字符串打印:" + a());
        Logs.v("字符串打印:" + a());
        Logs.d("字符串打印:" + a());
        Logs.i("字符串打印:" + a());
        Logs.w("字符串打印:" + a());
        Logs.e("字符串打印:" + a());
        Logs.wtf("字符串打印:" + a());

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                Logs.v("字符串打印:" + a());
                Logs.d("字符串打印:" + a());
                Logs.i("字符串打印:" + a());
                Logs.w("字符串打印:" + a());
                Logs.e("字符串打印:" + a());
                Logs.wtf("字符串打印:" + a());
            }).start();
        }

    }

    private static void testNull() {
        Logs.i(null);
        Logs.i("");
    }

    private static void testList() {
        List<String> list  =new ArrayList<String>(){{
            add("o1");
            add("o2");
            add("o3");
            add("o4");
            add("o5");
        }};
        Logs.i(list);
    }

    private static void testMap() {
        Map<String,String> map =new HashMap<String,String>(){{
            put("key1","value1");
            put("key2","value2");
            put("key3","value3");
            put("key4","value4");
        }};
        Logs.i(map);
    }

    private static String a() {
        return a("xx");
    }

    private static String a(String xx) {
        return Model.b(xx);
    }
}
