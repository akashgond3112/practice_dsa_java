package main.pattern.fastandslowpointer;

import main.pattern.ListNode;

public class PalindromeLinkedList {
    class Solution {
        public boolean isPalindrome(ListNode head) {

            ListNode fast = head;
            ListNode slow = head;

            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }

            if (fast != null) {
                slow = slow.next;
            }

            slow = reverseLinkedList(slow);
            fast = head;

            while (slow != null) {
                if (slow.val != fast.val) {
                    return false;
                }
                slow = slow.next;
                fast = fast.next;
            }
            return true;
        }

        private ListNode reverseLinkedList(ListNode slow) {

            // Base case: Agar slow null ya last node hai, toh wahi return karo
            if (slow == null || slow.next == null) {
                return slow;
            }

            // Recursive call: Next node ko reverse karo
            ListNode next = reverseLinkedList(slow.next);

            // Current node ko reverse karo
            slow.next.next = slow;
            slow.next = null;

            // Reverse linked list ka head return karo
            return next;
        }
    }
}
