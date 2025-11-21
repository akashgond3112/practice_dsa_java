package main.revision.october.meta.medium;

import main.revision.october.meta.TreeNode;
import java.util.*;

public class BinarySearchTreeIterator {

    class BSTIterator {

        Deque<TreeNode> st = new ArrayDeque<>();

        public BSTIterator(TreeNode root) {

            while (root != null) {
                st.push(root);
                root = root.left;
            }
        }

        public int next() {

            TreeNode node = st.pop();
            TreeNode curr = node.right;
            while (curr != null) {
                st.push(curr);
                curr = curr.left;
            }
            return node.val;
        }

        public boolean hasNext() {
            return !st.isEmpty();
        }
    }

    // revised on 11/9/2025
    class BSTIteratorRevisedOnDayThird {

        Deque<TreeNode> dq = new ArrayDeque<>();

        public BSTIteratorRevisedOnDayThird(TreeNode node) {

            while (node != null) {
                dq.push(node);
                node = node.left;
            }
        }

        public int next() {

            TreeNode node = dq.pop();
            TreeNode cur = node.right;
            while (cur != null) {
                dq.push(cur);
                cur = cur.right;
            }

            return node.val;
        }

        public boolean hasNext() {
            return !dq.isEmpty();
        }
    }
}
