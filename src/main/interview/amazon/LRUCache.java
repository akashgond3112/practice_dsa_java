/**
 * @author akash
 * @date Jul 12, 2026
 * @time 10:50:34 AM
 */
package main.interview.amazon;

import java.util.HashMap;
import java.util.Map;

// Ye class LRU (Least Recently Used) cache ko implement karta hai.
// Isme get() aur put() dono operations O(1) time mein ho jaate hain.
// Space complexity O(capacity) hai kyunki hum sirf capacity ke hisaab se nodes aur map rakhte hain.
public class LRUCache {

    private class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head; // dummy head (most recently used side)
    private final Node tail; // dummy tail (least recently used side)

    // Constructor: cache ko initialize karta hai.
    // Time complexity: O(1), Space complexity: O(1)
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    // Agar key present hai to usko most recently used bana deta hai aur value
    // return karta hai.
    // Time complexity: O(1), Space complexity: O(1)
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        moveToFront(node);
        return node.value;
    }

    // Agar key already hai to value update karta hai aur node ko front mein move
    // karta hai.
    // Agar key nahi hai to naya node banata hai aur agar cache full hai to least
    // recently used node ko remove karta hai.
    // Time complexity: O(1), Space complexity: O(1)
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            moveToFront(node);
            return;
        }

        if (map.size() == capacity) {
            // Evict least recently used (node right before tail)
            Node lru = tail.prev;
            removeNode(lru);
            map.remove(lru.key);
        }

        Node newNode = new Node(key, value);
        map.put(key, newNode);
        addToFront(newNode);
    }

    // --- Helper methods ---

    // Naya node ko head ke baad front mein add karta hai.
    // Time complexity: O(1), Space complexity: O(1)
    private void addToFront(Node node) {
        Node firstReal = head.next;
        head.next = node;
        node.prev = head;
        node.next = firstReal;
        firstReal.prev = node;
    }

    // Given node ko doubly linked list se remove karta hai.
    // Time complexity: O(1), Space complexity: O(1)
    private void removeNode(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    // Node ko remove karke phir front mein add karta hai, taaki wo most recently
    // used ban jaaye.
    // Time complexity: O(1), Space complexity: O(1)
    private void moveToFront(Node node) {
        removeNode(node);
        addToFront(node);
    }
}
