package main.pattern.fastandslowpointer;

import main.pattern.ListNode;

public class RemoveNthNodeFromEndOfList {

    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {

            ListNode dummy = new ListNode(0);
            dummy.next = head;

            ListNode fast = dummy;
            ListNode slow = dummy;

            while (n > 0) {
                fast = fast.next;
                n--;
            }

            while (fast != null) {
                slow = slow.next;
                fast = fast.next;
            }

            slow.next = slow.next.next;

            return dummy.next;
        }
    }
}
