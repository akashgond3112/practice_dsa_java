package main.revision.march.hard;

import java.util.*;

import main.revision.march.TreeNode;

public class VerticalOrderTraversalOfABinaryTree {

    public static class Tuple {
        TreeNode node;
        int vertical;
        int level;

        Tuple(TreeNode node, int vertical, int level) {
            this.node = node;
            this.vertical = vertical;
            this.level = level;
        }
    }

    // 15/03/2026
    class SolutionOnDayFirst {
        public List<List<Integer>> verticalTraversal(TreeNode root) {

            Map<Integer, Map<Integer, PriorityQueue<Integer>>> map = new HashMap<>();

            Queue<Tuple> q = new LinkedList<>();
            q.offer(new Tuple(root, 0, 0));

            while (!q.isEmpty()) {

                Tuple cur = q.poll();
                TreeNode curNode = cur.node;
                int curVertical = cur.vertical;
                int curLevel = cur.level;

                if (!map.containsKey(curVertical)) {
                    map.put(curVertical, new HashMap<>());
                }

                if (!map.get(curVertical).containsKey(curLevel)) {
                    map.get(curVertical).put(curLevel, new PriorityQueue<>());
                }

                map.get(curVertical).get(curLevel).offer(curNode.val);

                if (curNode.left != null) {
                    q.offer(new Tuple(curNode.left, curVertical - 1, curLevel + 1));
                }

                if (curNode.right != null) {
                    q.offer(new Tuple(curNode.right, curVertical + 1, curLevel + 1));
                }
            }

            List<List<Integer>> res = new ArrayList<>();

            for (Map<Integer, PriorityQueue<Integer>> m : map.values()) {
                res.add(new ArrayList<>());

                for (PriorityQueue<Integer> pq : m.values()) {
                    while (!pq.isEmpty()) {
                        res.getLast().add(pq.poll());
                    }
                }
            }

            return res;
        }
    }

    // 18/03/2026
    class SolutionRevisedOnDayThird {
        public List<List<Integer>> verticalTraversal(TreeNode root) {
            Map<Integer, Map<Integer, PriorityQueue<Integer>>> map = new HashMap<>();

            Queue<Tuple> q = new LinkedList<>();
            q.offer(new Tuple(root, 0, 0));

            while (!q.isEmpty()) {

                Tuple cur = q.poll();
                TreeNode node = cur.node;
                int level = cur.level;
                int vertical = cur.vertical;

                if (!map.containsKey(vertical)) {
                    map.put(vertical, new HashMap<>());
                }

                if (!map.get(vertical).containsKey(level)) {
                    map.get(vertical).put(level, new PriorityQueue<>());
                }

                map.get(vertical).get(level).offer(node.val);

                if (node.left != null) {
                    q.offer(new Tuple(node.left, vertical--, level++));
                }

                if (node.right != null) {
                    q.offer(new Tuple(node.left, vertical++, level++));
                }
            }

            List<List<Integer>> result = new ArrayList<>();

            for (Map<Integer, PriorityQueue<Integer>> m : map.values()) {

                result.add(new ArrayList<>());

                for (PriorityQueue<Integer> pq : m.values()) {
                    while (!pq.isEmpty()) {
                        result.getLast().add(pq.poll());
                    }
                }
            }

            return result;
        }
    }
}
