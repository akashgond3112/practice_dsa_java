package main.revision.october.meta.hard;

import main.revision.october.meta.ListNode;
import java.util.*;

public class MergekSortedLists {

    public ListNode mergeKLists(ListNode[] list) {

        if (list == null || list.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

        for (ListNode l : list) {
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

    // Revised on 08.10.2025
    public ListNode mergeKLists_Rev_1(ListNode[] list) {

        if (list == null || list.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

        for (ListNode l : list) {

            if (l != null) {
                pq.offer(l);
            }
        }

        ListNode node = new ListNode(0);
        ListNode cur = node;

        while (!pq.isEmpty()) {

            ListNode l = pq.poll();

            cur.next = l;
            cur = cur.next;

            if (l.next != null) {
                pq.offer(l.next);
            }

        }

        return node.next;
    }
}
