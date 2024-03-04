package com.dp;/**
 * @author zhf
 * @date 2024/2/24 10:37
 * @version 1.0
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：动态规划
 * @date 2024/2/24 10:37
 **/
@SuppressWarnings("all")
public class DynamicProgramming {

    /**
     * 509 斐波那契数列
     * @param n
     * @return
     */
    public int fib(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < n+1; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    /**
     * 70 爬楼梯
     * @param n
     * @return
     */
    int ret_70 = 0;
    public int climbStairs(int n) {
        climbStairsLevel(n,0,1);
        climbStairsLevel(n,0,2);
        return ret_70;
    }

    private void climbStairsLevel(int n,int level,int step) {
        level += step;
        if(level > n) return;
        if(level == n){
            ret_70++;
            return;
        }
        climbStairsLevel(n,level,1);
        climbStairsLevel(n,level,2);
    }

    /**
     * 分解为子问题：到达 f(x) = f(x-1) + f(x-2)
     */
    public int climbStairs_2(int n) {
        if(n == 1) return 1;
        if(n == 2) return 2;
        int n1 = 1,n2 = 2;
        int temp;
        for (int i = 3; i < n+1; i++) {
            temp = n2;
            n2 = n1 + n2;
            n1 = temp;
        }
        return n2;
    }


    /**
     * 746 使用最小花费爬楼梯
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int dp[] = new int[cost.length+1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i < cost.length+1 ; i++ ) {
            dp[i] = Math.min(dp[i-1] + cost[i-1],dp[i-2]+cost[i-2]);
        }
        return dp[cost.length];
    }


    /**
     * 62 不同路径
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] paths = new int[m][n];
        for (int i = 0; i < m; i++) {
            paths[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            paths[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                paths[i][j] = paths[i-1][j] + paths[i][j-1];
            }
        }
        return paths[m-1][n-1];
    }


    /**
     * 63 不同路径 II
     * @param obstacleGrid
     * @return
     */
    @Test
    public void test_63(){
        int[][] a = {{0,0,0},{0,1,0},{0,0,0}};
        uniquePathsWithObstacles(a);
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        dp[0][0] = 1;
        for (int i = 0; i < obstacleGrid.length; i++) {
            for (int j = 0; j < obstacleGrid[0].length; j++) {
                if(i ==0 && j==0 && obstacleGrid[0][0] != 1) continue;
                int valueLeft = (j-1 >= 0) ? dp[i][j-1] : 0;
                int valueUp = (i-1 >= 0) ? dp[i-1][j] : 0;
                dp[i][j] = obstacleGrid[i][j] == 1 ? 0 : valueLeft + valueUp;
            }
        }
        return dp[obstacleGrid.length-1][obstacleGrid[0].length-1];
    }


    /**
     * 343 整数拆分
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        if(n == 2) return 1;
        if(n == 3) return 2;
        if(n % 3 == 0) return (int) Math.pow(3,n/3);
        else if(n % 3 == 1) return ((int) Math.pow(3,(n/3)-1))*4;
        else return ((int) Math.pow(3,n/3)) * 2;
    }


    /**
     * 96 不同的二叉搜索树
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[1] =1 ; dp[0] = 1;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j < i + 1; j++) {
                dp[i] += dp[j-1]*dp[n-j];
            }
        }
        return dp[n];
    }

    /**
     * 416 分割等和子集
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        if(nums.length < 2) return false;
        int sum = Arrays.stream(nums).sum();
        if(sum % 2 != 0) return false;
        int target = sum / 2;
        boolean[][] dp = new boolean[nums.length][target + 1];
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true;
        }
        if(nums[0] <= target) {
            dp[0][nums[0]] = true;
        }else return false;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j < target + 1 ; j++) {
                if(nums[i] <= j){
                    dp[i][j] = dp[i-1][j] || dp[i-1][j - nums[i]];
                }else dp[i][j] = dp[i-1][j];
            }
        }
        return dp[nums.length - 1][target];
    }


    /**
     * 1049 最后一块石头的重量
     * @param stones
     * @return
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = Arrays.stream(stones).sum();
        int target = sum / 2;
        int[][] dp = new int[stones.length][target+1];
        for (int i = 0; i < target + 1; i++) {
            dp[0][i] = i >= stones[0]? stones[0] : 0 ;
        }
        for (int i = 1; i < stones.length; i++) {
            for (int j = 1; j < target+1; j++) {
                if(j < stones[i] || dp[i-1][j-stones[i]]+stones[i] > j || j - dp[i-1][j] < j - (dp[i-1][j-stones[i]]+stones[i])){
                    dp[i][j] = dp[i-1][j];
                }else dp[i][j] = dp[i-1][j-stones[i]] + stones[i];
            }
        }
        return sum - dp[stones.length-1][target] - dp[stones.length-1][target];
    }


    /**
     * 494 目标和
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = Arrays.stream(nums).sum();
        if(Math.abs(target) > sum) return 0;
        int[][] dp = new int[nums.length][sum + target + 1];
        dp[0][0] = 1;
        if(2*nums[0] < sum+target+1) dp[0][2*nums[0]] = 1;
        if(nums[0] == 0){
            dp[0][0] *= 2;
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < sum + target + 1; j++) {
                if(nums[i] == 0) dp[i][j] = 2*dp[i-1][j];
                else if(2*nums[i] > j) dp[i][j] = dp[i-1][j];
                else dp[i][j] = dp[i-1][j] + dp[i-1][j-2*nums[i]];
            }
        }
        return dp[nums.length - 1][sum + target];
    }


    /**
     * 474 一和零
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        if(m == 0 && n == 0) return 0;
        class element{
            int number;
            int zero;
            int one;
            public element() {
                this.number = 0;
                this.zero = 0;
                this.one = 0;
            }

            public element(int number, int zero, int one) {
                this.number = number;
                this.zero = zero;
                this.one = one;
            }
            public int getNumber() {
                return number;
            }
            public int getZero() {
                return zero;
            }

            public int getOne() {
                return one;
            }
        }
        int zero0 = (int) strs[0].chars().filter(e -> e == '0').count();
        int one0 = (int) strs[0].chars().filter(e -> e == '1').count();
        element[][] dp = new element[m+1][n+1];
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if(i < zero0 || j < one0) dp[i][j] = new element();
                else dp[i][j] = new element(1,zero0,one0);
            }
        }
        for (int i = 1; i < strs.length; i++) {
            int zero = (int) strs[i].chars().filter(e -> e == '0').count();
            int one = (int) strs[i].chars().filter(e -> e == '1').count();
            for (int j = m; j >= 0 ; j--) {
                for (int k = n; k >= 0; k--) {
                    if(zero > j || one > k) continue;
                    else if(dp[j-zero][k-one].getNumber()+1 > dp[j][k].getNumber()){
                        element temp = dp[j-zero][k-one];
                        dp[j][k] = new element(temp.getNumber()+1,temp.getZero()+zero,temp.getOne()+one);
                    }
                }
            }
        }
        return dp[m][n].getNumber();
    }


    /**
     * 518: 零钱兑换II
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length][amount+1];
        dp[0][0] = 1;
        for (int j = 1; j < amount + 1; j++) {
            if(j % coins[0] == 0) dp[0][j] = 1;
            else dp[0][j] = 0;
        }
        for (int i = 1; i < coins.length; i++) {
            for (int j = 0; j < amount + 1; j++) {
                int k = j/coins[i];
                dp[i][j] = dp[i-1][j];
                if(k == 0) continue;
                else {
                    for (int l = 1; l <= k; l++) {
                        dp[i][j] += dp[i-1][j - l*coins[i]];
                    }
                }
            }
        }
        return dp[coins.length-1][amount];
    }


    /**
     * 377: 组合总和 IV
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        int dp[] = new int[target+1];
        dp[0] = 1;
        for (int i = 0; i < target + 1; i++) {
            for (int j = 0; j < nums.length; j++) {
                if(i>=nums[j]) dp[i] += dp[i - nums[j]];
            }
        }
        return dp[target];
    }


    /**
     * 322 零钱兑换
     * @param coins
     * @param amount
     * @return
     */
    @Test
    public void test_322(){
        int[] coins = {1, 2, 5};
        coinChange(coins,11);
    }
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp,-1);
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i] ; j < amount + 1; j++) {
                if(dp[j - coins[i]] == -1) dp[j] = dp[j];
                else if(dp[j] == -1) dp[j] = dp[j - coins[i]]+1;
                else dp[j] = Math.min(dp[j],dp[j - coins[i]]+1);
            }
        }
        return dp[amount];
    }


    /**
     * 279: 完全平方数
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i*i <= n ; i++) {
            for (int j = i*i; j < n + 1; j++) {
                dp[j] = Math.min(dp[j],dp[j-(i*i)]+1);
            }
        }
        return dp[n];
    }


    /**
     * 139: 单词拆分
     * @param s
     * @param wordDict
     * @return
     */
    @Test
    public void test_139(){
        String s = "leetcode";
        List<String> strings = new ArrayList<>();
        strings.add("leet");
        strings.add("code");
        wordBreak(s,strings);
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int j = 1; j < s.length() + 1; j++) {
            for (int i = 0; i < wordDict.size(); i++) {
                if(j>=wordDict.get(i).length()){
                    dp[j] = (dp[j-wordDict.get(i).length()] && wordDict.get(i).equals(s.substring(j-wordDict.get(i).length(),j))) || dp[j];
                }
            }
        }
        return dp[s.length()];
    }


}
