package com.example.test.designpatterns.singleton;

/**
 * 【类功能说明】
 * CEO 饿汉单例模式
 * File: CEO.java
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/7/5
 * Changes (from 2018/7/5)
 * -------------------------------------------------------
 * 2018/7/5:创建CEO.java(longfeng)
 * -------------------------------------------------------
 */
public class CEO extends Staff {
    private static final CEO mCEO = new CEO();
    /**
     * 构造函数私有
     */
    private CEO() {}

    /**
     * 公有的静态函数，对外暴露获取单例对象的接口
     */
    public static CEO getCeo() {
        return mCEO;
    }

    @Override
    public void work() {
        //管理VP
    }
}
