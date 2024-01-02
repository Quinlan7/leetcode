package com.linkedtables;/**
 * @author zhf
 * @date 2023/3/9 9:09
 * @version 1.0
 */

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode24
 * @date 2023/3/9 9:09
 **/
public class PairWiseExchange {
    public static void main(String[] args) {
        Solution24 solution24 = new Solution24();
        ListNode four = new ListNode(4);
        ListNode three = new ListNode(3,four);
        ListNode two = new ListNode(2,three);
        ListNode one = new ListNode(1,two);
        solution24.swapPairs(one);
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
class Solution24 {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode temp = new ListNode();
        ListNode pre = temp;
        ListNode bac = head.next;
        temp.next = head;
        for (;  ; ) {

            pre.next.next = bac.next;
            bac.next = pre.next;
            pre.next = bac;
            pre = bac.next;

            if(pre.next == null || pre.next.next == null) break;
            bac = bac.next.next.next;
        }

        return temp.next;
    }
}