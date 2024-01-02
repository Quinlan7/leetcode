package com.linkedtables;/**
 * @author zhf
 * @date 2023/3/13 9:39
 * @version 1.0
 */

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode160
 * @date 2023/3/13 9:39
 **/
public class IntersectionLinkedTables {
}

class Solution160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tempA = headA ;
        ListNode tempB = headB ;
        ListNode ret;
        int lengthA , lengthB ,diff;
        for ( lengthA = 0; tempA != null ; lengthA++) {
            tempA = tempA.next;
        }
        for ( lengthB = 0; tempB != null ; lengthB++) {
            tempB = tempB.next;
        }
        diff = Math.abs(lengthA - lengthB);
        tempA = headA;
        tempB = headB;
        if(lengthA > lengthB){

            for (int i = 0; i < diff; i++) {
                tempA= tempA.next;
            }
        }else {

            for (int i = 0; i < diff; i++) {
                tempB= tempB.next;
            }
        }
        while(tempA != tempB && tempA != null && tempB != null){
            tempA = tempA.next;
            tempB = tempB.next;

        }
        ret = tempA;
        return ret;



    }
}