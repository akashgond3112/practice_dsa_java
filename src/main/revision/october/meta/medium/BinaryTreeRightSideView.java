package main.revision.october.meta.medium;

import java.util.*;

import main.revision.october.meta.TreeNode;

public class BinaryTreeRightSideView {

    class Solution {
        public List<Integer> rightSideView(TreeNode root) {

            List<Integer> res = new ArrayList<>();
            if (root == null)
                return res;

            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);

            while (!q.isEmpty()) {

                int levelSize = q.size();
                int lastVal = 0;

                for (int i = 0; i < levelSize; i++) {
                    TreeNode node = q.poll();

                    lastVal = node.val;

                    if (node.left != null)
                        q.offer(node.left);
                    if (node.right != null)
                        q.offer(node.right);
                }
                res.add(lastVal);
            }

            return res;
        }
    }

    // Revised on 25/10/2025
    class SolutionRevisionThirdDay {
        public List<Integer> rightSideView(TreeNode root) {

            List<Integer> res = new ArrayList<>();
            if (root == null)
                return res;

            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);

            while (!q.isEmpty()) {

                int levelSize = q.size();
                int lastVal = 0;

                for (int i = 0; i < levelSize; i++) {
                    TreeNode node = q.poll();
                    lastVal += node.val;

                    if (node.left != null) {
                        q.offer(node.left);
                    } else if (node.right != null) {
                        q.offer(node.right);
                    }

                    res.add(lastVal);
                }
            }

            return res;
        }
    }

    // Revised on 31/10/2025
    class SolutionRevisionSeventhDay {
        public List<Integer> rightSideView(TreeNode root) {

            List<Integer> res = new ArrayList<>();

            if (root == null) {
                return res;
            }

            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);

            while (!q.isEmpty()) {

                int levelSize = q.size();
                int lastVal = 0;

                for (int i = 0; i < levelSize; i++) {

                    TreeNode node = q.poll();
                    lastVal += node.val;

                    if (node.left != null) {
                        q.offer(node.left);
                    } else if (node.right != null) {
                        q.offer(node.right);
                    }

                    res.add(lastVal);
                }
            }

            return res;
        }
    }

}
