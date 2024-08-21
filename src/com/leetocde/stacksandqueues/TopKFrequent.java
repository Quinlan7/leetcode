package com.leetocde.stacksandqueues;/**
 * @author zhf
 * @date 2023/10/29 13:00
 * @version 1.0
 */

import java.util.*;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode347
 * @date 2023/10/29 13:00
 **/
public class TopKFrequent {
    public static void main(String[] args) {
        int[] ints = {1,1,1,2,2,3};
        ints = Solution347.topKFrequent1(ints,2);
        System.out.println(Arrays.toString(ints));

    }
}

class Solution347 {
    public static int[] topKFrequent1(int[] nums, int k) {
        int count[][] = new int[nums.length][2];
        Arrays.sort(nums);
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i < nums.length-1 && nums[i] != nums[i + 1]) {
                count[temp][0] = nums[i];
                temp++;
            } else if (i == nums.length-1) {
                count[temp][1] = count[temp][1] + 1;
                count[temp][0] = nums[i];
            } else count[temp][1] = count[temp][1] + 1;   //初始化为1
        }
        Arrays.sort(count, Comparator.comparingInt((int[] a) -> a[1]).reversed());
        int ret[] = new int[k];
        int temp2 = 0;
        for (int i = 0; i < k; i++) {
            ret[i] = count[i][0];
        }
        return  ret;
    }


    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }

        // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
        /**
         * java中的优先级队列：小根堆（可通过自定义比较器改变）
         * 比较器：Comparator
         * 比较两个参数的顺序。如果第一个参数小于、等于或大于第二个参数，则返回负整数、零或正整数。(正常逻辑)、
         * 如果你的比较器符合上面逻辑，那么就是最小堆，否则就是最大堆
         */
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] m, int[] n) {
                return m[1] - n[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            } else {
                queue.offer(new int[]{num, count});
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; ++i) {
            ret[i] = queue.poll()[0];
        }
        return ret;
    }


}