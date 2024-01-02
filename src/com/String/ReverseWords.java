package com.String;/**
 * @author zhf
 * @date 2023/3/30 9:37
 * @version 1.0
 */

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode151
 * @date 2023/3/30 9:37
 **/
public class ReverseWords {
    public static void main(String[] args) {
        Solution151 s = new Solution151();
        s.reverseWords_2("  hello world  ");
    }
}


class Solution151 {
    /**
     * @description 纯纯的调库法
     * @param s
     * @return
     */
    public String reverseWords_1(String s) {
        s = s.replaceAll(" +"," ").trim();
        String ret = new String();
        String[] str = s.split(" ");
        for (int i = str.length -1 ; i >= 0 ; i--) {
            ret = ret.concat(str[i] + " ");
        }
        ret = ret.trim();
        return ret;
    }
    public String reverseWords_2(String s) {
        //1 剪枝 去掉所有的多余的空格
        char[] charS = s.toCharArray();
        char[] noSpace = new char[charS.length];
        int end = 0;
        for (int i = 0; i < charS.length; i++) {
            while( i < charS.length-1 && charS[i] == 32 && charS[i + 1] == 32  ) i++;
            noSpace[end++] = charS[i];
        }
        int start = 0;
        while(noSpace[start] == 32 ) {start++;}
        while(noSpace[--end] == 32) ;
        //这第三个参数是count，所以用end-start+1
        s = String.valueOf(noSpace,start, end-start +1  );
        //2 从后往前遍历 填充到一个新的char数组
        charS = new char[s.length()];
        end = s.length();
        start = 0 ;
        for (int i = s.length()-1; i >= 0 ; i--) {
            while(i >= 0 && s.charAt(i) != 32) i--;
            for (int j = i+1 ; j < end; j++) {
                charS[start] = s.charAt(j);
                start++;
            }
            if (start < s.length()) {
                charS[start++] = 32;
            }
            end = i;
        }
        return new String(charS);
    }
}