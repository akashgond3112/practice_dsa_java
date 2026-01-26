package main.revision.october.meta.medium;

import main.revision.october.meta.TreeNode;

public class SumRootToLeafNumbers {

    public class Solution {
        public int sumNumbers(TreeNode root) {
            // DFS traversal start karte hain root node se, aur initial currentSum 0 set
            // karte hain
            return dfs(root, 0);
        }

        private int dfs(TreeNode node, int currentSum) {
            // Agar node null hai, iska matlab hum leaf ke baad pohanch gaye hain, to 0
            // return karte hain
            if (node == null) {
                return 0;
            }

            // Current node ka value currentSum me add karte hain, 10 se multiply karke
            currentSum = currentSum * 10 + node.val;

            // Agar current node leaf node hai (left aur right dono null hain), to
            // currentSum return karte hain
            if (node.left == null && node.right == null) {
                return currentSum;
            }

            // Left aur right subtree ke liye recursive call karte hain
            int leftSum = dfs(node.left, currentSum);
            int rightSum = dfs(node.right, currentSum);

            // Dono subtree ka sum return karte hain
            return leftSum + rightSum;
        }
    }

    // revised on 12/9/2025
    public class SolutionRevisedOnThirdDay {

        public int sumNumbers(TreeNode root) {
            return dfs(root, 0);
        }

        private int dfs(TreeNode node, int currentSum) {
            if (node == null) {
                return 0;
            }

            if (node.left == null || node.right == null) {
                return currentSum;
            }

            int leftSum = dfs(node.left, currentSum);
            int rightSum = dfs(node.right, currentSum);

            return leftSum + rightSum;
        }
    }

    // revised on 12/15/2025
    public class SolutionRevisedOnFourteenDay {

        public int sumNumbers(TreeNode root) {
            return dfs(root, 0);
        }

        private int dfs(TreeNode node, int currentSum) {
            if (node == null) {
                return 0;
            }

            if (node.left == null || node.right == null) {
                return currentSum;
            }

            int leftSum = dfs(node.left, currentSum);
            int rightSum = dfs(node.right, currentSum);

            return leftSum + rightSum;
        }
    }

    // revised on 12/29/2025
    class SolutionReviseOnFourteenDay {
        public int sumNumbers(TreeNode root) {

            return dfs(root, 0);
        }

        private int dfs(TreeNode node, int currentSum) {

            if (node == null) {
                return 0;
            }

            currentSum = currentSum * 10 + node.val;

            if (node.left == null || node.right == null) {
                return currentSum;
            }

            int leftSum = dfs(node.left, currentSum);
            int rightSum = dfs(node.right, currentSum);

            return leftSum + rightSum;
        }
    }

    // revised on 1/25/2026
    class SolutionRevisedOnDayThirty {
        public int sumNumbers(TreeNode root) {

            return dfs(root, 0);
        }

        private int dfs(TreeNode node, int curretnSum) {

            if (node == null) {
                return 0;
            }

            curretnSum += curretnSum * 10 + node.val;

            if (node.left != null && node.right != null) {
                return curretnSum;
            }

            int leftSum = dfs(node.left, curretnSum);
            int rightSum = dfs(node.right, curretnSum);

            return leftSum + rightSum;
        }
    }
}
