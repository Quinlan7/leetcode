package com.interview;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;

public class QuNa {
    public static void main1 (String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(reader.readLine());
        String[] str = reader.readLine().split(" ");

        Arrays.sort(str, (s1,s2) -> (s1 + s2).compareTo(s2 + s1));
        for (int i = 0; i < n; i++) {
            if(i != 0){
                writer.write(" ");
            }
            writer.write(str[i]);
        }
        writer.flush();
        writer.close();
        reader.close();
    }

    public static void main2 (String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] strFirst = reader.readLine().split(" ");
        int n = Integer.parseInt(strFirst[0]);
        long m = Long.parseLong(strFirst[1]);
        String[] strA = reader.readLine().split(" ");
        String[] strB = reader.readLine().split(" ");
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(strA[i]);
            b[i] = Integer.parseInt(strB[i]);
        }
        int left = 1, right = n + 1;
        while(left < right){
            int mid = (left + right) / 2;
            if(check(mid , a, b, n, m)){
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        if(left > n){
            writer.write("-1");
        }else {
            writer.write(left);
        }
        writer.flush();
        writer.close();
        reader.close();
    }

    private static boolean check(int len, int[] a,int[] b,int n,long m){
        PriorityQueue<Integer> queue1 = new PriorityQueue<>();
        PriorityQueue<Integer> queue2 = new PriorityQueue<>();
        for (int i = 0; i < len; i++) {
            queue1.add(a[i]);
            queue2.add(b[i]);
        }
        long ret = 0;
        for (int i = 0; i < len; i++) {
            ret += (long)queue1.poll() * queue2.poll();
        }
        return ret >= m;
    }


    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(reader.readLine());
        String str = reader.readLine();
        int[] cnt = new int[10];
        int ret = 0;
        int r = 0;
        while(r < n){
            cnt[str.charAt(r) - '0']++;
            if(check2(cnt)){
                ret++;
                Arrays.fill(cnt,0);
            }
            r++;
        }

        writer.write(ret + "\n");

        writer.flush();
        writer.close();
        reader.close();
    }

    private static boolean check2(int[] cnt){
        for (int i = 0; i < 10; i++) {
            if(cnt[i] == 0) continue;
            if(cnt[i] % 2 == 1) return false;
        }
        return true;
    }
}
