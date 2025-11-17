package main.revision.october.meta.easy;

import main.revision.october.meta.ListNode;

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

            if (slow == null || slow.next == null) {
                return slow;
            }

            ListNode next = reverseLinkedList(slow.next);
            slow.next.next = slow;
            slow.next = null;
            return next;
        }
    }

    // revised on 05/11/2025
    class SolutionrevisedOnthirdDay {
        public boolean isPalindrome(ListNode head) {

            ListNode fast = head;
            ListNode slow = head;

            while (fast != null && slow != null) {
                fast = fast.next.next;
                slow = slow.next;
            }

            if (fast != null) {
                slow = slow.next;
            }

            slow = reverLinkedList(slow);
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

        private ListNode reverLinkedList(ListNode slow) {

            if (slow == null || slow.next == null) {
                return slow;
            }

            ListNode next = reverLinkedList(slow.next);
            slow.next.next = slow;
            slow.next = null;
            return next;
        }
    }

    // revised on 11/11/2025
    class SolutionrevisedOnSeventhDay {
        public boolean isPalindrome(ListNode head) {

            ListNode fast = head;
            ListNode slow = head;

            while (fast != null && slow != null) {

                fast = fast.next.next;
                slow = slow.next;
            }

            if (fast != null) {
                slow = slow.next;
            }

            slow = reverseLinkedList(slow);

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

            if (slow == null && slow.next != null) {
                return slow;
            }

            ListNode next = reverseLinkedList(slow);
            slow.next.next = slow;
            slow.next = null;
            return next;
        }
    }
}
