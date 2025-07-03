/**
 * @author agond
 * @date Jun 13, 2025
 * @time 8:54:17 PM
 */
package main.revision.meta;

public class TreeNode {
    public int val;
    public int key;

    public TreeNode left;
    public TreeNode right;

    public TreeNode next;
    public TreeNode prev;

    public TreeNode random;

    TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
        this.next = null;
        this.prev = null;
        this.random = null;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode(int key, int value) {
        this.key = key;
        this.val = value;
        this.next = null;
        this.prev = null;
    }
}
