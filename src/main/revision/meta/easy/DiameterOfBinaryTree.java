/**
 * @author agond
 * @date May 29, 2025
 * @time 9:08:38 PM
 */
package main.revision.meta.easy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author agond
 * @date May 29, 2025
 * @time 9:08:38 PM
 */
public class DiameterOfBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int diameterOfBinaryTree(TreeNode root) {

        Map<TreeNode, Integer> map = new HashMap<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        int diameter = 0;

        if (root != null) {
            stack.push(root);
        }

        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();

            if (node.left != null && !map.containsKey(node.left)) {
                stack.push(node.left);
            } else if (node.right != null && !map.containsKey(node.right)) {
                stack.push(node.right);
            } else {

                stack.pop();
                int depthLeft = map.getOrDefault(node.left, 0);
                int depthRight = map.getOrDefault(node.right, 0);

                map.put(node, 1 + Math.max(depthLeft, depthRight));

                diameter = Math.max(diameter, depthLeft + depthRight);

            }
        }

        return diameter;
    }

}
