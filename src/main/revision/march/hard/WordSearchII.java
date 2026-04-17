package main.revision.march.hard;

import java.util.*;

public class WordSearchII {

    class Solution {

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

            int rows = board.length;
            int cols = board[0].length;

            TrieNode root = new TrieNode();

            Set<String> res = new HashSet<>(); // result store karne ke liye set
            boolean[][] visited = new boolean[rows][cols]; // board ke cells visit track karne ke liye

            for (String word : words) {
                root.addWord(word);
            }

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    dfs(board, row, col, root, visited, res, new StringBuilder());
                }
            }

            return new ArrayList<>(res);

        }

        private void dfs(char[][] board, int row, int col, TrieNode node, boolean[][] visited, Set<String> res,
                StringBuilder word) {
            int rows = board.length;
            int cols = board[0].length;

            if (row < 0 || col < 0 || row >= rows || col >= cols || visited[row][col]
                    || !node.children.containsKey(board[row][col])) {
                return;
            }

            visited[row][col] = true;
            node = node.children.get(board[row][col]);
            word.append(board[row][col]);

            if (node.isWord) {
                res.add(word.toString());
            }

            dfs(board, row + 1, col, node, visited, res, word);
            dfs(board, row - 1, col, node, visited, res, word);
            dfs(board, row, col + 1, node, visited, res, word);
            dfs(board, row, col - 1, node, visited, res, word);

            visited[row][col] = false;
        }
    }
}
