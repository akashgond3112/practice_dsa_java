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

    // 18/04/2026
    class SolutionRevisedOnDayThird {
        public ListNode reverseKGroup(ListNode head, int k) {

            if (head == null || k == 1) {
                return head;
            }

            ListNode dummy = new ListNode(0);
            dummy.next = head;

            ListNode currentGroupHead = head;
            ListNode prevGroupTail = dummy;

            while (currentGroupHead != null) {

                ListNode groupEnd = getGroupEnd(currentGroupHead, k);

                ListNode nextGroupHead = groupEnd.next;
                groupEnd.next = null;

                prevGroupTail.next = reverseList(currentGroupHead);
                currentGroupHead.next = nextGroupHead;

                prevGroupTail = currentGroupHead;
                currentGroupHead = nextGroupHead;

            }

            return dummy.next;

        }

        private ListNode reverseList(ListNode groupEnd) {
            ListNode prev = null;
            ListNode cur = groupEnd;

            while (cur != null) {
                ListNode tmp = cur.next;
                cur.next = prev;
                prev = cur;
                cur = tmp;
            }
            return prev;
        }

        private ListNode getGroupEnd(ListNode head, int k) {
            int count = 1;
            ListNode cur = head;

            while (count < k && cur != null) {
                cur = cur.next;
                count++;
            }

            return cur;
        }
    }

    // 24/04/2026
    class SolutionRevisedOnDaySeventh {
        public ListNode reverseKGroup(ListNode head, int k) {

            if (head == null || k == 1) {
                return head;
            }

            ListNode dummy = new ListNode(0);
            dummy.next = head;

            ListNode curGroupHead = head;
            ListNode prevGroupTail = dummy;

            while (curGroupHead != null) {
                ListNode groupEnd = getGroupEnd(curGroupHead, k);

                ListNode nextGroupHead = groupEnd.next;

                groupEnd.next = null;

                prevGroupTail.next = reverseList(groupEnd);

                curGroupHead.next = nextGroupHead;

                prevGroupTail = curGroupHead;
                curGroupHead = nextGroupHead;

            }

            return dummy.next;
        }

        private ListNode reverseList(ListNode head) {

            ListNode prev = null;
            ListNode cur = head;

            while (cur != null) {
                ListNode tmp = cur.next;
                cur.next = prev;
                prev = cur;
                cur = tmp;
            }

            return prev;
        }

        private ListNode getGroupEnd(ListNode head, int k) {

            ListNode cur = head;
            int count = 0;

            while (count < k) {
                cur = cur.next;
                count++;
            }

            return cur;

        }
    }
}
