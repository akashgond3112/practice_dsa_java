/**
 * @author akash
 * @date Jul 22, 2026
 * @time 6:48:00 PM
 */
package main.pattern.fastandslowpointer;

import main.pattern.ListNode;

public class LinkedListCycleII {

    public class Solution {

        private ListNode intersect(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;

            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;

                if (slow == fast) {
                    return slow;
                }
            }

            return null;
        }

        public ListNode detectCycle(ListNode head) {

            if (head == null || head.next == null) {
                return null;
            }

            ListNode intersect = intersect(head);

            if (intersect == null) {
                return null;
            }

            ListNode start = head;

            while (intersect != start) {

                start = start.next;
                intersect = intersect.next;
            }

            return start;
        }
    }

}
