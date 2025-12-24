package main.revision.october.meta.easy;

import java.util.*;

import main.revision.october.meta.TreeNode;

public class AverageOfLevelsInBinaryTree {

    class Solution {
        public List<Double> averageOfLevels(TreeNode root) {

            List<Double> result = new ArrayList<>();
            if (root == null) {
                return result;
            }

            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);

            while (!q.isEmpty()) {
                int levelSize = q.size();
                double sum = 0.0;

                for (int i = 0; i < levelSize; i++) {
                    TreeNode node = q.poll();
                    sum += node.val;

                    if (node.left != null) {
                        q.add(node.left);
                    }

                    if (node.right != null) {
                        q.add(node.right);
                    }
                }

                result.add(sum / levelSize);

            }

            return result;
        }
    }
}
