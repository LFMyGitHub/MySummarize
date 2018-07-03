package com.example.test.javalib.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 【类功能说明】
 * File: TestIterator.java
 * Author: longfeng
 * Vesion: 3.2.0
 * Create: 2018/7/3
 * Changes (from 2018/7/3)
 * -------------------------------------------------------
 * 2018/7/3:创建TestIterator.java(longfeng)
 * -------------------------------------------------------
 */
public class TestIterator {
    /**
     * iterator实现遍历
     * 必须依附于Collection对象，若有一个iterator对象，必然有一个与之关联的Collection对象
     */
    public void testIterator(){
        System.out.println("---------------------iterator实现遍历----------------------");
        //创建一个集合
        Collection books = new HashSet();
        books.add("轻量级Java EE企业应用实战");
        books.add("疯狂Java讲义");
        books.add("疯狂Android讲义");

        //获取books集合对应的迭代器
        Iterator it = books.iterator();
        while(it.hasNext()) {
            //it.next()方法返回的数据类型是Object类型，
            //需要强制类型转换
            String book = (String)it.next();
            System.out.println(book);
            if (book.equals("疯狂Java讲义")) {
                //从集合中删除上一次next方法返回的元素
                it.remove();
            }
            //对book变量赋值，不会改变集合元素本身
            book = "测试字符串";
        }
        System.out.println(books);
        System.out.println();
    }

    /**
     * foreach实现遍历
     */
    public void testForeach(){
        System.out.println("---------------------foreach实现遍历----------------------");
        //创建一个集合
        Collection books = new HashSet();
        books.add(new String("轻量级Java EE企业应用实战"));
        books.add(new String("疯狂Java讲义"));
        books.add(new String("疯狂Android讲义"));

        for (Object obj : books) {
            //此处的book变量也不是集合元素本身
            String book = (String)obj;
            System.out.println(book);
            if (book.equals("疯狂Android讲义")) {
                //下面代码会引发ConcurrentModificationException异常
                //books.remove(book);
            }
        }
        System.out.println(books);
        System.out.println();
    }

    /**
     * ListIterator实现遍历
     */
    public void testListIterator(){
        System.out.println("---------------------ListIterator实现遍历----------------------");
        String[] books = {
                "疯狂Java讲义",
                "轻量级Java EE企业应用实战"
        };
        List bookList = new ArrayList();
        for (int i = 0; i < books.length ; i++ ) {
            bookList.add(books[i]);
        }
        ListIterator lit = bookList.listIterator();
        while (lit.hasNext()) {
            System.out.println(lit.next());
        }
        System.out.println("-------反向迭代-------");
        while(lit.hasPrevious()) {
            System.out.println(lit.previous());
        }
        System.out.println();
    }
}
