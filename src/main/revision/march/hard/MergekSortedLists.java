package main.revision.march.hard;

import main.revision.march.ListNode;
import java.util.*;

public class MergekSortedLists {
    // 16/03/2026
    class SolutionOnDayFirst {
        public ListNode mergeKLists(ListNode[] lists) {

            if (lists == null || lists.length == 0) {
                return null;
            }

            PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

            for (ListNode l : lists) {
                if (l != null) {
                    pq.offer(l);
                }
            }

            ListNode dummy = new ListNode(0);
            ListNode cur = dummy;

            while (!pq.isEmpty()) {
                ListNode node = pq.poll();

                cur.next = node;
                cur = cur.next;

                if (node.next != null) {
                    pq.offer(node.next);
                }
            }

            return dummy.next;
        }
    }

    // 19/03/2026
    class SolutionRevisedOnDayThird {
        public ListNode mergeKLists(ListNode[] lists) {

            if (lists == null || lists.length == 0) {
                return null;
            }

            PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

            for (ListNode node : lists) {
                if (node != null) {
                    pq.offer(node);
                }
            }

            ListNode dummy = new ListNode(0);
            ListNode cur = dummy;

            while (!pq.isEmpty()) {

                ListNode node = pq.poll();

                cur.next = node;
                cur = cur.next;

                if (node.next != null) {
                    pq.offer(node.next);
                }
            }

            return dummy.next;
        }
    }

    // 25/03/2026
    class SolutionRevisedOnDayFifth {
        public ListNode mergeKLists(ListNode[] lists) {

            if (lists == null || lists.length == 0) {

            }

            PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

            for (ListNode node : lists) {
                if (node != null) {
                    pq.offer(node);
                }
            }

            ListNode dummy = new ListNode(0);
            ListNode node = dummy;

            while (!pq.isEmpty()) {

                ListNode cur = pq.poll();

                node.next = cur;
                node = node.next;

                if (cur.next != null) {
                    pq.offer(cur.next);
                }
            }
            return dummy.next;
        }
    }

    // 08/04/2026
    class SolutionRevisedOnDayFourteen {
        public ListNode mergeKLists(ListNode[] lists) {

            if (lists == null || lists.length == 0) {

            }

            PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

            for (ListNode node : lists) {
                if (node != null) {
                    pq.offer(node);
                }
            }

            ListNode dummy = new ListNode(0);
            ListNode node = dummy;

            while (!pq.isEmpty()) {

                ListNode cur = pq.poll();

                node.next = cur;
                node = node.next;

                if (cur.next != null) {
                    pq.offer(cur.next);
                }
            }
            return dummy.next;
        }
    }
}
