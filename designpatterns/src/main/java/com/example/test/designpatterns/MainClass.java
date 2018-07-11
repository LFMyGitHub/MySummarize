package com.example.test.designpatterns;

import com.example.test.designpatterns.singleton.Singleton;

/**
 * 【类功能说明】
 * ...
 * File: MainClass.java
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/7/5
 * Changes (from 2018/7/5)
 * -------------------------------------------------------
 * 2018/7/5:创建MainClass.java(longfeng)
 * -------------------------------------------------------
 */
public class MainClass {
    public static void main(String[] args) {
        MethodClass methodClass = new MethodClass();
        //单例模式饿汉模式
        methodClass.testSingleton();
        //懒汉模式
        System.out.println(Singleton.getInstance().toString());
        System.out.println();
        //DCL模式
        //静态内部类模式
    }
}
