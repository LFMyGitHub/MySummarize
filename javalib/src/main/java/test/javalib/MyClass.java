package test.javalib;

import test.javalib.collection.TestIterator;
import test.javalib.collection.TestList;
import test.javalib.collection.TestMap;
import test.javalib.collection.TestSet;
import test.javalib.task.MyTask;
import test.javalib.task.TestThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 【类功能说明】
 * java测试代码入口
 * File: MyClass.java
 * @author LongFeng
 * Vesion: 3.2.0
 * Create: 2018/6/27
 * Changes (from 14:55)
 */
public class MyClass {
    public static void main(String[] args){
        System.out.print("Java Test");

        // byte
        System.out.println("基本类型：byte 二进制位数：" + Byte.SIZE);
        System.out.println("包装类：java.lang.Byte");
        System.out.println("最小值：Byte.MIN_VALUE=" + Byte.MIN_VALUE);
        System.out.println("最大值：Byte.MAX_VALUE=" + Byte.MAX_VALUE);
        System.out.println();

        // short
        System.out.println("基本类型：short 二进制位数：" + Short.SIZE);
        System.out.println("包装类：java.lang.Short");
        System.out.println("最小值：Short.MIN_VALUE=" + Short.MIN_VALUE);
        System.out.println("最大值：Short.MAX_VALUE=" + Short.MAX_VALUE);
        System.out.println();

        // int
        System.out.println("基本类型：int 二进制位数：" + Integer.SIZE);
        System.out.println("包装类：java.lang.Integer");
        System.out.println("最小值：Integer.MIN_VALUE=" + Integer.MIN_VALUE);
        System.out.println("最大值：Integer.MAX_VALUE=" + Integer.MAX_VALUE);
        System.out.println();

        // long
        System.out.println("基本类型：long 二进制位数：" + Long.SIZE);
        System.out.println("包装类：java.lang.Long");
        System.out.println("最小值：Long.MIN_VALUE=" + Long.MIN_VALUE);
        System.out.println("最大值：Long.MAX_VALUE=" + Long.MAX_VALUE);
        System.out.println();

        // float
        System.out.println("基本类型：float 二进制位数：" + Float.SIZE);
        System.out.println("包装类：java.lang.Float");
        System.out.println("最小值：Float.MIN_VALUE=" + Float.MIN_VALUE);
        System.out.println("最大值：Float.MAX_VALUE=" + Float.MAX_VALUE);
        System.out.println();

        // double
        System.out.println("基本类型：double 二进制位数：" + Double.SIZE);
        System.out.println("包装类：java.lang.Double");
        System.out.println("最小值：Double.MIN_VALUE=" + Double.MIN_VALUE);
        System.out.println("最大值：Double.MAX_VALUE=" + Double.MAX_VALUE);
        System.out.println();

        // char
        System.out.println("基本类型：char 二进制位数：" + Character.SIZE);
        System.out.println("包装类：java.lang.Character");
        // 以数值形式而不是字符形式将Character.MIN_VALUE输出到控制台
        System.out.println("最小值：Character.MIN_VALUE="
                + (int) Character.MIN_VALUE);
        // 以数值形式而不是字符形式将Character.MAX_VALUE输出到控制台
        System.out.println("最大值：Character.MAX_VALUE="
                + (int) Character.MAX_VALUE);
        System.out.println();

        //线程池测试
        TestThreadPool testThreadPool = new TestThreadPool(5, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
        for(int i=0;i<15;i++){
            MyTask myTask = new MyTask(i);
            testThreadPool.execute(myTask);
            System.out.println("线程池中线程数目："+testThreadPool.getPoolSize()+"，队列中等待执行的任务数目："+
                    testThreadPool.getQueue().size()+"，已执行完别的任务数目："+testThreadPool.getCompletedTaskCount());
        }
        testThreadPool.shutdown();

        TestSet testSet = new TestSet();
        testSet.testHashSet();
        testSet.testLinkedHashSet();
        testSet.testTreeSet();
        testSet.testEnumSet();

        TestList testList = new TestList();
        testList.testArrayList();
        testList.testStackList();
        testList.testLinkedList();

        TestIterator testIterator = new TestIterator();
        testIterator.testIterator();
        testIterator.testForeach();
        testIterator.testListIterator();

        TestMap testMap =  new TestMap();
        testMap.testHashTable();
        testMap.testLinkedHashMap();
        testMap.testProperties();
        testMap.testTreeMap();
        testMap.testWeakHashMap();
        testMap.testIdentityHashMap();
        testMap.testEnumMap();
    }
}
