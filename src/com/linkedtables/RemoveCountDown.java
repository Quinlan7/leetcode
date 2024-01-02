package com.linkedtables;/**
 * @author zhf
 * @date 2023/3/12 8:53
 * @version 1.0
 */

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode19
 * @date 2023/3/12 8:53
 **/
public class RemoveCountDown {
}


class Solution19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode bac = head;
        for (int i = 0; bac.next != null ; i++) {
            bac = bac.next;
            if( i >= n-1 ) pre = pre.next;
        }
        if(pre.next == head ) head = head.next;
        else pre.next = pre.next.next;
        return head;
    }
}