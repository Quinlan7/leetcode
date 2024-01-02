package com.array;/**
 * @author zhf
 * @date 2023/2/28 13:17
 * @version 1.0
 */

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode209
 * @date 2023/2/28 13:17
 **/

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 给定一个含有n个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组[numsl, numsl+1, ..., numsr-1, numsr] ，
 * 并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 * 链接：https://leetcode.cn/problems/minimum-size-subarray-sum

 */
public class The_Smallest_Subarray_209 {
    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1,2,4,3};
        Solution209 so = new Solution209();
        int i = so.minSubArrayLen(7, nums);
        System.out.println(i);
    }

}

class Solution209 {
    public int minSubArrayLen(int target, int[] nums) {
        int sum = nums[0],ret =0;
        for (int head = 0,tail = 0; head < nums.length; head++) {
            while(sum < target && tail< nums.length-1 ) {tail++; sum += nums[tail];}
            if(sum >= target && ret == 0) ret = tail-head+1 ;
            else if ( sum >= target && tail - head + 1 < ret) {
                ret = tail - head + 1;
            }
            sum -= nums[head];

        }

        return ret;
    }









/**
 * 又tm看错题目了，最大   连续子数组 ！！！！
 */
//class Solution209 {
//    public int minSubArrayLen(int target, int[] nums) {
//
//        int ret = 0 ;
//        int flag = 0 ;
//
//        Arrays.sort(nums);
//        // QuickSort(nums,0, nums.length-1);
//        for (int i = nums.length-1; i >= 0 ; i--) {
//            if(nums[i] >= target ) return 1;
//            flag += nums[i];
//            ret++;
//            if(flag >= target) return ret;
//
//        }
//        return 0;
//    }



/**
 * @description 看错题目了，以为是flag=target才可以。
 */
//class Solution209 {
//    public int minSubArrayLen(int target, int[] nums) {
//
//        int ret = 0 ;
//        int flag = 0 ;
//
//        Arrays.sort(nums);
////        QuickSort(nums,0, nums.length-1);
//        for (int i = nums.length-1; i >= 0 ; i--) {
//            if(nums[i] == target ) return 1;
//            if(nums[i] < target){
//                flag += nums[i];
//                ret++;
//            }
//            if(flag == target) return ret;
//
//        }
//        return 0;
//    }


    /**
     * @description 手写一个快排庆祝一下
     */
    public static void QuickSort(int[] a, int left, int right) {

        if (left < right) {
            int tempLeft, tempRight, key;

            tempLeft = left;
            tempRight = right;
            key = a[tempLeft];
            while (tempLeft < tempRight) {
                while (tempLeft < tempRight && a[tempRight] > key)
                    tempRight--; // 从右向左找第一个小于x的数
                if (tempLeft < tempRight)
                    a[tempLeft++] = a[tempRight];
                while (tempLeft < tempRight && a[tempLeft] < key)
                    tempLeft++; // 从左向右找第一个大于x的数
                if (tempLeft < tempRight)
                    a[tempRight--] = a[tempLeft];
            }
            a[tempLeft] = key;
            QuickSort(a, left, tempLeft - 1); /* 递归调用 */
            QuickSort(a, tempLeft + 1, right); /* 递归调用 */
        }
    }
}