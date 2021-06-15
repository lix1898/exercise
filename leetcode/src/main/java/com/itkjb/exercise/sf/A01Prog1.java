package com.itkjb.exercise.sf;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/6/15 9:59 上午
 * @Description: 指数算法问题
 * 有一对兔子，从出生后第三个月开始，每个月都生一对兔子，小兔子长大后三个月后，每个月又生一对兔子，如果兔子都不死，问每个月的兔子对数为多少
 * 分析 ： 兔子的规律数列为： 1，1，2，3，5，8，13，21
 * @Version: V1.0.0
 */
public class A01Prog1 {
    public static void main(String[] args) {
        int n = 6;
        System.out.println(getProgNum(n));
    }

    private static int getProgNum(int n) {
        if (n <= 0) {
            return 0;
        } else if (n <= 2) {
            return 1;
        }else{
            return getProgNum(n-1) + getProgNum(n-2);
        }
    }
}
