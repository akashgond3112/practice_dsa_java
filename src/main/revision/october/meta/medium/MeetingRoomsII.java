package main.revision.october.meta.medium;

import java.util.Arrays;

public class MeetingRoomsII {

    static class Solution {
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
}
