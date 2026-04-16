package main.revision.march.hard;

import main.revision.march.ListNode;

public class ReverseNodesInKGroup {

    class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {

            if (head == null || k == 1) {
                return head;
            }

            ListNode dummy = new ListNode(0);
            dummy.next = head;

            ListNode prevGroupTail = dummy;
            ListNode currentGroupHead = head;

            while (currentGroupHead != null) {

                ListNode groupEnd = getGroupEnd(currentGroupHead, k);

                if (groupEnd == null) {
                    break;
                }

                ListNode nextGroupHead = groupEnd.next;
                groupEnd.next = null;

                prevGroupTail.next = reverse(nextGroupHead);

                currentGroupHead.next = nextGroupHead;
                prevGroupTail = currentGroupHead;
                currentGroupHead = nextGroupHead;
            }

            return dummy.next;
        }

        private ListNode reverse(ListNode nextGroupHead) {
            ListNode prev = null;
            ListNode cur = nextGroupHead;

            while (cur != null) {
                ListNode temp = cur.next;
                cur.next = prev;
                prev = cur;
                cur = temp;
            }

            return prev;
        }

        private ListNode getGroupEnd(ListNode currentGroupHead, int k) {
            ListNode cur = currentGroupHead;
            int count = 1;

            while (cur != null && count < k) {
                cur = cur.next;
                count++;
            }
            return cur;
        }
    }
}
