package com.stacksandqueues;/**
 * @author zhf
 * @date 2023/10/18 15:25
 * @version 1.0
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode225
 * @date 2023/10/18 15:25
 **/
public class MyStack {
    public static void main(String[] args) {

    }
}

class MyStack225{
    Queue<Integer> queue;

    public void MyStack255() {
        this.queue = new LinkedList<>();
    }

    public void push(int x) {
        queue.offer(x);
    }

    public int pop() {
        before();
        return queue.poll();
    }

    public int top() {
        before();
        int peek = queue.peek();
        queue.offer(queue.poll());
        return peek;
    }

    void before(){

        for (int i = 1 ; i < queue.size() ; i++ ) {
            queue.offer(queue.poll());
        }
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
