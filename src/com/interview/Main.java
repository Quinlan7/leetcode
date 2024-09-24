package com.interview;

import java.util.*;
import java.io.*;

public class Main {


    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] strFirst = reader.readLine().split(" ");
        int T = Integer.parseInt(strFirst[1]);
        int n = Integer.parseInt(strFirst[0]);
        int[] a = new int[n + 1];
        int[] b = new int[n + 1];
        int[] c = new int[n + 1];
        String[] strSec = reader.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(strSec[(i-1)*3]);
            b[i] = Integer.parseInt(strSec[(i-1)*3 + 1]);
            c[i] = Integer.parseInt(strSec[(i-1)*3 + 2]);
        }
        int[][] dp = new int[n+1][T+1];
        for (int[] row : dp) {
            Arrays.fill(row,-1);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n ; i++) {
            for (int j = 0; j <= T ; j++) {
                if(dp[i-1][j] == -1) continue;
                if(j + a[i] <= T){
                    dp[i][j + a[i]] = Math.max(dp[i][j + a[i]],dp[i-1][j] + b[i]);
                }
                int new_j = Math.max(j - c[i], 0 );
                dp[i][new_j] = Math.max(dp[i][new_j] , dp[i-1][j]);
            }
        }
        int ans = Arrays.stream(dp[n]).max().getAsInt();
        writer.write(ans + "");
        writer.flush();
        reader.close();
        writer.close();


    }
    public static void main2(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(reader.readLine());
        int nums[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] temp = reader.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                nums[i][j] = Integer.parseInt(temp[j]);
            }
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int temp = nums[i][j];
                if(i - 1 >= 0){
                    temp += nums[i-1][j];
                }
                if(i+1 < n){
                    temp += nums[i+1][j];
                }
                if(j-1 >= 0){
                    temp += nums[i][j-1];
                }
                if(j+1 < n){
                    temp += nums[i][j + 1];
                }
                max = Math.max(max,temp);
            }
        }
        System.out.println(max);

        writer.flush();
        reader.close();
        writer.close();


    }
}
