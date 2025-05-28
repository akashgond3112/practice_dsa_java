package main.revision.meta.hard;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author agond
 * @date May 28, 2025
 * @time 7:27:57 PM
 */
public class MergeKthSortedLists {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public class Tuple {

        ListNode node;
        int val;

        Tuple(ListNode node, int val) {
            this.node = node;
            this.val = val;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {

        PriorityQueue<Tuple> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

        for (ListNode l : lists) {
            if (l != null) {
                pq.add(new Tuple(l, l.val));
            }
        }

        ListNode dummyNode = new ListNode(-1);
        ListNode temp = dummyNode;

        while (!pq.isEmpty()) {

            Tuple curr = pq.poll();
            temp.next = curr.node;
            temp = temp.next;

            if (curr.node.next != null) {
                pq.add(new Tuple(curr.node.next, curr.node.next.val));
            }
        }

        return dummyNode.next;

    }

}
