package com.itkjb.exercise.jvm;

import java.util.Random;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/6/4 10:17 上午
 * @Description:
 * @Version: V1.0.0
 */
public class Jvm001 {
    public static void main(String[] args) {
        // 输出当前主机cpu 内核数
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(Runtime.getRuntime().maxMemory() / 1024 / 1024);
        System.out.println(Runtime.getRuntime().totalMemory() / 1024 / 1024);
        String str = "www.itkjb.com";
        while (true){
            str += str +new Random().nextInt(88888888) +new Random().nextInt(999999999);
        }
    }
}
