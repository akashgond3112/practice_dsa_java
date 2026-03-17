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
}
