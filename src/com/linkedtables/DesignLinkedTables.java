package com.linkedtables;/**
 * @author zhf
 * @date 2023/3/6 8:22
 * @version 1.0
 */

/**
 * @author zhf
 * 项目：leetcode
 * 描述：leetcode707
 * @date 2023/3/6 8:22
 **/
public class DesignLinkedTables {
    /**
     * ["MyLinkedList","addAtHead","addAtHead","addAtHead","addAtIndex","deleteAtIndex","addAtHead","addAtTail","get","addAtHead","addAtIndex","addAtHead"]
     * [[],[7],[2],[1],[3,0],[2],[6],[4],[4],[4],[5,0],[6]]@param args
     */

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(7);
        myLinkedList.addAtHead(2);
        myLinkedList.addAtHead(1);
        myLinkedList.addAtIndex(3,0);
        myLinkedList.deleteAtIndex(2);
        myLinkedList.addAtHead(6);
        myLinkedList.addAtTail(4);
        myLinkedList.get(4);
        myLinkedList.addAtHead(4);
        myLinkedList.addAtIndex(5,0);
        myLinkedList.addAtHead(6);

    }
}

/**
 * 我的链表为带有一个空元素的头指针
 */

class MyLinkedList {

    int length;
    LinkedNode head;
    private class LinkedNode{
        int val;
        LinkedNode next;

        private LinkedNode(int val) {
            this.val = val;
            this.next = null;
        }

        public LinkedNode() {
            this.next = null;
        }
    }

    public MyLinkedList() {
        this.length = 0;
        head = new LinkedNode();
    }

    public int get(int index) {
        if ( index >= length || index < 0) return -1;
        LinkedNode temp = head;
        for (int i = 0; i < index; i++) {
             temp = temp.next;
        }
        return temp.next.val;

    }

    public void addAtHead(int val) {
        LinkedNode entry = new LinkedNode(val);
        entry.next = head.next;
        head.next = entry;
        length++;
    }

    public void addAtTail(int val) {
        LinkedNode temp = head;
        while(temp.next != null) {temp = temp.next;}
        LinkedNode entry = new LinkedNode(val);
        temp.next = entry;
        length++;

    }

    /**
     * addAtIndex(index,val)：在链表中的第index个节点之前添加值为val 的节点。
     * 如果index等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。
     * 如果index小于0，则在头部插入节点。
     * @param index
     */
    public void addAtIndex(int index, int val) {
        if(index > length  ) return;
        LinkedNode entry = new LinkedNode(val);
        LinkedNode temp = head;
        if(index == length) {
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = entry;
            length++;
            return;
        }
        if(index <= 0) {entry.next = head.next; head.next = entry; length++;return;}
        if(index > 0 && index < length) {
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            entry.next = temp.next;
            temp.next = entry;
            length++;
            return;
        }
    }

    public void deleteAtIndex(int index) {
        if ( index >= length || index < 0) return;
        LinkedNode temp = head;
        for (int i = 0; i < index; i++) {
             temp = temp.next;
        }
        temp.next = temp.next.next;
        length--;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
