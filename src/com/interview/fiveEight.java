package com.interview;

import java.util.ArrayList;
import java.util.List;

public class fiveEight {
    public static void main(String[] args) {

    }

    public int[][] findIntersection (int[][] firstList, int[][] secondList) {
        // write code here
        int i = 0,j = 0;
        int n1 = firstList.length,n2 = secondList.length;
        List<int[]> list = new ArrayList<>();
        while(i < n1 && j < n2){
            int start1 = firstList[i][0];
            int end1 = firstList[i][1];
            int start2 = secondList[j][0];
            int end2 = secondList[j][1];
            if(start1 <= end2 && start2 <= end1){
                int start = Math.max(start1,start2);
                int end = Math.min(end1,end2);
                list.add(new int[]{start,end});
            }
            if(end1 < end2){
                i++;
            }else {
                j++;
            }

        }

        int[][] ret = new int[list.size()][2];
        int k = 0;
        for (int[] num : list) {
            ret[k++] = num;
        }
        return  ret;
    }

    public int numberOfWays (int startPos, int endPos, int k) {
        // write code here
        final int MOD = 1000000007;
        int max = Math.max(startPos,endPos) + k;
        int[][] dp = new int[k + 1][2 * max + 1];

        dp[0][startPos] = 1;
        for (int i = 1; i <= k ; i++) {
            for (int j = 0; j < dp[i - 1].length; j++) {
                if(dp[i - 1][j] > 0){
                    if(j - 1 >= 0){
                        dp[i][j - 1] = (dp[i][j - 1] + dp[i - 1][j]) % MOD;
                    }
                    if(j + 1 < dp[i].length){
                        dp[i][j + 1] = (dp[i][j + 1] + dp[i - 1][j]) % MOD;
                    }
                }
            }
        }
        return dp[k][endPos];
    }


    public int StringSplit (String str) {
        // write code here
        int n = str.length();
        int[] total = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            if(str.charAt(i) == 'b'){
                total[i]++;
            }
            if(i != n - 1) total[i] += total[i + 1];
        }
        int totalA = 0, totalB = 0,max = 0;
        for (int i = 0; i < n - 1; i++) {
            if(str.charAt(i) == 'a') totalA++;
            else totalB++;
            max = Math.max(max,totalA + total[i] - totalB);
        }
        return max;
    }
}
