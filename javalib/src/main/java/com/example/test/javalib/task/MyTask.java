package com.example.test.javalib.task;

/**
 * 【类功能说明】
 * ...
 * File: MyTask.java
 * Author: longfeng
 * Vesion: 3.2.0
 * Create: 2018/7/2
 * Changes (from 2018/7/2)
 * -------------------------------------------------------
 * 2018/7/2:创建MyTask.java(longfeng)
 * -------------------------------------------------------
 */
public class MyTask implements Runnable {
    private int taskNum;

    public MyTask(int num) {
        this.taskNum = num;
    }

    @Override
    public void run() {
        System.out.println("正在执行tsak" + taskNum);
        try {
            Thread.currentThread().sleep(4000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("tsak" + taskNum + "执行完毕");
    }
}
