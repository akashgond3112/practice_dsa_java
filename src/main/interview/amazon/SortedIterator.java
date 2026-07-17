/**
 * @author akash
 * @date Jul 16, 2026
 * @time 4:19:53 PM
 */
package main.interview.amazon;

import java.util.Iterator;
import java.util.PriorityQueue;

public class SortedIterator implements Iterator<Integer> {

    private static class Node {
        int value;
        Iterator<Integer> iterator;

        Node(int value, Iterator<Integer> iterator) {
            this.value = value;
            this.iterator = iterator;
        }
    }

    private PriorityQueue<Node> minHeap;

    public SortedIterator(Iterator<Integer>[] iterators) {
        minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.value, b.value));

        for (Iterator<Integer> it : iterators) {
            if (it.hasNext()) {
                minHeap.add(new Node(it.next(), it));
            }
        }
    }

    @Override
    public boolean hasNext() {
        return !minHeap.isEmpty();
    }

    @Override
    public Integer next() {
        Node node = minHeap.poll();
        if (node.iterator.hasNext()) {
            minHeap.add(new Node(node.iterator.next(), node.iterator));
        }
        return node.value;
    }
}
