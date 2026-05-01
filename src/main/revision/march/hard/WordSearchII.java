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

    // 17/04/2026
    class SolutionRevisedOnDayThird {

        public class Trie {

            Map<Character, Trie> map;
            boolean endWord;

            Trie() {
                this.map = new HashMap<>();
                this.endWord = false;
            }

            public void addWord(String word) {

                Trie trie = new Trie();
                for (char c : word.toCharArray()) {
                    trie.map.put(c, new Trie());

                    trie = trie.map.get(c);
                }

                trie.endWord = true;
            }
        }

        public List<String> findWords(char[][] board, String[] words) {

            int rows = board.length;
            int cols = board[0].length;

            Trie trie = new Trie();

            Set<String> result = new HashSet<>();
            boolean[][] visited = new boolean[rows][cols];

            for (String word : words) {
                trie.addWord(word);
            }

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    dfs(board, trie, visited, row, col, result, new StringBuilder());
                }
            }

            return new ArrayList<>(result);

        }

        private void dfs(char[][] board, Trie trie, boolean[][] visited, int row, int col, Set<String> result,
                StringBuilder word) {

            int rows = board.length;
            int cols = board[0].length;

            if (row < 0 || col < 0 || row >= rows || col >= cols || visited[row][col]
                    || !trie.map.containsKey(board[row][col])) {
                return;
            }

            visited[row][col] = true;
            trie = trie.map.get(board[row][col]);
            word.append(board[row][col]);

            if (trie.endWord) {
                result.add(word.toString());
            }

            dfs(board, trie, visited, row + 1, col, result, word);
            dfs(board, trie, visited, row - 1, col, result, word);
            dfs(board, trie, visited, row, col + 1, result, word);
            dfs(board, trie, visited, row, col - 1, result, word);

            visited[row][col] = false;

        }

    }

    // 23/04/2026
    class SolutionRevisedOnDaySeventh {

        public class Trie {

            Map<Character, Trie> map;
            boolean isEndWord;

            Trie() {
                this.map = new HashMap<>();
                this.isEndWord = false;
            }

            public void addWord(String word) {

                Trie node = new Trie();

                for (char c : word.toCharArray()) {
                    node.map.put(c, new Trie());

                    node = node.map.get(c);
                }

                node.isEndWord = true;
            }
        }

        public List<String> findWords(char[][] board, String[] words) {

            int rows = board.length;
            int cols = board[0].length;

            List<String> result = new ArrayList<>();
            boolean[][] visited = new boolean[rows][cols];

            Trie trie = new Trie();

            for (String word : words) {
                trie.addWord(word);
            }

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    dfs(board, trie, visited, row, col, result, new StringBuilder());
                }
            }

            return result;
        }

        private void dfs(char[][] board, Trie trie, boolean[][] visited, int row, int col, List<String> result,
                StringBuilder word) {
            int rows = board.length;
            int cols = board[0].length;

            if (row < 0 || col < 0 || row >= rows || col >= cols || visited[row][col]
                    || !trie.map.containsKey(board[row][col])) {
                return;
            }

            visited[row][col] = true;
            trie = trie.map.get(board[row][col]);
            word.append(board[row][col]);

            if (trie.isEndWord) {
                result.add(word.toString());
            }

            dfs(board, trie, visited, row + 1, col, result, word);
            dfs(board, trie, visited, row - 1, col, result, word);
            dfs(board, trie, visited, row, col + 1, result, word);
            dfs(board, trie, visited, row, col - 1, result, word);

        }

    }
}
