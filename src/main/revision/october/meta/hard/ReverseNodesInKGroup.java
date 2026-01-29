package main.revision.october.meta.hard;

import main.revision.october.meta.ListNode;

public class ReverseNodesInKGroup {

    public class Solution {

        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null || k == 1) {
                return head; // Agar list empty hai ya k = 1 hai, toh koi reversal ki zarurat nahi.
            }

            ListNode dummy = new ListNode(0); // Dummy node banate hain to simplify edge cases.
            dummy.next = head;

            ListNode prevGroupTail = dummy; // Pehle group ka tail track karne ke liye.
            ListNode currentGroupHead = head; // Current group ka head track karne ke liye.

            while (currentGroupHead != null) {
                ListNode groupEnd = getGroupEnd(currentGroupHead, k); // Current group ka end node find karte hain.
                if (groupEnd == null) {
                    // Agar k se kam nodes bachi hain, toh reversal ki zarurat nahi.
                    break;
                }

                ListNode nextGroupHead = groupEnd.next; // Next group ka head store karte hain.
                groupEnd.next = null; // Current group ko isolate karte hain.

                // Current group ko reverse karte hain aur connect karte hain.
                prevGroupTail.next = reverseList(currentGroupHead);

                // Current group ka original head ab tail ban gaya hai.
                currentGroupHead.next = nextGroupHead;

                // Pointers ko next iteration ke liye update karte hain.
                prevGroupTail = currentGroupHead;
                currentGroupHead = nextGroupHead;
            }

            return dummy.next; // Reversed list ka head return karte hain.
        }

        /**
         * Finds the k-th node from the startNode.
         * 
         * @return The k-th node, or null if fewer than k nodes exist.
         */
        private ListNode getGroupEnd(ListNode startNode, int k) {
            ListNode curr = startNode;
            int count = 1;
            while (curr != null && count < k) {
                curr = curr.next;
                count++;
            }
            return curr; // Agar k-th node mil jaye toh return karte hain, warna null.
        }

        /**
         * Reverses a linked list iteratively.
         * 
         * @return The new head of the reversed list.
         */
        private ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode nextTemp = curr.next; // Next node ko temporarily store karte hain.
                curr.next = prev; // Current node ka next pointer reverse karte hain.
                prev = curr; // Prev pointer ko update karte hain.
                curr = nextTemp; // Current pointer ko next node par move karte hain.
            }
            return prev; // Reversed list ka new head return karte hain.
        }
    }

    // revision on 11/22/2025
    public class SolutionRevisionThirdDay {
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

                prevGroupTail.next = reverseList(currentGroupHead);

                currentGroupHead.next = nextGroupHead;

                prevGroupTail = currentGroupHead;
                currentGroupHead = nextGroupHead;

            }

            return dummy.next;
        }

        private ListNode getGroupEnd(ListNode startNode, int k) {

            ListNode cur = startNode;
            int count = 1;

            while (cur != null && count < k) {
                cur = cur.next;
                count++;
            }

            return cur;
        }

        private ListNode reverseList(ListNode head) {

            ListNode prev = null;
            ListNode cur = head;

            while (cur != null) {

                ListNode nextTmp = cur.next;
                cur.next = prev;
                prev = cur;
                cur = nextTmp;
            }

            return prev;
        }
    }

    // revision on 11/28/2025
    public class SolutionRevisionFourteenDay {
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

                prevGroupTail.next = reverseList(currentGroupHead);

                currentGroupHead.next = nextGroupHead;

                prevGroupTail = currentGroupHead;
                currentGroupHead = nextGroupHead;
            }

            return dummy.next;
        }

        private ListNode getGroupEnd(ListNode startNode, int k) {
            ListNode curr = startNode;
            int count = 1;
            while (curr != null && count < k) {
                curr = curr.next;
                count++;
            }
            return curr;
        }

        private ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode nextTemp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextTemp;
            }
            return prev;
        }

    }

    // revision on 12/12/2025
    public class SolutionRevisionThirtyDay {
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

                if (groupEnd == null)
                    break;

                ListNode nextGroupHead = groupEnd.next;

                groupEnd.next = null;

                prevGroupTail.next = reverseList(currentGroupHead);

                currentGroupHead.next = nextGroupHead;

                prevGroupTail = currentGroupHead;
                currentGroupHead = nextGroupHead;
            }

            return dummy.next;
        }

        private ListNode getGroupEnd(ListNode startNode, int k) {

            ListNode cur = startNode;
            int count = 1;
            while (cur != null && count < k) {
                cur = cur.next;
                count++;
            }

            return cur;
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
    }

    // revised on 1/10/2026
    class SolutionRevisedOnDayThirty {
        public ListNode reverseKGroup(ListNode head, int k) {

            if (head == null || k == 1) {
                return head;
            }

            ListNode dummy = new ListNode(0);
            dummy.next = head;

            ListNode previousGrouptail = dummy;
            ListNode currentGroupHead = head;

            while (currentGroupHead != null) {

                ListNode kThNode = getKthNode(currentGroupHead, k);

                if (kThNode == null)
                    break;

                ListNode nextGroupHead = kThNode.next;
                kThNode.next = null;

                previousGrouptail.next = reverse(currentGroupHead);

                currentGroupHead.next = nextGroupHead;

                previousGrouptail = currentGroupHead;
                currentGroupHead = nextGroupHead;
            }

            return dummy.next;
        }

        private ListNode getKthNode(ListNode node, int k) {

            ListNode cur = node;
            int count = 1;

            while (cur != null && count < k) {
                cur = cur.next;
                count++;
            }

            return cur;
        }

        private ListNode reverse(ListNode node) {

            ListNode prev = null;
            ListNode cur = node;

            while (cur != null) {

                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }

            return prev;
        }
    }
}
