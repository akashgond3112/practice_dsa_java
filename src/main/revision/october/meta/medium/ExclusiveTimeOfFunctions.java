package main.revision.october.meta.medium;

import java.util.*;

public class ExclusiveTimeOfFunctions {

    static class Pair {
        int functionId;
        int startTime;

        Pair(int functionId, int startTime) {
            this.functionId = functionId;
            this.startTime = startTime;
        }
    }

    class Solution {
        public int[] exclusiveTime(int n, List<String> logs) {

            int[] result = new int[n];

            Stack<Pair> st = new Stack<>();

            for (String log : logs) {

                String[] split = log.split(":");
                int functionId = Integer.parseInt(split[0]);
                boolean isStarted = split[1].equals("start"); // "start" or "end"
                int currentTimeStamp = Integer.parseInt(split[2]);

                if (isStarted) {

                    if (!st.isEmpty()) {
                        Pair current = st.peek();
                        result[current.functionId] += currentTimeStamp + current.startTime;
                    }

                    st.push(new Pair(functionId, currentTimeStamp));
                } else {

                    Pair completed = st.pop();
                    result[completed.functionId] += currentTimeStamp - completed.startTime + 1;

                    if (!st.isEmpty()) {
                        st.peek().startTime = currentTimeStamp + 1;
                    }
                }
            }

            return result;
        }
    }

    // revised on 12/1/2025
    class SolutionRevisedOnThirdDay {
        public int[] exclusiveTime(int n, List<String> logs) {
            int[] result = new int[n];

            Stack<Pair> st = new Stack<>();

            for (String log : logs) {

                String[] split = log.split(":");
                Integer functionId = Integer.parseInt(split[0]);
                boolean isStarted = split[1].equals("start");
                int currentTimeStamp = Integer.parseInt(split[2]);

                if (isStarted) {

                    if (!st.isEmpty()) {
                        Pair cur = st.peek();
                        result[cur.functionId] += currentTimeStamp + cur.startTime;
                    }
                    st.push(new Pair(functionId, currentTimeStamp));
                } else {
                    Pair completed = st.peek();
                    result[completed.functionId] += currentTimeStamp - completed.startTime + 1;

                    if (!st.empty()) {
                        st.peek().startTime = currentTimeStamp + 1;
                    }
                }
            }

            return result;
        }
    }

    // revised on 12/7/2025
    class SolutionRevisedOnSevethDay {
        public int[] exclusiveTime(int n, List<String> logs) {

            int[] result = new int[n];

            Stack<Pair> st = new Stack<>();

            for (String log : logs) {

                String[] split = log.split(":");

                int functionId = Integer.parseInt(split[0]);
                boolean isStarted = split[1].equals("start");
                int currentTimeStamp = Integer.parseInt(split[2]);

                if (isStarted) {

                    if (!st.isEmpty()) {

                        Pair cur = st.peek();
                        result[cur.functionId] += currentTimeStamp + cur.startTime;
                    }

                    st.push(new Pair(functionId, currentTimeStamp));
                } else {
                    Pair completed = st.peek();
                    result[completed.functionId] += currentTimeStamp - completed.startTime + 1;

                    if (!st.isEmpty()) {
                        st.peek().startTime = currentTimeStamp + 1;
                    }
                }
            }

            return result;
        }
    }
}
