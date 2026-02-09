package main.revision.october.meta.hard;

import main.revision.october.meta.TreeNode;

import java.util.*;

public class VerticalOrderTraversalofBinaryTree {

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

    public List<List<Integer>> verticalTraversal(TreeNode root) {

        Map<Integer, Map<Integer, PriorityQueue<Integer>>> map = new HashMap<>();

        Queue<Tuple> q = new LinkedList<>();

        q.offer(new Tuple(root, 0, 0));

        while (!q.isEmpty()) {

            Tuple cur = q.poll();
            TreeNode curNode = cur.node;
            int vertical = cur.vertical;
            int level = cur.level;

            if (!map.containsKey(vertical)) {
                map.put(vertical, new HashMap<>());
            }

            if (!map.get(vertical).containsKey(level)) {
                map.get(vertical).put(level, new PriorityQueue<>());
            }

            map.get(vertical).get(level).offer(curNode.val);

            if (curNode.left != null) {
                q.offer(new Tuple(curNode.left, vertical - 1, level + 1));
            }

            if (curNode.right != null) {
                q.offer(new Tuple(curNode.right, vertical + 1, level + 1));
            }
        }

        List<List<Integer>> res = new ArrayList<>();

        for (Map<Integer, PriorityQueue<Integer>> val : map.values()) {

            res.add(new ArrayList<>());

            for (PriorityQueue<Integer> node : val.values()) {
                while (!node.isEmpty()) {
                    res.get(res.size() - 1).add(node.poll());
                }
            }
        }

        return res;

    }

    // Revised on 06.10.2025
    public List<List<Integer>> verticalTraversal_Rev_1(TreeNode root) {

        Map<Integer, Map<Integer, PriorityQueue<Integer>>> map = new HashMap<>();

        Queue<Tuple> q = new LinkedList<>();

        q.offer(new Tuple(root, 0, 0));

        while (!q.isEmpty()) {

            Tuple t = q.poll();

            TreeNode node = t.node;

            int vertical = t.vertical;
            int level = t.level;

            if (!map.containsKey(vertical)) {
                map.put(vertical, new HashMap<>());
            }

            if (!map.get(vertical).containsKey(level)) {
                map.get(vertical).put(level, new PriorityQueue<Integer>());
            }

            map.get(vertical).get(level).offer(node.val);

            if (node.left != null) {
                q.offer(new Tuple(node.left, vertical - 1, level + 1));
            }

            if (node.right != null) {
                q.offer(new Tuple(node.right, vertical + 1, level + 1));
            }
        }

        List<List<Integer>> list = new ArrayList<>();

        for (Map<Integer, PriorityQueue<Integer>> val : map.values()) {

            list.add(new ArrayList<>());

            for (PriorityQueue<Integer> v : val.values()) {
                while (!v.isEmpty()) {
                    list.get(list.size() - 1).add(v.poll());
                }
            }
        }

        return list;
    }

    // Revised on 12.10.2025
    public List<List<Integer>> verticalTraversal_Rev_2(TreeNode root) {

        // Vertical, <Level, PQ>
        Map<Integer, Map<Integer, PriorityQueue<Integer>>> map = new HashMap<>();

        Queue<Tuple> q = new LinkedList<>();

        q.offer(new Tuple(root, 0, 0));

        while (!q.isEmpty()) {

            Tuple cur = q.peek();
            TreeNode curNode = cur.node;
            int curVertical = cur.vertical;
            int curLevel = cur.level;

            // check if vertical is there in map or not
            if (!map.containsKey(curVertical)) {
                map.put(curVertical, new HashMap<>());
            }

            // check if the vertical level is present or not
            if (!map.get(curVertical).containsKey(curLevel)) {
                map.get(curVertical).put(curLevel, new PriorityQueue<>());
            }

            // if vertical and level botht are present then add the val

            map.get(curVertical).get(curLevel).offer(curNode.val);

            // now move left if not null

            if (curNode.left != null) {
                q.offer(new Tuple(curNode.left, curVertical - 1, curLevel + 1));
            }

            // now move right if not null
            if (curNode.right != null) {
                q.offer(new Tuple(curNode.right, curVertical + 1, curLevel + 1));
            }
        }

        List<List<Integer>> res = new ArrayList<>();

        for (Map<Integer, PriorityQueue<Integer>> val : map.values()) {

            res.add(new ArrayList<>());

            for (PriorityQueue<Integer> p : val.values()) {
                res.get(res.size() - 1).add(p.poll());
            }
        }

        return res;
    }

    // Revised on 26.10.2025
    public List<List<Integer>> verticalTraversal_Rev_3(TreeNode root) {

        Map<Integer, Map<Integer, PriorityQueue<Integer>>> map = new HashMap<>();

        Queue<Tuple> q = new LinkedList<>();

        q.offer(new Tuple(root, 0, 0));

        while (!q.isEmpty()) {

            Tuple cur = q.poll();
            TreeNode node = cur.node;
            int vertical = cur.vertical;
            int level = cur.level;

            if (!map.containsKey(vertical)) {
                map.put(vertical, new HashMap<>());
            }

            if (!map.get(vertical).containsKey(level)) {
                map.get(vertical).put(level, new PriorityQueue<>());
            }

            map.get(vertical).get(level).offer(node.val);

            if (node.left != null) {
                q.offer(new Tuple(node.left, vertical - 1, level + 1));
            }

            if (node.right != null) {
                q.offer(new Tuple(node.right, vertical + 1, level + 1));
            }
        }

        List<List<Integer>> res = new ArrayList<>();

        for (Map<Integer, PriorityQueue<Integer>> val : map.values()) {
            res.add(new ArrayList<>());

            for (PriorityQueue<Integer> v : val.values()) {
                while (!v.isEmpty()) {
                    res.get(res.size() - 1).add(v.poll());
                }
            }
        }

        return res;
    }

    // Revised on 11/24/2025
    public List<List<Integer>> verticalTraversal_Rev_4(TreeNode root) {

        Map<Integer, Map<Integer, PriorityQueue<Integer>>> map = new HashMap<>();

        Queue<Tuple> q = new LinkedList<>();
        q.offer(new Tuple(root, 0, 0));

        while (!q.isEmpty()) {

            Tuple t = q.poll();

            TreeNode node = t.node;

            int vertical = t.vertical;
            int level = t.level;

            if (!map.containsKey(vertical)) {
                map.put(vertical, new HashMap<>());
            }

            if (!map.get(vertical).containsKey(level)) {
                map.get(vertical).put(level, new PriorityQueue<>());
            }

            map.get(vertical).get(level).add(node.val);

            if (node.left != null) {
                q.offer(new Tuple(node.left, vertical - 1, level + 1));
            }

            if (node.right != null) {
                q.offer(new Tuple(node.right, vertical + 1, level + 1));
            }
        }

        List<List<Integer>> list = new ArrayList<>();

        for (Map<Integer, PriorityQueue<Integer>> val : map.values()) {

            list.add(new ArrayList<>());

            for (PriorityQueue<Integer> pq : val.values()) {
                list.get(list.size() - 1).add(pq.poll());
            }
        }

        return list;
    }
}