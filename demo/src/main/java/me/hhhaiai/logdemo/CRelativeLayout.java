package me.hhhaiai.logdemo;


import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.security.MessageDigest;


/**
 * @Copyright © 2021 sanbo Inc. All rights reserved.
 * @Description: 自定义相对页面<p />
 * 2.0: 增加每列个数动态配置，去除基础参考ID
 * 1.2: 支持大体积的消息回显，增加回显内容验证
 * 1.1: 页面增加消息回显模快，内部每列动态调整为4个按钮
 * 1.0: 完成动态按行增加按钮功能
 * https://github.com/hhhaiai/CL
 * @Version: 1.0
 * @Create: 2021/09/258 11:15:36
 * @author: sanbo
 */
public class CRelativeLayout extends RelativeLayout {

    public static final String Version = "v2.0";
    private Context mContext = null;
    private OnClickListener mOnClickListener = null;
    //    public static final int BASE_INDEX = 9000;
    // 每行几个按钮
    private int itemCountInLine = 4;
    private String TAG = "MRelativeLayout";

    public CRelativeLayout(Context context) {
        super(context);
        mContext = context;
        initHandler();
    }

    /**
     * @param context
     * @param clickListener      按钮点击回调
     * @param itemCountInOneLine 每行按钮个数
     * @param lineCount          行数
     */
    public CRelativeLayout(Context context, OnClickListener clickListener, int itemCountInOneLine, int lineCount) {
        super(context);
        mContext = context;
        mOnClickListener = clickListener;
        this.itemCountInLine = itemCountInOneLine;
        addOneGroup(lineCount);
        addTextView();
        initHandler();
    }

    private HandlerThread thread;
    private Handler mHandler;
    private TextView tv = null;
    private int tvID = 0x66601;
    private String mShowMsg = "";

    private void initHandler() {
        if (thread == null) {
            thread = new HandlerThread("mt",
                    android.os.Process.THREAD_PRIORITY_FOREGROUND);
            thread.start();
        }
        if (mHandler == null) {
            mHandler = new RHandler(thread.getLooper());
        }

    }

    public static final String md5(final String toEncrypt) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("md5");
            digest.update(toEncrypt.getBytes());
            final byte[] bytes = digest.digest();
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(String.format("%02X", bytes[i]));
            }
            return sb.toString().toLowerCase();
        } catch (Exception exc) {
            return ""; // Impossibru!
        }
    }

    class RHandler extends Handler {
        public RHandler(Looper looper) {
            super(Looper.getMainLooper());
        }

        @Override
        public void handleMessage(Message msg) {
            try {
                switch (msg.what) {
                    case 1:
                        if (TextUtils.isEmpty(mShowMsg)) {
                            return;
                        }
                        if (tv == null) {
                            tv = findViewById(tvID);
                        }
                        String md5 = (String) msg.obj;
                        if (md5.equals(md5(mShowMsg))) {
                            tv.setText(mShowMsg);
                            mShowMsg = "";
                        } else {
                            loe(TAG, "字符串md5不一致，不进行展示");
                        }
                        break;
                }

            } catch (Throwable igone) {
                loe(TAG, Log.getStackTraceString(igone));
            }
        }
    }


    /**
     * 增加TextView
     */
    private void addTextView() {
        tv = new TextView(mContext);
        LayoutParams params = new LayoutParams(
                //int w, int h
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        tv.setBackgroundColor(0xFF85C1E9);
        tv.setId(tvID);
        tv.setLayoutParams(params);
        tv.setHeight(600);
        tv.setText("Hello, This's a Textview");
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());
        addView(tv);
    }


    public void showInPage(String msgInfo) {
        if (TextUtils.isEmpty(msgInfo)) {
            loe(TAG, "msg which will show is null! will return");
            return;
        }
        mShowMsg = msgInfo;
        Message msg = Message.obtain();
        msg.what = 1;
        msg.obj = md5(msgInfo);
        mHandler.sendMessage(msg);
    }


    /**
     * 增加一排几个按钮
     *
     * @param groupCount 组数
     */
    private void addOneGroup(int groupCount) {
        if (groupCount <= 0) {
            lod(TAG, "addOneGroup has not group");
            return;
        }
        for (int i = 1; i <= groupCount; i++) {
            int index = (i - 1) * itemCountInLine + 1;
            for (int j = 0; j < itemCountInLine; j++) {
                firstLine(index, j);
            }
        }
    }


    /**
     * 自动增加组件
     *
     * @param lineIndex   第几行
     * @param indexInLine 每行第几个元素
     */
    private void firstLine(int lineIndex, int indexInLine) {
//        int id = BASE_INDEX + lineIndex + indexInLine;
        int id = lineIndex + indexInLine;
        Button btn = new Button(mContext);
        btn.setId(id);
        LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        if (indexInLine > 0) {
            params.setMargins(5, 0, 0, 0);//4个参数按顺序分别是左上右下
//            params.addRule(RelativeLayout.RIGHT_OF, BASE_INDEX + (lineIndex + indexInLine - 1));
            params.addRule(RelativeLayout.RIGHT_OF, (lineIndex + indexInLine - 1));
//            params.addRule(RelativeLayout.ALIGN_TOP, BASE_INDEX + lineIndex);
            params.addRule(RelativeLayout.ALIGN_TOP, lineIndex);
        } else {
            params.setMargins(5, 5, 0, 0);//4个参数按顺序分别是左上右下
        }
        if (lineIndex > itemCountInLine) {
//            params.addRule(RelativeLayout.BELOW, BASE_INDEX + lineIndex - itemCountInLine);
            params.addRule(RelativeLayout.BELOW, lineIndex - itemCountInLine);
        }

        btn.setLayoutParams(params);
        btn.setText("测试" + (lineIndex + indexInLine));
        if (mOnClickListener != null) {
            btn.setOnClickListener(mOnClickListener);
        }

        addView(btn);
    }

    private void lod(String tag, String info) {
        Log.d(tag, info);
    }

    private void loe(String tag, String info) {
        Log.e(tag, info);
    }
}

