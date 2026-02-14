package main.revision.october.meta.easy;

import java.util.*;

import main.revision.october.meta.TreeNode;

public class AverageOfLevelsInBinaryTree {

    class Solution {
        public List<Double> averageOfLevels(TreeNode root) {

            // Result list banate hain jo har level ka average store karega
            List<Double> result = new ArrayList<>();
            if (root == null) {
                return result; // Agar root null hai toh empty result return karte hain
            }

            // Queue use karte hain level order traversal ke liye
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root); // Root ko queue mein daalte hain

            while (!q.isEmpty()) {
                int levelSize = q.size(); // Current level ke nodes ka size nikalte hain
                double sum = 0.0; // Current level ke nodes ka sum calculate karne ke liye

                // Har level ke nodes ko process karte hain
                for (int i = 0; i < levelSize; i++) {
                    TreeNode node = q.poll(); // Queue se ek node nikalte hain
                    sum += node.val; // Node ka value sum mein add karte hain

                    // Agar left child hai toh usse queue mein daalte hain
                    if (node.left != null) {
                        q.add(node.left);
                    }

                    // Agar right child hai toh usse queue mein daalte hain
                    if (node.right != null) {
                        q.add(node.right);
                    }
                }

                // Current level ka average calculate karke result mein add karte hain
                result.add(sum / levelSize);
            }

            return result; // Final result return karte hain
        }
    }

    // revised on 12/27/2025
    class SolutionRevisedOnThirdDay {
        public List<Double> averageOfLevels(TreeNode root) {

            List<Double> result = new ArrayList<>();

            if (root == null) {
                return result;
            }

            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);

            while (!q.isEmpty()) {
                int levels = q.size();
                double sum = 0.0;

                for (int i = 0; i < levels; i++) {
                    TreeNode node = q.poll();
                    sum += node.val;

                    if (node.left != null) {
                        q.add(node.left);
                    }

                    if (node.right != null) {
                        q.add(node.right);
                    }
                }

                result.add(sum / levels);
            }

            return result;
        }
    }

    // revised on 1/2/2026
    class SolutionRevisedOnSeventhDay {
        public List<Double> averageOfLevels(TreeNode root) {

            List<Double> result = new ArrayList<>();

            if (root == null) {
                return result;
            }

            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);

            while (!q.isEmpty()) {
                int level = q.size();
                double sum = 0.0;

                for (int i = 0; i < level; i++) {
                    TreeNode cur = q.poll();
                    sum += cur.val;

                    if (cur.left != null) {
                        q.offer(cur.left);
                    }

                    if (cur.right != null) {
                        q.offer(cur.right);
                    }
                }

                result.add(sum / level);
            }

            return result;
        }
    }

    // revised on 1/16/2026
    class SolutionRevisedOnDayFourteen {
        public List<Double> averageOfLevels(TreeNode root) {

            List<Double> result = new ArrayList<>();

            if (root == null) {
                return result;
            }

            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);

            while (!q.isEmpty()) {
                int level = q.size();
                double sum = 0.0;

                for (int i = 0; i < level; i++) {
                    TreeNode cur = q.poll();
                    sum += cur.val;

                    if (cur.left != null) {
                        q.offer(cur.left);
                    }

                    if (cur.right != null) {
                        q.offer(cur.right);
                    }
                }

                result.add(sum / level);
            }

            return result;
        }
    }

    // revised on 2/13/2026
    class SolutionRevisedOnDayThirty {
        public List<Double> averageOfLevels(TreeNode root) {

            List<Double> result = new ArrayList<>();

            if (root == null) {
                return result;
            }

            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);

            while (!q.isEmpty()) {

                int levelSize = q.size();
                double sum = Double.MIN_VALUE;

                for (int i = 0; i < levelSize; i++) {
                    TreeNode cur = q.poll();

                    sum += cur.val;

                    if (cur.left != null) {
                        q.add(cur.left);
                    }

                    if (cur.right != null) {
                        q.add(cur.right);
                    }
                }

                result.add(sum / levelSize);
            }

            return result;
        }
    }

}
