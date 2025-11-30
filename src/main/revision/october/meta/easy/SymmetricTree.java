package main.revision.october.meta.easy;

import main.revision.october.meta.TreeNode;
import java.util.*;

public class SymmetricTree {

    class Solution {
        public boolean isSymmetric(TreeNode root) {

            if (root == null)
                return true;

            Queue<TreeNode> left = new LinkedList<>();
            Queue<TreeNode> right = new LinkedList<>();

            left.add(root.left);
            right.add(root.right);

            while (!left.isEmpty() && !right.isEmpty()) {

                TreeNode l = left.poll();
                TreeNode r = right.poll();

                if (l == null || r == null)
                    return false;

                if (l.val != r.val)
                    return false;

                left.add(l.left);
                left.add(l.right);

                right.add(r.right);
                right.add(r.left);
            }

            return true;

        }
    }
}
