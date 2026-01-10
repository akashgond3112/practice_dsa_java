package main.revision.october.meta.easy;

public class FirstBadVersion {

    /*
     * The isBadVersion API is defined in the parent class VersionControl.
     * boolean isBadVersion(int version);
     * Dummy class
     */
    public class VersionControl {
        boolean isBadVersion(int version) {
            return true;
        }
    }

    public class Solution extends VersionControl {
        public int firstBadVersion(int n) {
            int left = 1, right = n;
            while (left < right) {
                int mid = left + (right - left) / 2; // Overflow avoid karne ke liye
                if (isBadVersion(mid)) {
                    right = mid; // Agar mid bad version hai, toh pehla bad version left mein hoga
                } else {
                    left = mid + 1; // Agar mid bad version nahi hai, toh pehla bad version right mein hoga
                }
            }
            return left; // Left ya right dono pehla bad version point karenge
        }
    }

    // revied on 12/21/2025
    public class SolutionRevisedOnThirdDay extends VersionControl {
        public int firstBadVersion(int n) {
            int left = 0;
            int right = n;

            while (left < right) {
                int mid = left + (right - left) / 2;

                if (isBadVersion(mid)) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }
    }

    // revied on 12/27/2025
    public class SolutionRevisedOnSeventhDay extends VersionControl {
        public int firstBadVersion(int n) {
            int left = 0;
            int right = n;

            while (left < right) {
                int mid = left + (right - left) / 2;

                if (isBadVersion(mid)) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }
    }

    // revied on 1/10/2026
    public class SolutionRevisedOnFourteenDay extends VersionControl {
        public int firstBadVersion(int n) {

            int left = 0;
            int right = n;

            while (left < right) {

                int mid = left + (right - left) / 2;

                if (isBadVersion(mid)) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }
    }
}
