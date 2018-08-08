package com.common.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.common.base.BaseApplication;

/**
 * 【类功能说明】
 * 避免同样的信息多次触发重复弹出的问题
 * File: ToastUtils.java
 *
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/8/8
 * Changes (from 2018/8/8)
 * -------------------------------------------------------
 * 2018/8/8:创建ToastUtils.java(longfeng)
 * -------------------------------------------------------
 */
public class ToastUtils {
    private static String oldMsg;
    private static long time;
    private static Handler sHandler = new Handler(Looper.getMainLooper());

    public static void showShortToast(final String msg) {
        sHandler.post(() -> {
            if (msg != null) {
                Context context = BaseApplication.getInstance();
                // 当显示的内容不一样时，即断定为不是同一个Toast
                if (!msg.equals(oldMsg)) {
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    time = System.currentTimeMillis();
                } else {
                    // 显示内容一样时，只有间隔时间大于Toast.LENGTH_SHORT时才显示
                    if (System.currentTimeMillis() - time > Toast.LENGTH_SHORT) {
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                        time = System.currentTimeMillis();
                    }
                }
                oldMsg = msg;
            }
        });
    }

    public static void showLongToast(final String msg) {
        sHandler.post(() -> {
            Context context = BaseApplication.getInstance();
            // 当显示的内容不一样时，即断定为不是同一个Toast
            if (!msg.equals(oldMsg)) {
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
                time = System.currentTimeMillis();
            } else {
                // 显示内容一样时，只有间隔时间大于Toast.LENGTH_SHORT时才显示
                if (System.currentTimeMillis() - time > Toast.LENGTH_LONG) {
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
                    time = System.currentTimeMillis();
                }
            }
            oldMsg = msg;
        });
    }
}
