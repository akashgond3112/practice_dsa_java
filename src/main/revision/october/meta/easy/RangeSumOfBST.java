package main.revision.october.meta.easy;

import main.revision.october.meta.TreeNode;

public class RangeSumOfBST {

    public int rangeSumBST(TreeNode root, int low, int high) {

        if (root == null)
            return 0;

        int sum = 0;

        if (root.val > low) {
            sum += rangeSumBST(root.left, low, high);
        }

        if (root.val >= low && root.val <= high) {
            sum += root.val;
        }

        if (root.val < high) {
            sum += rangeSumBST(root.right, low, high);

        }

        return sum;
    }

    // Revised on 08.10.2025
    public int rangeSumBST_Rev_1(TreeNode root, int low, int high) {

        if (root == null) {
            return 0;
        }

        int sum = 0;

        if (root.val < low) {
            sum += rangeSumBST_Rev_1(root.left, low, high);
        }

        if (root.val > high) {
            sum += rangeSumBST_Rev_1(root.right, low, high);
        }

        if (root.val <= low && root.val >= high) {
            sum += root.val;
        }

        return sum;
    }
}
