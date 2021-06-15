package com.itkjb.exercise.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/6/6 2:13 下午
 * @Description: 更换condition 实现
 * @Version: V1.0.0
 */
public class NotifyWaitDemo3 {
    public static void main(String[] args) {
        ShareData sd = new ShareData();
        new Thread(() -> {

            for (int i = 1; i <= 10; i++) {
                try {
                    sd.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {

            for (int i = 1; i <= 10; i++) {
                try {
                    sd.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {

            for (int i = 1; i <= 10; i++) {
                try {
                    sd.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    sd.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();

    }
}
//资源类
class ShareData {
    private int number = 0;//初始值为零的一个变量
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (number != 0) {
                condition.await();
            }
            //干活
            ++number;
            System.out.println(Thread.currentThread().getName() + " \t " + number);
            //通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (number != 1) {
                condition.await();
            }
            //干活
            --number;
            System.out.println(Thread.currentThread().getName() + " \t " + number);
            //通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


}
