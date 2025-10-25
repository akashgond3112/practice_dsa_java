package main.revision.october.meta.hard;

import main.revision.october.meta.TreeNode;

public class BinaryTreeMaximumPathSum {

    class Solution {
        public int maxPathSum(TreeNode root) {
            int[] res = new int[] { Integer.MIN_VALUE };
            dfs(root, res);
            return res[0];
        }

        /**
         * Performs a depth-first search to compute the maximum path sum for the binary
         * tree.
         *
         * @param root the current TreeNode being processed
         * @param res  an array holding the maximum path sum found so far
         * @return the maximum path sum including the current node and one of its
         *         subtrees
         */
        private int dfs(TreeNode root, int[] res) {

            if (root == null) {
                return 0;
            }

            int leftMax = Math.max(dfs(root.left, res), 0);
            int rightMax = Math.max(dfs(root.right, res), 0);

            // Update res[0] with the maximum path sum passing through root, including both
            // left and right subtrees
            res[0] = Math.max(res[0], root.val + leftMax + rightMax);

            return root.val + Math.max(leftMax, rightMax);
        }
    }
}
