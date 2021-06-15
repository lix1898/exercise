package com.itkjb.exercise.juc;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/6/7 1:58 下午
 * @Description: 信号灯 示例
 * 在信号量上我们定义两种操作:
 * acquire（获取） 当一个线程调用acquire操作时，它要么通过成功获取信号量（信号量减1），
 * 要么一直等下去，直到有线程释放信号量，或超时。
 * release（释放）实际上会将信号量的值加1，然后唤醒等待的线程。
 * <p>
 * 信号量主要用于两个目的，一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制。
 * @Version: V1.0.0
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        //模拟3个停车位
        Semaphore semaphore = new Semaphore(3);
        //模拟6部汽车
        for (int i = 1; i <= 6; i++)
        {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t 抢到了车位");
                    TimeUnit.SECONDS.sleep(new SecureRandom().nextInt(5));
                    System.out.println(Thread.currentThread().getName() + "\t------- 离开");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
