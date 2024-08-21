package com.leetocde.linkedtables;/**
 * @author zhf
 * @date 2023/3/14 9:41
 * @version 1.0
 */

import java.util.HashSet;

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode142
 * @date 2023/3/14 9:41
 **/
public class CircularlyLinkedTable {
    public static void main(String[] args) {
        Solution142 solution142 = new Solution142();
        ListNode listNode3 = new ListNode(-4);
        ListNode listNode2 = new ListNode(0,listNode3);
        ListNode listNode1 = new ListNode(2,listNode2);
        ListNode listNode0 = new ListNode(3,listNode1);
        listNode3.next = listNode1;
        solution142.detectCycle2(listNode0);

    }


}
class Solution142 {
    public ListNode detectCycle(ListNode head) {

        ListNode ret = null ;

        HashSet<ListNode> listNodes = new HashSet<>();
        while(head != null ){
            boolean flag = listNodes.add(head);
            if(flag == false) { ret = head; break;}
            head = head.next;
        }
        return ret;

    }

    public ListNode detectCycle2(ListNode head) {

        ListNode fast = head ;
        ListNode slow = head ;
        ListNode ret = null ;
        if(fast == null || fast.next == null || fast.next.next == null) return ret;
        do {

            slow = slow.next;
            fast = fast.next.next;

        } while (fast.next != null && fast.next.next != null && fast != slow );
        if(fast.next == null || fast.next.next == null ) return ret;
        ret = head;
        while(ret != slow){
            slow = slow.next;
            ret = ret.next;
        }
        return ret;
    }

}
