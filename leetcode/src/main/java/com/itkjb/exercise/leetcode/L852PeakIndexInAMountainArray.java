package com.itkjb.exercise.leetcode;

/**
 * Copyright (C), 2021-2099
 *
 * @Author: lix
 * @Date: 2021/6/15 10:21 上午
 * @Description: 山脉数组的峰顶索引
 *
 * 符合下列属性的数组 arr 称为 ==山脉数组== ：
 * arr.length >= 3
 * 存在 i（0 < i < arr.length - 1）使得：
 *          arr[0] < arr[1] < ... arr[i-1] < arr[i]
 *          arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 * 给你由整数组成的山脉数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i 。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/peak-index-in-a-mountain-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Version: V1.0.0
 */
public class L852PeakIndexInAMountainArray {

    public static void main(String[] args) {
        // int arr[] = {24,30,69,100,99,79,78,67,36,26,19};
         // int arr[] = {1,2,3,4,5,6,7,8,9,10,24,69,100,99,79,78,67,36,26,19};
         int arr[] = {40,48,61,75,100,99,98,39,30,10};
        System.out.println(peakIndexInMountainArray(arr));
    }

    public static int peakIndexInMountainArray(int[] arr) {
        int begin = 1;
        int end = arr.length ;
        int i = end / 2;

        while(begin <=end){
            if (arr[i] > arr[i-1] && arr[i] > arr[i+1] ) {
                break;
            }else if (arr[i] < arr[i-1]){ // 后面一个小于前面一个，往前找
                end  = i;
                i = (i-begin)/2  +begin;
            }else { // 往后找
                begin = i ;
                i = (end -begin)/2 + begin;
            }
        }
        return i;
    }
}
