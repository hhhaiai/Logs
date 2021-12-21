package me.hhhaiai.log.parsers;

import android.util.Log;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Copyright © 2021 sanbo Inc. All rights reserved.
 * @Description: TODO
 * @Version: 1.0
 * @Create: 2021/12/21 4:00 PM
 * @author: sanbo
 */
public class SupervisionTest extends TestCase {


    public void testGetParser() {
        Map m = new HashMap();
        m.put("a",1);
        m.put("b",2);
                Log.i("sanbo",Supervision.getParser(m).toString());

        List<String> ls =new ArrayList<>();
        ls.add("1");
        ls.add("2");
        Log.i("sanbo",Supervision.getParser(ls).toString());


        String[] ss =new String[]{"1","2"};
        Log.i("sanbo",Supervision.getParser(ss).toString());

        int[] is = new int[]{1, 2, 4, 2, 1};
        Log.i("sanbo",Supervision.getParser(is).toString());
    }
}