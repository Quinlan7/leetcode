package com.leetocde.hashtable;/**
 * @author zhf
 * @date 2023/3/21 8:52
 * @version 1.0
 */

import java.util.*;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode15
 * @date 2023/3/21 8:52
 **/
public class ThreeSum {
    public static void main(String[] args) {
        int[] nums = new int[]{-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0};
        Solution15 solution15 = new Solution15();
        solution15.threeSum(nums);
    }
}

class Solution15 {
    public List<List<Integer>> threeSum(int[] nums) {

        HashMap<LinkedList<Integer>, Integer> mapSum = new HashMap<>();
        List<List<Integer>> ret = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                LinkedList<Integer> list = new LinkedList<>();
                list.add(i);
                list.add(j);
                mapSum.put(list, nums[i] + nums[j]);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (mapSum.containsValue(-nums[i])) {
                for (List<Integer> list :
                        (List<List<Integer>>) getKey(mapSum, -nums[i])) {
                    if (list.get(0) != i && list.get(1) != i) {
                        List<Integer> temp = new LinkedList<Integer>();
                        temp.add(nums[list.get(0)]);
                        temp.add(nums[list.get(1)]);
                        temp.add(nums[i]);
                        Collections.sort(temp);
                        int count=0;
                        for (List<Integer> list1 : ret) {
                            if (temp.equals(list1)) {
                                count++;
                                break;
                            }
                        }
                        if(count == 0) ret.add(temp);
                        mapSum.remove(list);
                    }
                }
            }
        }
        return ret;

    }

    public static Object getKey(Map map, Integer value) {
        List<Object> keyList = new LinkedList<>();
        for (Object key : map.keySet()) {
            if (map.get(key).equals(value)) {
                keyList.add(key);
            }
        }
        return keyList;
    }
}