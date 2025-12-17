package main.revision.october.meta.easy;

import main.revision.meta.TreeNode;

public class ClosestBinarySearchTreeValue {

    public class Solution {

        public int closestValue(TreeNode root, double target) {

            double minDifference = Double.MAX_VALUE;

            int closestValue = root.val;
            TreeNode currentNode = root;

            while (root != null) {

                double currentDifference = Math.abs(currentNode.val - target);

                if (currentDifference < minDifference ||
                        (currentDifference == minDifference && currentNode.val < closestValue)) {
                    minDifference = currentDifference;
                    closestValue = currentNode.val;
                }

                // Decide which subtree to explore next
                if (currentNode.val > target) {
                    // If current value is greater than target, go left
                    currentNode = currentNode.left;
                } else {
                    // If current value is less than or equal to target, go right
                    currentNode = currentNode.right;
                }

            }

            return closestValue;
        }
    }

    // revised on 29/10/2025
    public class SolutionRevisionOnThirdDay {

        public int closestValue(TreeNode root, double target) {

            double minDifference = Double.MAX_VALUE;
            int closestValue = root.val;
            TreeNode node = root;

            while (root != null) {

                double currentDifference = Math.abs(node.val - target);

                if (currentDifference < minDifference || (minDifference == currentDifference &&
                        node.val < closestValue)) {
                    minDifference = currentDifference;
                    closestValue = node.val;
                }

                if (node.val > target) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }

            return closestValue;
        }
    }

    // revised on 04/11/2025
    public class SolutionRevisionOnSeventhDay {

        public int closestValue(TreeNode root, double target) {

            double minDifference = Double.MAX_VALUE;
            int closetValue = root.val;
            TreeNode node = root;

            while (root != null) {

                double currentDifference = Math.abs(node.val - target);

                if (currentDifference < minDifference ||
                        (minDifference == currentDifference && node.val < closetValue)) {
                    minDifference = currentDifference;
                    closetValue = root.val;
                }

                if (node.val > target) {

                    node = node.left;
                } else {
                    node = node.right;
                }
            }

            return closetValue;
        }
    }

    // revised on 04/11/2025
    public class SolutionRevisionOnFourteenDay {

        public int closestValue(TreeNode root, double target) {
            double minDifference = Double.MAX_VALUE;
            int closetValue = root.val;
            TreeNode node = root;

            while (root != null) {

                double curDiff = Math.abs(root.val - target);

                if (curDiff < minDifference || (minDifference == curDiff && node.val < closetValue)) {
                    minDifference = curDiff;
                    closetValue = root.val;
                }

                if (node.val > target) {
                    node = node.right;
                } else {
                    node = node.left;
                }
            }

            return closetValue;
        }
    }

    // revised on 12/17/2025
    public class SolutionRevisionOnThirtyDay {

        public int closestValue(TreeNode root, double target) {
            double minDifference = Double.MAX_VALUE;
            int closetValue = root.val;
            TreeNode node = root;

            while (root != null) {

                double curDiff = Math.abs(root.val - target);

                if (curDiff < minDifference || (minDifference == curDiff && node.val < closetValue)) {
                    minDifference = curDiff;
                    closetValue = root.val;
                }

                if (node.val > target) {
                    node = node.right;
                } else {
                    node = node.left;
                }
            }

            return closetValue;
        }
    }

}
