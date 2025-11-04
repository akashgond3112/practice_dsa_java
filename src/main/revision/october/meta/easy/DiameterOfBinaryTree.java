package main.revision.october.meta.easy;

import main.revision.october.meta.TreeNode;
import java.util.*;

public class DiameterOfBinaryTree {
    public int diameterOfBinaryTree(TreeNode root) {
        Map<TreeNode, Integer> map = new HashMap<>();
        Stack<TreeNode> st = new Stack<>();
        int diameter = 0;
        if (root != null) {
            st.push(root);
        }
        while (!st.isEmpty()) {
            TreeNode node = st.peek();
            if (node.left != null && !map.containsKey(node.left)) {
                st.push(node.left);
            } else if (node.right != null && !map.containsKey(node.right)) {
                st.push(node.right);
            } else {
                st.pop();
                int leftDepth = map.getOrDefault(node.left, 0);
                int rightDepth = map.getOrDefault(node.right, 0);

                map.put(node, 1 + Math.max(leftDepth, rightDepth));

                diameter = Math.max(diameter, leftDepth + rightDepth);
            }
        }

        return diameter;
    }

    // Revision on 20/10/2025
    public int diameterOfBinaryTreeRevisionSeventhDay(TreeNode root) {

        Map<TreeNode, Integer> map = new HashMap<>();
        Stack<TreeNode> st = new Stack<>();
        int diameter = 0;
        if (root != null) {
            st.push(root);
        }

        while (!st.isEmpty()) {
            TreeNode node = st.peek();

            if (node.left != null && !map.containsKey(node.left)) {
                st.push(node.left);
            } else if (node.right != null && !map.containsKey(node.right)) {
                st.push(node.right);
            } else {
                st.pop();
                int leftDepth = map.getOrDefault(node.left, 0);
                int rightDepth = map.getOrDefault(node.right, 0);

                map.put(node, 1 + Math.max(leftDepth, rightDepth));
                diameter = Math.max(diameter, leftDepth + rightDepth);
            }
        }

        return diameter;
    }

    // Revision on 04/11/2025
    public int diameterOfBinaryTreeRevisionFourteenDay(TreeNode root) {

        Map<TreeNode, Integer> map = new HashMap<>();
        Stack<TreeNode> st = new Stack<>();
        int diameter = 0;

        if (root != null) {
            st.push(root);
        }

        while (!st.isEmpty()) {

            TreeNode node = st.peek();

            if (node.left != null && !map.containsKey(node.left)) {
                st.push(node.left);
            } else if (node.right != null && !map.containsKey(node.right)) {
                st.push(node.right);
            } else {
                st.pop();
                int leftDepth = map.getOrDefault(node.left, 0);
                int rightDepth = map.getOrDefault(node.right, 0);

                map.put(node, 1 + Math.max(leftDepth, rightDepth));
                diameter = Math.max(diameter, leftDepth + rightDepth);
            }
        }

        return diameter;
    }
}