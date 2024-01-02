package com.stacksandqueues;/**
 * @author zhf
 * @date 2023/10/20 9:22
 * @version 1.0
 */

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：1047
 * @date 2023/10/20 9:22
 **/
public class RemoveDuplicates {
    public static void main(String[] args) {

    }
}

class Solution1047 {
    public String removeDuplicates(String s) {
        char c[] = s.toCharArray();
        Stack<Character> stack = new Stack();
        for (int i = 0; i < c.length; i++) {
            //1 栈 空直接推
            if(stack.isEmpty()){
                stack.push(c[i]);
            }
            //2 栈顶元素 如果和当前元素相同则pop
            else if(stack.peek() == c[i]){ stack.pop();}
            //3 栈顶元素 不同则push
            else if(stack.peek() != c[i]){ stack.push(c[i]);}
        }
        LinkedList<String> reversedList = new LinkedList<>();
        while (!stack.isEmpty()) {
            reversedList.addFirst(String.valueOf(stack.pop()));
        }

        // 将LinkedList转换为字符串
        StringBuilder stackAsString = new StringBuilder();
        for (String item : reversedList) {
            stackAsString.append(item);
        }
        return stackAsString.toString();
    }
}