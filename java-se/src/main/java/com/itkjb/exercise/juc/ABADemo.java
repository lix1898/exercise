package com.itkjb.exercise.juc;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/6/2 1:08 下午
 * @Description: ABA 问题演示
 * @Version: V1.0.0
 */
public class ABADemo {
    // 不带版本号的，会引起aba 问题
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    // 带时间戳的 版本号，不会引起aba 问题呢
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {

    }
}
