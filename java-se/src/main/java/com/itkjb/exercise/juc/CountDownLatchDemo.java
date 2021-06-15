package com.itkjb.exercise.juc;

import java.util.concurrent.CountDownLatch;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/6/7 1:45 下午
 * @Description: 减少计数
 * 让一些线程阻塞直到另一些线程完成一系列操作后才被唤醒。
 *  CountDownLatch主要有两个方法，当一个或多个线程调用await方法时，这些线程会阻塞。
 *  其它线程调用countDown方法会将计数器减1(调用countDown方法的线程不会阻塞)，
 *  当计数器的值变为0时，因await方法阻塞的线程会被唤醒，继续执行。
 *
 *  解释：6个同学陆续离开教室后值班同学才可以关门。
 *  main主线程必须要等前面6个线程完成全部工作后，自己才能开干 
 * @Version: V1.0.0
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(6);
        //6个上自习的同学，各自离开教室的时间不一致
        for (int i = 1; i <= 6; i++)
        {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 号同学离开教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t****** 班长关门走人，main线程是班长");
    }
}
