package main.revision.october.meta;

public class TreeNode {
    public int key;
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode next;
    public TreeNode prev;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
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

    TreeNode(int val, TreeNode left, TreeNode right, TreeNode next, TreeNode prev) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.next = next;
        this.prev = prev;
    }
}
