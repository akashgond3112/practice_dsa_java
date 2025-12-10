package main.revision.october.meta.medium;

import main.revision.october.meta.Node;

import java.util.*;

public class LowestCommonAncestorOfABinaryTreeIII {

    public Node lowestCommonAncestor(Node p, Node q) {
        Node p_start = p, q_start = q;

        while (p_start != q_start) {
            p_start = p_start.parent == null ? q : p_start.parent;
            q_start = q_start.parent == null ? p : q_start.parent;
        }

        return p_start;
    }

    // Revision 15/10/2025
    public Node lowestCommonAncestor_rev_2(Node p, Node q) {

        Node p_start = p;
        Node q_start = q;

        while (p_start != q_start) {

            p_start = p_start.parent == null ? q : p_start.parent;
            q_start = q_start.parent == null ? p : q_start.parent;
        }

        return p_start;

    }

    // Revision 29/10/2025
    public Node lowestCommonAncestor_rev_3(Node p, Node q) {

        Node p_start = p;
        Node q_start = q;

        while (p_start != q_start) {

            p_start = p_start.parent == null ? q : p_start.parent;
            q_start = q_start.parent == null ? p : q_start.parent;
        }

        return p_start;

    }

    // Revision 01/11/2025
    public Node lowestCommonAncestor_rev_4(Node p, Node q) {
        Node p_start = p;
        Node q_start = q;

        while (p_start != q_start) {

            p_start = p_start.parent == null ? q : p_start.parent;
            q_start = q_start.parent == null ? p : q_start.parent;
        }

        return p_start;
    }

    // Revision 11/30/2025
    public Node lowestCommonAncestor_rev_5(Node p, Node q) {

        Node q_start = p;
        Node p_start = q;

        while (p_start != q_start) {

            p_start = p_start.parent != null ? q : p_start.parent;
            q_start = q_start.parent != null ? p : q_start.parent;
        }
        return p_start;
    }

    public Node lowestCommonAncestorWithoutParent(List<Node> lists, Node p, Node q) {

        Map<Node, Node> childToParent = new HashMap<>();

        for (Node node : lists) {

            if (node.left != null) {
                childToParent.put(node.left, node);
            }

            if (node.right != null) {
                childToParent.put(node.right, node);
            }
        }

        Node p_start = p, q_start = q;

        while (p_start != q_start) {
            p_start = childToParent.containsKey(p_start) ? childToParent.get(p_start) : q;
            q_start = childToParent.containsKey(q_start) ? childToParent.get(q_start) : p;
        }

        return p_start;
    }
}
