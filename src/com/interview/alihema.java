package com.interview;

import java.io.*;

public class alihema {
    public static void main1(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(reader.readLine());
        while(T-- > 0){
            String[] temp = reader.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);
            int c = Integer.parseInt(temp[2]);
            int d = Integer.parseInt(temp[3]);
            int min = Math.min(a,Math.min(b,Math.min(c,d)));
            boolean flag = false;
            for (int i = 2; i <= Math.sqrt(min); i++) {
                if(a % i ==0 && b % i == 0 && c % i ==0 && d % i == 0){
                    writer.write(i + "\n");
                    flag = true;
                    break;
                }
            }
            for (int i = (int) Math.sqrt(min); i > 1 ; i--) {
                if(a % min ==0 && b % min == 0 && c % min ==0 && d % min == 0){
                    writer.write(min + "\n");
                    flag = true;
                    break;
                }
            }
            if(!flag) writer.write("-1" + "\n");
        }

        writer.flush();
        writer.close();
        reader.close();
    }


    public static void main2(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String target = "AcMer";
        int minCost = Integer.MAX_VALUE;
        String input = reader.readLine();
        for (int i = 0; i <= input.length() - 5; i++) {
            int cost = 0;
            for (int j = 0; j < 5; j++) {
                char currentChar = input.charAt(i + j);
                char targetChar = target.charAt(j);
                if(currentChar == targetChar){
                    continue;
                }
                if(Character.toLowerCase(currentChar) == Character.toLowerCase(targetChar)){
                    cost+=5;
                }else if(Character.isLowerCase(currentChar) && Character.isUpperCase(targetChar)){
                    cost+=10;
                }else if(Character.isLowerCase(targetChar) && Character.isUpperCase(currentChar)){
                    cost += 10;
                }else {
                    cost += 5;
                }
            }
            minCost = Math.min(minCost,cost);
        }
        writer.write(minCost + "\n");

        writer.flush();
        writer.close();
        reader.close();
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] temp = reader.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int k = Integer.parseInt(temp[1]);
        int[][] dp = new int[n + 1][k + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = 0;
                for (int t = 0; t <= Math.min(j , i -1); t++) {
                    dp[i][j] += dp[i - 1][j - t];
                }
            }
        }
        int ret = 0;
        for (int i = 0; i <= k; i++) {
            ret += dp[n][i];
        }
        writer.write(ret + "\n");
        writer.flush();
        writer.close();
        reader.close();
    }
}
