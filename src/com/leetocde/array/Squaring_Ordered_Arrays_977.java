package com.leetocde.array;/**
 * @author zhf
 * @date 2023/2/27 10:36
 * @version 1.0
 */

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode977
 * @date 2023/2/27 10:36
 **/
public class Squaring_Ordered_Arrays_977 {
    public static void main(String[] args) {
        int[] nums = new int[]{-3,0,2};
        Solution977 so =new Solution977();
        int[] arr= so.sortedSquares(nums);
    }
}


/**
 * @description 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 */
class Solution977 {
    public int[] sortedSquares(int[] nums) {
        int[] ints = new int[nums.length];
        int flag=0;
        int head=0;
        for (int i = nums.length-1; i >= 0; i--) {
            int temp = nums[i] * nums[i];
            if(nums[i]<=0){
                if(nums[nums.length-1]>0){
                    if(flag == 0 && i!=nums.length-1) flag = i+1 ;
                    for ( ;  flag < nums.length && ints[flag] < temp ; ) {
                        ints[head] = ints[flag];
                        head++;
                        flag++;
                    }
                    ints[head++]=temp;
                }
                else{
                    ints[head++]=temp;
                }
            }else {
                ints[i] = temp ;
            }

        }
        return ints;

    }
}