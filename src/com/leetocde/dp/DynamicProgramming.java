package com.leetocde.dp;/**
 * @author zhf
 * @date 2024/2/24 10:37
 * @version 1.0
 */

import org.junit.Test;

import java.util.*;

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


    /**
     * 198: 打家劫舍
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if(nums.length == 1) return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[nums.length-1];
    }

    /**
     * 213: 打家劫舍II
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.max(nums[0],nums[1]);
        return Math.max(rob(Arrays.copyOfRange(nums,0,nums.length-1)),rob(Arrays.copyOfRange(nums,1,nums.length)));
    }

     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode() {}
         TreeNode(int val) { this.val = val; }
         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
     }

    /**
     * 337:打家劫舍III
     * @param root
     * @return
     */
    Map<TreeNode, Integer> f = new HashMap<TreeNode, Integer>();
    Map<TreeNode, Integer> g = new HashMap<TreeNode, Integer>();
    public int rob3(TreeNode root) {
        dfs(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        dfs(node.right);
        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }


    /**
     * 121: 买卖股票的最佳时机
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if(prices.length == 1) return 0;
        int dp = Math.max(prices[1] - prices[0],0);
        int min = Math.min(prices[0],prices[1]);
        for (int i = 2; i < prices.length; i++) {
            dp = Math.max(prices[i] - min,dp);
            min = Math.min(min,prices[i]);
        }
        return dp;
    }

    /**
     * 123: 买卖股票的最佳时机III
     * 用了全力优化，可是最后一个测试用例还是过不了
     * @param prices
     * @return
     */
    public int maxProfitIII(int[] prices) {
        if(prices.length == 1) return 0;
        int dp = maxProfit(prices);
        int min = Math.min(prices[0],prices[1]);
        int temp = Math.max(prices[1] - prices[0],0);
        if(prices.length == 2) return temp;
        for (int i = 2; i < prices.length; i++) {
            if(prices[i-1] - min >= temp) {
                temp = prices[i-1] - min;
                dp = Math.max((temp + maxProfit(Arrays.copyOfRange(prices, i, prices.length))),dp);
            }else min = Math.min(min,prices[i-1]);
        }
        return dp;
    }

    public int maxProfitIII2(int[] prices) {
        int n = prices.length;
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < n; ++i) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }


    public int maxProfitIV(int k, int[] prices) {
        if(prices.length == 1) return 0;
        int[] dp = new int[prices.length];
        dp[0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i] = Math.max(dp[i-1],-prices[i]);
        }
        for (int i = 1; i < 2 * k; i++) {
            dp[0] = i % 2 == 0? -prices[0] : 0 ;
            for (int j = 1; j < prices.length; j++) {
                dp[j] = i % 2 == 0? Math.max(dp[j-1],dp[j] - prices[j]) : Math.max(dp[j-1],dp[j]+prices[j]);
            }
        }
        return dp[prices.length-1];
    }

    /**
     * 309: 买卖股票的最佳时机含冷冻期
     * @param prices
     * @return
     */
    public int maxProfitFrozen(int[] prices) {
        if(prices.length == 1) return 0;
        if(prices.length == 2) return Math.max(0,prices[1] - prices[0]);
        int[] bug = new int[prices.length];
        int[] sell = new int[prices.length];
        bug[0] = - prices[0];bug[1] = Math.max(- prices[0],- prices[1]);
        sell[0] = 0; sell[1] = Math.max(0,prices[1] - prices[0]);
        for (int i = 2; i < prices.length; i++) {
            bug[i] = Math.max(sell[i-2] - prices[i],bug[i-1]);
            sell[i] = Math.max(sell[i-1],bug[i]+prices[i]);
        }
        return sell[prices.length-1];
    }


    /**
     * 714: 买卖股票的最佳时机含手续费
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfitCommission(int[] prices, int fee) {
        if(prices.length == 1) return 0;
        int buy = -prices[0];
        int sell = 0;
        for (int i = 1; i < prices.length; i++) {
            buy = Math.max(sell - prices[i],buy);
            sell = Math.max(sell,buy + prices[i] - fee);
        }
        return sell;
    }


    /**
     * 300: 最长递增子序列
     * @param nums
     * @return
     */
    @Test
    public void test_300(){
        int[] nums = new int[]{1,3,6,7,9,4,10,5,6};
        lengthOfLIS(nums);
    }
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 1) return 1;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = i-1; j >= 0 ; j--) {
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }


    /**
     * 674: 最长连续递增序列
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        if(nums.length == 1) return 1;
        int max = 0,temp = 1;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] > nums[i-1]) temp++;
            else {
                temp = 1;
            }
            max = Math.max(temp,max);
        }
        return max;
    }

    /**
     * 718: 最长重复子数组
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLength(int[] nums1, int[] nums2) {
        int max = 0 , temp = 0 ;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if(nums1[i] == nums2[j]){
                    temp++;
                    for (int m = i+1, n = j+1; m<nums1.length && n<nums2.length && nums1[m] == nums2[n] ; m++,n++) {
                        temp++;
                    }
                    max = Math.max(temp,max);
                    temp = 0;
                }
            }
        }
        return max;
    }

    // 动态规划的解法：对于dp的定义：最长公共前缀长

    public int findLength2(int[] A, int[] B) {
        int n = A.length, m = B.length;
        int[][] dp = new int[n + 1][m + 1];
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                dp[i][j] = A[i] == B[j] ? dp[i + 1][j + 1] + 1 : 0;
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }


    /**
     * 1143：最长公共子序列
     * @param text1
     * @param text2
     * @return
     */
    @Test
    public void test_1143(){
        longestCommonSubsequence("abcba","abcbcba");
    }
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()+1][text2.length()+1];
        for (int i = 1; i < text1.length() + 1; i++) {
            for (int j = 1; j < text2.length() + 1; j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1)) dp[i][j] = dp[i-1][j-1]+1;
                else dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }

        return dp[text1.length()][text2.length()];
    }


    /**
     * 1035: 不相交的线
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length+1][nums2.length+1];
        for (int i = 1; i < nums1.length + 1; i++) {
            for (int j = 1; j < nums2.length + 1; j++) {
                if(nums1[i-1] == nums2[j-1]) dp[i][j] = dp[i-1][j-1]+1;
                else dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        return dp[nums1.length][nums2.length];
    }


    /**
     * 392: 判断子序列
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        if(s.length() == 0) return true;
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            for (; j < t.length(); j++) {
                if(s.charAt(i) == t.charAt(j)) {
                    if(i == s.length()-1 ) return true;
                    j++;
                    break;
                }
            }
        }
        return false;
    }


    /**
     * 115: 不同的子序列
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        int[][] dp = new int[t.length()+1][s.length()+1];
        for (int i = 0; i < s.length() + 1; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < t.length() + 1; i++) {
            for (int j = 1; j < s.length() + 1; j++) {
                if(s.charAt(j-1) == t.charAt(i-1)) dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
                else dp[i][j] = dp[i][j-1];
            }
        }
        return dp[t.length()][s.length()];
    }


    /**
     * 583: 两个字符串的删除操作
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for (int i = 1; i < word1.length() + 1; i++) {
            for (int j = 1; j < word2.length() + 1; j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)) dp[i][j] = dp[i-1][j-1]+1;
                else dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }

        return word1.length()+word2.length()-2*dp[word1.length()][word2.length()];
    }


    /**
     * 72: 编辑距离
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance_72(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for (int i = 0; i < word1.length() + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < word2.length() + 1; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i < word1.length() + 1; i++) {
            for (int j = 1; j < word2.length() + 1; j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else dp[i][j] = Math.min(dp[i-1][j-1]+1 ,Math.min(dp[i-1][j]+1,dp[i][j-1]+1));
            }
        }
        return dp[word1.length()][word2.length()];
    }


    /**
     * 647:回文子串
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int[] dp = new int[s.length()];
        dp[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            dp[i] = dp[i-1] + 1;
            for (int j = 0; j < i; j++) {
                dp[i] += isPalindrome(s.substring( j, i+1));
            }
        }
        return dp[s.length()-1];
    }

    private int isPalindrome(String s){
        int i = s.length()/2;
        for (int j = 0; j < i; j++) {
            if(s.charAt(j) != s.charAt(s.length()-1-j)) return 0;
        }
        return 1;
    }
    // 动态规划

    public int countSubstrings2(String s) {
        int[][] dp = new int[s.length()][s.length()];
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
            count += dp[i][i];
        }
        for (int i = s.length()-2; i >= 0; i--) {
            for (int j = i+1; j < s.length(); j++) {
                if(j-1 == i){
                    dp[i][j] = (s.charAt(i) == s.charAt(j)) ? 1:0;
                }else dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i+1][j-1] == 1) ? 1:0;
                count += dp[i][j];
            }
        }
        return count;
    }


    /**
     * 516: 最长回文子序列
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }
        for (int i = s.length()-2; i >= 0; i--) {
            for (int j = i+1; j < s.length(); j++) {
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1] + 2;
                }else dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
            }
        }
        return dp[0][s.length()-1];
    }



}
