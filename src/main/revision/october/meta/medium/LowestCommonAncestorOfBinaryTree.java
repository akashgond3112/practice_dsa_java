package main.revision.october.meta.medium;

import main.revision.october.meta.TreeNode;

public class LowestCommonAncestorOfBinaryTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return root;
        }
    }

    // Date 15/10/2025
    public TreeNode lowestCommonAncestorRevOne(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null || p == null || q == null) {
            return root;
        }

        TreeNode left = lowestCommonAncestorRevOne(root.left, p, q);

        TreeNode right = lowestCommonAncestorRevOne(root.right, p, q);

        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return root;
        }
    }

    // Date 11/27/2025
    public TreeNode lowestCommonAncestorRevOnThirtyDay(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null || p == null || q == null) {
            return root;
        }

        TreeNode left = lowestCommonAncestorRevOnThirtyDay(root.left, p, q);
        TreeNode right = lowestCommonAncestorRevOnThirtyDay(root.right, q, p);

        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return root;
        }
    }
}
