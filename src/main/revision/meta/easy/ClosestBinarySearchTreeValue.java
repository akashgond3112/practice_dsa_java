/**
 * @author agond
 * @date Jun 17, 2025
 * @time 7:23:32 PM
 */
package main.revision.meta.easy;

import main.revision.meta.TreeNode;

public class ClosestBinarySearchTreeValue {

    public int closestValue(TreeNode root, double target) {

        int closetValue = root.val;
        double minDiff = Double.MAX_VALUE;

        TreeNode curNode = root;

        while (curNode != null) {

            double curDifference = Math.abs(curNode.val - target);

            if (curDifference < minDiff || (curDifference == minDiff && curNode.val < closetValue)) {
                minDiff = curDifference;
                closetValue = curNode.val;
            }

            if (curNode.val < target) {
                curNode = curNode.left;
            } else {
                curNode = curNode.right;
            }
        }

        return closetValue;
    }
}
