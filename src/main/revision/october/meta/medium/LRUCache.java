package main.revision.october.meta.medium;

import java.util.HashMap;
import java.util.Map;

import main.revision.meta.TreeNode;

public class LRUCache {

    // Yeh class ek LRU Cache implement karti hai
    class Solution {

        // Map ka use karte hain key-value pairs store karne ke liye
        private final Map<Integer, TreeNode> map;
        private final int capacity; // Cache ki maximum capacity

        // Doubly linked list ke head aur tail nodes
        private final TreeNode head;
        private final TreeNode tail;

        // Constructor cache ki capacity set karta hai aur doubly linked list initialize
        // karta hai
        public Solution(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();

            this.head = new TreeNode(0, 0); // Dummy head node
            this.tail = new TreeNode(0, 0); // Dummy tail node
            head.next = tail; // Head ke baad tail ko link karte hain
            tail.prev = head; // Tail ke pehle head ko link karte hain
        }

        // Node ko doubly linked list se remove karne ka method
        public void removeNode(TreeNode node) {
            TreeNode prevNode = node.prev; // Node ke pehle wali node
            TreeNode nextNode = node.next; // Node ke baad wali node

            prevNode.next = nextNode; // Pehle wali node ko next node se link karte hain
            nextNode.prev = prevNode; // Next node ko pehle wali node se link karte hain
        }

        // Node ko head ke baad add karne ka method
        public void addAfterHead(TreeNode node) {
            node.next = head.next; // Node ke baad wali node ko head ke baad wali node banate hain
            node.prev = head; // Node ke pehle head ko link karte hain

            head.next.prev = node; // Head ke baad wali node ke pehle node ko link karte hain
            head.next = node; // Head ke baad wali node ko update karte hain
        }

        // Cache se value retrieve karne ka method
        public int get(int key) {
            if (!map.containsKey(key)) { // Agar key map mein nahi hai
                return -1; // -1 return karte hain
            }

            TreeNode node = map.get(key); // Key se corresponding node lete hain

            removeNode(node); // Node ko doubly linked list se remove karte hain
            addAfterHead(node); // Node ko head ke baad add karte hain
            return node.val; // Node ki value return karte hain
        }

        // Cache mein value insert karne ka method
        public void put(int key, int val) {
            if (map.containsKey(key)) { // Agar key pehle se map mein hai
                TreeNode node = map.get(key); // Node ko retrieve karte hain
                node.val = val; // Node ki value update karte hain
                removeNode(node); // Node ko doubly linked list se remove karte hain
                addAfterHead(node); // Node ko head ke baad add karte hain
            } else {
                if (map.size() == capacity) { // Agar cache full hai
                    TreeNode lru = tail.prev; // Least recently used node ko identify karte hain
                    map.remove(lru.key); // Map se LRU node ko remove karte hain
                    removeNode(lru); // Doubly linked list se LRU node ko remove karte hain
                }

                TreeNode node = new TreeNode(key, val); // Nayi node banate hain
                map.put(key, node); // Map mein nayi node ko add karte hain
                addAfterHead(node); // Node ko head ke baad add karte hain
            }
        }
    }

    // revised on 05/11/2025
    class SolutionRevisionOnThirdDay {

        private final Map<Integer, TreeNode> map;
        private final int capacity;

        private final TreeNode head;
        private final TreeNode tail;

        public SolutionRevisionOnThirdDay(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();

            this.head = new TreeNode(0, 0);
            this.tail = new TreeNode(0, 0);
            head.next = tail;
            head.prev = head;
        }

        public void removeNode(TreeNode node) {

            TreeNode prevNode = node.prev;
            TreeNode nextNode = node.next;

            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }

        public void addAfterHead(TreeNode node) {

            node.next = head.next;
            node.prev = head;

            head.next.prev = node;
            head.next = node;
        }

        public int get(int key) {

            if (!map.containsKey(key)) {
                return -1;
            }

            TreeNode node = map.get(key);
            removeNode(node);
            addAfterHead(node);
            return node.val;
        }

        public void put(int key, int val) {

            if (map.containsKey(key)) {
                TreeNode node = map.get(key);
                removeNode(node);
                addAfterHead(node);
            } else {
                if (map.size() == capacity) {

                    TreeNode lru = tail.prev;
                    map.remove(lru.key);
                    removeNode(lru);
                }

                TreeNode node = new TreeNode(key, val);
                map.put(key, node);
                addAfterHead(node);
            }
        }
    }

    // revised on 11/11/2025
    class SolutionRevisionOnSeventhDay {

        private final Map<Integer, TreeNode> map;
        private final int capacity;

        private final TreeNode head;
        private final TreeNode tail;

        SolutionRevisionOnSeventhDay(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();

            this.head = new TreeNode(0, 0);
            this.tail = new TreeNode(0, 0);

            head.next = tail;
            head.prev = head;
        }

        public void removeNode(TreeNode node) {

            TreeNode prev = node.prev;
            TreeNode next = node.next;

            prev.next = next;
            next.prev = prev;
        }

        public void addAfterHead(TreeNode node) {

            node.next = head.next;
            node.prev = head;

            head.next.prev = node;
            head.next = node;
        }

        public int get(int key) {

            if (!map.containsKey(key)) {
                return -1;
            }

            TreeNode node = map.get(key);
            removeNode(node);
            addAfterHead(node);
            return node.val;
        }

        public void put(int key, int val) {

            if (map.containsKey(key)) {
                TreeNode node = map.get(key);
                removeNode(node);
                addAfterHead(node);
            } else {

                if (map.size() == capacity) {

                    TreeNode lru = map.get(key);
                    map.remove(lru.key);
                    removeNode(lru);
                }

                TreeNode node = new TreeNode(key, val);
                map.put(key, node);
                addAfterHead(node);
            }
        }
    }

    // revised on 11/25/2025
    class SolutionRevisionOnFourteenDay {

        Map<Integer, TreeNode> map;
        int capacity;

        TreeNode head;
        TreeNode tail;

        SolutionRevisionOnFourteenDay(int capacity) {
            this.map = new HashMap<>();
            this.capacity = capacity;

            this.head = new TreeNode(0, 0);
            this.tail = new TreeNode(0, 0);
        }

        public void removeNode(TreeNode node) {

            TreeNode prev = node.prev;
            TreeNode next = node.next;

            prev.next = next;
            next.prev = prev;
        }

        public void afterHead(TreeNode node) {

            node.next = head.next;
            node.prev = head;

            head.next.prev = node;
            head.next = node;
        }

        public int get(int key) {

            if (!map.containsKey(key)) {
                return -1;
            }

            TreeNode node = map.get(key);
            removeNode(node);
            afterHead(node);

            return node.val;
        }

        public void put(int key, int val) {

            if (map.containsKey(key)) {
                TreeNode node = map.get(key);
                removeNode(node);
                afterHead(node);
            } else {

                if (map.size() == capacity) {
                    TreeNode node = tail.prev;
                    map.remove(node.key);
                    removeNode(node);
                }

                TreeNode node = new TreeNode(key, val);
                map.put(key, node);
                afterHead(node);
            }
        }
    }

    // revised on 12/24/2025
    class LRUCacheRevisedOnThirdDay {

        Map<Integer, TreeNode> map;
        int capacity;

        TreeNode head;
        TreeNode tail;

        public LRUCacheRevisedOnThirdDay(int capacity) {
            this.map = new HashMap<>();
            this.capacity = capacity;

            this.head = new TreeNode(0, 0);
            this.tail = new TreeNode(0, 0);
        }

        public int get(int key) {

            if (!map.containsKey(key)) {
                return -1;
            }

            TreeNode node = map.get(key);
            removeNode(node);
            addAfterHead(node);
            return node.val;

        }

        public void put(int key, int value) {

            if (map.containsKey(key)) {
                TreeNode node = map.get(key);
                node.val = value;
                removeNode(node);
                addAfterHead(node);
            } else {
                if (map.size() == this.capacity) {

                    TreeNode lru = tail.prev;
                    map.remove(lru.key);
                    removeNode(lru);
                }

                TreeNode node = new TreeNode(key, value);
                map.put(key, node);
                addAfterHead(node);
            }

        }

        public void removeNode(TreeNode node) {

            TreeNode prev = node.prev;
            TreeNode next = node.next;

            prev.next = next;
            next.prev = prev;
        }

        public void addAfterHead(TreeNode node) {

            node.next = head.next;
            node.prev = head;

            head.next.prev = node;
            head.next = node;
        }
    }
}
