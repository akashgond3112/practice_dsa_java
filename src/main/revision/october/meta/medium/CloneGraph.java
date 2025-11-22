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
}
