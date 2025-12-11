package main.revision.october.meta.easy;

import main.revision.october.meta.TreeNode;

public class PathSum {
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
