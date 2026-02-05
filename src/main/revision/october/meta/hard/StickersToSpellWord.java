package main.revision.october.meta.hard;

import java.util.*;

public class StickersToSpellWord {

    class Solution {
        Map<String, Integer> memo; // Memoization ke liye map, jo target string ka minimum stickers store karega
        Map<String, Map<Character, Integer>> map; // Har sticker ka character frequency store karne ke liye map

        public int minStickers(String[] stickers, String target) {
            this.map = new HashMap<>(); // Sticker ke character frequency ko store karne ke liye map initialize
            this.memo = new HashMap<>(); // Memoization map initialize

            // Har sticker ke liye character frequency calculate karte hain
            for (String str : stickers) {
                Map<Character, Integer> charCount = new HashMap<>();
                for (char cur : str.toCharArray()) {
                    charCount.put(cur, charCount.getOrDefault(cur, 0) + 1); // Character count update karte hain
                }
                map.put(str, charCount); // Sticker aur uske character frequency ko map me store karte hain
            }

            int result = dfs(stickers, target); // DFS function call karte hain target ke liye
            return result == Integer.MAX_VALUE ? -1 : result; // Agar result maximum value hai, to -1 return karte hain
        }

        private int dfs(String[] stickers, String target) {
            if (target.length() == 0) { // Agar target empty hai, to 0 return karte hain
                return 0;
            }

            if (memo.containsKey(target)) { // Agar target memo me already hai, to uska result return karte hain
                return memo.get(target);
            }

            int ans = Integer.MAX_VALUE; // Minimum stickers ke liye initial value set karte hain

            // Har sticker ke liye check karte hain
            for (String sticker : stickers) {
                Map<Character, Integer> stickerCharCount = map.get(sticker); // Sticker ka character frequency lete hain

                // Agar sticker me target ka pehla character nahi hai, to skip karte hain
                if (!stickerCharCount.containsKey(target.charAt(0))) {
                    continue;
                }

                // Naya target banate hain sticker use karne ke baad
                StringBuilder newTarget = new StringBuilder();
                Map<Character, Integer> tempCount = new HashMap<>(stickerCharCount); // Sticker ka temporary copy banate
                                                                                     // hain

                for (char c : target.toCharArray()) { // Target ke har character ke liye check karte hain
                    if (tempCount.getOrDefault(c, 0) > 0) { // Agar sticker me character available hai
                        tempCount.put(c, tempCount.get(c) - 1); // Sticker se character use karte hain
                    } else {
                        newTarget.append(c); // Agar character available nahi hai, to naya target me add karte hain
                    }
                }

                int subResult = dfs(stickers, newTarget.toString()); // Recursive call karte hain naye target ke liye
                if (subResult != Integer.MAX_VALUE) { // Agar subResult valid hai
                    ans = Math.min(ans, 1 + subResult); // Minimum stickers update karte hain
                }
            }

            memo.put(target, ans); // Memoization map me result store karte hain
            return ans; // Final answer return karte hain
        }
    }

    // revision on 12/2/2025
    class SolutionRevisedOnthirdDay {
        Map<String, Integer> memo;
        Map<String, Map<Character, Integer>> map;

        public int minStickers(String[] stickers, String target) {

            this.map = new HashMap<>();
            this.memo = new HashMap<>();

            for (String str : stickers) {
                Map<Character, Integer> charCount = new HashMap<>();

                for (char c : str.toCharArray()) {
                    charCount.put(c, charCount.getOrDefault(c, 0) + 1);
                }
                map.put(str, charCount);
            }

            int result = dfs(stickers, target);
            return result == Integer.MAX_VALUE ? -1 : result;
        }

        private int dfs(String[] stickers, String target) {

            if (target.length() == 0) {
                return 0;
            }

            if (memo.containsKey(target)) {
                return memo.get(target);
            }

            int ans = Integer.MAX_VALUE;

            for (String sticker : stickers) {
                Map<Character, Integer> stickerCharCount = map.get(sticker);

                if (!stickerCharCount.containsKey(target.charAt(0))) {
                    continue;
                }

                StringBuilder newTarget = new StringBuilder();
                Map<Character, Integer> tempCount = new HashMap<>(stickerCharCount);

                for (char c : target.toCharArray()) {
                    if (tempCount.getOrDefault(c, 0) > 0) {
                        tempCount.put(c, tempCount.get(c) - 1);
                    } else {
                        newTarget.append(c);
                    }
                }

                int subResult = dfs(stickers, newTarget.toString());
                if (subResult != Integer.MAX_VALUE) {
                    ans = Math.min(ans, 1 + subResult);
                }

            }

            memo.put(target, ans);
            return ans;
        }
    }

    // revision on 12/8/2025
    class SolutionRevisedOnDaySeventh {
        Map<String, Integer> memo;
        Map<String, Map<Character, Integer>> map;

        public int minStickers(String[] stickers, String target) {
            this.map = new HashMap<>();
            this.memo = new HashMap<>();

            for (String sticker : stickers) {
                Map<Character, Integer> charCount = new HashMap<>();
                for (char c : sticker.toCharArray()) {
                    charCount.put(c, charCount.getOrDefault(c, 0) + 1);
                }
                map.put(sticker, charCount);
            }

            int result = dfs(stickers, target);
            return result == Integer.MAX_VALUE ? -1 : result;
        }

        private int dfs(String[] stickers, String target) {

            if (target.length() == 0) {
                return 0;
            }

            if (memo.containsKey(target)) {
                return memo.get(target);
            }

            int ans = Integer.MAX_VALUE;

            for (String sticker : stickers) {

                Map<Character, Integer> stickerCharCount = map.get(sticker);

                if (!stickerCharCount.containsKey(target.charAt(0))) {
                    continue;
                }

                StringBuilder newTarget = new StringBuilder();
                Map<Character, Integer> tmpCharCount = new HashMap<>();

                for (char c : target.toCharArray()) {
                    if (tmpCharCount.getOrDefault(c, 0) > 0) {
                        tmpCharCount.put(c, tmpCharCount.get(c) - 1);
                    } else {
                        newTarget.append(c);
                    }
                }

                int subresult = dfs(stickers, newTarget.toString());

                if (subresult != Integer.MAX_VALUE) {
                    ans = Math.min(ans, 1 + subresult);
                }
            }

            memo.put(target, ans);
            return ans;
        }
    }

    // revision on 12/8/2025
    class SolutionRevisedOnDayFourteen {
        Map<String, Integer> memo;
        Map<String, Map<Character, Integer>> map;

        public int minStickers(String[] stickers, String target) {
            this.memo = new HashMap<>();
            this.map = new HashMap<>();

            for (String sticker : stickers) {
                char[] stc = sticker.toCharArray();
                Map<Character, Integer> charCount = new HashMap<>();

                for (char c : stc) {
                    charCount.put(c, charCount.getOrDefault(c, 0) + 1);
                }
                map.put(sticker, charCount);
            }

            int result = dfs(stickers, target);
            return result;
        }

        private int dfs(String[] stickers, String target) {

            if (target.length() == 0) {
                return 0;
            }

            if (memo.containsKey(target)) {
                return memo.get(target);
            }

            int result = Integer.MAX_VALUE;

            for (String sticker : stickers) {

                Map<Character, Integer> cur = map.get(sticker);

                if (!cur.containsKey(target.toCharArray()[0])) {
                    continue;
                }

                Map<Character, Integer> temp = new HashMap<>(cur);
                StringBuilder newTarget = new StringBuilder();

                for (char c : target.toCharArray()) {

                    if (temp.getOrDefault(c, 0) > 0) {
                        temp.put(c, temp.get(c) - 1);
                    } else {
                        newTarget.append(c);
                    }
                }

                int subresult = dfs(stickers, newTarget.toString());

                if (subresult != Integer.MAX_VALUE) {
                    result = Math.min(result, 1 + subresult);
                }
            }

            this.memo.put(target, result);
            return result;
        }
    }

    // revised on 1/20/2026
    class SolutionRevisedOnDayThirty {
        Map<String, Map<Character, Integer>> map = new HashMap<>();
        Map<String, Integer> memo = new HashMap<>();

        public int minStickers(String[] stickers, String target) {

            for (String sticker : stickers) {
                Map<Character, Integer> count = new HashMap<>();

                for (char c : sticker.toCharArray()) {
                    count.put(c, count.getOrDefault(c, 0) + 1);
                }
                map.put(sticker, count);
            }

            int result = dfs(stickers, target);

            return result == Integer.MAX_VALUE ? -1 : result;

        }

        private int dfs(String[] stickers, String target) {

            if (target.length() == 0) {
                return 0;
            }

            if (memo.containsKey(target)) {
                return memo.get(target);
            }

            int ans = Integer.MAX_VALUE;

            for (String sticker : stickers) {
                Map<Character, Integer> stickerCharCount = map.get(sticker);

                if (!stickerCharCount.containsKey(target.charAt(0))) {
                    continue;
                }

                StringBuilder newTarget = new StringBuilder();
                Map<Character, Integer> tempStickerCharCount = map.get(sticker);

                for (char c : target.toCharArray()) {

                    if (tempStickerCharCount.getOrDefault(c, 0) > 0) {
                        tempStickerCharCount.put(c, tempStickerCharCount.get(c) - 1);
                    } else {
                        newTarget.append(c);
                    }
                }

                int subResult = dfs(stickers, newTarget.toString());

                if (subResult != Integer.MAX_VALUE) {
                    ans = Math.min(ans, 1 + subResult);
                }
            }

            memo.put(target, ans);
            return ans;
        }

    }
}
