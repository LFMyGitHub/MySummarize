package com.example.test.javalib.collection;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

/**
 * 【类功能说明】
 * ...
 * File: TestQueue.java
 * Author: longfeng
 * Vesion: 3.2.0
 * Create: 2018/7/3
 * Changes (from 2018/7/3)
 * -------------------------------------------------------
 * 2018/7/3:创建TestQueue.java(longfeng)
 * -------------------------------------------------------
 */
public class TestQueue {
    /**
     * 不允许插入null元素，还需要对队列元素进行排序(类似TreeSet)
     */
    public void testPriorityQueue(){
        System.out.println("---------------------testPriorityQueue----------------------");
        PriorityQueue pq = new PriorityQueue();
        //下面代码依次向pq中加入四个元素
        pq.offer(6);
        pq.offer(-3);
        pq.offer(9);
        pq.offer(0);

        //输出pq队列，并不是按元素的加入顺序排列，
        //而是按元素的大小顺序排列，输出[-3, 0, 9, 6]
        System.out.println(pq);
        //访问队列第一个元素，其实就是队列中最小的元素：-3
        System.out.println(pq.poll());
    }

    public void testArrayDeque(){
        System.out.println("---------------------testArrayDeque----------------------");
        ArrayDeque stack = new ArrayDeque();
        //依次将三个元素push入"栈"
        stack.push("疯狂Java讲义");
        stack.push("轻量级Java EE企业应用实战");
        stack.push("疯狂Android讲义");

        //输出：[疯狂Java讲义, 轻量级Java EE企业应用实战 , 疯狂Android讲义]
        System.out.println(stack);

        //访问第一个元素，但并不将其pop出"栈"，输出：疯狂Android讲义
        System.out.println(stack.peek());

        //依然输出：[疯狂Java讲义, 轻量级Java EE企业应用实战 , 疯狂Android讲义]
        System.out.println(stack);

        //pop出第一个元素，输出：疯狂Android讲义
        System.out.println(stack.pop());

        //输出：[疯狂Java讲义, 轻量级Java EE企业应用实战]
        System.out.println(stack);
    }
}
