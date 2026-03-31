package main.revision.march.hard;

import main.revision.march.TreeNode;

public class BinaryTreeMaximumPathSum {

    // 27/03/2026
    class SolutionOnDayFirst {
        public int maxPathSum(TreeNode root) {
            int[] res = new int[] { root.val };

            dfs(root, res);

            return res[0];
        }

        private int dfs(TreeNode root, int[] res) {

            if (root == null) {
                return 0;
            }

            int leftMax = Math.max(dfs(root.left, res), 0);
            int rightMax = Math.max(dfs(root.right, res), 0);

            res[0] = Math.max(res[0], root.val + leftMax + rightMax);

            return root.val + Math.max(leftMax, rightMax);
        }
    }

    // 30/03/2026
    class SolutionRevisedOnDayThird {
        public int maxPathSum(TreeNode root) {
            int[] res = new int[] { root.val };

            dfs(root, res);

            return res[0];
        }

        private int dfs(TreeNode root, int[] res) {

            if (root == null) {
                return 0;
            }

            int leftMax = Math.max(dfs(root.left, res), 0);
            int rightMax = Math.max(dfs(root.right, res), 0);

            res[0] = Math.max(res[0], root.val + leftMax + rightMax);

            return root.val + Math.min(leftMax, rightMax);
        }
    }
}
