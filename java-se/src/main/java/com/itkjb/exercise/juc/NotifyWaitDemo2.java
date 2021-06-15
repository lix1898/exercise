package com.itkjb.exercise.juc;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/6/6 11:43 上午
 * @Description: 线程唤醒示例
 * 换成4个线程会导致错误，虚假唤醒
 * 原因：在java多线程判断时，不能用if，程序出事出在了判断上面，
 * 突然有一添加的线程进到if了，突然中断了交出控制权，
 * 没有进行验证，而是直接走下去了，加了两次，甚至多次
 * 解决办法
 *
 * @Version: V1.0.0
 */
public class NotifyWaitDemo2 {
    public static void main(String[] args){
        ShareDataOne2 sd = new ShareDataOne2();
        new Thread(() -> {
            for (int i = 1; i < 10; i++) {
                try {
                    sd.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i < 10; i++) {
                try {
                    sd.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 1; i < 10; i++) {
                try {
                    sd.decrement();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i < 10; i++) {
                try {
                    sd.decrement();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}

// 资源类
class ShareDataOne2 {
    //初始值为零的一个变量
    private int number = 0;
    public synchronized void increment() throws InterruptedException {
        //1判断
        while (number != 0) {
            this.wait();
        }
        // 2干活
        ++number;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        //3通知
        this.notifyAll();
    }
    public synchronized void decrement() throws InterruptedException {
        // 1判断
        while (number == 0) {
            this.wait();
        }
        // 2干活
        --number;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        // 3通知
        this.notifyAll();
    }
}
