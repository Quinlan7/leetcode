package com.stacksandqueues;/**
 * @author zhf
 * @date 2023/10/23 20:51
 * @version 1.0
 */

import java.util.*;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode239
 * @date 2023/10/23 20:51
 **/
public class MaxSlidingWindow {
    public static void main(String[] args) {
        int[] ints = {1,3,-1,-3,5,3,6,7};
        ints = Solution239.maxSlidingWindow(ints,3);
        System.out.println(Arrays.toString(ints));
    }
}


class Solution239 {
    //重点： 如何找到 滑动窗口的最大值
    /**
     * 方案一： 传统队列 ，每次都找窗口内的最大值
     **/
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if(k==1) return nums;
        Queue<Integer> queue = new LinkedList<>();
        int[] ints = new int[nums.length - k + 1];
        for (int i = 0; i < k - 1; i++) {
            queue.offer(nums[i]);
        }
        int temp = nums[0];
        for (int i = 0; i < nums.length-k+1;i++) {
            queue.offer(nums[i+k-1]);
            if( i == 0 ){
                ints[i] = findMax(queue);
                temp = queue.poll();
                continue;
            }
            if (temp == ints[i-1] && nums[i+k-1] < ints[i-1]) {
                ints[i] = findMax(queue);
            } else if (nums[i+k-1] >= ints[i-1] ) {
                ints[i] = nums[i+k-1];
            } else ints[i] = ints[i-1];
            temp = queue.poll();
        }
        return ints;
    }

    static int findMax(Queue<Integer> queue){
        int max = queue.peek();
        for (int num : queue) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
}

class Solution239_2 {
    //重点： 如何找到 滑动窗口的最大值
    /**
     * 方案二：直接双循环，暴力解
     **/

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if(k==1) return nums;
        int[] ret = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length - k + 1; i++) {
            ret[i] = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                ret[i] = Math.max(ret[i],nums[j]);
            }
        }
        return ret;
    }

}


class Solution239_3 {
    //重点： 如何找到 滑动窗口的最大值
    /**
     * 方案三：双端队列，
     * ①：首先传入的是一个数组，我们完全没有必要存储具体数字，直接存储数组下标即可，队列中存放的是数组的下标，而且我们完全可以通过下标判断当前元素是否已经滑出窗口
     * ②：我们需要维护一个单调递减的双端队列，队列中的元素从队头到队尾是从大到小排列的，这样我们只需要判断队头元素是否已经滑出窗口即可
     * ③：滑动窗口最大值一个特点就是：比新加入的元素小的，且下标在它之前的元素都可以踢出队列，因为它不可能是最大值了
     **/

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if(k==1) return nums;
        int[] ret = new int[nums.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            if(!deque.isEmpty() && deque.peekFirst() <= i-k){
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]){
                deque.pollLast();
            }
            deque.offerLast(i);
            if(i >= k-1){
                ret[i-k+1] = nums[deque.peekFirst()];
            }
        }
        return ret;
    }

}