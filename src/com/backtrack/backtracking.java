package com.backtrack;/**
 * @author zhf
 * @date 2024/1/15 11:20
 * @version 1.0
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：回溯算法问题
 * @date 2024/1/15 11:20
 **/
@SuppressWarnings("all")
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


    /**
     * 39:组合总和
     * @param candidates
     * @param target
     * @return
     */
    List<List<Integer>> ret_39 = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> temp = new ArrayList<>();
        backtrack_39(0 , candidates, target, temp);
        return ret_39;
    }

    private void backtrack_39(int i, int[] candidates, int target, List<Integer> temp) {
        if(temp.stream().mapToInt(Integer::intValue).sum() == target){
            ret_39.add(new ArrayList<>(temp));
            return;
        }
        if(temp.stream().mapToInt(Integer::intValue).sum() > target) return;
        for (int j = i; j < candidates.length ; j++) {
            temp.add(candidates[j]);
            backtrack_39(j, candidates, target, temp);
            temp.remove(temp.size() - 1);
        }
    }


    /**
     * 40: 组合总和二
     * @param candidates
     * @param target
     * @return
     */
    List<List<Integer>> ret_40 = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack_40(0 , candidates, target, temp);
        return ret_40;
    }

    private void backtrack_40(int i, int[] candidates, int target, List<Integer> temp) {
        if(temp.stream().mapToInt(Integer::intValue).sum() == target){
            ret_40.add(new ArrayList<>(temp));
            return;
        }
        if(temp.stream().mapToInt(Integer::intValue).sum() > target) return;
        int same = 0;
        for (int j = i; j < candidates.length ; j++) {
            if(candidates[j] == same) continue;
            temp.add(candidates[j]);
            backtrack_40(j+1, candidates, target, temp);
            temp.remove(temp.size() - 1);
            same = candidates[j];
        }
    }

    /**
     * 131:分割回文串
     * @param s
     * @return
     */
    List<List<String>> ret_131 = new ArrayList<>();
    public List<List<String>> partition(String s) {
        List<String> strings = new ArrayList<>();
        backtrack_131(0, s , strings);
        return ret_131;
    }

    private void backtrack_131(int i, String s, List<String> strings) {
        if(i == s.length()){
            ret_131.add(new ArrayList<>(strings));
            return;
        }
        String temp = new String();
        for (int j = i; j < s.length(); j++) {
            temp += s.charAt(j);
            if(isPalindrome(temp)){
                strings.add(temp);
                backtrack_131(j+1,s,strings);
                strings.remove(strings.size() - 1);
            }

        }
    }

    private boolean isPalindrome(String s){
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if(charArray[i] != charArray[charArray.length-1-i]) return false;
        }
        return true;
    }


    /**
     *93:复原ip地址
     * @param s
     * @return
     */
    @Test
    public void test_93(){
        restoreIpAddresses("25525511135");
    }

    List<String> ret_93 = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        String string = new String();
        backtrack_93(0, s , string,0);
        return ret_93;
    }

    private void backtrack_93(int i, String s, String string,int k) {
        if(k==4 && i == s.length()){
            string = string.substring(0,string.length()-1);
            ret_93.add(new String(string));
            return;
        }
        if(k==4) return;
        String temp = new String();
        for (int j = i; j < s.length(); j++) {
            temp += s.charAt(j);
            if(isIPPart(temp)){
                string += temp+".";
                backtrack_93(j+1,s,string,k+1);
                string = string.substring(0,string.length() - temp.length()+1);

            }
        }
    }

    private boolean isIPPart(String ipSegment) {
        // 判断字符串是否为空
        if (ipSegment == null || ipSegment.isEmpty()) return false;
        if(ipSegment.length()>3) return false;
        // 判断字符串是否为数字
        if (!ipSegment.matches("\\d+")) return false;
        // 判断是否以0开头但长度不为1
        if (ipSegment.startsWith("0") && ipSegment.length() != 1) return false;
        // 转换为整数
        int segmentValue = Integer.parseInt(ipSegment);
        // 判断是否在合法的范围内
        return segmentValue >= 0 && segmentValue <= 255;
    }


    /**
     * 78：子集
     * @param nums
     * @return
     */
    List<List<Integer>> ret_78 = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> temp = new ArrayList<>();
        backtrack_78(0, temp , nums);
        return ret_78;
    }

    private void backtrack_78(int i, List<Integer> temp, int[] nums) {
        if(temp.size() > temp.size()) return;
        if(isSubsets(temp,nums)){
            ret_78.add(new ArrayList<>(temp));
        }
        for (int j = i; j < nums.length; j++) {
            temp.add(nums[j] );
            backtrack_78(j+1,temp,nums);
            temp.remove(temp.size()-1);
        }
    }

    private boolean isSubsets(List<Integer> temp, int[] nums) {
        for (int i : temp) {
            boolean flag = false;
            for (int num : nums) {
                if(num == i) flag = true;
            }
            if(flag == false) return false;
        }
        return true;
    }


    /**
     * 90:子集II
     * @param nums
     * @return
     */
    List<List<Integer>> ret_90 = new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(nums);
        backtrack_90(0, temp , nums);
        return ret_90;
    }

    private void backtrack_90(int i, List<Integer> temp, int[] nums) {
        if(temp.size() > temp.size()) return;
        if(isSubsets(temp,nums)){
            ret_90.add(new ArrayList<>(temp));
        }
        boolean same = false;
        for (int j = i; j < nums.length; j++) {
            for (int k = i; k < j; k++) {
                if(nums[k] == nums[j]) same = true;
            }
            if(same){
                same = false;
                continue;
            }
            temp.add(nums[j] );
            backtrack_90(j+1,temp,nums);
            temp.remove(temp.size()-1);
        }
    }


    /**
     * 491:非递减子序列
     * @param nums
     * @return
     */
    List<List<Integer>> ret_491 = new ArrayList<>();
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<Integer> temp = new ArrayList<>();
        backtrack_491(0, temp , nums);
        return ret_491;
    }

    private void backtrack_491(int i, List<Integer> temp, int[] nums) {
        if(temp.size()>nums.length) return;
        if(isASCSubsequences(temp)){
            ret_491.add(new ArrayList<>(temp));
        }
        boolean same = false;
        for (int j = i; j < nums.length; j++) {
            for (int k = i; k < j; k++) {
                if(nums[k] == nums[j]) same = true;
            }
            if(same){
                same = false;
                continue;
            }
            temp.add(nums[j]);
            backtrack_491(j+1,temp,nums);
            temp.remove(temp.size()-1);
        }
    }

    private boolean isASCSubsequences(List<Integer> temp) {
        if(temp.size()<2) return  false;
        for (int i = 1; i < temp.size(); i++) {
            if(temp.get(i) < temp.get(i-1)) return false;
        }
        return true;
    }


    /**
     * 46:全排列
     * @param nums
     * @return
     */
    List<List<Integer>> ret_46 = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> temp = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack_46(used, temp , nums);
        return ret_46;
    }

    private void backtrack_46(boolean[] used, List<Integer> temp, int[] nums) {
        if(temp.size() == nums.length){
            ret_46.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(used[i]) continue;
            used[i] = true;
            temp.add(nums[i]);
            backtrack_46(used,temp,nums);
            used[i] = false;
            temp.remove(temp.size() - 1);
        }
    }


    /**
     * 47:全排列II
     * @param nums
     * @return
     */
    List<List<Integer>> ret_47 = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> temp = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack_47(used, temp , nums);
        return ret_47;
    }

    private void backtrack_47(boolean[] used, List<Integer> temp, int[] nums) {
        if(temp.size() == nums.length){
            ret_47.add(new ArrayList<>(temp));
            return;
        }
        boolean same = false;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if(used[j]) continue;
                if(nums[i] == nums[j]) same = true;
            }
            if(same){
                same = false;
                continue;
            }
            if(used[i]) continue;
            used[i] = true;
            temp.add(nums[i]);
            backtrack_47(used,temp,nums);
            used[i] = false;
            temp.remove(temp.size() - 1);
        }
    }


    /**
     * 332:重新安排行程
     * @param tickets
     * @return
     */
    @Test
    public void test(){
        List<List<String>> flights = new ArrayList<>();
        flights.add(Arrays.asList("JFK", "SFO"));
        flights.add(Arrays.asList("JFK", "ATL"));
        flights.add(Arrays.asList("SFO", "ATL"));
        flights.add(Arrays.asList("ATL", "JFK"));
        flights.add(Arrays.asList("ATL", "SFO"));
        System.out.println(findItinerary(flights));
    }
    List<String> ret_332 = new ArrayList<>();
    public List<String> findItinerary(List<List<String>> tickets) {
        boolean[] used = new boolean[tickets.size()];
        List<String> temp = new ArrayList<>();
        backtrack_332(used, temp, tickets );
        return ret_332;
    }

    private void backtrack_332(boolean[] used, List<String> temp, List<List<String>> tickets) {
        if(temp.size() == tickets.size()+1){
            if(!ret_332.isEmpty()){
                if(isSmall(temp)){
                    ret_332.clear();
                    ret_332.addAll(new ArrayList<>(temp));
                }
            }else ret_332.addAll(new ArrayList<>(temp));
        }
        for (int i = 0; i < tickets.size(); i++) {
            if(used[i]) continue;
            String s = tickets.get(i).get(0);
            if(!temp.isEmpty()){
                if (Objects.equals(temp.get(temp.size() - 1), s)) {
                    used[i] = true;
                    temp.add(tickets.get(i).get(1));
                    backtrack_332(used,temp,tickets);
                    temp.remove(temp.size()-1);
                    used[i] = false;
                }
            }else {
                if (Objects.equals("JFK", s)) {
                    used[i] = true;
                    temp.add(tickets.get(i).get(0));
                    temp.add(tickets.get(i).get(1));
                    backtrack_332(used,temp,tickets);
                    temp.remove(temp.size()-1);
                    temp.remove(temp.size()-1);
                    used[i] = false;
                }
            }
        }
    }

    private boolean isSmall(List<String> temp) {
        for (int i = 1; i < temp.size(); i++) {
            char[] charArray = temp.get(i).toCharArray();
            char[] ret = ret_332.get(i).toCharArray();
            for (int j = 0; j < ret.length; j++) {
                if(charArray[j] < ret[j]) return true;
                if(charArray[j] > ret[j]) return false;
            }
        }
        return false;
    }

}
