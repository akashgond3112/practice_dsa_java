/**
 * @author agond
 * @date Jul 03, 2025
 * @time 8:51:31 PM
 */
package main.revision.meta.medium;

import java.util.*;
import main.revision.meta.TreeNode;

public class LRUCache {

    private final Map<Integer, TreeNode> map;
    private final int capacity;

    private final TreeNode head;
    private final TreeNode tail;

    public LRUCache(int capacity) {

        this.capacity = capacity;
        this.map = new HashMap<>();

        this.head = new TreeNode(0, 0);
        this.tail = new TreeNode(0, 0);
        head.next = tail;
        tail.prev = head;
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
            node.val = val;
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
