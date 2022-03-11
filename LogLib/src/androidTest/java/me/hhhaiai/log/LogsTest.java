package me.hhhaiai.log;


import android.util.Log;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class LogsTest extends BaseLogTest {

    @Test
    public void testV() {
        Logs.v("test");
    }

    @Test
    public void testD() {
        Logs.d("test");
    }

    @Test
    public void testI() {
        Logs.i("test");
        System.err.println("xxxxx");
    }

    @Test
    public void testW() {
        Logs.w("test");
    }

    @Test
    public void testE() {
        Logs.e("test");
    }

    @Test
    public void testWtf() {
        Logs.wtf("test");
    }

    @Test
    public void testtt() {
        test("b", "a");
        test(new String[]{"1", "2"});
        test(new int[]{1, 2});
    }

    //[Z = boolean
    //[B = byte
    //[S = short
    //[I = int
    //[J = long
    //[F = float
    //[D = double
    //[C = char
    //[L = any non-primitives(Object)
    private void test(Object... objects) {
        Log.i("sanbo", "objects size:" + objects.length);
        if (objects.length == 1) {
            Log.i("sanbo", "arr:" + objects.getClass().isArray());
            Log.i("sanbo", "objects:" + Arrays.asList(objects).toString());
            Object is = objects[0];
            Log.i("sanbo", "is:" + is.toString());

            if (is.toString().startsWith("[I")) {
                int[] ar = (int[]) is;
                Log.i("sanbo", "ar size:" + ar.length);
                Log.i("sanbo", "ar :" + Arrays.asList(ar));
                ArrayList arrayList = new ArrayList();
                Collections.addAll(arrayList, ar);
                Log.i("sanbo", "arrayList:" + arrayList);
            }

        }
    }
}