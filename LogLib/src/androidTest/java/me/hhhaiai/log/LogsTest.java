package me.hhhaiai.log;


public class LogsTest extends BaseLogTest {

    public void testV() {
        Logs.v("test");
    }

    public void testD() {
        Logs.d("test");
    }

    public void testI() {
        Logs.i("test");
        System.err.println("xxxxx");
    }

    public void testW() {
        Logs.w("test");
    }

    public void testE() {
        Logs.e("test");
    }

    public void testWtf() {
        Logs.wtf("test");
    }
}