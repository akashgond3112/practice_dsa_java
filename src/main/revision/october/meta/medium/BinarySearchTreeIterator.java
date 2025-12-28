package main.revision.october.meta.medium;

import main.revision.october.meta.TreeNode;
import java.util.*;

public class BinarySearchTreeIterator {

    class BSTIterator {

        // Step 1: Stack ko initialize karte hain jo TreeNode objects ko store karega
        Deque<TreeNode> st = new ArrayDeque<>();

        // Constructor: Binary Search Tree ke root node ko input lete hain
        public BSTIterator(TreeNode root) {

            // Step 2: Root se leftmost node tak traverse karte hain aur har node ko stack
            // mein push karte hain
            while (root != null) {
                st.push(root); // Current node ko stack mein daalo
                root = root.left; // Left child par move karo
            }
        }

        // next() method: Stack se next smallest element ko return karta hai
        public int next() {

            // Step 3: Stack ke top element ko nikaalo (yeh current smallest hoga)
            TreeNode node = st.pop();

            // Step 4: Agar is node ka right child hai, toh uske leftmost subtree ko stack
            // mein daalo
            TreeNode curr = node.right;
            while (curr != null) {
                st.push(curr); // Right subtree ke nodes ko stack mein daalo
                curr = curr.left; // Leftmost node tak traverse karo
            }

            // Step 5: Current node ka value return karo
            return node.val;
        }

        // hasNext() method: Check karta hai ki stack empty hai ya nahi
        public boolean hasNext() {
            // Step 6: Agar stack empty nahi hai, toh iska matlab aur elements baaki hain
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

    // revised on 11/15/2025
    class BSTIteratorRevisedOnDaySeventh {

        Deque<TreeNode> dq = new ArrayDeque<>();

        public BSTIteratorRevisedOnDaySeventh(TreeNode node) {

            while (node != null) {
                if (node.left != null) {
                    dq.push(node);
                    node = node.left;
                }
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

    // revised on 11/29/2025
    class BSTIteratorRevisedOnDayFourteen {

        Deque<TreeNode> dq = new ArrayDeque<>();

        public BSTIteratorRevisedOnDayFourteen(TreeNode node) {

            while (node != null) {
                if (node.left != null) {
                    dq.push(node);
                    node = node.left;
                }
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

    // revised on 12/28/2025
    class BSTIteratorRevisedOnDayThirty {

        Deque<TreeNode> q = new ArrayDeque<>();

        public BSTIteratorRevisedOnDayThirty(TreeNode root) {

            while (root != null) {
                q.push(root);
                root = root.left;
            }
        }

        public int next() {

            TreeNode node = q.pop();

            TreeNode right = node.right;

            while (right != null) {
                q.push(right);
                right = right.left;
            }

            return node.val;
        }

        public boolean hasNext() {
            return !q.isEmpty();
        }
    }
}
