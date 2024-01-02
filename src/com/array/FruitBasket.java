package com.array;/**
 * @author zhf
 * @date 2023/3/11 18:16
 * @version 1.0
 */

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode904
 * @date 2023/3/11 18:16
 **/
public class FruitBasket {
    public static void main(String[] args) {
        Solution904 solution904 = new Solution904();

        int ret = solution904.totalFruit(new int[]{1,0,1,4,1,4,1,2,3});
        System.out.println(ret);
    }

}

class Solution904 {
    public int totalFruit(int[] fruits) {
        int head = 0 ;
        int tail = 0 ;
        int ret = 0;
        int[] type = new int[2];
        for ( ; tail < fruits.length ; ) {
            type[0] = fruits[tail];
            while(tail < fruits.length  &&  type[0] == fruits[tail]) tail++;
            if(tail == fruits.length) return Math.max(ret, tail - head);
            type[1] = fruits[tail];
            while(tail < fruits.length && (fruits[tail] == type[0] || fruits[tail]==type[1])  ) tail++;
            if(tail == fruits.length) return Math.max(ret, tail - head);
            ret = Math.max(ret,tail-head);
            for( head = --tail; fruits[head] == fruits[tail] ; head--) {}
            head++;
            tail = head;

        }
        return ret;
    }
}