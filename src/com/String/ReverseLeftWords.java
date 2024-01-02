package com.String;/**
 * @author zhf
 * @date 2023/4/5 21:02
 * @version 1.0
 */

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode offer 58 Ⅱ
 * @date 2023/4/5 21:02
 **/
public class ReverseLeftWords {
    public static void main(String[] args) {
        SolutionOffer58 solutionOffer58 = new SolutionOffer58();
        solutionOffer58.reverseLeftWords2("abcdefg",2);
    }
}


class SolutionOffer58 {
    public String reverseLeftWords(String s, int n) {
        String s1 = s.substring(0,n);
        String s2 = s.substring(n,s.length());
        return s2 + s1;

    }

    public String reverseLeftWords2(String s, int n) {
        char[] chars = s.toCharArray();
        char[] ret = new char[s.length()];
        for (int i = 0,start = 0,end = n ; i < chars.length; i++,end--) {
            if(i < n) ret[s.length() - end] = chars[i];
            else ret[start++] = chars[i];
        }

        return new String(ret);
    }
}