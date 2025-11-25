package main.revision.october.meta.easy;

import main.revision.october.meta.TreeNode;

public class RangeSumOfBST {

    public int rangeSumBST(TreeNode root, int low, int high) {

        int total = 0;
        if (root == null) {
            return total;
        }

        if (root.val >= low && root.val <= high) {
            total += root.val;
        }

        total += rangeSumBST(root.left, low, high);
        total += rangeSumBST(root.right, low, high);

        return total;
    }

    // Revised on 08.10.2025
    public int rangeSumBST_Rev_1(TreeNode root, int low, int high) {

        int total = 0;
        if (root == null) {
            return total;
        }

        if (root.val >= low && root.val <= high) {
            total += root.val;
        }

        total += rangeSumBST(root.left, low, high);
        total += rangeSumBST(root.right, low, high);

        return total;
    }

    // Revised on 27.10.2025
    public int rangeSumBST_Rev_3(TreeNode root, int low, int high) {

        int total = 0;
        if (root == null) {
            return total;
        }

        if (root.val >= low && root.val <= high) {
            total += root.val;
        }

        total += rangeSumBST(root.left, low, high);
        total += rangeSumBST(root.right, low, high);

        return total;
    }

    // Revised on 27.10.2025
    public int rangeSumBST_Rev_4(TreeNode root, int low, int high) {

        int total = 0;
        if (root == null) {
            return total;
        }

        if (root.val >= low && root.val <= high) {
            total += root.val;
        }

        total += rangeSumBST(root.left, low, high);
        total += rangeSumBST(root.right, low, high);

        return total;
    }
}
