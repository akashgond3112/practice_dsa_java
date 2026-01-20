package main.revision.october.meta.medium;

import java.util.Arrays;

public class MeetingRoomsII {

    static class Solution {
        public int minMeetingRooms(int[] start, int[] end) {
            // Step 1: Dono start aur end time arrays ko sort karo.
            // Isse hum meetings ko unke start time ke order mein process kar sakte hain.
            Arrays.sort(start);
            Arrays.sort(end);

            // Step 2: Variables initialize karo.
            int rooms = 0; // Current time par kitne rooms use ho rahe hain.
            int maxRooms = 0; // Ab tak maximum kitne rooms lage hain.
            int i = 0; // Start times ke liye pointer.
            int j = 0; // End times ke liye pointer.
            int n = start.length; // Total meetings ka count.

            // Step 3: Start time array ko iterate karo.
            while (i < n) {
                // Step 4: Check karo ki kya current meeting start ho sakti hai.
                // Agar current meeting ka start time, sabse pehle khatam hone wali meeting ke
                // end time se pehle hai,
                // toh humein ek naya room chahiye.
                if (start[i] < end[j]) {
                    // Ek naya room allocate karo.
                    rooms++;
                    // Maximum rooms ka count update karo agar zaroorat ho.
                    maxRooms = Math.max(maxRooms, rooms);
                    // Agli meeting ke start time par jao.
                    i++;
                } else {
                    // Agar current meeting ka start time, kisi meeting ke end time ke baad ya
                    // barabar hai,
                    // toh ek room free ho jayega.
                    rooms--;
                    // Agle end time par jao.
                    j++;
                }
            }
            // Step 5: Maximum rooms jo lage, woh return karo.
            return maxRooms;
        }
    }

    // revised on 12/2/2025
    static class SolutionRevisedOnThirdDay {
        public int minMeetingRooms(int[] start, int[] end) {
            Arrays.sort(start);
            Arrays.sort(end);

            int rooms = 0, maxRooms = 0;
            int i = 0, j = 0;
            int n = start.length;

            while (i < n) {
                if (start[i] < end[j]) {
                    // A new meeting starts before the previous one ends
                    rooms++;
                    maxRooms = Math.max(maxRooms, rooms);
                    i++;
                } else {
                    // A meeting ends
                    rooms--;
                    j++;
                }
            }
            return maxRooms;
        }
    }

    // revision on 12/8/2025
    static class SolutionRevisedOnDaySeventh {

        public int minMeetingRooms(int[] start, int[] end) {
            Arrays.sort(start);
            Arrays.sort(end);

            int rooms = 0;
            int maxRoom = 0;
            int i = 0;
            int j = 0;
            int n = start.length;

            while (i < n) {

                if (start[i] < end[j]) {
                    rooms++;
                    maxRoom = Math.max(maxRoom, rooms);
                    i++;
                } else {
                    rooms--;
                    j++;
                }
            }

            return maxRoom;
        }

    }

    // revision on 12/22/2025
    static class SolutionRevisedOnDayFourteen {
        public int minMeetingRooms(int[] start, int[] end) {
            Arrays.sort(start);
            Arrays.sort(end);

            int room = 0;
            int maxRoom = 0;
            int i = 0;
            int j = 0;

            int n = start.length;

            while (i < n) {

                if (start[i] < end[j]) {
                    room++;
                    maxRoom = Math.max(room, maxRoom);
                    i++;
                } else {
                    room--;
                    j++;
                }
            }

            return maxRoom;
        }
    }

    // revised on 1/19/2026
    class SolutionRevisedOnDayThirty {
        public int minMeetingRooms(int[] start, int[] end) {
            Arrays.sort(start);
            Arrays.sort(end);

            int room = 0;
            int maxRoom = Integer.MIN_VALUE;
            int i = 0;
            int j = 0;
            int n = start.length;

            while (i < n) {

                if (start[i] < end[i]) {
                    room++;
                    maxRoom = Math.max(room, maxRoom);
                    i++;
                } else {
                    room--;
                    j++;
                }
            }

            return maxRoom;
        }
    }
}
