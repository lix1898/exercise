package com.itkjb.exercise.juc;

import java.util.concurrent.RecursiveTask;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/6/7 6:12 下午
 * @Description: 递归任务：继承后可以实现递归(自己调自己)调用的任务
 * @Version: V1.0.0
 */
public class Fibonacci extends RecursiveTask<Integer> {
    final int n;

    Fibonacci(int n) {
        this.n = n;
    }
    @Override
    public Integer compute() {
        if (n <= 1){
            return n;
        }
        Fibonacci f1 = new Fibonacci(n - 1);
        f1.fork();
        Fibonacci f2 = new Fibonacci(n - 2);
        return f2.compute() + f1.join();
    }
}
