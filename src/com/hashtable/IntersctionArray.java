package com.hashtable;/**
 * @author zhf
 * @date 2023/3/16 13:08
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode349
 * @date 2023/3/16 13:08
 **/
public class IntersctionArray {
    public static void main(String[] args) {

    }
}


class Solution349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> temp = new HashSet<>();
        HashSet<Integer> set = new HashSet<Integer>();
        for (int num :
                nums1) {
            set.add(num);
        }
        for (int num :
                nums2) {
            if (set.contains(num)) {
                temp.add(num);
            }

        }
        int[] ret = new int[temp.size()];
        int i = 0 ;
        for (Integer e :
                temp) {
            ret[i] = e;
            i++;
        }
        return  ret;
    }
}