package com.itkjb.exercise.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/6/6 10:31 下午
 * @Description: 集合不安全示例
 * @Version: V1.0.0
 */
public class NotSafeDemo {
    public static void main(String[] args) {
//        listNoSafe();
        setNoSafe();
//        mapNoSafe();
        /** 一下是改良后发方法  **/
//         listSafe();
//         setSafe();
//         mapSafe();
    }

    /**
     * 线程不安全的list
     */
    private static void listNoSafe() {
        List<String> list1 = Arrays.asList("a", "b", "c");
        list1.forEach(System.out::println);
        // list1 不能修改，回报 UnsupportedOperationException ；因为 Arrarys 类里面有个内部类ArrayList，而这个类的属性使用final 修饰的
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
    /**
     * 线程不安全的set
     */
    private static void setNoSafe() {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
    /**
     * 不安全的map
     */
    private static void mapNoSafe(){
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    /************   以下是改良的代码      线程安全的**************/

    /**
     * 写时复制
     * CopyOnWrite容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器Object[]添加，
     * 而是先将当前容器Object[]进行Copy，复制出一个新的容器Object[] newElements，
     * 然后向新的容器Object[] newElements里添加元素。 添加元素后，
     * 再将原容器的引用指向新的容器setArray(newElements)。
     * 这样做的好处是可以对CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。
     * 所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器。
     */
    private static void listSafe() {
        // list 1.0 提供了线程安全的 类Vetor ,不过里面是通过 synchronized 实现的，效率较低
        // List<String> list = new Vector<>();
        // List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

    /**
     * 线程安全的set
     */
    private static void setSafe() {
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
    /**
     * 线程安全的map
     */
    private static void mapSafe() {
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }


}

