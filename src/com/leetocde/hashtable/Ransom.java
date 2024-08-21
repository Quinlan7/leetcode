package com.leetocde.hashtable;/**
 * @author zhf
 * @date 2023/3/21 7:57
 * @version 1.0
 */

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode383
 * @date 2023/3/21 7:57
 **/
public class Ransom {
}

class Solution383 {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] flag = new int[26];
        for (int temp:
             magazine.toCharArray()) {
            flag[temp - 97]++;
        }
        for (int temp :
                ransomNote.toCharArray()) {
            if (--flag[temp - 97] == -1) return false;
        }
        return true;


    }
}
