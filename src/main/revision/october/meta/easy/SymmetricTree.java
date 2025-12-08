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

    // revised on 12/2/2025
    class SolutionRevisedOnThirdDay {
        public boolean isSymmetric(TreeNode root) {
            if (root == null)
                return false;

            Queue<TreeNode> left = new LinkedList<>();
            Queue<TreeNode> right = new LinkedList<>();

            left.add(root.left);
            right.add(root.right);

            while (!left.isEmpty() && !right.isEmpty()) {

                TreeNode cL = left.poll();
                TreeNode cR = right.poll();

                if (cL == null || cR == null) {
                    return false;
                }

                if (cL.val != cR.val)
                    return false;

                left.add(cL.left);
                left.add(cL.right);

                right.add(cR.right);
                right.add(cR.left);
            }

            return true;
        }
    }

    // revised on 12/8/2025
    class SolutionRevisedOnSeventhDay {
        public boolean isSymmetric(TreeNode root) {

            if (root == null) {
                return false;
            }

            Queue<TreeNode> left = new LinkedList<>();
            Queue<TreeNode> right = new LinkedList<>();

            left.add(root.left);
            right.add(root.right);

            while (!left.isEmpty() && !right.isEmpty()) {

                TreeNode cl = left.poll();
                TreeNode cR = right.poll();

                if (cl == null || cR == null) {
                    return false;
                }

                if (cl.val != cR.val) {
                    return false;
                }

                left.add(cl.left);
                left.add(cl.right);

                right.add(cR.right);
                right.add(cR.left);
            }

            return true;
        }
    }
}
