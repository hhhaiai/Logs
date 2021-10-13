package me.hhhaiai.logdemo;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;

import java.lang.reflect.Field;

public class MainActivity extends Activity {
    private static final String TAG = "sanbo.MainActivity";

    private CRelativeLayout mLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLayout = new CRelativeLayout(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case 1:
                        Class c = Build.class;
                        Field[] fs = c.getFields();
                        JSONArray ar = new JSONArray();
                        for (Field f : fs) {
                            ar.put(f.toString());
                        }
                        log(ar.toString());
                        break;
                    default:
                        break;
                }
            }
        }, 4, 3);
        setContentView(mLayout);
    }

    private void log(String s) {
        Log.i(TAG, "" + s);
    }
}