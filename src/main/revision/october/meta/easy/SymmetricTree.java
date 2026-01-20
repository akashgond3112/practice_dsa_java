package main.revision.october.meta.easy;

import main.revision.october.meta.TreeNode;

public class SymmetricTree {

    class Solution {
        public boolean isSymmetric(TreeNode root) {
            return root == null || checkIsSymmetric(root.left, root.right);
        }

        private static boolean checkIsSymmetric(TreeNode left, TreeNode right) {

            if (left == null && right == null) {
                return true;
            }

            if (left.val != right.val)
                return false;

            return checkIsSymmetric(left.left, right.right) && checkIsSymmetric(left.right, right.left);
        }
    }

    // revised on 12/2/2025
    class SolutionRevisedOnThirdDay {
        public boolean isSymmetric(TreeNode root) {
            return root == null || checkIsSymmetric(root.left, root.right);
        }

        private static boolean checkIsSymmetric(TreeNode left, TreeNode right) {

            if (left == null && right == null) {
                return true;
            }

            if (left.val != right.val)
                return false;

            return checkIsSymmetric(left.left, right.right) && checkIsSymmetric(left.right, right.left);
        }
    }

    // revised on 12/8/2025
    class SolutionRevisedOnSeventhDay {
        public boolean isSymmetric(TreeNode root) {
            return root == null || checkIsSymmetric(root.left, root.right);
        }

        private static boolean checkIsSymmetric(TreeNode left, TreeNode right) {

            if (left == null && right == null) {
                return true;
            }

            if (left.val != right.val)
                return false;

            return checkIsSymmetric(left.left, right.right) && checkIsSymmetric(left.right, right.left);
        }
    }

    // revised on 12/22/2025
    class SolutionRevisedOnFourteenDay {
        public boolean isSymmetric(TreeNode root) {
            return root == null || checkIsSymmetric(root.left, root.right);
        }

        private static boolean checkIsSymmetric(TreeNode left, TreeNode right) {

            if (left == null && right == null) {
                return true;
            }

            if (left.val != right.val)
                return false;

            return checkIsSymmetric(left.left, right.right) && checkIsSymmetric(left.right, right.left);
        }
    }

    // revised on 1/19/2026
    class SolutionRevisedOnDayThirty {
        public boolean isSymmetric(TreeNode root) {
            return root == null || checkIsSymmetric(root.left, root.right);
        }

        private static boolean checkIsSymmetric(TreeNode left, TreeNode right) {

            if (left == null && right == null) {
                return true;
            }

            if (left.val != right.val)
                return false;

            return checkIsSymmetric(left.left, right.right) && checkIsSymmetric(left.right, right.left);
        }
    }
}
