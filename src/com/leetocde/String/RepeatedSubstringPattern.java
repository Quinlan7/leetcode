package com.leetocde.String;/**
 * @author zhf
 * @date 2023/4/13 9:32
 * @version 1.0
 */

import java.util.ArrayList;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode459
 * @date 2023/4/13 9:32
 **/
public class RepeatedSubstringPattern {
    public static void main(String[] args) {
        System.out.println(Solution459.repeatedSubstringPattern("abac"));
    }
}


class Solution459 {
    public static boolean repeatedSubstringPattern(String s) {
//        char[] chars = s.toCharArray();
//        if(s.length() % 2 == 1){
//
//            for (int i = 1; i < chars.length; i++) {
//                if(chars[i-1] != chars[i]) return false;
//            }
//        }else {
//            for (int i = 0,j = chars.length / 2; i < chars.length / 2; i++,j++) {
//                if(chars[i] != chars[j]) return false;
//            }
//        }
        boolean ret = true;
        char[] chars = s.toCharArray();
        ArrayList<Integer> num = new ArrayList<>();
        for (int i = 1; i < (s.length() / 2)+1; i++) {
            if(s.length() % i == 0) num.add(i);
        }
        for (int flag :
                num) {
            String str = s.substring(0,flag);
            for (int i = 2; i*flag < (s.length() + 1) ; i++) {
                String temp = s.substring(i*flag -flag,i*flag);
                if(!temp.equals(str)) {ret = false; break;}
            }
            if(ret) return ret;
            else ret = true;
        }

        return false;

    }
}