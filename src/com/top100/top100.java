package com.top100;/**
 * @author zhf
 * @date 2024/3/27 8:53
 * @version 1.0
 */

import org.junit.Test;

import java.util.*;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：热题100
 * @date 2024/3/27 8:53
 **/
public class top100 {

    /**
     * 49：字母移位词分组
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ret = new ArrayList<>();
        Map<String,List<Integer>> position = new HashMap<>();
        for (int i = 0 ; i < strs.length ; i++) {
            char[] temp = strs[i].toCharArray();
            Arrays.sort(temp);
            List<Integer> positionList = position.getOrDefault(Arrays.toString(temp),new ArrayList<>());
            positionList.add(i);
            position.put(Arrays.toString(temp), positionList);
        }
        for (List<Integer> value : position.values()) {
            List<String> group = new ArrayList<>();
            for (Integer i : value) {
                group.add(strs[i]);
            }
            ret.add(group);
        }
        return ret;
    }

    /**
     * 128: 最长连续序列
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        if(nums.length == 0) return 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.offer(num);
        }
        int count = 1;
        int max = 1;
        int last = queue.poll();
        while (!queue.isEmpty()) {
            int now = queue.poll();
            if(now - last == 0){
                continue;
            }else if(now - last == 1){
                count++;
            }else{
                max = Math.max(max,count);
                count = 1;
            }
            last = now;
        }
        max = Math.max(max,count);
        return max;
    }

    /**
     * 283: 移动0
     * @param nums
     */
    @Test
    public void test_283(){
        moveZeroes(new int[]{0,1,0,3,12});
    }
    public void moveZeroes(int[] nums) {
        int tail = nums.length - 1;
        for (int i = 0; i < tail+1; i++) {
            if(nums[i] == 0){
                for (int j = i; j < tail; j++) {
                    nums[j] = nums[j+1];
                }
                nums[tail] = 0;
                tail--;
                i--;
            }
        }
    }


    /**
     * 11: 盛最多水的容器
     * * @param height
     * @return
     */
    // 暴力解，超时
    public int maxArea1(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length-1; i++) {
            for (int j = i+1; j < height.length; j++) {
                max = Math.max(max,Math.min(height[i],height[j]) * (j-i));
            }
        }
        return max;
    }

    public int maxArea2(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length-1;
        while (left < right) {
            max = Math.max(max,Math.min(height[left],height[right]) * (right-left));
            if(height[left] < height[right]) left++;
            else right--;
        }
        return max;
    }


    /**
     * 42: 接雨水
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int count = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i < height.length; i++) {
            if(height[i-1] > height[i]) stack.push(i-1);
            if(height[i-1] < height[i]){
                if(stack.isEmpty()) continue;

                int min = Math.min(height[i],height[stack.peek()]);
                count += (i-stack.peek()-1) * (min - height[i-1]);
                for (int j = stack.peek()+1; j < i; j++) {
                    height[j] = min;
                }
                if(height[stack.peek()] < height[i]) i--;
                if(height[stack.peek()] <= height[i]) stack.pop();

            }
        }
        return count;
    }


    /**
     * 3: 无重复字符的最长字串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if(s.isEmpty()) return 0;
        Map<Character,Integer> map = new HashMap<>();
        map.put(s.charAt(0),0);
        int left = 0,right = 1;
        int max = 1;
        for (; right < s.length(); ) {
            char temp = s.charAt(right);
            if(!map.containsKey(temp)){
                map.put(temp,right);
                right++;
                max = Math.max(right - left,max);
            }else {
                int repeat = map.get(temp);
                for (int i = left; i < repeat; i++) {
                    map.remove(s.charAt(i));
                }
                left = repeat+1;
                map.put(temp,right);
                right++;
            }
        }
        return max;
    }


    /**
     * 438: 找到字符串中所有字母异位词
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        if(s.length()<p.length()) return new ArrayList<>();
        List<Integer> ret = new ArrayList<>();
        Map<Character,Integer> pCount = new HashMap<>();
        Map<Character,Integer> sCount = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            pCount.put(p.charAt(i),pCount.getOrDefault(p.charAt(i),0)+1);
        }
        for (int i = 0; i < p.length(); i++) {
            sCount.put(s.charAt(i),sCount.getOrDefault(s.charAt(i),0)+1);
        }
        int left = 0,right = p.length();
        for (; right < s.length(); right++,left++) {
            if(pCount.equals(sCount)) ret.add(left);
            char leftChar = s.charAt(left);
            if(sCount.get(leftChar) == 1) sCount.remove(leftChar);
            else sCount.put(leftChar,sCount.get(leftChar)-1);
            sCount.put(s.charAt(right),sCount.getOrDefault(s.charAt(right),0)+1);
        }
        if(pCount.equals(sCount)) ret.add(left);
        return ret;
    }




}
