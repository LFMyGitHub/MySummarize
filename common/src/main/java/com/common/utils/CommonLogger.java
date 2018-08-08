package com.common.utils;

import android.util.Log;

/**
 * 【类功能说明】
 * ...
 * File: CommonLogger.java
 *
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/8/8
 * Changes (from 2018/8/8)
 * -------------------------------------------------------
 * 2018/8/8:创建CommonLogger.java(longfeng)
 * -------------------------------------------------------
 */
public class CommonLogger {
    private static boolean mIsLogEnable = true;

    private static String TAG = "LogUtils";

    public static void debug(boolean isEnable) {
        debug(TAG, isEnable);
    }

    public static void debug(String logTag, boolean isEnable) {
        TAG = logTag;
        mIsLogEnable = isEnable;
    }

    public static void v(String msg) {
        v(TAG, msg);
    }

    public static void v(String tag, String msg) {
        if (mIsLogEnable) {
            Log.v(tag, msg);
        }
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void d(String tag, String msg) {
        if (mIsLogEnable) {
            Log.d(tag, msg);
        }
    }

    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void i(String tag, String msg) {
        if (mIsLogEnable) {
            Log.i(tag, msg);
        }
    }

    public static void w(String msg) {
        w(TAG, msg);
    }

    public static void w(String tag, String msg) {
        if (mIsLogEnable) {
            Log.w(tag, msg);
        }
    }

    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void e(String tag, String msg) {
        if (mIsLogEnable) {
            Log.e(tag, msg);
        }
    }

    public static void printStackTrace(Throwable t) {
        if (mIsLogEnable && t != null) {
            t.printStackTrace();
        }
    }


    public static void e(Throwable e) {
        if (!mIsLogEnable) {
            return;
        }
        if (e != null && e.getStackTrace() != null) {
            if (e.getCause() != null) {
                CommonLogger.e("cause:" + e.getCause().toString());
            }
            CommonLogger.e("message:" + e.getMessage());
            for (StackTraceElement item :
                    e.getStackTrace()) {
                CommonLogger.e(item.toString());
            }
        }
    }
}
