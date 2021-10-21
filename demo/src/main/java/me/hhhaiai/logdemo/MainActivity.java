package me.hhhaiai.logdemo;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;

import java.lang.reflect.Field;

import me.hhhaiai.log.parsers.ClassParser;

public class MainActivity extends Activity {


    private void click(View v) throws NoSuchFieldException, IllegalAccessException {
        switch (v.getId()) {
            case 1:
                String buildField = ClassParser.parserFieldByClass(Build.class);
                print(buildField);
                break;
            case 2:
                String buildMethod = ClassParser.parserMethodByClass(Build.class);
                print(buildMethod);
                break;
            default:
                break;
        }
    }


    private void format1() {
        Class c = Build.class;
        Field[] fs = c.getFields();
        JSONArray ar = new JSONArray();
        for (Field f : fs) {
            ar.put(f.toString());
        }
        log(ar.toString());
    }

    private void print(String info) {
        log(info);
        mLayout.showInPage(info);
    }

    private void log(String s) {
        Log.i(TAG, "" + s);
    }

    private void ee(Throwable e) {
        ee(Log.getStackTraceString(e));
    }

    private void ee(String info) {
        Log.e(TAG, "" + info);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLayout = new CRelativeLayout(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    click(v);
                } catch (Throwable e) {
                    ee(e);
                }
            }
        }, 4, 3);
        setContentView(mLayout);
    }

    private static final String TAG = "sanbo.MainActivity";
    private CRelativeLayout mLayout = null;
}