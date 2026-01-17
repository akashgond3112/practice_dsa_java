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

    // Revision on 25/10/2025
    class SolutionRevisionThirdDay {
        private int maxPath;

        public int maxPathSum(TreeNode root) {
            maxPath = Integer.MIN_VALUE;
            dfs(root);
            return maxPath;
        }

        private int dfs(TreeNode node) {
            if (node == null) {
                return 0;
            }

            // Recursively get the max path sum from left and right subtrees
            // If a subtree path sum is negative, we don't include it (hence Math.max(...,
            // 0))
            int leftMax = Math.max(0, dfs(node.left));
            int rightMax = Math.max(0, dfs(node.right));

            // Update the global maximum path sum.
            // This path can "split" at the current node.
            maxPath = Math.max(maxPath, node.val + leftMax + rightMax);

            // Return the max path sum that can extend upwards to the parent.
            // A path cannot split, so we take the current node's value plus the larger of
            // the two sub-paths.
            return node.val + Math.max(leftMax, rightMax);
        }
    }

    // Revision on 25/10/2025
    class SolutionRevisionSeventhDay {

        private int maxPath;

        public int maxPathSum(TreeNode root) {

            maxPath = Integer.MIN_VALUE;
            dfs(root);
            return maxPath;
        }

        private int dfs(TreeNode node) {
            if (node == null) {
                return 0;
            }

            int leftMax = Math.max(0, dfs(node.left));
            int rightMax = Math.max(0, dfs(node.right));

            maxPath = Math.max(maxPath, node.val + leftMax + rightMax);

            return (node.val + leftMax + rightMax);
        }

    }

    // Revision on 11/14/2025
    class SolutionRevisionFourteenDay {

        private int maxPath;

        public int maxPathSum(TreeNode root) {
            this.maxPath = Integer.MIN_VALUE;
            dfs(root);
            return maxPath;
        }

        private int dfs(TreeNode node) {

            if (node == null) {
                return 0;
            }

            int leftmax = Math.max(maxPath, dfs(node.left));
            int rightmax = Math.max(maxPath, dfs(node.right));

            maxPath = Math.max(maxPath, node.val + leftmax + rightmax);

            return (node.val + leftmax + rightmax);
        }
    }

    // Revision on 12/13/2025
    class SolutionRevisionDayThirty {

        private int maxPath;

        public int maxPathSum(TreeNode root) {
            this.maxPath = Integer.MIN_VALUE;
            dfs(root);
            return maxPath;
        }

        private int dfs(TreeNode node) {

            if (node == null) {
                return 0;
            }

            int leftMax = Math.max(maxPath, dfs(node.left));
            int rightMax = Math.max(maxPath, dfs(node.right));

            int curMax = node.val + leftMax + rightMax;

            maxPath = Math.max(maxPath, curMax);

            return curMax;
        }
    }
}
