package com.luo.matrixcaculator.robo;



import android.util.Log;

/**
 * Created by Tulin_TonJie on 2018/2/10.
 */

public class L {
    //开关
    public static final boolean DEBUG = true;

    //TAG
    public static final String TAG = "tonjies";

    //5个等级 DIWEF

    public static void d(String text) {
        if (DEBUG) {
            Log.d(TAG, text+"");
        }
    }

    public static void i(String text) {
        if (DEBUG) {
            Log.i(TAG, text+"");
        }
    }

    public static void w(String text) {
        if (DEBUG) {
            Log.w(TAG, text+"");
        }
    }

    public static void e(String text) {
        if (DEBUG) {
            Log.e(TAG, text+"");
        }
    }
}
