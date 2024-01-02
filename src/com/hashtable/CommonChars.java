package com.hashtable;/**
 * @author zhf
 * @date 2023/3/15 9:46
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode1002
 * @date 2023/3/15 9:46
 **/
public class CommonChars {
    public static void main(String[] args) {

    }
}



class Solution1002 {
    public List<String> commonChars(String[] words) {
        List<String> strings = new ArrayList<>();
        int[][] counts = new int[words.length][26];
        for (int i = 0 ; i< words.length ;i++) {
            String word = words[i];
            char[] sChar= word.toCharArray();
            for (char c :
                    sChar) {
                counts[i][c-97]++;
            }
        }
        for (int i = 0; i < 26; i++) {
            int flag = counts[0][i];
            for (int j = 0; j < words.length; j++) {

                if(counts[j][i] == 0) {  flag = 0;    break;}
                if(counts[j][i] > 0 && counts[j][i] < flag ){
                    flag = counts[j][i];
                }
            }
            for (int j = 0; j < flag; j++) {
                strings.add(Character.toString((char)(i+97)));

            }
        }
        return strings;

    }

    public HashMap<Character, Integer> charCount(char[] sChar){

        HashMap<Character, Integer> sMap = new HashMap<>();

        for (int i = 0; i < sChar.length; i++) {
            boolean flag = sMap.containsKey(sChar[i]);
            if (flag == false) {
                sMap.put(sChar[i],1);
            }else {
                sMap.put(sChar[i] , sMap.get(sChar[i])+1 );
            }
        }
        return sMap;

    }


}
