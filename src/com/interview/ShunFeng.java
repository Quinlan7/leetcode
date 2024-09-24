package com.interview;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShunFeng {

    private static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] a = new int[6];
        String[] strFir = reader.readLine().split(" ");
        for (int i = 0; i < 6; i++) {
            a[i] = Integer.parseInt(strFir[i]);
        }
        int k = Integer.parseInt(reader.readLine());
        int[][] nums = new int[3][3];
        traverse(nums, k, 0 , 0, a);
        writer.write(count + "\n");
        writer.flush();
        writer.close();
        reader.close();
    }


    private static void traverse(int[][] nums, int k,int row ,int col,int[] a){
        if(row == 3){
            if(check(nums,k,a)){
                count++;
            }
            return;
        }
        int nextRow = (col == 2) ? row + 1:row;
        int nextCol = (col == 2) ? 0 : col + 1;
        for (int i = 0; i <= 3; i++) {
            nums[row][col] = i;
            traverse(nums,k,nextRow,nextCol,a);
        }
    }

    private static boolean check(int[][] nums,int k,int[] a){
        int ok = 0;
        if((nums[0][0] ^ nums[0][1] ^ nums[0][2]) == a[0]){
            ok++;
        }
        if((nums[1][0] ^ nums[1][1] ^ nums[1][2]) == a[1]){
            ok++;
        }
        if((nums[2][0] ^ nums[2][1] ^ nums[2][2]) == a[2]){
            ok++;
        }
        if((nums[0][0] ^ nums[1][0] ^ nums[2][0]) == a[3]){
            ok++;
        }
        if((nums[0][1] ^ nums[1][1] ^ nums[2][1]) == a[4]){
            ok++;
        }
        if((nums[0][2] ^ nums[1][2] ^ nums[2][2]) == a[5]){
            ok++;
        }

        if(ok == k) return true;
        return false;
    }

    public static void main1(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(reader.readLine());
        while(T-- > 0){
            String[] strFir = reader.readLine().split(" ");
            int K = Integer.parseInt(strFir[0]);
            int t = Integer.parseInt(strFir[1]);
            int N = Integer.parseInt(strFir[2]);
            int[] x = new int[N];
            int[] y = new int[N];
            String[] strSec = reader.readLine().split(" ");
            String[] strTir = reader.readLine().split(" ");
            for (int i = 0; i < N; i++) {
                x[i] = Integer.parseInt(strSec[i]);
                y[i] = Integer.parseInt(strTir[i]);
            }
            int nowPosition = 0;
            int resourceIndex = 0;
            while(t-- > 0){
                int nextPosition = nowPosition + K;
                //资源在这天走的路上，决定时候获取资源
                int maxStrp = -1;
                int maxResource = -1;
                // 确定获取哪一个资源
                while(resourceIndex < N && x[resourceIndex] <= nextPosition){
                    if(y[resourceIndex] * t >= (nextPosition - x[resourceIndex])){
                        int  step = y[resourceIndex] * t - (nextPosition - x[resourceIndex]);
                        if(step > maxStrp){
                            maxStrp = step;
                            maxResource = resourceIndex;

                        }
                    }
                    resourceIndex++;
                }
                //不获取资源
                if(maxResource == -1){
                    nowPosition = nextPosition;
                }else {
                    nowPosition = x[maxResource];
                    K += y[maxResource];
                    if(x[maxResource+1] == x[maxResource]){
                        maxResource++;
                    }
                    resourceIndex = maxResource + 1;
                }

            }

            writer.write(nowPosition + "\n");
        }

        writer.flush();
        writer.close();
        reader.close();
    }


    private static final int MAXN = 50 + 7;
    public static void main2(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(reader.readLine());
        while(T-- > 0){
            String[] strFir = reader.readLine().split(" ");
            int K = Integer.parseInt(strFir[0]);
            int t = Integer.parseInt(strFir[1]);
            int N = Integer.parseInt(strFir[2]);
            int[] x = new int[N];
            int[] y = new int[N];
            int s = K;
            String[] strSec = reader.readLine().split(" ");
            String[] strTir = reader.readLine().split(" ");
            for (int i = 0; i < N; i++) {
                x[i] = Integer.parseInt(strSec[i]);
                y[i] = Integer.parseInt(strTir[i]);
                s += y[i];
            }
            int[] pos = new int[MAXN];
            Arrays.fill(pos , 0);
            for (int i = 0; i < N; i++) {
                pos[x[i]] = Math.max(pos[x[i]] , y[i]);
            }
            int distance = 0;
            while(t-- > 0){
                int maxInc = 0;
                int idx = distance;
                for (int i = distance + 1; i <= distance + K ; i++) {
                    if(pos[i] == 0) continue;
                    if(pos[i] >= maxInc){
                        maxInc = Math.max(maxInc, pos[i]);
                        idx = i;
                    }
                }
                if(maxInc == 0){
                    distance += K;
                }else {
                    distance = idx;
                    K += maxInc;
                }
            }

            writer.write(distance + "\n");
        }

        writer.flush();
        writer.close();
        reader.close();
    }
}
