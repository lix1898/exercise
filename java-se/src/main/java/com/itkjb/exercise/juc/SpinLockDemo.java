package com.itkjb.exercise.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/6/7 10:02 下午
 * @Description: 手写自旋锁
 * @Version: V1.0.0
 */
public class SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void mylock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() +"\t coming ，尝试枷锁");
        while (!atomicReference.compareAndSet(null, thread)){

        }
        System.out.println(thread.getName() +"\t 枷锁成功");
    }

    public void myUnlock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() +"\t 尝试解锁");
        while (!atomicReference.compareAndSet(thread, null)) {

        }
        System.out.println(thread.getName() +"\t 解锁成功");
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() ->{
            spinLockDemo.mylock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnlock();
        },"A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() ->{
            spinLockDemo.mylock();
            spinLockDemo.myUnlock();
        },"B").start();

    }
}
