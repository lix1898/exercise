package com.itkjb.exercise.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/6/6 10:02 上午
 * @Description: 卖票demo
 * @Version: V1.0.0
 */
public class SaleTicket {


    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
                ticket.sale();
            };
        }, "AA").start();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
                ticket.sale();
            };
        }, "BB").start();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
                ticket.sale();
            };
        }, "CC").start();
    }
}

//实例例eld +method
class Ticket {
    private int number = 30;
    /* //1同步 public synchronized void sale()
    {//2同步 synchronized(this) {}
     if(number > 0) {
     System.out.println(Thread.currentThread().getName()+"卖出"+(number--)+"\t 还剩number);
     }
    }*/

    // Lock implementations provide more extensive locking operations
    // than can be obtained using synchronized methods and statements.
    private Lock lock = new ReentrantLock();//List list = new ArrayList()

    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出" + (number--) + "\t 还剩" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}
