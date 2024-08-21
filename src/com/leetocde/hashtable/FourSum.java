package com.leetocde.hashtable;/**
 * @author zhf
 * @date 2023/3/26 13:48
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode18
 * @date 2023/3/26 13:48
 **/
public class FourSum {
    public static void main(String[] args) {
        List<List<Integer>> ret = Solution18.fourSum(new int[]{1000000000,1000000000,1000000000,1000000000}, -294967296);
        System.out.println(ret);
    }
}

class Solution18 {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            while(i != 0 && nums[i] == nums[i-1]) i++;
            for (int j = i+1; j < nums.length - 2; j++) {
                while(j != i+1 && nums[j] == nums[j-1]) j++;
                int head = j+1;
                int tail = nums.length-1;
                while (head < tail) {
                    long sum = (long)nums[i] + nums[j] + nums[head] + nums[tail];
                    if(sum == target){
                        ret.add(Arrays.asList(nums[i], nums[j], nums[head], nums[tail]));
                        while(head < tail && nums[head] == nums[head+1]) head++;
                        while(head < tail && nums[tail] == nums[tail-1]) tail--;
                        head++;
                        tail--;
                    }else if(sum < target) {head++;}
                    else {tail--;}
                }
            }
        }
        return ret;
    }
}