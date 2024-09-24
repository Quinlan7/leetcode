package com.interview;

import java.io.*;
import java.util.*;

public class BD {
    public static void main1(String[] args)  throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(reader.readLine());
        int m = n;
        Map<Integer,Integer> map = new HashMap<>();
        int ret = 0;
        int[] end = new int[n - 1];
        int i = 0;
        while(n-- > 1){
            String[] temp = reader.readLine().split(" ");
            int u = Integer.parseInt(temp[0]);
            int v = Integer.parseInt(temp[1]);
            end[i++] = v;
            int countStart = map.getOrDefault(u,0);
            ret += countStart;
            map.put(u,countStart + 1);
        }
        for (int j = 0; j < m - 1; j++) {
            ret += map.getOrDefault(end[j],0);
        }

        writer.write(ret + "\n");
        writer.flush();
        writer.close();
        reader.close();
    }

    public static void main3(String[] args)  throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(reader.readLine());
        String s = reader.readLine();;
        char[] chars = s.toCharArray();
        int op = 0;
        for (int i = 1; i < n; i++) {
            while(chars[i] == chars[i - 1]){
                chars[i] = shiftRight(chars[i]);
                op++;
            }
        }
        int tmp = op;
        op = 0;
        char[] tmpChars = s.toCharArray();
        for (int i = n - 2; i >= 0 ; i--) {
            while(tmpChars[i] == tmpChars[i + 1]){
                tmpChars[i] = shiftRight(tmpChars[i]);
                op++;
            }
        }
        int ret = Math.min(op,tmp);
        writer.write(ret + "\n");
        writer.flush();
        writer.close();
        reader.close();
    }

    private static char shiftRight(char c){
        return c == 'z' ? 'a' : (char)(c + 1);
    }

    public static void main2(String[] args)  throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] strFir = reader.readLine().split(" ");
        int n = Integer.parseInt(strFir[0]);
        int q = Integer.parseInt(strFir[1]);
        String[] strSec = reader.readLine().split(" ");
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(strSec[i]);
        }
        while(q-- > 0){
            String[] temp = reader.readLine().split(" ");
            int minL = Integer.parseInt(temp[0]);
            int maxL = Integer.parseInt(temp[1]);
            int sum = 0;
            int l = 0,r = 0;
            int max = 0;
            while(r - l  < maxL && r < n){
                sum += a[r];
                r++;
                if(r-l >= minL && r-l <= maxL ) max = Math.max(max,sum);
                while(r-l > minL && a[l] < 0){
                    sum -= a[l];
                    max = Math.max(max,sum);
                    l++;
                }
                if(r - l  == maxL){
                    sum -= a[l];
                    l++;
                    if(r-l >= minL && r-l <= maxL ) max = Math.max(max,sum);
                }
            }
            writer.write(max + "\n");
        }
        writer.flush();
        writer.close();
        reader.close();
    }


    static final long MOD = 1000000007;
    static long[] dp;
    static long[] tree;
    static class Node{
        long val;
        int id;
        public Node(long val, int id){
            this.val = val;
            this.id = id;
        }
    }

    static long query(int l, int r,int n){
        l += n - 1;
        r += n - 1;
        long sum = 0;
        while(l <= r){
            if((l & 1) == 1){
                sum = (sum + tree[l]) % MOD;
                l++;
            }
            if((r & 1) == 0){
                sum = (sum + tree[r]) % MOD;
                r--;
            }
            l >>= 1;
            r >>= 1;
        }
        return sum;
    }

    static void update(int index, long value, int n){
        index += n - 1;
        tree[index] = (tree[index] + value) % MOD;
        while(index > 1){
            index >>= 1;
            tree[index] = (tree[index << 1] + tree[index << 1 | 1]) % MOD;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Node[] a = new Node[n];
        for (int i = n - 1; i >= 0; i--) {
            a[i] = new Node(sc.nextLong(), i);
        }
        Arrays.sort(a, Comparator.comparingLong(o -> o.val));
        int node = 1;
        int[] c = new int[n];
        c[a[0].id] = node;
        for (int i = 1; i < n; i++) {
            if(a[i].val != a[i-1].val){
                node++;
            }
            c[a[i].id] = node;
        }
        dp = new long[node + 1];
        tree = new long[node * 2];
        long ret = 0;
        for (int i = 0; i < n; i++) {
            long now = query(1,c[i] - 1, node);
            update(c[i], now + 1 - dp[c[i]], node);
            dp[c[i]] = (now + 1) % MOD;
        }
        for (int i = 1 ; i <= node ; i++) {
            ret = (ret + dp[i] - 1 + MOD) % MOD;
        }
        System.out.println(ret + n);
        sc.close();
    }
}
