package com.queen;

public class NQueen {


    public static void main(String[] args) {
        int queenNum =4 ;
        new NQueen().placeQueen(queenNum);
    }

    int ways;
    int [] queenLocation;
    private  void placeQueen(int queenNum) {
        queenLocation = new int[queenNum];
        place(0);
        System.out.println(queenNum+ "皇后有【"+ways+"】中摆放方式");

    }

    private void place(int rowNum) {
        if(rowNum==queenLocation.length){
            ways++;
            show();
            return;
        }
        for (int i = 0; i < queenLocation.length; i++) {
            if(isValid(rowNum,i)){
                queenLocation[rowNum] = i;
                place(rowNum+1);
            }
        }
    }
    private void show(){
        for (int i = 0; i < queenLocation.length; i++) {
            for (int i1 = 0; i1 < queenLocation.length; i1++) {
                if(i1==queenLocation[i]){
                    System.out.print("*"+"\t");
                }else {
                    System.out.print("0"+"\t");
                }
            }
            System.out.println();
        }
        System.out.println("----------------------");
    }
    private boolean isValid(int rowNum, int i) {
        for (int j = 0; j < rowNum; j++) {
            //是否处在同一列
            if(queenLocation[j]==i) return false;
            //是否处在同一对角线上
            if(rowNum-j==Math.abs(i-queenLocation[j])) return false;

        }
        return true;
    }


}
