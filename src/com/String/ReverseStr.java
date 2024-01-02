package com.String;/**
 * @author zhf
 * @date 2023/3/28 14:30
 * @version 1.0
 */

import com.sun.xml.internal.messaging.saaj.util.CharReader;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode541
 * @date 2023/3/28 14:30
 **/
public class ReverseStr {
}
class Solution541 {
    public String reverseStr(String s, int k) {
        if(s.length() % (2*k) < k){
            s = revers(s,(2*k)*(s.length()/(2*k)),s.length()-1);
        }else { s = revers(s,(2*k)*(s.length()/(2*k)),(2*k)*(s.length()/(2*k))+k-1);}
        for (int i = 0; i < s.length() / (2 * k); i++) {
            s = revers(s ,  i * (2 * k) , i * (2 * k) + k - 1);
        }
        return s;
    }
    private String revers(String s, int head, int tail){
        char[] charS = s.toCharArray();
        while(head < tail){
            char temp = charS[head];
            charS[head] = charS[tail];
            charS[tail] = temp;
            head++;
            tail--;
        }
        return new String(charS);
    }

}