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

    // Revised on 07.10.2025
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

    // Revised on 13.10.2025
    public ListNode mergeKLists_Rev_2(ListNode[] list) {

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

            ListNode curNode = pq.poll();

            cur.next = curNode;
            cur = cur.next;

            if (curNode.next != null) {
                pq.offer(curNode.next);
            }
        }

        return node.next;
    }

    // Revised on 27.10.2025
    public ListNode mergeKLists_Rev_3(ListNode[] list) {

        if (list == null || list.length == 0)
            return null;

        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

        for (ListNode node : list) {
            if (node != null) {
                pq.offer(node);
            }
        }

        ListNode curNode = new ListNode(0);
        ListNode cur = curNode.next;

        while (!pq.isEmpty()) {

            ListNode node = pq.poll();

            cur.next = node;
            cur = cur.next;

            if (node.next != null) {
                pq.offer(node.next);
            }
        }

        return curNode.next;

    }
}
