package com.String;/**
 * @author zhf
 * @date 2023/4/9 10:12
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode 28
 * @date 2023/4/9 10:12
 **/
public class KMP {

    public static void main(String[] args) {

        System.out.println(Solution28.strStr("leetcodeaaaaaaaaaaaa",

                "ississippi"));
    }
}
class Solution28 {
    public static int strStr(String haystack, String needle) {
        if(needle.length() > haystack.length()) return -1;
        if(needle.length() == 0 ) return 0;
        //得到next数组
        int[] next = new int[needle.length()];
        getNext(next,needle);
        System.out.println(Arrays.toString(next));
        //i模式串（长的）指针 ， j匹配串（短的）指针
        for (int i = 0,j = 0; i < haystack.length();) {
            //1 当匹配时 i，j 都加一
            if(j < needle.length() && haystack.charAt(i) == needle.charAt(j)) {j++;i++;}
            //2 当不匹配时利用next数组回溯，但是注意如果是在我们的匹配串的第一个位置就不匹配时，即next[j] = -1 时，i和j都要加一。
            //  因为正常当next[j] == 0 时，我们不需要对i进行加一操作，直接比较模式串的当前位置即可（上一次不匹配的位置），
            //  可是当前我们的匹配串已经在第一个位置就不匹配时，若i还不加一，则会造成 死循环的出现。所以当我们在匹配串的第一个位置不匹配时，
            //  必须要让i加一，以为用j==-1，来标记这种情况的发生，所以j也要加一。
            else if(j < needle.length() && haystack.charAt(i) != needle.charAt(j)) {
                j = next[j];
                if(j == -1) {i++;j++;}
            }
            //3 当全部匹配成功时返回，即j等于needle.length
            if(j == needle.length() ) return i-needle.length();
        }


        return -1;

    }

    /**
     * int GetNext(char ch[],int cLen,int next[]){//cLen为串ch的长度
     *     next[1] = 0;
     *     int i = 1,j = 0;
     *     while(i<=cLen){
     *         if(j==0||ch[i]==ch[j]) next[++i] = ++j;
     *         else j = next[j];
     *     }
     * }
     * 求next的最重要的一点是：next[j]的可能的最大值就是next[j-1]+1,所以在求每一个next[j]的时候，我们只需要利用已有的next数组来跳转即可，一次不匹配就继续跳转j = next[j]
     * 我的next标准:
     * 1.当不匹配时，直接找当前不匹配的下标的next。不用找前一个的
     * 2.以0为最小，next小标从0开始记录
     * 3.求next[j]的时候看next[j-1]及之前的位置相同的前后缀长
     * 4.next[0]固定为-1,next[1]固定为0
     *
     * next为什么不为0，需要好好解释
     * @param next
     * @param s
     */

    public static void getNext(int[] next, String s) {
        if(s.length()==1){ next[0]=-1;return;}
        next[0] = -1;next[1]=0;
        int i = 1,j = 0;
        while(i < next.length-1){
            if( s.charAt(i) == s.charAt(j)){
                next[++i] = ++j;
            }
            else if( j == 0 ) {
                next[++i] = j;
            } else j = next[j];
        }
    }
}