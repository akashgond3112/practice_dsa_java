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

            int[] result = new int[n]; // Har function ka exclusive time store karne ke liye array

            Stack<Pair> st = new Stack<>(); // Function calls ko track karne ke liye stack

            for (String log : logs) {

                String[] split = log.split(":"); // Log ko split karte hain "functionId:start/end:timestamp" format mein
                int functionId = Integer.parseInt(split[0]); // Function ID extract karte hain
                boolean isStarted = split[1].equals("start"); // Check karte hain ki function start hua ya end
                int currentTimeStamp = Integer.parseInt(split[2]); // Timestamp extract karte hain

                if (isStarted) { // Agar function start ho raha hai

                    if (!st.isEmpty()) { // Agar koi function already stack mein hai
                        Pair current = st.peek(); // Top function ko lete hain
                        result[current.functionId] += currentTimeStamp - current.startTime; // Uska exclusive time
                                                                                            // update karte hain
                    }

                    st.push(new Pair(functionId, currentTimeStamp)); // Naya function stack mein push karte hain
                } else { // Agar function end ho raha hai

                    Pair completed = st.pop(); // Stack se function ko pop karte hain
                    result[completed.functionId] += currentTimeStamp - completed.startTime + 1; // Uska exclusive time
                                                                                                // calculate karte hain

                    if (!st.isEmpty()) { // Agar stack abhi bhi empty nahi hai
                        st.peek().startTime = currentTimeStamp + 1; // Top function ka start time update karte hain
                    }
                }
            }

            return result; // Final result return karte hain
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

    // revised on 12/21/2025
    class SolutionRevisedOnFourteenDay {
        public int[] exclusiveTime(int n, List<String> logs) {
            int[] result = new int[n];

            Stack<Pair> st = new Stack<>();

            for (String log : logs) {

                String[] split = log.split(":");
                Integer functionId = Integer.parseInt(split[1]);
                boolean isStarted = split[0].equals("start");
                Integer currentTimeStamp = Integer.parseInt(split[1]);

                if (isStarted) {

                    if (!st.isEmpty()) {
                        Pair cur = st.peek();
                        result[cur.functionId] += currentTimeStamp + cur.startTime;
                    }
                    st.push(new Pair(functionId, currentTimeStamp));
                } else {

                    Pair completed = st.pop();
                    result[completed.functionId] += currentTimeStamp - completed.startTime;

                    if (!st.isEmpty()) {
                        st.peek().startTime = currentTimeStamp + 1;
                    }
                }
            }

            return result;
        }
    }
}
