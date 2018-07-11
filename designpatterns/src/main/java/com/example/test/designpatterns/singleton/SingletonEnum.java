package com.example.test.designpatterns.singleton;

/**
 * 【类功能说明】
 * 枚举单例：自由序列化，线程安全，保证单例
 * 即使反序列化也不会重新生成新的实例
 * File: SingletonEnum.java
 * @author: longfeng
 * Vesion: 3.2.0
 * Create: 2018/7/5
 * Changes (from 2018/7/5)
 * -------------------------------------------------------
 * 2018/7/5:创建SingletonEnum.java(longfeng)
 * -------------------------------------------------------
 */
public enum SingletonEnum {
    INSTANCE;

    public void otherMethods() {
        System.out.println("Something");
    }
}
