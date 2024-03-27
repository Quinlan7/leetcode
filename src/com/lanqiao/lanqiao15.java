package com.lanqiao;/**
 * @author zhf
 * @date 2024/3/25 21:49
 * @version 1.0
 */

import java.util.Scanner;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：
 * @date 2024/3/25 21:49
 **/
public class lanqiao15 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //在此输入您的代码...
        int[][] nums = new int[30][20];
        for(int i = 0; i<30;i++){
            for(int j = 0; j < 20 ; j++){
                nums[i][j] = scan.nextInt();
            }
        }
        int max = 0;
        for(int i = 0; i<30;i++){
            for(int j = 0; j < 20 ; j++){
                for(int m = i; m < i+5; m++){
                    for(int n = j; (m-i)+(n-1)<=5;n++){
                        max = Math.max(Math.abs(nums[i][j]-nums[m][n]),max);
                    }
                }
            }
        }
        System.out.println(max);
        scan.close();
    }


}
