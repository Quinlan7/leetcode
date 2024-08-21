package com.leetocde.hashtable;/**
 * @author zhf
 * @date 2023/3/17 10:04
 * @version 1.0
 */

import java.util.HashSet;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode202
 * @date 2023/3/17 10:04
 **/
public class HappyNum {
    public static void main(String[] args) {

    }
}


class Solution202 {
    public boolean isHappy(int n) {


        for (HashSet<Integer> set = new HashSet<>(); set.add(n)  ;) {
            char[] chars = String.valueOf(n).toCharArray();
            n = 0 ;
            for (int temp :
                    chars) {
                temp -= 48;
                n += temp*temp;
            }
            if(n == 1 ) return true;

        }
        return false;


    }
}