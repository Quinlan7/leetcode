package com.array;/**
 * @author zhf
 * @date 2023/3/1 13:25
 * @version 1.0
 */

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode59
 * @date 2023/3/1 13:25
 **/
public class Spiral_Arrays_59 {










    /**
     * 默写个快排，庆祝一下
     */

    public void QuickSort(int[] arr,int left,int right){
        if(left<right){
            int tempLeft = left;
            int tempRight = right;
            int key = arr[tempLeft];
            while(tempLeft < tempRight){
                while(tempLeft < tempRight && arr[tempRight] > key){
                    tempRight--;
                }
                if(tempLeft < tempRight) arr[tempLeft++] = arr[tempRight]  ;
                while(tempLeft < tempRight && arr[tempLeft] < key){
                    tempLeft++;
                }
                if(tempLeft < tempRight) arr[tempRight--] = arr[tempLeft]  ;

            }
            arr[tempLeft]=key;
            QuickSort(arr,left,tempLeft-1);
            QuickSort(arr,tempLeft+1,right);
        }
    }
}


class Solution_59 {
    public int[][] generateMatrix(int n) {
        int[][] arr = new int[n][n];

        Func(arr,0,n-1,1);
        return arr;
    }

    private void Func(int[][] arr, int low, int high, int num) {
        if ( low < high ) {
            for (int i = 0; i <= high-low; i++) {
                arr[low][low + i] = num + i;
                arr[low + i][high] = num + high - low + i;
                arr[high][high - i] = num + 2 * (high - low) + i;
                if (i != high - low) arr[high - i][low] = num + 3 * (high - low) + i;
            }
            Func(arr ,low + 1,high - 1,num + (4*(high - low)));
        }else arr[low][high] = num;
    }
}




