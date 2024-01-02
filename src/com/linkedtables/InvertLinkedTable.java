package com.linkedtables;/**
 * @author zhf
 * @date 2023/3/7 20:34
 * @version 1.0
 */

import com.sun.org.apache.bcel.internal.generic.ATHROW;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode206
 * @date 2023/3/7 20:34
 **/
public class InvertLinkedTable {
    public static void main(String[] args) {
        Solution206 solution206 = new Solution206();
        ListNode206 five = new ListNode206(5);
        ListNode206 four = new ListNode206(4,five);
        ListNode206 three = new ListNode206(3,four);
        ListNode206 two = new ListNode206(2,three);
        ListNode206 one = new ListNode206(1,two);
        solution206.reverseList(one);
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class ListNode206 {
    int val;
    ListNode206 next;
    ListNode206() {}
    ListNode206(int val) { this.val = val; }
    ListNode206(int val, ListNode206 next) { this.val = val; this.next = next; }
}


class Solution206 {
    public ListNode206 reverseList(ListNode206 head) {
        ListNode206 temp = head;
        while(temp.next!=null){
            temp = temp.next;
        }

        invert(head);
        head.next = null;

        return temp;


    }
    void invert(ListNode206 node){
        if(node.next.next != null){invert(node.next);}
        node.next.next = node;

    }
}