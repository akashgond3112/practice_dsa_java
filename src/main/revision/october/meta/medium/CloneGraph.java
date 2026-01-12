package main.revision.october.meta.medium;

import main.revision.october.meta.Node;
import java.util.*;

public class CloneGraph {

    // Yeh Solution class graph ko clone karta hai (deep copy banata hai)
    class Solution {
        public Node cloneGraph(Node node) {

            // Agar input node null hai toh kuch return nahi karna
            if (node == null) {
                return null;
            }

            // oldToNew map original node ko uske clone se map karta hai
            Map<Node, Node> oldToNew = new HashMap<>();
            Queue<Node> q = new LinkedList<>();
            // Pehle node ka clone bana ke map me daal diya
            oldToNew.put(node, new Node(node.val));
            q.add(node);

            // BFS traversal se saare nodes visit karenge
            while (!q.isEmpty()) {

                Node cur = q.poll();

                // Har neighbor ke liye check karo ki clone bana hai ya nahi
                for (Node nei : cur.neighbors) {
                    if (!oldToNew.containsKey(nei)) {
                        // Agar clone nahi bana toh bana lo aur queue me daal do
                        oldToNew.put(nei, new Node(nei.val));
                        q.add(nei);
                    }
                    // Current node ke clone ke neighbors me neighbor ka clone add karo
                    oldToNew.get(cur).neighbors.add(oldToNew.get(nei));
                }
            }
            // Cloned graph ka starting node return karo
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

    // revised on 12/14/2025
    class SolutionRevisiononFourteenDay {
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

    // revised on 1/12/2026
    class SolutionRevisiononDayThirty {
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
}
