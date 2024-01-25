package com.greedy;/**
 * @author zhf
 * @date 2024/1/21 19:38
 * @version 1.0
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：贪心算法
 * @date 2024/1/21 19:38
 **/
public class greedy {


    /**
     * 455：分发饼干
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        int j = g.length-1;
        for (int i = s.length-1; i >= 0; i--) {
            for (; j >= 0; j--) {
                if(s[i] >= g[j]){
                    j--;
                    count++;
                    break;
                }
            }
        }
        return count;
    }


    public int wiggleMaxLength(int[] nums) {
        if(nums.length == 1) return nums.length;
        int count = 1;
        int flag = 0;
        int k = 1;
        for (; k < nums.length; k++) {
            if(nums[k] > nums[k-1]) {flag = 1;break;}
            if(nums[k] < nums[k-1]) {flag = 0;break;}
        }
        count++;
        for (k++ ; k < nums.length; k++) {
            if(flag == 0){
                if(nums[k] > nums[k-1]){
                    flag = 1;
                    count++;
                }
            }else {
                if(nums[k] < nums[k-1]){
                    flag = 0;
                    count++;
                }

            }
        }
        return count;
    }


    /**
     * 53:最大子数组和
     * @param nums
     * @return
     */
    @Test
    public void test_53(){
        maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4});
    }
    public int maxSubArray(int[] nums) {
        int head = 0,ret = -10000,sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int temp=0;
            for (int j = head; j < i; j++) {
                temp += nums[j];
            }
            if(temp < 0){
                sum -= temp;
                head = i-1;
            }
            ret = Math.max(sum, ret);
        }
        return ret;
    }


    public int maxSubArray1(int[] nums) {
        int ret = -10000,sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = Math.max(sum+nums[i],nums[i]);
            ret = Math.max(sum, ret);
        }
        return ret;
    }


    /**
     * 122：买卖股票的最佳时机 II
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int inPrice = prices[0],sum = 0;
        for (int i = 1; i < prices.length; i++) {
            if(prices[i] > inPrice){
                sum += prices[i] - inPrice;
            }
            inPrice = prices[i];
        }
        return sum;
    }


    /**
     * 55：跳跃游戏
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if(nums.length == 1) return true;
        int max=0;
        for (int i = 0; i < nums.length-1; i++) {
            if(i > max) return false;
            if(i + nums[i] >= nums.length-1) return true;
            max = Math.max(i + nums[i],max);
        }
        return false;
    }


    /**
     * 45:跳跃游戏II
     * @param nums
     * @return
     */
    int count_45 = 0;
    public int jump(int[] nums) {
        jumpStep(nums,nums.length-1);
        return count_45;
    }

    private void jumpStep(int[] nums, int i) {
        if(i == 0)return;
        int min = i;
        for (int j = i-1; j >= 0; j--) {
            if(nums[j] + j >= i) min = j;
        }
        count_45++;
        jumpStep(nums,min);
    }


    /**
     * 1005:k次取反后最大化的数组和
     * @param nums
     * @param k
     * @return
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        for (int i = 0; i < k; i++) {
            if(i>nums.length-1){
                Arrays.sort(nums);
                if((k-i) % 2 ==1) nums[0] = -nums[0];
                break;
            }
            if(nums[i]<0){
                nums[i] = Math.abs(nums[i]);
            }else {
                Arrays.sort(nums);
                if((k-i) % 2 ==1) nums[0] = -nums[0];
                break;
            }
        }
        return Arrays.stream(nums).sum();
    }


    /**
     * 134：加油站
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if(cost.length == 1 && gas[0] >= cost[0]) return 0;
        if(Arrays.stream(gas).sum() < Arrays.stream(cost).sum()) return -1;
        for (int i = 0; i < gas.length; i++) {
            int spare = gas[i] - cost[i];
            if(spare <= 0) continue;
            for (int j = i+1; j < gas.length + i; j++) {
                spare += gas[j % gas.length] - cost[j % gas.length];
                if(spare < 0) break;
            }
            if(spare >= 0) return i;
        }
        return -1;
    }

    public int canCompleteCircuit_1(int[] gas, int[] cost) {
        int n = gas.length;
        int i = 0;
        while (i < n) {
            int sumOfGas = 0, sumOfCost = 0;
            int cnt = 0;
            while (cnt < n) {
                int j = (i + cnt) % n;
                sumOfGas += gas[j];
                sumOfCost += cost[j];
                if (sumOfCost > sumOfGas) {
                    break;
                }
                cnt++;
            }
            if (cnt == n) {
                return i;
            } else {
                i = i + cnt + 1;
            }
        }
        return -1;
    }

    /**
     * 135:分发糖果
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        candies[0]=1;
        for (int i = 1; i < ratings.length; i++) {
            if(ratings[i] > ratings[i-1]) candies[i] = candies[i-1] + 1;
            else candies[i] = 1;
        }
        candies[ratings.length-1] = Math.max(candies[ratings.length-1],1);
        for (int i = ratings.length-2; i >= 0; i--) {
            if(ratings[i] > ratings[i+1]) candies[i] = Math.max(candies[i+1] + 1,candies[i]);
            else candies[i] = Math.max(1,candies[i]);
        }
        return Arrays.stream(candies).sum();
    }

}
