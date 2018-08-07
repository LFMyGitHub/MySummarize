package com.designpatterns.singleton;

/**
 * 【类功能说明】
 * 懒汉模式
 * File: Singleton.java
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/7/5
 * Changes (from 2018/7/5)
 * -------------------------------------------------------
 * 2018/7/5:创建Singleton.java(longfeng)
 * -------------------------------------------------------
 */
public class Singleton {
    private static Singleton instance;
    private Singleton() {}

    /**
     * synchronized关键字修饰，同步方法，多线程情况下保证单例对象唯一性的手段
     * 问题：第一次调用初始化对象后，每次调用该方法都会进行同步，消耗不必要的资源
     * @return
     */
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    /**
     * 双重检锁
     * 第一层判空避免不必要的同步
     * 优点：资源利用率高，效率高
     * 缺点：第一次加载慢，由于java内存模型原因会偶尔失败，高并发环境下有一定缺陷(小概率)
     * @return
     */
    public static synchronized Singleton getInstance2() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static Singleton getInstance3() {
        return SingletonHolder.mInstance;
    }

    /**
     * 静态内部类
     * 为什么线程安全
     * 虚拟机会保证一个类的类构造器在多线程环境中被正确的加锁、同步，
     * 如果多个线程同时去初始化一个类，那么只会有一个线程去执行这个类的类构造器，
     * 其他线程都需要阻塞等待，直到活动线程执行方法完毕。
     * 在这种情形下，其他线程虽然会被阻塞，但如果执行初始化方法的那条线程退出后，
     * 其他线程在唤醒之后不会再次进入/执行初始化方法，因为在同一个类加载器下，
     * 一个类型只会被初始化一次。
     */
    private static class SingletonHolder {
        private static final Singleton mInstance = new Singleton();
    }
}
