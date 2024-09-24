package com.interview;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Tuan {
    public static void main1(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = reader.readLine();
        int R = 0;
        int B = 0;
        int G = 0;
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == 'R') R = i;
            if(str.charAt(i) == 'B') B = i;
            if(str.charAt(i) == 'G') G = i;
        }
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '#'){
                if(i > R && i > B && i > G) continue;
                if(i < R && i < B && i < G) continue;
                if(i > R && i < B && i < G) R = -1;
                if(i > B && i < R && i < G) B = -1;
                if(i > G && i < B && i < R) G = -1;
                if(i > R && i > B && i < G) G = -1;
                if(i > G && i > B && i < R) R = -1;
                if(i > R && i > G && i < B) B = -1;
            }
        }
        int retR = Math.min(Math.abs(R - B),Math.abs(R - G));
        int retB = Math.min(Math.abs(R - B),Math.abs(B - G));
        int retG = Math.min(Math.abs(G - B),Math.abs(R - G));
        if(R == -1){
            retR = -1;
            retB = Math.abs(B - G);
            retG = Math.abs(G - B);
        }else if(B == -1){
            retB = -1;
            retR = Math.abs(R - G);
            retG = Math.abs(G - B);
        }else if(G == -1){
            retG = -1;
            retR = Math.abs(R - B);
            retB = Math.abs(R - B);
        }else if(G == -1 && (R == -1 || B == -1)){
            retG = -1;
            retR = -1;
            retB = -1;
        }


        writer.write(retR + " ");
        writer.write(retB + " ");
        writer.write(retG + "\n");


        writer.flush();
        writer.close();
        reader.close();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(reader.readLine());
        while(T-- > 0){
            String[] strTemp = reader.readLine().split(" ");
            int a = Integer.parseInt(strTemp[0]);
            int b = Integer.parseInt(strTemp[1]);
            int c = Integer.parseInt(strTemp[2]);
            int x = Integer.parseInt(strTemp[3]);
            int y = Integer.parseInt(strTemp[4]);

            int ret = cal(a,b,c,x,y);
            writer.write(ret + "\n");
        }

        writer.flush();
        writer.close();
        reader.close();
    }

    private static int cal(int a, int b, int c, int x, int y) {
        int min = minTree(a,b,c);
        if(a == min) return a;
        int currentA =a;
        int currentB =b;
        int currentC =c;
        int subA = currentA - x;
        int ret = 0;
        while (subA != minTree(subA,currentB + 1,currentC)){
            currentB++;
            currentA = subA;
            subA -= x;
            ret = Math.max(ret,minTree(currentA,currentB,currentC));
            boolean f = false;
            while(currentB > y && currentC == minTree(currentA,currentB,currentC) && currentB != currentC){
                f = true;
                currentC++;
                currentB -= y;
                ret = Math.max(ret,minTree(currentA,currentB,currentC));
            }

            if(f){
                currentC--;
                currentB += y;
            }
        }
        return ret;
    }

    private static int minTree(int a, int b, int c) {
        Map<Integer,Integer> map = new HashMap<>();
        return Math.min(a,Math.min(b,c));
    }
}
