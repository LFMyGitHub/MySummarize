package com.common.utils;

/**
 * 【类功能说明】
 * ...
 * File: StringUtils.java
 *
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/7/25
 * Changes (from 2018/7/25)
 * -------------------------------------------------------
 * 2018/7/25:创建StringUtils.java(longfeng)
 * -------------------------------------------------------
 */
public class StringUtils {

    private static long lastClickTime;

    /**
     * 防止一秒内重复点击
     *
     * @return
     */
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
