package com.itkjb.exercise.juc;

import java.util.concurrent.*;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/6/7 5:36 下午
 * @Description: 线程池示例
 * @Version: V1.0.0
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                //new ThreadPoolExecutor.AbortPolicy()
                //new ThreadPoolExecutor.CallerRunsPolicy()
                //new ThreadPoolExecutor.DiscardOldestPolicy()
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
        //10个顾客请求
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " \t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }

    /**
     * 这只是个测试方法，实际工作中不这么用
     */
    private static void threadPool() {
        //List list = new ArrayList();
        //List list = Arrays.asList("a","b");
        //固定数的线程池，一池五线程

        // ExecutorService threadPool = Executors.newFixedThreadPool(5); //一个银行网点，5个受理业务的窗口
        // ExecutorService threadPool = Executors.newSingleThreadExecutor(); //一个银行网点，1个受理业务的窗口
        ExecutorService threadPool = Executors.newCachedThreadPool(); //一个银行网点，可扩展受理业务的窗口

        //10个顾客请求
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " \t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

}
