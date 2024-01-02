package com.linkedtables;/**
 * @author zhf
 * @date 2023/3/3 9:13
 * @version 1.0
 */

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode203
 * @date 2023/3/3 9:13
 **/
public class RemoveElement {
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
class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
/**
 * @description 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode fast = head;
        ListNode slow = head;
        for (; fast != null; fast = fast.next) {
            if (fast.val == val) {
                if(fast == head){ head = head.next;slow = slow.next; continue; }
                slow.next = fast.next;
                continue;
            }
            if(fast != head) slow = slow.next;
        }

            return head;


    }
}
