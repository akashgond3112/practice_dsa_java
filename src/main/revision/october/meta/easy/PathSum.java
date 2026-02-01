package main.revision.october.meta.easy;

import main.revision.october.meta.TreeNode;

public class PathSum {
    /**
     * Given the root of a binary tree and an integer targetSum, this class
     * determines if the tree has a root-to-leaf path such that adding up all
     * the values along the path equals targetSum.
     *
     * <p>
     * A leaf is a node with no children.
     *
     * <p>
     * Step-by-step (Hinglish Explanation):
     * 1. Sabse pehle check karo ki root null hai ya nahi. Agar null hai toh koi
     * path exist nahi karta, toh false return karo.
     * 2. Agar current node leaf hai (left aur right dono null hain), toh check karo
     * ki target sum abhi ke node ke value ke barabar hai ya nahi. Agar hai toh true
     * return karo.
     * 3. Agar leaf nahi hai, toh left aur right subtree mein recursively check
     * karo, aur target sum mein se current node ka value minus kar do.
     * 4. Agar kisi bhi subtree mein path mil gaya toh true return ho jayega, warna
     * false.
     *
     * <p>
     * <b>Time Complexity:</b> O(N), kyunki har node ek baar visit hoti hai.
     * <p>
     * <b>Space Complexity:</b> O(H), jahan H tree ki height hai (recursion stack ke
     * liye).
     */
    class Solution {
        public boolean hasPathSum(TreeNode root, int target) {
            // Step 1: Root null hai? Toh koi path nahi ho sakta.
            if (root == null)
                return false;

            // Step 2: Leaf node hai? Target sum check karo.
            if (root.left == null && root.right == null)
                return target == root.val;

            // Step 3: Left aur right subtree mein check karo, target se current value minus
            // karke.
            return hasPathSum(root.left, target - root.val) ||
                    hasPathSum(root.right, target - root.val);
        }
    }

    // revised on 12/14/2025
    class SolutionRevisedOnThirDay {
        public boolean hasPathSum(TreeNode root, int target) {
            // Step 1: Root null hai? Toh koi path nahi ho sakta.
            if (root == null)
                return false;

            // Step 2: Leaf node hai? Target sum check karo.
            if (root.left == null && root.right == null)
                return target == root.val;

            // Step 3: Left aur right subtree mein check karo, target se current value minus
            // karke.
            return hasPathSum(root.left, target - root.val) ||
                    hasPathSum(root.right, target - root.val);
        }
    }

    // revised on 12/20/2025
    class SolutionRevisedOnSeventhDay {
        public boolean hasPathSum(TreeNode root, int target) {

            if (root == null) {
                return false;
            }

            if (root.left == null && root.right == null) {
                return target == root.val;
            }

            return hasPathSum(root.left, target - root.val) || hasPathSum(root.right, target - root.val);
        }
    }

    // revised on 1/3/2026
    class SolutionRevisedOnDayFourteen {
        public boolean hasPathSum(TreeNode root, int target) {
            if (root == null)
                return false;

            if (root.left == null && root.right == null)
                return target == root.val;

            return hasPathSum(root.left, target - root.val) ||
                    hasPathSum(root.right, target - root.val);
        }
    }

    // revised on 2/1/2026
    class SolutionRevisedOnDayThirty {
        public boolean hasPathSum(TreeNode root, int target) {
            if (root == null) {
                return false;
            }

            if (root.left == null && root.right == null) {
                return target == root.val;
            }

            return hasPathSum(root.left, target - root.val) || hasPathSum(root.right, target - root.val);
        }
    }
}
