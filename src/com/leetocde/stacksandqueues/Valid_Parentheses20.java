package com.leetocde.stacksandqueues;/**
 * @author zhf
 * @date 2023/10/19 20:22
 * @version 1.0
 */

import java.util.Stack;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode20
 * @date 2023/10/19 20:22
 **/
public class Valid_Parentheses20 {
    public static void main(String[] args) {

    }
}

class Solution {
    public boolean isValid(String s) {
        char c[] = s.toCharArray();
        Stack<Character> stack = new Stack();
        for (int i = 0; i < c.length; i++) {
            if(c[i] == '{' || c[i] == '(' || c[i] == '['){
                stack.push(c[i]);
            }else {
                if(stack.isEmpty()) return false;
                if(c[i] == ')' && stack.pop() != '(') return false;
                if(c[i] == '}' && stack.pop() != '{') return false;
                if(c[i] == ']' && stack.pop() != '[') return false;
            }

        }
        if(stack.isEmpty()) return true;
        return false;
    }
}