package com.String;/**
 * @author zhf
 * @date 2023/3/27 9:24
 * @version 1.0
 */

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode344
 * @date 2023/3/27 9:24
 **/
public class ReverseString {
}

class Solution344 {
    public void reverseString(char[] s) {
        char temp;
        int head = 0;
        int tail = s.length-1;
        while(head < tail){
            temp = s[head];
            s[head] = s[tail];
            s[tail] = temp;
            head++;
            tail--;
        }

    }
}