package com.example.test.javalib.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 【类功能说明】
 * 1. java提供的List就是一个"线性表接口"，ArrayList(基于数组的线性表)、LinkedList(基于链的线性表)是线性表的两种典型实现
 * 2. Queue代表了队列，Deque代表了双端队列(既可以作为队列使用、也可以作为栈使用)
 * 3. 因为数组以一块连续内存来保存所有的数组元素，所以数组在随机访问时性能最好。所以的内部以数组作为底层实现的集合在随机访问时性能最好。
 * 4. 内部以链表作为底层实现的集合在执行插入、删除操作时有很好的性能
 * 5. 进行迭代操作时，以链表作为底层实现的集合比以数组作为底层实现的集合性能好
 * File: TestList.java
 * Author: longfeng
 * Vesion: 3.2.0
 * Create: 2018/7/3
 * Changes (from 2018/7/3)
 * -------------------------------------------------------
 * 2018/7/3:创建TestList.java(longfeng)
 * -------------------------------------------------------
 */
public class TestList {
    /**
     * 如果一开始就知道ArrayList集合需要保存多少元素，则可以在创建它们时就指定initialCapacity大小，
     * 这样可以减少重新分配的次数，提供性能，ArrayList还提供了如下方法来重新分配Object[]数组
     * 1) ensureCapacity(int minCapacity): 将ArrayList集合的Object[]数组长度增加minCapacity
     * 2) trimToSize(): 调整ArrayList集合的Object[]数组长度为当前元素的个数。程序可以通过此方法来减少ArrayList集合对象占用的内存空间
     */
    public void testArrayList(){
        System.out.println("---------------------testArrayList----------------------");
        List books = new ArrayList();
        //向books集合中添加三个元素
        books.add(new String("轻量级Java EE企业应用实战"));
        books.add(new String("疯狂Java讲义"));
        books.add(new String("疯狂Android讲义"));
        System.out.println(books);

        //将新字符串对象插入在第二个位置
        books.add(1 , new String("疯狂Ajax讲义"));
        for (int i = 0 ; i < books.size() ; i++ )
        {
            System.out.println(books.get(i));
        }

        //删除第三个元素
        books.remove(2);
        System.out.println(books);

        //判断指定元素在List集合中位置：输出1，表明位于第二位
        System.out.println(books.indexOf(new String("疯狂Ajax讲义")));  //①
        //将第二个元素替换成新的字符串对象
        books.set(1, new String("LittleHann"));
        System.out.println(books);

        //将books集合的第二个元素（包括）
        //到第三个元素（不包括）截取成子集合
        System.out.println(books.subList(1 , 2));
        System.out.println();
    }

    /**
     * 后进先出
     */
    public void testStackList(){
        System.out.println("---------------------testStackList----------------------");
        Stack v = new Stack();
        //依次将三个元素push入"栈"
        v.push("疯狂Java讲义");
        v.push("轻量级Java EE企业应用实战");
        v.push("疯狂Android讲义");

        //输出：[疯狂Java讲义, 轻量级Java EE企业应用实战 , 疯狂Android讲义]
        System.out.println(v);

        //访问第一个元素，但并不将其pop出"栈"，输出：疯狂Android讲义
        System.out.println(v.peek());

        //依然输出：[疯狂Java讲义, 轻量级Java EE企业应用实战 , 疯狂Android讲义]
        System.out.println(v);

        //pop出第一个元素，输出：疯狂Android讲义
        System.out.println(v.pop());

        //输出：[疯狂Java讲义, 轻量级Java EE企业应用实战]
        System.out.println(v);
        System.out.println();
    }

    /**
     * 双端队列
     */
    public void testLinkedList(){
        System.out.println("---------------------testLinkedList----------------------");
        LinkedList books = new LinkedList();

        //将字符串元素加入队列的尾部(双端队列)
        books.offer("疯狂Java讲义");

        //将一个字符串元素加入栈的顶部(双端队列)
        books.push("轻量级Java EE企业应用实战");

        //将字符串元素添加到队列的头(相当于栈的顶部)
        books.offerFirst("疯狂Android讲义");

        for (int i = 0; i < books.size() ; i++ )
        {
            System.out.println(books.get(i));
        }

        //访问、并不删除栈顶的元素
        System.out.println(books.peekFirst());
        //访问、并不删除队列的最后一个元素
        System.out.println(books.peekLast());
        //将栈顶的元素弹出"栈"
        System.out.println(books.pop());
        //下面输出将看到队列中第一个元素被删除
        System.out.println(books);
        //访问、并删除队列的最后一个元素
        System.out.println(books.pollLast());
        //下面输出将看到队列中只剩下中间一个元素：
        //轻量级Java EE企业应用实战
        System.out.println(books);
        System.out.println();
    }
}
