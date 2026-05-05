package main.revision.march.hard;

import java.util.*;

public class StickersToSpellWord {

    class Solution {
        public int minStickers(String[] stickers, String target) {
            Map<String, Integer> memo = new HashMap<>();
            Map<String, Map<Character, Integer>> stickerMap = new HashMap<>();

            for (String sticker : stickers) {
                Map<Character, Integer> charCount = new HashMap<>();
                for (char c : sticker.toCharArray()) {
                    charCount.put(c, charCount.getOrDefault(c, 0) + 1);
                }
                stickerMap.put(sticker, charCount);
            }

            int result = dfs(stickerMap, target, memo);
            return result == Integer.MAX_VALUE ? -1 : result;
        }

        private int dfs(Map<String, Map<Character, Integer>> stickerMap, String target,
                Map<String, Integer> memo) {
            if (target.length() == 0) {
                return 0;
            }

            if (memo.containsKey(target)) {
                return memo.get(target);
            }

            int ans = Integer.MAX_VALUE;

            for (Map<Character, Integer> charCount : stickerMap.values()) {
                if (!charCount.containsKey(target.charAt(0))) {
                    continue;
                }

                StringBuilder newTarget = new StringBuilder();
                Map<Character, Integer> tmpCharCount = new HashMap<>(charCount);

                for (char c : target.toCharArray()) {
                    if (tmpCharCount.getOrDefault(c, 0) > 0) {
                        tmpCharCount.put(c, tmpCharCount.get(c) - 1);
                    } else {
                        newTarget.append(c);
                    }
                }

                int subResult = dfs(stickerMap, newTarget.toString(), memo);
                if (subResult != Integer.MAX_VALUE) {
                    ans = Math.min(ans, subResult + 1);
                }
            }

            memo.put(target, ans);
            return ans;
        }
    }

    // 26/04/2026
    class SolutionRevisedOnDayThird {
        public int minStickers(String[] stickers, String target) {

            Map<String, Integer> memo = new HashMap<>();
            Map<String, Map<Character, Integer>> stickerCharMap = new HashMap<>();

            for (String sticker : stickers) {
                Map<Character, Integer> charMap = new HashMap<>();

                for (char c : sticker.toCharArray()) {
                    charMap.put(c, charMap.getOrDefault(c, 0) + 1);
                }

                stickerCharMap.put(sticker, charMap);
            }

            int result = dfs(memo, stickerCharMap, target);

            return result == Integer.MAX_VALUE ? -1 : result;
        }

        private int dfs(Map<String, Integer> memo, Map<String, Map<Character, Integer>> stickerCharMap, String target) {

            if (target.length() == 0) {
                return 0;
            }

            if (memo.containsKey(target)) {
                return memo.get(target);
            }

            int ans = Integer.MAX_VALUE;

            for (Map<Character, Integer> charMap : stickerCharMap.values()) {

                if (!charMap.containsKey(target.charAt(0))) {
                    continue;
                }

                StringBuilder newTarget = new StringBuilder();

                Map<Character, Integer> tmpCharMap = new HashMap<>(charMap);

                for (char c : target.toCharArray()) {

                    if (tmpCharMap.getOrDefault(c, 0) > 0) {
                        tmpCharMap.put(c, tmpCharMap.get(c) - 1);
                    } else {
                        newTarget.append(c);
                    }
                }

                int curAns = dfs(memo, stickerCharMap, newTarget.toString());
                if (curAns != Integer.MAX_VALUE) {
                    ans = Math.min(ans, curAns + 1);
                }
            }

            memo.put(target, ans);
            return ans;
        }
    }

    // 02/05/2026
    class SolutionRevisedOnDaySeventh {
        public int minStickers(String[] stickers, String target) {
            Map<String, Integer> memo = new HashMap<>();
            Map<String, Map<Character, Integer>> map = new HashMap<>();

            for (String sticker : stickers) {
                Map<Character, Integer> charCount = new HashMap<>();
                for (char c : sticker.toCharArray()) {
                    charCount.put(c, charCount.getOrDefault(c, 0) + 1);
                }
                map.put(sticker, charCount);
            }

            int result = dfs(map, target, memo);

            return result == Integer.MAX_VALUE ? -1 : result;

        }

        private int dfs(Map<String, Map<Character, Integer>> stickerMap, String target,
                Map<String, Integer> memo) {

            if (target.length() == 0) {
                return 0;
            }

            if (memo.containsKey(target)) {
                return memo.get(target);
            }

            int ans = Integer.MAX_VALUE;

            for (Map<Character, Integer> map : stickerMap.values()) {

                if (!map.containsKey(target.charAt(0))) {
                    continue;
                }

                StringBuilder newtarget = new StringBuilder();

                Map<Character, Integer> charCount = new HashMap<>(map);

                for (char c : target.toCharArray()) {

                    if (charCount.getOrDefault(c, 0) > 0) {
                        charCount.put(c, charCount.get(c) - 1);
                    } else {
                        newtarget.append(c);
                    }
                }

                int subResult = dfs(stickerMap, newtarget.toString(), memo);

                if (subResult != Integer.MAX_VALUE) {
                    ans = Math.min(ans, subResult + 1);
                }
            }

            memo.put(target, ans);
            return ans;
        }

    }
}
