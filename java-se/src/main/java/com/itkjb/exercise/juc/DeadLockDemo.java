package com.itkjb.exercise.juc;

import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/6/8 11:25 上午
 * @Description: 死锁案例
 * @Version: V1.0.0
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldThread(lockA,lockB), "threadAAA").start();
        new Thread(new HoldThread(lockB,lockA), "threadBBB").start();
    }
}

class HoldThread implements Runnable {
    private String lockA;
    private String lockB;

    public HoldThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + " \t 自己持有锁 " + lockA + " 尝试获得 " + lockB);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + " \t 自己持有锁 " + lockB + " 尝试获得 " + lockA);
            }
        }
    }
}
