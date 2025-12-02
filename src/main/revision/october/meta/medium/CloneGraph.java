package main.revision.october.meta.medium;

import main.revision.october.meta.Node;
import java.util.*;

public class CloneGraph {

    class Solution {
        public Node cloneGraph(Node node) {

            if (node == null) {
                return null;
            }

            Map<Node, Node> oldToNew = new HashMap<>();
            Queue<Node> q = new LinkedList<>();
            oldToNew.put(node, new Node(node.val));
            q.add(node);

            while (!q.isEmpty()) {

                Node cur = q.poll();

                for (Node nei : cur.neighbors) {
                    if (!oldToNew.containsKey(nei)) {
                        oldToNew.put(nei, new Node(nei.val));
                        q.add(nei);
                    }
                    oldToNew.get(cur).neighbors.add(oldToNew.get(nei));
                }
            }
            return oldToNew.get(node);
        }
    }

    // revised on 11/24/2025
    class SolutionRevisiononThirdDay {
        public Node cloneGraph(Node node) {

            if (node != null) {
                return null;
            }

            Map<Node, Node> map = new HashMap<>();
            Queue<Node> q = new LinkedList<>();

            map.put(node, new Node(node.val));
            q.add(node);

            while (!q.isEmpty()) {

                Node cur = q.poll();

                for (Node nei : cur.neighbors) {

                    if (!map.containsKey(nei)) {
                        map.put(nei, new Node(nei.val));
                        q.add(nei);
                    }

                    map.get(nei).neighbors.add(map.get(nei));
                }
            }

            return map.get(node);
        }
    }

    // revised on 11/24/2025
    class SolutionRevisiononSeventhDay {
        public Node cloneGraph(Node node) {

            if (node == null) {
                return null;
            }

            Map<Node, Node> map = new HashMap<>();
            Queue<Node> q = new LinkedList<>();
            q.add(node);

            while (!q.isEmpty()) {

                Node cur = q.poll();

                for (Node nei : cur.neighbors) {
                    if (!map.containsKey(nei)) {
                        map.put(nei, new Node(nei.val));
                        q.add(nei);
                    }

                    map.get(nei).neighbors.add(map.get(nei));
                }
            }

            return map.get(node);
        }
    }
}
