/**
 * @author agond
 * @date Jun 13, 2025
 * @time 8:54:17 PM
 */
package main.revision.meta;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    public int val;
    public int key;

    public TreeNode left;
    public TreeNode right;

    public TreeNode next;
    public TreeNode prev;

    public TreeNode random;

    public List<TreeNode> neighbors;

    TreeNode() {
        val = 0;
        neighbors = new ArrayList<TreeNode>();
    }

    public TreeNode(int val) {
        this.val = val;
        this.next = null;
        this.prev = null;
        this.random = null;
        neighbors = new ArrayList<TreeNode>();
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

    public TreeNode(int val, TreeNode next) {
        this.val = val;
        this.next = next;
    }

    public TreeNode(int _val, ArrayList<TreeNode> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
