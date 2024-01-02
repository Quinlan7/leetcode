package com.hashtable;/**
 * @author zhf
 * @date 2023/3/15 8:36
 * @version 1.0
 */

import org.junit.Test;

import java.util.HashMap;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode242
 * @date 2023/3/15 8:36
 **/
public class Anagram {
    public static void main(String[] args) {


    }
}

class Solution242 {
    public boolean isAnagram(String s, String t) {
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        HashMap<Character,Integer> sMap = new HashMap();
        HashMap<Character,Integer> tMap = new HashMap();
        for (int i = 0; i < sChar.length; i++) {
            boolean flag = sMap.containsKey(sChar[i]);
            if (flag == false) {
                sMap.put(sChar[i],1);
            }else {
                sMap.put(sChar[i] , sMap.get(sChar[i])+1 );
            }
        }
        for (int i = 0; i < tChar.length; i++) {
            boolean flag = tMap.containsKey(tChar[i]);
            if (flag == false) {
                tMap.put(tChar[i],1);
            }else {
                tMap.put(tChar[i] , tMap.get(tChar[i])+1 );
            }
        }
        return sMap.equals(tMap);
    }



}


class test{
    @Test
    public void test(){
        HashMap<Object, Object> map = new HashMap<>();
        Object o = map.put(1,1);
        Object b = map.put(1,1);
        System.out.println(o  );
        System.out.println(b  );

    }

}
