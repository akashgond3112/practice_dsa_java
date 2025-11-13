package main.revision.october.meta.medium;

import java.util.*;

public class SimplifyPath {

    public class Solution {
        public String simplifyPath(String path) {
            Deque<String> st = new ArrayDeque<>();

            for (int i = 0; i < path.length(); ++i) {
                if (path.charAt(i) == '/') {
                    continue;
                }
                StringBuilder temp = new StringBuilder();

                while (i < path.length() && path.charAt(i) != '/') {
                    temp.append(path.charAt(i));
                    ++i;
                }

                String dir = temp.toString();

                if (dir.equals(".")) {
                    continue; // Ignore current directory reference
                } else if (dir.equals("..")) {
                    if (!st.isEmpty()) {
                        st.removeLast(); // Move one level up
                    }
                } else {
                    st.addLast(dir); // Add directory to the stack
                }
            }

            if (st.isEmpty()) {
                return "/";
            }

            StringBuilder resultPath = new StringBuilder();
            while (!st.isEmpty()) {
                resultPath.append("/").append(st.removeFirst());
            }

            return resultPath.toString();
        }
    }

    // Revise on 21/10/2025
    public class SolutionRevisionThirdDay {
        public String simplifyPath(String path) {

            Deque<String> st = new ArrayDeque<>();

            int n = path.length();

            for (int i = 0; i < n; ++i) {

                if (path.charAt(i) == '/') {
                    continue;
                }

                StringBuilder tmp = new StringBuilder();

                while (i < n && path.charAt(i) != '/') {
                    tmp.append(path.charAt(i));
                    ++i;
                }

                String dir = tmp.toString();

                if (dir.equals(".")) {
                    continue;
                } else if (dir.equals("..")) {
                    if (!st.isEmpty()) {
                        st.removeLast();
                    }

                } else {
                    st.add(dir);
                }

            }

            if (st.isEmpty()) {
                return "/";
            }

            StringBuilder res = new StringBuilder();

            while (!st.isEmpty()) {
                res.append("/").append(st.removeFirst());
            }

            return res.toString();
        }
    }

    // Revise on 27/10/2025
    public class SolutionRevisionSeventhDay {
        public String simplifyPath(String path) {

            Deque<String> st = new ArrayDeque<>();

            int n = path.length();

            for (int i = 0; i < n; i++) {

                if (path.charAt(i) == '/') {
                    continue;
                }

                StringBuilder sb = new StringBuilder();

                while (i < n && path.charAt(i) != '/') {
                    sb.append(path.charAt(i));
                }

                String dir = sb.toString();

                if (dir.equals(".")) {
                    continue;
                } else if (dir.equals("..")) {
                    if (!st.isEmpty()) {
                        st.pop();
                    }
                } else {
                    st.add(dir);
                }
            }

            if (st.isEmpty()) {
                return "/";
            }

            StringBuilder res = new StringBuilder();

            while (!st.isEmpty()) {
                res.append("/").append(st.pop());
            }

            return res.toString();
        }
    }

}
