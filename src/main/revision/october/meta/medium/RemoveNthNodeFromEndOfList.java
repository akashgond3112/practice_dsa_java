package main.revision.october.meta.medium;

import main.revision.october.meta.ListNode;

public class RemoveNthNodeFromEndOfList {

    /**
     * Alternative one-pass solution using two pointers.
     *
     * Approach:
     * - Use a fast and slow pointer
     * - Move fast pointer n nodes ahead
     * - Then move both pointers until fast reaches the end
     * - Remove the node after slow pointer
     *
     * Time Complexity: O(L) where L is the length of the linked list
     * - Only requires a single pass through the list
     *
     * Space Complexity: O(1) - only using constant extra space
     */
    class Solution {
        /**
         * Removes the nth node from the end of the linked list in one pass.
         *
         * @param head The head of the linked list
         * @param n    The position from the end to remove (1-indexed)
         * @return The head of the modified linked list
         */
        public ListNode removeNthFromEnd(ListNode head, int n) {

            // Create a dummy node to handle edge cases like removing the head
            ListNode dummy = new ListNode(0);
            dummy.next = head;

            ListNode fast = dummy;
            ListNode slow = dummy;

            // Move fast pointer n+1 nodes ahead
            for (int i = 0; i <= n; i++) {
                if (fast != null) {
                    fast = fast.next;
                }
            }

            // Move both pointers until fast reaches the end
            while (fast != null) {
                slow = slow.next;
                fast = fast.next;
            }

            // Remove the nth node from the end
            slow.next = slow.next.next;

            return dummy.next;

        }
    }

    // revised on 12/21/2025
    class SolutionRevisedOnThirDay {
        public ListNode removeNthFromEnd(ListNode head, int n) {

            ListNode dummy = new ListNode(0);
            dummy.next = head;

            ListNode fast = dummy;
            ListNode slow = dummy;

            for (int i = 0; i <= n; i++) {
                if (fast != null) {
                    fast = fast.next;
                }
            }

            while (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }

            slow.next = slow.next.next;

            return dummy.next;
        }
    }

    // revised on 12/27/2025
    class SolutionRevisedOnSeventhDay {
        public ListNode removeNthFromEnd(ListNode head, int n) {

            ListNode dummy = new ListNode(0);
            dummy.next = head;

            ListNode fast = dummy;
            ListNode slow = dummy;

            for (int i = 0; i <= n; i++) {
                if (fast != null) {
                    fast = fast.next;
                }
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
