package main.revision.october.meta.easy;

import main.revision.october.meta.TreeNode;

public class MergeTwoBinaryTrees {

    class Solution {
        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {

            // Agar pehla tree (root1) null hai, toh doosra tree (root2) hi result hoga.
            if (root1 == null) {
                return root2;
            }

            // Agar doosra tree (root2) null hai, toh pehla tree (root1) hi result hoga.
            if (root2 == null) {
                return root1;
            }

            // Dono nodes ki values ko add karke pehle node (root1) mein store karo.
            root1.val = root1.val + root2.val;

            // Recursively left subtrees ko merge karo aur result ko root1 ke left child
            // mein set karo.
            root1.left = mergeTrees(root1.left, root2.left);

            // Recursively right subtrees ko merge karo aur result ko root1 ke right child
            // mein set karo.
            root1.right = mergeTrees(root1.right, root2.right);

            // Modified pehla tree (root1) return karo.
            return root1;
        }
    }

    // revised on 12/22/2025
    class SolutionRevisedOnThirdDay {
        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {

            if (root1 == null) {
                return root2;
            }
            if (root2 == null) {
                return root1;
            }

            root1.val = root1.val + root2.val;

            root1.left = mergeTrees(root1.left, root2.left);
            root1.right = mergeTrees(root1.right, root2.right);

            return root1;
        }
    }

    // revised on 12/28/2025
    class SolutionRevisedOnSeventhDay {
        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {

            if (root1 == null) {
                return root2;
            }

            if (root2 == null) {
                return root1;
            }

            root1.val = root1.val + root2.val;

            root1.left = mergeTrees(root1.left, root2.left);
            root1.right = mergeTrees(root1.right, root2.right);

            return root1;
        }
    }

    // revised on 1/11/2026
    class SolutionRevisedOnDayFourteen {
        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {

            if (root1 == null) {
                return root2;
            }

            if (root2 == null) {
                return root1;
            }

            root1.val = root1.val + root2.val;

            root1.left = mergeTrees(root1.left, root2.left);
            root1.right = mergeTrees(root1.right, root2.right);

            return root1;
        }
    }

    // revised on 2/9/2026
    class SolutionRevisedOnDayThirty {
        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {

            if (root1 == null) {
                return root2;
            }

            if (root2 == null) {
                return root1;
            }

            root1.val = root1.val + root2.val;

            root1.left = mergeTrees(root1.left, root2.left);
            root1.right = mergeTrees(root1.right, root2.right);

            return root1;
        }
    }
}
