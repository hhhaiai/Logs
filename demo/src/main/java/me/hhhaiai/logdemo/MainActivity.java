package me.hhhaiai.logdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.hhhaiai.log.Logs;

public class MainActivity extends Activity {


    private void click(View v) throws NoSuchFieldException, IllegalAccessException {
        switch (v.getId()) {
            case 1:
//                String buildField = ClassParser.parserFieldByClass(Build.class);
//                print(buildField);
                Logs.i(Build.class);
                break;
            case 2:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_ALL_APPS);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(this, MainActivity.class);
                Logs.i(intent);
                break;
            case 3:
                Map<String, Integer> m = new HashMap<>();

                m.put("周", 1);
                m.put("吴", 2);
                m.put("郑", 3);
                m.put("王", 4);
                Logs.i(m);
                break;
            default:
                break;
        }
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