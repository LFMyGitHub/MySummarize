package com.common.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;

/**
 * 【类功能说明】
 * ...
 * File: UIUtils.java
 *
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/8/7
 * Changes (from 2018/8/7)
 * -------------------------------------------------------
 * 2018/8/7:创建UIUtils.java(longfeng)
 * -------------------------------------------------------
 */
public class UIUtils {
    /**
     * 判断当前应用是否在debug状态下
     * @param context
     * @return
     */
    public static boolean idApkInDebug(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            return (applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }
}
