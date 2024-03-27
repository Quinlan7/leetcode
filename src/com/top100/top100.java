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


}
