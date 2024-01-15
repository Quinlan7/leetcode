package com.backtrack;/**
 * @author zhf
 * @date 2024/1/15 11:20
 * @version 1.0
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：回溯算法问题
 * @date 2024/1/15 11:20
 **/
public class backtracking {


    /**
     * 77：组合
     * @param n
     * @param k
     * @return
     */
    List<List<Integer>> ret_77 = new ArrayList<>();

    @Test
    public void test_77(){
        combine(4,2);
    }


    public List<List<Integer>> combine(int n, int k) {
        List<Integer> temp = new ArrayList<>();
        boolean[] used = new boolean[n];
        backtrack(1,n,k,temp,used);
        return ret_77;
    }

    private void backtrack(int i,int n, int k, List<Integer> temp, boolean[] used) {
        if(temp.size() == k){
            ret_77.add(new ArrayList<>(temp));
            return;
        }
        for (int j = i; j < n + 1; j++) {
            if(used[j - 1]) continue;
            used[j-1] = true;
            temp.add(j);
            backtrack(j,n,k,temp,used);
            used[j-1] = false;
            temp.remove(temp.size()-1);
        }
    }


    /**
     * 216:组合总和3
     * @param k
     * @param n
     * @return
     */
    List<List<Integer>> ret_216 = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> temp = new ArrayList<>();
        backtrack_216(1,n,k,temp);
        return ret_216;
    }
    int sum_216 = 0;

    private void backtrack_216(int i,int n, int k, List<Integer> temp) {

        if(temp.size() == k && sum_216 == n){
            ret_216.add(new ArrayList<>(temp));
            return;
        }
        if(temp.size() >= k){
            return;
        }
        for (int j = i; j < 10; j++) {
            temp.add(j);
            sum_216 += j;
            if (sum_216<=n) {
                backtrack_216(j+1,n,k,temp);
            }
            sum_216 -= j;
            temp.remove(temp.size()-1);
        }
    }


    /**
     * 17:电话号码的字母组合
     * @param digits
     * @return
     */
    List<List<String>> mapping_17 = initializeMapping();
    private static List<List<String>> initializeMapping() {
        List<List<String>> mapping = new ArrayList<>();

        // 初始化映射关系
        mapping.add(Arrays.asList());           // 数字 0 对应的字母列表：空列表
        mapping.add(Arrays.asList());           // 数字 1 对应的字母列表：空列表
        mapping.add(Arrays.asList("a", "b", "c"));    // 数字 2 对应的字母列表：a, b, c
        mapping.add(Arrays.asList("d", "e", "f"));    // 数字 3 对应的字母列表：d, e, f
        mapping.add(Arrays.asList("g", "h", "i"));    // 数字 4 对应的字母列表：g, h, i
        mapping.add(Arrays.asList("j", "k", "l"));    // 数字 5 对应的字母列表：j, k, l
        mapping.add(Arrays.asList("m", "n", "o"));    // 数字 6 对应的字母列表：m, n, o
        mapping.add(Arrays.asList("p", "q", "r", "s")); // 数字 7 对应的字母列表：p, q, r, s
        mapping.add(Arrays.asList("t", "u", "v"));    // 数字 8 对应的字母列表：t, u, v
        mapping.add(Arrays.asList("w", "x", "y", "z")); // 数字 9 对应的字母列表：w, x, y, z

        return mapping;
    }
    List<String> ret_17 = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0 ) return ret_17;
        char[] numc = digits.toCharArray();
        List<Integer> num = new ArrayList<>();
        for (char c :
                numc) {
            num.add(Character.getNumericValue(c));
        }
        backtrack_17(0,"",num);
        return ret_17;
    }

    private void backtrack_17(int i, String s, List<Integer> num) {
        if(s.length() == num.size()){
            ret_17.add(s);
            return;
        }
        List<String> temp = mapping_17.get(num.get(i));
        for (String c : temp) {
               s += c;
               backtrack_17(i+1,s,num);
               s = s.substring(0,s.length()-1);
        }
    }

}
