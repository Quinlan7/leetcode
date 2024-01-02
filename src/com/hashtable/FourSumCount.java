package com.hashtable;/**
 * @author zhf
 * @date 2023/3/20 9:28
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode454
 * @date 2023/3/20 9:28
 **/
public class FourSumCount {
}


class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int count = 0;
        HashMap<Integer, Integer> temp1 = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                    temp1.put(nums1[i]+nums2[j],temp1.getOrDefault(nums1[i]+nums2[j],0)+1);
            }
        }
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                count += temp1.getOrDefault(-(nums3[i]+nums4[j]),0);
            }
        }return count;
    }
}