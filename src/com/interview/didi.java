package com.interview;

import java.io.*;

public class didi {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] strFir = reader.readLine().split(" ");
        int n = Integer.parseInt(strFir[0]);
        int k = Integer.parseInt(strFir[1]);
        int[][] costs = new int[k][k];
        for (int i = 0; i < k; i++) {
            String[] strCost = reader.readLine().split(" ");
            for (int j = 0; j < k; j++) {
                costs[i][j] = Integer.parseInt(strCost[j]);
            }
        }
        String str = reader.readLine();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = str.charAt(i) - 'a';
        }
        int[][] dp =new int[n][n];
        for (int i = 0; i < n - 1; i++) {
            dp[i][i+1] = costs[arr[i]][arr[i+1]];
        }
        for (int i = n - 2; i >= 0 ; i--) {
            for (int j = i + 3; j < n; j = j + 2) {
                dp[i][j] = Math.max(dp[i][j-2] + dp[j-1][j],dp[i + 1][j - 1] + costs[arr[i]][arr[j]]);
                int condition1  = 0;
                if(j - i == 7){
                    condition1 = dp[i][i+3] + dp[i+4][j];
                    dp[i][j] = Math.max(condition1,dp[i][j]);
                }
            }
        }
        writer.write(dp[0][n-1]+ "\n");


        writer.flush();
        writer.close();
        reader.close();
    }
}
