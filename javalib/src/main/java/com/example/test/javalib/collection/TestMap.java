package com.example.test.javalib.collection;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.TreeMap;
import java.util.WeakHashMap;

/**
 * 【类功能说明】
 * 1) HashMap和Hashtable的效率大致相同，因为它们的实现机制几乎完全一样。但HashMap通常比Hashtable要快一点，因为Hashtable需要额外的线程同步控制
 * 2) TreeMap通常比HashMap、Hashtable要慢(尤其是在插入、删除key-value对时更慢)，因为TreeMap底层采用红黑树来管理key-value对
 * 3) 使用TreeMap的一个好处就是： TreeMap中的key-value对总是处于有序状态，无须专门进行排序操作
 * File: TestMap.java
 * Author: longfeng
 * Vesion: 3.2.0
 * Create: 2018/7/3
 * Changes (from 2018/7/3)
 * -------------------------------------------------------
 * 2018/7/3:创建TestMap.java(longfeng)
 * -------------------------------------------------------
 */
public class TestMap {
    public void testHashTable(){
        System.out.println("---------------------testHashTable----------------------");
        Hashtable ht = new Hashtable();
        ht.put(new D(60000) , "疯狂Java讲义");
        ht.put(new D(87563) , "轻量级Java EE企业应用实战");
        ht.put(new D(1232) , new E());
        System.out.println(ht);

        //只要两个对象通过equals比较返回true，
        //Hashtable就认为它们是相等的value。
        //由于Hashtable中有一个B对象，
        //它与任何对象通过equals比较都相等，所以下面输出true。
        System.out.println(ht.containsValue("测试字符串"));  //①

        //只要两个A对象的count相等，它们通过equals比较返回true，且hashCode相等
        //Hashtable即认为它们是相同的key，所以下面输出true。
        System.out.println(ht.containsKey(new D(87563)));   //②

        //下面语句可以删除最后一个key-value对
        ht.remove(new D(1232));    //③

        //通过返回Hashtable的所有key组成的Set集合，
        //从而遍历Hashtable每个key-value对
        for (Object key : ht.keySet()) {
            System.out.print(key + "---->");
            System.out.print(ht.get(key) + "\n");
            System.out.println();
        }
    }

    public void testLinkedHashMap(){
        System.out.println("---------------------testLinkedHashMap----------------------");
        LinkedHashMap scores = new LinkedHashMap();
        scores.put("语文" , 80);
        scores.put("英文" , 82);
        scores.put("数学" , 76);
        //遍历scores里的所有的key-value对
        for (Object key : scores.keySet()) {
            System.out.println(key + "------>" + scores.get(key));
        }
        System.out.println();
    }

    /**
     * Properties还可以把key-value对以XML文件的形式保存起来，也可以从XML文件中加载key-value对
     */
    public void testProperties(){
        System.out.println("---------------------testProperties----------------------");
        Properties props = new Properties();
        //向Properties中增加属性
        props.setProperty("username" , "yeeku");
        props.setProperty("password" , "123456");

        try{
            //将Properties中的key-value对保存到a.ini文件中
            props.store(new FileOutputStream("a.ini"), "comment line");   //①
        }catch (IOException e){
            e.printStackTrace();
        }

        //新建一个Properties对象
        Properties props2 = new Properties();
        //向Properties中增加属性
        props2.setProperty("gender" , "male");

        try{
            //将a.ini文件中的key-value对追加到props2中
            props2.load(new FileInputStream("a.ini") );    //②
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(props2);
        System.out.println();
    }

    /**
     * 类似于TreeSet中判断两个元素是否相等的标准
     * 两个key通过compareTo()方法返回0
     * equals()返回true
     */
    public void testTreeMap(){
        System.out.println("---------------------testTreeMap----------------------");
        TreeMap tm = new TreeMap();
        tm.put(new R(3) , "轻量级Java EE企业应用实战");
        tm.put(new R(-5) , "疯狂Java讲义");
        tm.put(new R(9) , "疯狂Android讲义");

        System.out.println(tm);

        //返回该TreeMap的第一个Entry对象
        System.out.println(tm.firstEntry());

        //返回该TreeMap的最后一个key值
        System.out.println(tm.lastKey());

        //返回该TreeMap的比new R(2)大的最小key值。
        System.out.println(tm.higherKey(new R(2)));

        //返回该TreeMap的比new R(2)小的最大的key-value对。
        System.out.println(tm.lowerEntry(new R(2)));

        //返回该TreeMap的子TreeMap
        System.out.println(tm.subMap(new R(-1) , new R(4)));
        System.out.println();
    }

    /**
     * 不要让key所引用的对象具有任何强引用，否则将失去使用WeakHashMap的意义
     */
    public void testWeakHashMap(){
        System.out.println("---------------------testWeakHashMap----------------------");
        WeakHashMap whm = new WeakHashMap();
        //将WeakHashMap中添加三个key-value对，
        //三个key都是匿名字符串对象（没有其他引用）
        whm.put(new String("语文") , new String("良好"));
        whm.put(new String("数学") , new String("及格"));
        whm.put(new String("英文") , new String("中等"));

        //将WeakHashMap中添加一个key-value对，
        //该key是一个系统缓存的字符串对象。"java"是一个常量字符串强引用
        whm.put("java" , new String("中等"));
        //输出whm对象，将看到4个key-value对。
        System.out.println(whm);
        //通知系统立即进行垃圾回收
        System.gc();
        System.runFinalization();
        //通常情况下，将只看到一个key-value对。
        System.out.println(whm);
        System.out.println();
    }

    public void testIdentityHashMap(){
        System.out.println("---------------------testIdentityHashMap----------------------");
        IdentityHashMap ihm = new IdentityHashMap();
        //下面两行代码将会向IdentityHashMap对象中添加两个key-value对
        ihm.put(new String("语文") , 89);
        ihm.put(new String("语文") , 78);

        //下面两行代码只会向IdentityHashMap对象中添加一个key-value对
        ihm.put("java" , 93);
        ihm.put("java" , 98);
        System.out.println(ihm);
        System.out.println();
    }

    public void testEnumMap(){
        System.out.println("---------------------testEnumMap----------------------");
        //创建一个EnumMap对象，该EnumMap的所有key
        //必须是Season枚举类的枚举值
        EnumMap enumMap = new EnumMap(TestSet.Season.class);
        enumMap.put(TestSet.Season.SUMMER , "夏日炎炎");
        enumMap.put(TestSet.Season.SPRING , "春暖花开");
        System.out.println(enumMap);
        System.out.println();
    }
}

class D {
    int count;
    public D(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj!=null && obj.getClass()==D.class) {
            D d = (D)obj;
            return this.count == d.count;
        }
        return false;
    }
    @Override
    public int hashCode() {
        return this.count;
    }
}

class E {
    @Override
    public boolean equals(Object obj) {
        return true;
    }
}

class R implements Comparable {
    int count;
    public R(int count) {
        this.count = count;
    }
    @Override
    public String toString() {
        return "R[count:" + count + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj!=null && obj.getClass()==R.class) {
            R r = (R)obj;
            return r.count == this.count;
        }
        return false;
    }

    @Override
    public int compareTo(Object obj) {
        R r = (R)obj;
        return count > r.count ? 1 :
                count < r.count ? -1 : 0;
    }
}
