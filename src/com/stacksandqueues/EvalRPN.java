package com.stacksandqueues;/**
 * @author zhf
 * @date 2023/10/23 14:06
 * @version 1.0
 */

import java.util.Stack;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：150
 * @date 2023/10/23 14:06
 **/
public class EvalRPN {
    public static void main(String[] args) {
        String[] tokens = {"2","1","+","3","*"};
        Integer ret = Solution150.evalRPN(tokens);
        System.out.println(ret);
    }
}


class Solution150 {
    public static int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if(tokens[i] != "+" && tokens[i] != "-" && tokens[i] != "*" && tokens[i] != "/" ){
                stack.push(tokens[i]);
                continue;
            }
            Integer op2 = Integer.valueOf(stack.pop());
            Integer op1 = Integer.valueOf(stack.pop());
            String temp = new String();
            switch (tokens[i]){
                case "+":
                    temp = String.valueOf(op1 + op2);
                    break;
                case "-":
                    temp = String.valueOf(op1 - op2);
                    break;
                case "*":
                    temp = String.valueOf(op1 * op2);
                    break;
                case "/":
                    temp = String.valueOf(op1 / op2);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported operator: " + op1);

            }
            stack.push(temp);

        }
        return Integer.valueOf(stack.pop());
    }
}