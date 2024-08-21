package com.leetocde.greedy;/**
 * @author zhf
 * @date 2024/1/21 19:38
 * @version 1.0
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：贪心算法
 * @date 2024/1/21 19:38
 **/
@SuppressWarnings("all")
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


    /**
     * 860：柠檬水找零
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        int money[] = new int[2];
        for (int i = 0; i < bills.length; i++) {
            if(bills[i] == 5) money[0]++;
            if(bills[i] == 10) {
                money[1]++;
                if (money[0] >= 1) {
                    money[0]--;
                }else return false;
            }
            if(bills[i] == 20){
                if(money[1] >= 1 && money[0] >= 1){
                    money[0]--;
                    money[1]--;
                }else if(money[0] >= 3){
                    money[0] -= 3;
                }else return false;
            }
        }
        return true;
    }


    /**
     * 406:根据身高重建队列
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] != o2[0]){
                    return o1[0] - o2[0];
                }else return o2[1] - o1[1];
            }
        });
        int[][] ret = new int[people.length][];
        for (int[] person : people) {
            int space = person[1];
            for (int i = 0; i < people.length; i++) {
                if(ret[i] == null) {
                    if(space == 0){
                        ret[i] = person;
                        break;
                    }
                    space--;
                }
            }
        }
        return ret;
    }

    /**
     * 452：用最少数量的箭引爆气球
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        if(points.length < 1) return 0;
        Arrays.sort(points,Comparator.comparingInt(e -> e[0]));
        int count = 0;
        int[] used = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            if(used[i] == 1) continue;
            count++;
            int[] arrows = points[i];
            for (int j = i+1; j < points.length; j++) {
                if(points[j][0] <= arrows[1]){
                    arrows = getIntersection(arrows,points[j]);
                    used[j] = 1;
                }
            }
        }
        return count;
    }

    private int[] getIntersection(int[] arrows, int[] point) {
        arrows[0] = point[0];
        arrows[1] = Math.min(arrows[1],point[1]);
        return arrows;
    }

    public int findMinArrowShots_1(int[][] points) {
        if(points.length < 1) return 0;
        Arrays.sort(points,Comparator.comparingInt(e -> e[1]));
        int count = 1;
        int[] arrow = points[0];
        for (int[] point : points) {
            if(point[0] > arrow[1]){
                count++;
                arrow = point;
            }
        }
        return count;
    }


    /**
     * 435：无重叠区间
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(e -> e[1]));
        int count = 0;
        int[] temp = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] < temp[1]){
                count++;
            }else temp = intervals[i];
        }
        return count;
    }


    /**
     * 763:划分字母区间
     * @param s
     * @return
     */
    public List<Integer> partitionLabels(String s) {
        List<Integer> ret = new ArrayList<>();
        if(s.length() == 1) {ret.add(1); return ret;}
        char[] charList = s.toCharArray();
        int[] position = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char temp = charList[i];
            for (int j = s.length() - 1; j >= 0 ; j--) {
                if(charList[j] == temp) {
                    position[i] = j;
                    break;
                }
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if(position[i] != i){
                int max = position[i];
                for (int j = i+1; j <= max; j++) {
                    max = Math.max(position[j], max);
                }
                ret.add(max+1-i);
                i = max;
            }else ret.add(1);
        }
        return ret;
    }


    /**
     * 56:合并区间
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,Comparator.comparingInt(e -> e[0]));
        List<int[]> ret = new ArrayList<>();
        int[] temp = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] <= temp[1]){
                temp[0] = temp[0];
                temp[1] = Math.max(intervals[i][1],temp[1]);
            }else {
                ret.add(temp);
                temp = intervals[i];
            }
        }
        ret.add(temp);
        return ret.toArray(new int[0][]);
    }


    /**
     * 738：单调递增的数字
     * @param n
     * @return
     */
    public int monotoneIncreasingDigits(int n) {
        //处理输入
        int bitCount = String.valueOf(n).length();
        List<Integer> list = new ArrayList<>();
        for (int i = 0;i < bitCount; i++) {
            list.add(((int) (n / Math.pow(10 , i))) % 10);
        }
        //处理 要减一位 的特殊情况
        int valat = 0;
        for (int j = 0; j < list.size() - 1 ; j++) {
            valat += list.get(j) * Math.pow(10,j);
        }
        if(list.get(list.size()-1) == 1 && valat == 0) return n-1;
        //从 百位 十位 个位 这个顺序递增
        int temp = 0;
        for (int j = 1; j < list.size(); j++) {
            if(list.get(j) > list.get(j-1)){
                list.set(j,list.get(j)-1);
                temp = j;
            }
        }
        for (int j = temp - 1 ; j >= 0 ; j--) {
            list.set(j,9);
        }
        //结果求和
        int ret = 0;
        for (int j = 0; j < list.size(); j++) {
            ret += list.get(j) * Math.pow(10,j);
        }
        return ret;
    }


    /**
     * 968:监控二叉树
     * @param root
     * @return
     */
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

     int ret_968 = 0;
     public int minCameraCover(TreeNode root) {
         if(traversal(root) == 0) ret_968++;
         return ret_968;
    }

    // 后序遍历： 左右中

    /**
     * 0：无覆盖
     * 1：有摄像头
     * 2：有覆盖
     * @param root
     * @return
     */
    int traversal(TreeNode root){
        if(root == null) return 2;
        int left = traversal(root.left);
        int right = traversal(root.right);
        if(left == 2 && right == 2 ) return 0;
        else if((left == 1 && right == 1)||(left == 2 && right == 1) ||(left == 1 && right == 2)) return 2;
        else{
            ret_968++;
            return 1;
        }
    }





}
