package com.interview;

import java.io.*;
import java.util.HashSet;

public class Main2 {
    public static void main2(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(reader.readLine());
        String str = reader.readLine();
        int count = 0;
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '0') count++;
        }
        for (int i = 3; i <= str.length(); i = i + 2) {
            for (int j = 0; j < str.length() - i + 1; j++) {
                if(set.contains(str.substring(j,j+i))){
                    count++;
                }else if(isWeightJiShu(str.substring(j,j+i))){
                    set.add(str.substring(j,j+i));
                    count++;
                }
            }
        }
        writer.write(count + "\n");

        reader.close();
        writer.flush();
        writer.close();
    }

    private static boolean isWeightJiShu(String s){
        int count  = 0;
        for (int i = s.length() - 2; i >= 0 ; i--) {
            if(s.charAt(i) != s.charAt(i+1)) count++;
        }
        if(s.charAt(s.length() - 1) == '0') count++;
        return count % 2 == 1;
    }

    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] strFir = reader.readLine().split(" ");
        int n = Integer.parseInt(strFir[0]);
        int m = Integer.parseInt(strFir[1]);
        int k = Integer.parseInt(strFir[2]);
        countNumbers(n,m,k);
        writer.write(count + "\n");
        reader.close();
        writer.flush();
        writer.close();
    }

    private static void countNumbers(int n, int m, int k) {
        boolean[] used = new boolean[n + 1];
        backtrack(n,m,k,used,"");
    }

    private static void backtrack(int n, int m, int k, boolean[] used, String num) {
        if(num.length() > m){
            return;
        }
        if(num.length() == m && Integer.parseInt(num) > k){
            count++;
        }

        for (int i = 0; i <= n; i++) {
            if(num.equals("") && i == 0) continue;
            if(!used[i]){
                used[i] = true;
                backtrack(n,m,k,used,num + i);
                used[i] = false;
            }
        }
    }


    public static void main4(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] strFir = reader.readLine().split(" ");
        int n = Integer.parseInt(strFir[0]);
        int k = Integer.parseInt(strFir[1]);
        int sum = Integer.parseInt(strFir[2]);
        String[] strSec = reader.readLine().split(" ");
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(strSec[i - 1]);
        }
        int[] dp = new int[n+1];
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n ; i++) {
            prefixSum[i] = prefixSum[i-1] + a[i];
        }
        for (int i = 1; i <= n ; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;
        for (int i = 1; i <= n ; i++) {
            for (int j = Math.max(0,i-k); j < i ; j++) {
                int currentSum = prefixSum[i] - prefixSum[j];
                if(currentSum <= sum){
                    dp[i] = Math.min(dp[i],dp[j]);
                }else {
                    int over = currentSum - sum;
                    dp[i] = Math.min(dp[i],dp[j]+over);
                }
            }
        }

        writer.write(dp[n] + "\n");
        reader.close();
        writer.flush();
        writer.close();
    }
}
