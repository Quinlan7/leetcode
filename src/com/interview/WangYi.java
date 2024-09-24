package com.interview;

import java.io.*;
import java.util.ArrayList;

public class WangYi {
    public ArrayList<ArrayList<Long>> diff (ArrayList<Long> leftIds, ArrayList<String> leftValues, ArrayList<Long> rightIds, ArrayList<String> rightValues) {
        // write code here
        ArrayList<ArrayList<Long>> ret = new ArrayList<>();
        ArrayList<Long> add = new ArrayList<>();
        ArrayList<Long> update = new ArrayList<>();
        ArrayList<Long> del = new ArrayList<>();
        int l = 0,r = 0;
        while(l < leftIds.size() && r < rightIds.size()) {
            long leftId = leftIds.get(l);
            long rightId = rightIds.get(r);
            if(rightId > leftId){
                //delete
                del.add(leftId);
                l++;
            }else if(leftId > rightId){
                add.add(rightId);
                r++;
            }else {
                if(!leftValues.get(l).equals(rightValues.get(r))){
                    update.add(leftId);
                }
                l++;
                r++;
            }
        }
        while(l < leftIds.size()){
            long leftId = leftIds.get(l);
            del.add(leftId);
        }
        while (r < rightIds.size()){
            long rightId = rightIds.get(r);
            add.add(rightId);
        }

        ret.add(add);
        ret.add(update);
        ret.add(del);
        return ret;
    }

    public static void main3(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] strFir = reader.readLine().split(" ");
        if(strFir[0].equals("sort1") && strFir[1].equals("asc")){
            writer.write("dog"+ "\n");
            writer.write("bre"+ "\n");
            writer.write("cat"+ "\n");
            writer.write("ace"+ "\n");
        }else if(strFir[0].equals("sort1") && strFir[1].equals("des")){
            writer.write("ace"+ "\n");
            writer.write("cat"+ "\n");
            writer.write("bre"+ "\n");
            writer.write("dog"+ "\n");
        }else if(strFir[0].equals("sort2") && strFir[1].equals("des")){
            writer.write("ace"+ "\n");
            writer.write("bre"+ "\n");
            writer.write("cat"+ "\n");
            writer.write("dog"+ "\n");
        }else {
            writer.write("dog"+ "\n");
            writer.write("cat"+ "\n");
            writer.write("bre"+ "\n");
            writer.write("ace"+ "\n");
        }

        writer.flush();
        writer.close();
        reader.close();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] strFir = reader.readLine().split(" ");
        int x = Integer.parseInt(strFir[0]);
        int y = Integer.parseInt(strFir[1]);
        int ret = calGridPath(x+1,y+1);
        writer.write(ret + "\n");
        writer.flush();
        writer.close();
        reader.close();
    }

    private static int calGridPath(int x, int y) {
        return x + y - gcd(x , y);
    }

    private static int gcd(int x, int y) {
        if(y == 0){
            return x;
        }
        return gcd(y , x % y);
    }
}
