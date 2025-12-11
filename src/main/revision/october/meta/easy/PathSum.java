package main.revision.october.meta.easy;

import main.revision.october.meta.TreeNode;

public class PathSum {
    /**
     * Given the root of a binary tree and an integer targetSum, this class
     * determines if the tree has a root-to-leaf path such that adding up all
     * the values along the path equals targetSum.
     *
     * <p>A leaf is a node with no children.
     *
     * <p>The method recursively traverses the tree. For each node, it subtracts the
     * node's value from the target sum and passes the new sum to its children.
     * When a leaf node is reached, it checks if the remaining target sum is equal
     * to the leaf's value.
     *
     * <p><b>Time Complexity:</b> O(N), where N is the number of nodes in the tree.
     * In the worst case, the algorithm has to visit every node once.
     *
     * <p><b>Space Complexity:</b> O(H), where H is the height of the tree. This
     * represents the maximum depth of the recursion stack. In the worst case
     * (a skewed tree), the space complexity can be O(N). For a balanced tree,
     * it would be O(log N).
     */
    class Solution {
        public boolean hasPathSum(TreeNode root, int target) {
            if (root == null)
                return false;

            if (root.left == null && root.right == null)
                return target == root.val;

            return hasPathSum(root.left, target - root.val) ||
                    hasPathSum(root.right, target - root.val);
        }
    }
}
