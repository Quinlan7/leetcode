package com.array;/**
 * @author zhf
 * @date 2023/2/25 20:38
 * @version 1.0
 */



/**
 * @author zhf
 * 项目：leetcode
 * 描述：二分查找
 * @date 2023/2/25 20:38
 **/
public class BinarySearch_704 {
    public static void main(String[] args) {
        int [] arr = new int[]{1,23,5,46,5756,8,679,67};
        System.out.print(arr.length);
    }


}


/**
 * 给定一个n个元素有序的（升序）整型数组nums 和一个目标值target ，
 * 写一个函数搜索nums中的 target，如果目标值存在返回下标，否则返回 -1。
 * 链接：https://leetcode.cn/problems/binary-search

 */
class Solution01 {
    public int search(int[] nums, int target) {
        int  low=0,high=nums.length-1;
        int mid = (low + high)/2;
        for (; low <= high; mid = (low +high)/2) {
            if(nums[mid] == target){
                return mid;
            } else if (nums[mid] > target) {
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }

        return -1;
    }
}
