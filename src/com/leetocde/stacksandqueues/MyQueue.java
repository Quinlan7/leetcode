package com.leetocde.stacksandqueues;/**
 * @author zhf
 * @date 2023/4/14 10:45
 * @version 1.0
 */

import java.util.Stack;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode232
 * @date 2023/4/14 10:45
 **/
public class MyQueue {
    public static void main(String[] args) {
        MyQueue232 myQueue232 = new MyQueue232();
        myQueue232.push(1);
        myQueue232.push(2);
        myQueue232.push(3);
        myQueue232.push(4);
        myQueue232.pop();
        myQueue232.push(5);
        myQueue232.pop();
        myQueue232.pop();
        myQueue232.pop();
        myQueue232.pop();
//        integers.forEach();
    }
}
class MyQueue232 {
    Stack stack;
    Stack temp;


    public MyQueue232() {
        this.stack = new Stack<Integer>();
        this.temp = new Stack<Integer>();
    }

    public void push(int x) {

        stack.push(x);

    }

    public int pop() {


        while (!stack.isEmpty()) {
            temp.push(stack.pop());
        }
        int pop = (int) temp.pop();
        after();
        return pop;



    }

    public int peek() {

        while (!stack.isEmpty()) {
            temp.push(stack.pop());
        }
        int peek = (int) temp.peek();
        after();
        return peek;

    }

    public boolean empty() {
        return stack.isEmpty();
    }

    public boolean after(){

        while(!temp.isEmpty()) {
            stack.push(temp.pop());
        }
        return true;
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */