package com.itkjb.exercise.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/6/7 6:27 下午
 * @Description: 异步回调示例
 * @Version: V1.0.0
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //同步，异步，异步回调
        //同步
        // CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(()->{
        // System.out.println(Thread.currentThread().getName()+"\t completableFuture1");
        // });
        // completableFuture1.get();

        //异步回调
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " \t completableFuture2");
            int i = 10 / 0;
            return 1024;
        });
        completableFuture2.whenComplete((t, u) -> {
            System.out.println("-------t=" + t);
            System.out.println("-------u=" + u);
        }).exceptionally(f -> {
            System.out.println("-----exception:" + f.getMessage());
            return 444;
        }).get();
    }
}
