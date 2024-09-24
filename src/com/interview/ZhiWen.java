package com.interview;

import java.io.*;
import java.util.*;

public class ZhiWen {
    public static void main1(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(reader.readLine());
        int[] a = new int[n];
        String[] strSec = reader.readLine().split(" ");
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(strSec[i]);
            if(a[i] % 2 == 0){
                count1 += a[i] + 1;
            }else {
                count2 += a[i] + 1;
            }
        }
        int ret = Math.min(count1,count2);
        writer.write(ret + "\n");

        writer.flush();
        writer.close();
        reader.close();
    }



    public static void main3(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(reader.readLine());
        while(T-- > 0){
            int n = Integer.parseInt(reader.readLine());
            int[][] nums = new int[n-1][2];
            Map<Integer,Integer> SonMap = new HashMap<>();
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < n - 1; i++) {
                String[] temp = reader.readLine().split(" ");
                nums[i][0] = Integer.parseInt(temp[0]);
                nums[i][1] = Integer.parseInt(temp[1]);
                set.add(nums[i][1]);
                if(set.contains(nums[i][0])){
                    set.remove(nums[i][0]);
                }
                SonMap.put(nums[i][0],SonMap.getOrDefault(nums[i][0],0) +  1);
            }
            Map<Integer,Integer> SameMap = new HashMap<>();
            for (int count : SonMap.values() ) {
                SameMap.put(count,SameMap.getOrDefault(count,0) + 1);
            }
            int ret = 0;
            for (int count : SonMap.values() ) {
                ret += (count*(count - 1)) / 2;
            }
            //叶子节点
            ret += (set.size()*(set.size() - 1)) / 2;

            writer.write(ret + "\n");
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
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < n - 1; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < n - 1; i++) {
                String[] temp = reader.readLine().split(" ");
                int u = Integer.parseInt(temp[0]) - 1;
                int v = Integer.parseInt(temp[1]) - 1;
                graph.get(u).add(v);
                graph.get(v).add(u);
            }
            int [] memo = new int[n];
            Arrays.fill(memo,-1);
            dfs(0,-1,memo,graph);
            writer.write(calRes(memo) + "\n");
        }


        writer.flush();
        writer.close();
        reader.close();
    }

    private static int dfs(int node,int parent,int[] memo,List<List<Integer>> graph){
        if(memo[node] != -1) return memo[node];
        int res = 0;
        for (int next : graph.get(node)) {
            if(next == parent) continue;
            dfs(next,node,memo,graph);
            res++;
        }
        memo[node] = res;
        return res;
    }

    private static int calRes(int[] memo){
        Map<Integer,Integer> mp = new HashMap<>();
        for (int x : memo) {
            mp.put(x,mp.getOrDefault(x,0) + 1);
        }
        int res = 0;
        for (int v : mp.values()) {
            res += v * ( v -1 ) /2;
        }
        return res;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            int sum = scanner.nextInt();
            System.out.println(minOperations(n, k, sum));
        }
        scanner.close();
    }

    private static int minOperations(int n, int k, int sum) {
        int[] array = new int[n];
        // 初始化数组
        for (int i = 0; i < n; i++) {
            array[i] = i + 1;
        }
        // 计算每个滑动窗口的和，并找出超出 sum 的窗口
        int currentSum = 0;
        int count = 0;
        int l = 0, r = 0;
        while(r < array.length){
            currentSum += array[r];
            if(currentSum > sum){
                array[r] = array[r] - (currentSum - sum);
                count += currentSum - sum;
                currentSum = sum;
            }
            r++;
            if(r - l < k){
                continue;
            }
            currentSum -= array[l];
            l++;
        }
        return count;
    }
}
