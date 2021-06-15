package com.itkjb.exercise.jvm;

import java.util.WeakHashMap;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/6/13 1:56 下午
 * @Description: 强弱引用 示例
 * @Version: V1.0.0
 */
public class StronggRefrenceDemo {
    public static void main(String[] args) {
        myWeakHashMap();
    }

    private static void myWeakHashMap() {
        WeakHashMap<Integer,String> map = new WeakHashMap<>();
        Integer key = new Integer(2);
        String value =  "weakHashMap";
        map.put(key, value);

        System.out.println(map);
        // key = null;

        System.gc();
        System.out.println(map);
        System.out.println(map.size());


    }
}
