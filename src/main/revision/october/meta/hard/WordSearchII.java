package main.revision.october.meta.hard;

import java.util.*;

public class WordSearchII {

    // Approach Time Complexity Space Complexity
    // O(L + M * 4^N)
    // O(L + M)
    class Solution {

        // TrieNode class banaya jisme ek map children aur ek boolean isWord rakha hai
        public class TrieNode {
            Map<Character, TrieNode> children;
            boolean isWord;

            public TrieNode() {
                children = new HashMap<>();
                isWord = false;
            }

            // addWord method ek word ko Trie mein add karne ke liye hai
            public void addWord(String word) {
                TrieNode cur = this;

                // har character ke liye TrieNode create karte hain agar pehle se nahi hai
                for (char c : word.toCharArray()) {
                    cur.children.putIfAbsent(c, new TrieNode());
                    cur = cur.children.get(c);
                }
                // last character ke liye isWord true set karte hain
                cur.isWord = true;
            }
        }

        private Set<String> res; // result store karne ke liye set
        private boolean[][] visit; // board ke cells visit track karne ke liye

        public List<String> findWords(char[][] board, String[] words) {

            TrieNode root = new TrieNode();
            // sabhi words ko Trie mein add karte hain
            for (String word : words) {
                root.addWord(word);
            }

            int rows = board.length;
            int cols = board[0].length;
            res = new HashSet<>();
            visit = new boolean[rows][cols];

            // board ke har cell se DFS start karte hain
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    dfs(board, r, c, root, "");
                }
            }

            // result ko list mein convert karke return karte hain
            return new ArrayList<>(res);
        }

        private void dfs(char[][] board, int r, int c, TrieNode node, String word) {

            int rows = board.length, cols = board[0].length;

            // agar out of bounds, already visited ya current character Trie mein nahi hai,
            // to return karte hain
            if (r < 0 || c < 0 || r >= rows || c >= cols || visit[r][c] || !node.children.containsKey(board[r][c])) {
                return;
            }

            visit[r][c] = true; // current cell ko visit mark karte hain
            node = node.children.get(board[r][c]); // Trie mein next node pe move karte hain
            word += board[r][c]; // word mein current character add karte hain

            // agar current node ek word complete karta hai, to result mein add karte hain
            if (node.isWord) {
                res.add(word);
            }

            // 4 directions mein DFS call karte hain
            dfs(board, r + 1, c, node, word);
            dfs(board, r - 1, c, node, word);
            dfs(board, r, c + 1, node, word);
            dfs(board, r, c - 1, node, word);

            visit[r][c] = false; // backtrack karte hain aur cell ko unvisit mark karte hain
        }
    }

    // revised on 11/9/2025
    class SolutionRevisionThirdDay {

        public class TrieNode {
            Map<Character, TrieNode> children;
            boolean isWord;

            public TrieNode() {
                this.children = new HashMap<>();
                this.isWord = false;
            }

            public void addWord(String word) {

                TrieNode cur = this;

                for (char c : word.toCharArray()) {

                    cur.children.putIfAbsent(c, new TrieNode());
                    cur = cur.children.get(c);
                }
                cur.isWord = true;
            }
        }

        private Set<String> res;
        private boolean[][] visit;

        public List<String> findWords(char[][] board, String[] words) {

            TrieNode root = new TrieNode();

            for (String word : words) {
                root.addWord(word);
            }

            int rows = board.length;
            int cols = board[0].length;
            res = new HashSet<>();
            visit = new boolean[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    dfs(board, i, j, root, "");
                }
            }

            return new ArrayList<>(res);
        }

        public void dfs(char[][] board, int r, int c,
                TrieNode node, String word) {

            int rows = board.length;
            int cols = board[0].length;

            if (r < 0 || c < 0 || r >= rows || c >= cols || visit[r][c] || !node.children.containsKey(board[r][c])) {
                return;
            }

            visit[r][c] = true;
            node = node.children.get(board[r][c]);
            word += board[r][c];

            if (node.isWord) {
                res.add(word);
            }

            dfs(board, r + 1, c, node, word);
            dfs(board, r - 1, c, node, word);
            dfs(board, r, c + 1, node, word);
            dfs(board, r, c - 1, node, word);

            visit[r][c] = false;
        }
    }

    // revised on 11/29/2025
    class SolutionRevisionFourteenDayDay {

        public class TrieNode {
            Map<Character, TrieNode> children;
            boolean isWord;

            TrieNode() {
                this.children = new HashMap<>();
                this.isWord = false;
            }

            public void addWord(String word) {
                TrieNode node = this;

                for (char c : word.toCharArray()) {
                    node.children.putIfAbsent(c, new TrieNode());
                    node = node.children.get(c);
                }
                node.isWord = true;
            }
        }

        Set<String> res;
        boolean[][] visited;
        int rows = 0;
        int cols = 0;

        public List<String> findWords(char[][] board, String[] words) {

            this.rows = board.length;
            this.cols = board[0].length;

            this.res = new HashSet<>();
            this.visited = new boolean[rows][cols];

            TrieNode node = new TrieNode();

            for (String word : words) {
                node.addWord(word);
            }

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    dfs(board, i, j, node, "");
                }
            }

            return new ArrayList<>(res);

        }

        private void dfs(char[][] board, int row, int col,
                TrieNode node, String word) {

            char curChar = board[row][col];

            if (row < 0 || col < 0 || row >= rows || col >= cols || visited[row][col]
                    || !node.children.containsKey(curChar)) {
                return;
            }

            this.visited[row][col] = true;
            node = node.children.get(board[row][col]);
            word += curChar;

            if (node.isWord) {
                this.res.add(word);
            }

            dfs(board, row + 1, col, node, word);
            dfs(board, row - 1, col, node, word);
            dfs(board, row, col + 1, node, word);
            dfs(board, row, col - 1, node, word);

            this.visited[row][col] = false;

        }

    }

    // revised on 12/28/2025
    class SolutionRevisedOnDayThirty {

        public class TrieNode {

            Map<Character, TrieNode> children;
            boolean isWord;

            public TrieNode() {
                this.children = new HashMap<>();
                this.isWord = false;
            }

            public void addWord(String word) {
                TrieNode node = this;

                for (char c : word.toCharArray()) {
                    node.children.putIfAbsent(c, new TrieNode());
                    node = node.children.get(c);
                }

                node.isWord = true;
            }
        }

        public List<String> findWords(char[][] board, String[] words) {

            TrieNode root = new TrieNode();

            for (String word : words) {
                root.addWord(word);
            }

            int rows = board.length;
            int cols = board[0].length;
            Set<String> res = new HashSet<>();// result store karne ke liye set
            boolean[][] visit = new boolean[rows][cols]; // board ke cells visit track karne ke liye

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    dfs(board, row, col, root, "", res, visit);
                }
            }

            return new ArrayList<>(res);

        }

        private void dfs(char[][] board, int r, int c, TrieNode node, String word, Set<String> res,
                boolean[][] visited) {

            int rows = board.length;
            int cols = board[0].length;

            if (r < 0 ||
                    c < 0 ||
                    r >= rows ||
                    c >= cols ||
                    visited[r][c] ||
                    !node.children.containsKey(board[r][c])) {
                return;
            }

            visited[r][c] = true;
            node = node.children.get(board[r][c]);
            word += board[r][c];

            if (node.isWord) {
                res.add(word);
            }

            dfs(board, r + 1, c, node, "", res, visited);
            dfs(board, r - 1, c, node, "", res, visited);
            dfs(board, r, c + 1, node, "", res, visited);
            dfs(board, r, c - 1, node, "", res, visited);

            visited[r][c] = false;
        }
    }
}
