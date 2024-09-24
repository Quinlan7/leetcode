package com.interview;

import java.io.*;
import java.util.*;

public class PDD {
    public static void main1(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] strFir = reader.readLine().split(" ");
        int N = Integer.parseInt(strFir[0]);
        int M = Integer.parseInt(strFir[1]);
        List<String> priStr = new ArrayList<>();
        while(N-- > 0){
            String temp = reader.readLine();
            priStr.add(temp);

        }
        Collections.sort(priStr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                boolean s1ConPDD = o1.contains("PDD");
                boolean s2ConPDD = o2.contains("PDD");
                if(s1ConPDD && !s2ConPDD){
                    return -1;
                }else if(!s1ConPDD && s2ConPDD){
                    return 1;
                }
                return o1.compareTo(o2);
            }
        });
        for (int i = 0; i < M; i++) {
            writer.write(priStr.get(i) + "\n");
        }
        writer.flush();
        writer.close();
        reader.close();
    }

    public static void main2(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(reader.readLine());
        while(T-- > 0){
            int n = Integer.parseInt(reader.readLine());
            long[] a = new long[n];
            String[] temp = reader.readLine().split(" ");
            long sum = 0;
            for (int i = 0; i < n; i++) {
                a[i] = Long.parseLong(temp[i]);
                sum += a[i];
            }
            if((2 * sum) % n != 0){
                writer.write(0 + "\n");
                continue;
            }
            long target = 2 * (sum / n);
            Map<Long, Integer> countMap = new HashMap<>();
            long ret = 0;
            for (long num : a) {
                countMap.put(num, countMap.getOrDefault(num,0) + 1);
            }
            for (long num : a) {
                long complement = target - num;
                if(countMap.containsKey(complement)){
                    ret += countMap.get(complement);
                    if(complement == num) ret--;
                }
            }
            writer.write(ret + "\n");
        }

        writer.flush();
        writer.close();
        reader.close();
    }

    public static void main3(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(reader.readLine());
        while(T-- > 0){

            writer.write("yes" + "\n");
        }

        writer.flush();
        writer.close();
        reader.close();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(reader.readLine());
        while(T-- > 0){
            int n = Integer.parseInt(reader.readLine());
            int[] Pi = new int[n];
            int score = 0;
            int cost = 0;
            int[] free = new int[n];
            for (int i = 0; i < n; i++) {
                Pi[i] = Integer.parseInt(reader.readLine());
            }
            int[] dp = new int[n];
            dp[0] = Pi[0];
            for (int i = 0; i < n; i++) {
                if(free[i] != 0){

                }
            }


            writer.write(cost + "\n");
        }

        writer.flush();
        writer.close();
        reader.close();
    }
}
