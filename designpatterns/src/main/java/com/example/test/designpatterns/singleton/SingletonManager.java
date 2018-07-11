package com.example.test.designpatterns.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * 【类功能说明】
 * 使用容器管理单例
 * File: SingletonManager.java
 *
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/7/5
 * Changes (from 2018/7/5)
 * -------------------------------------------------------
 * 2018/7/5:创建SingletonManager.java(longfeng)
 * -------------------------------------------------------
 */
public class SingletonManager {
    private static Map<String, Object> objMap = new HashMap<String, Object>();

    private SingletonManager() {
    }

    public static void registerService(String key, Object instance) {
        if (!objMap.containsKey(key)) {
            objMap.put(key, instance);
        }
    }

    public static Object getService(String key) {
        return objMap.get(key);
    }
}
