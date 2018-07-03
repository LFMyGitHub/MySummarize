package com.example.test.javalib.collection;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/**
 * 【类功能说明】
 * 1) HashSet的性能总是比TreeSet好(特别是最常用的添加、查询元素等操作)，因为TreeSet需要额外的红黑树算法来维护集合元素的次序。只有当需要一个保持排序的Set时，才应该使用TreeSet，否则都应该使用HashSet
 * 2) 对于普通的插入、删除操作，LinkedHashSet比HashSet要略慢一点，这是由维护链表所带来的开销造成的。不过，因为有了链表的存在，遍历LinkedHashSet会更快
 * 3) EnumSet是所有Set实现类中性能最好的，但它只能保存同一个枚举类的枚举值作为集合元素
 * 4) HashSet、TreeSet、EnumSet都是"线程不安全"的，通常可以通过Collections工具类的synchronizedSortedSet方法来"包装"该Set集合。
 * SortedSet s = Collections.synchronizedSortedSet(new TreeSet(...));
 * File: TestSet.java
 * @author longfeng
 * Vesion: 3.2.0
 * Create: 2018/7/3
 * Changes (from 2018/7/3)
 * -------------------------------------------------------
 * 2018/7/3:创建TestCollection.java(longfeng)
 * -------------------------------------------------------
 */
public class TestSet {
    /** HashSet集合测试返回结果如下：
     * [B@1,B@1,C@2,A@12a3a380,A@7ea987ac]
     * 结论：
     * 两个对象通过equals()方法比较返回true,但是hashCode()方法返回不同的hashCode时，HashSet会将两个对象保存在Hash表不同位置
     * equals()决定是否可以加入HashSet、hashCode()决定存放的位置
     * hashCode相同，equlas返回值不同，HashSet会在这个位置用链式结构来保存多个对象。HashSet访问集合元素时根据元素的HashCode值来快速定位的，这种链式结构会导致性能下降
     * 把某个类的对象保存到HashSet集合中，尽量保证两个对象通过equals()方法比较返回true时，它们的hashCode()方法返回值也相等
     */
    public void testHashSet(){
        System.out.println("---------------------testHashSet----------------------");
        HashSet hashSet = new HashSet();
        //分别向books集合中添加两个A对象，两个B对象，两个C对象
        hashSet.add(new A());
        hashSet.add(new A());

        hashSet.add(new B());
        hashSet.add(new B());

        hashSet.add(new C());
        hashSet.add(new C());
        System.out.println(hashSet);
        System.out.println();
    }

    /**
     * 元素的顺序总是与添加顺序一致，不允许集合元素重复
     */
    public void testLinkedHashSet(){
        System.out.println("---------------------testLinkedHashSet----------------------");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add("java");
        linkedHashSet.add("hello world");
        System.out.println(linkedHashSet);

        //删除java
        linkedHashSet.remove("java");
        //重新添加 Java
        linkedHashSet.add("java");
        linkedHashSet.add("java");
        System.out.println(linkedHashSet);
        System.out.println();
    }

    /**
     * 红黑树的数据结构来存储集合元素
     * 自然排序:调用集合元素的compareTo(Object obj)方法来比较元素之间的大小关系，然后将集合元素按升序排序
     * 定制排序:通过Comparator接口的帮助,该接口里包含一个int compare(T o1， T o2)方法，该方法用于比较大小
     */
    public void testTreeSet(){
        System.out.println("---------------------testTreeSet----------------------");
        TreeSet nums = new TreeSet();
        //向TreeSet中添加四个Integer对象
        nums.add(5);
        nums.add(2);
        nums.add(10);
        nums.add(-9);

        //输出集合元素，看到集合元素已经处于排序状态
        System.out.println(nums);

        //输出集合里的第一个元素
        System.out.println(nums.first());

        //输出集合里的最后一个元素
        System.out.println(nums.last());

        //返回小于4的子集，不包含4
        System.out.println(nums.headSet(4));

        //返回大于5的子集，如果Set中包含5，子集中还包含5
        System.out.println(nums.tailSet(5));

        //返回大于等于-3，小于4的子集。
        System.out.println(nums.subSet(-3 , 4));
        System.out.println();
    }

    /**
     * EnumSet
     */
    enum Season {
        SPRING,SUMMER,FALL,WINTER
    }
    public void testEnumSet(){
        System.out.println("---------------------testEnumSet----------------------");
        //创建一个EnumSet集合，集合元素就是Season枚举类的全部枚举值
        EnumSet es1 = EnumSet.allOf(Season.class);
        //输出[SPRING,SUMMER,FALL,WINTER]
        System.out.println(es1);

        //创建一个EnumSet空集合，指定其集合元素是Season类的枚举值。
        EnumSet es2 = EnumSet.noneOf(Season.class);
        //输出[]
        System.out.println(es2);
        //手动添加两个元素
        es2.add(Season.WINTER);
        es2.add(Season.SPRING);
        //输出[SPRING,WINTER]
        System.out.println(es2);

        //以指定枚举值创建EnumSet集合
        EnumSet es3 = EnumSet.of(Season.SUMMER , Season.WINTER);
        //输出[SUMMER,WINTER]
        System.out.println(es3);

        EnumSet es4 = EnumSet.range(Season.SUMMER , Season.WINTER);
        //输出[SUMMER,FALL,WINTER]
        System.out.println(es4);

        //新创建的EnumSet集合的元素和es4集合的元素有相同类型，
        //es5的集合元素 + es4集合元素 = Season枚举类的全部枚举值
        EnumSet es5 = EnumSet.complementOf(es4);
        //输出[SPRING]
        System.out.println(es5);
        System.out.println();
    }
}

/**
 * 类A的equals方法总是返回true,但没有重写其hashCode()方法。不能保证当前对象是HashSet中的唯一对象
 */
class A {
    @Override
    public boolean equals(Object obj) {
        return true;
    }
}

/**
 * 类B的hashCode()方法总是返回1,但没有重写其equals()方法。不能保证当前对象是HashSet中的唯一对象
 */
class B {
    @Override
    public int hashCode() {
        return 1;
    }
}

/**
 * 类C的hashCode()方法总是返回2,且有重写其equals()方法
 */
class C {
    @Override
    public int hashCode() {
        return 2;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }
}