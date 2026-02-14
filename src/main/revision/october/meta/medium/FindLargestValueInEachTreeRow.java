package main.revision.october.meta.medium;

import java.util.*;

import main.revision.october.meta.TreeNode;

public class FindLargestValueInEachTreeRow {

    class Solution {
        public List<Integer> largestValues(TreeNode root) {
            // Result list banate hain jo har row ka largest value store karega
            List<Integer> result = new ArrayList<>();

            // Agar root null hai to empty result return karte hain
            if (root == null) {
                return result;
            }

            // Queue banate hain BFS ke liye aur root ko add karte hain
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);

            // Jab tak queue empty na ho, har level ka largest value find karte hain
            while (!q.isEmpty()) {
                int levelSize = q.size(); // Current level ke nodes ka size
                int max = Integer.MIN_VALUE; // Level ka maximum value track karne ke liye

                // Current level ke saare nodes process karte hain
                for (int i = 0; i < levelSize; i++) {
                    TreeNode node = q.poll(); // Queue se ek node nikalte hain
                    max = Math.max(max, node.val); // Max value update karte hain

                    // Agar left child hai to queue me add karte hain
                    if (node.left != null) {
                        q.add(node.left);
                    }

                    // Agar right child hai to queue me add karte hain
                    if (node.right != null) {
                        q.add(node.right);
                    }
                }
                // Current level ka max value result list me add karte hain
                result.add(max);
            }

            // Final result return karte hain
            return result;
        }
    }

    // revised on 12/27/2025
    class SolutionRevisedOnThirdDay {
        public List<Integer> largestValues(TreeNode root) {

            List<Integer> result = new ArrayList<>();

            if (root == null) {
                return result;
            }

            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);

            while (!q.isEmpty()) {
                int levels = q.size();
                int max = Integer.MIN_VALUE;

                for (int i = 0; i < levels; i++) {
                    TreeNode cur = q.poll();
                    max = Math.max(max, cur.val);

                    if (cur.left != null) {
                        q.add(cur.left);
                    }

                    if (cur.right != null) {
                        q.add(cur.right);
                    }
                }
                result.add(max);
            }

            return result;
        }
    }

    // revised on 1/2/2026
    class SolutionRevisedOnSeventhDay {
        public List<Integer> largestValues(TreeNode root) {

            List<Integer> result = new ArrayList<>();

            if (root == null) {
                return result;
            }

            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);

            while (!q.isEmpty()) {

                int levelSize = q.size();

                int max = Integer.MIN_VALUE;
                for (int i = 0; i < levelSize; i++) {
                    TreeNode cur = q.poll();
                    max = Math.max(max, cur.val);

                    if (cur.right != null) {
                        q.offer(cur.right);
                    }

                    if (cur.left != null) {
                        q.offer(cur.left);
                    }
                }

                result.add(max);
            }
            return result;
        }
    }

    // revised on 1/16/2026
    class SolutionRevisedOnDayFourteen {
        public List<Integer> largestValues(TreeNode root) {

            List<Integer> result = new ArrayList<>();

            if (root == null) {
                return null;
            }

            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);

            while (!q.isEmpty()) {

                int level = q.size();
                int max = Integer.MIN_VALUE;

                for (int i = 0; i < level; i++) {
                    TreeNode cur = q.poll();
                    max = Math.max(max, cur.val);

                    if (cur.left != null) {
                        q.add(cur.left);
                    }

                    if (cur.right != null) {
                        q.add(cur.right);
                    }
                }

                result.add(max);
            }
            return result;
        }
    }

    // revised on 2/13/2026
    class SolutionRevisedOnDayThirty {
        public List<Integer> largestValues(TreeNode root) {

            List<Integer> result = new ArrayList<>();

            if (root == null) {
                return result;
            }

            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);

            while (!q.isEmpty()) {

                int levelSize = q.size();
                int max = Integer.MIN_VALUE;

                for (int i = 0; i < levelSize; i++) {
                    TreeNode cur = q.poll();

                    max = Math.max(max, cur.val);

                    if (cur.left != null) {
                        q.add(cur.left);
                    }

                    if (cur.right != null) {
                        q.add(cur.right);
                    }
                }

                result.add(max);
            }

            return result;
        }
    }
}
