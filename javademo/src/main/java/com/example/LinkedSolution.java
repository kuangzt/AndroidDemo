package com.example;

/**
 * @author Davis
 * @Description: 链表
 * @date 2019/5/11 15:26
 * @copyright
 */
public class LinkedSolution {

    public static class LinkedNode {
        public LinkedNode next;
        public int value;
    }

    //单链表反序
    public static LinkedNode reverse(LinkedNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        LinkedNode p1 = head;
        LinkedNode p2 = p1.next;
        p1.next = null;
        while (p2 != null) {
            LinkedNode tmp = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = tmp;
        }
        return p1;
    }

    //找到单链表中倒数第K大小的节点
    public static LinkedNode findLastKNode(LinkedNode head,int k) {
        if (head == null) {
            return head;
        }
        LinkedNode slow = head;
        LinkedNode fast = null;
        if (slow != null) {
              fast = slow;
            int count = 1;
            while (fast != null && count <= k) {
                fast = fast.next;
                count++;
            }
            if (count != k+1) {
                return null;
            }
            if (fast == null) {
                return slow;
            }
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

}
