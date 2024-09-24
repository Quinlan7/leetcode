package com.interview;

import java.io.*;
import java.util.Arrays;

public class Main1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] strFir = reader.readLine().split(" ");
        int n = Integer.parseInt(strFir[0]);
        int x = Integer.parseInt(strFir[1]);
        int[] a = new int[n];
        String[] strA = reader.readLine().split(" ");
        int sum = 0;
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(strA[i]);
            sum += a[i];
        }
        if(sum % x == 0 ){
            writer.write("0"+"\n");
            writer.flush();
            return;
        }
        int[][] dp = new int[n + 1][x];
        for (int i = 0; i <= n ; i++) {
            dp[0][i] = Integer.MAX_VALUE;
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int mod = 0; mod < x; mod++) {
                dp[i][mod] = dp[i-1][(mod - a[i-1]%x + x )% x];

                dp[i][mod] = Math.min(dp[i][mod],dp[i-1][mod] == Integer.MAX_VALUE?Integer.MAX_VALUE:1+dp[i-1][mod]);
                int val = dp[i-1][(mod - (a[i-1] + 1) % x + x) % x];
                if(val != Integer.MAX_VALUE){
                    val += 1;
                }
                dp[i][mod] = Math.min(dp[i][mod], val);
            }
        }
        writer.write(dp[n][0]+"\n");



        writer.flush();
        writer.close();
        reader.close();
    }
}
