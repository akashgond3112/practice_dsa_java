/**
 * @author agond
 * @date May 30, 2025
 * @time 8:14:09 PM
 */
package main.revision.meta.medium;

public class LowestCommonAncestorOfBinaryTreeIII {

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    public Node lowestCommonAncestor(Node p, Node q) {

        Node p_ = p;
        Node q_ = q;

        while (p_ != q_) {

            p_ = p_.parent == null ? p_ : p_.parent;
            q_ = q_.parent == null ? q_ : q_.parent;
        }

        return p_;
    }
}
