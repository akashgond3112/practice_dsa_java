package main.revision.march.hard;

public class MedianOfTwoSortedArrays {

    // 08/04/2026
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {

            int n1 = nums1.length;
            int n2 = nums2.length;

            if (n1 > n2) {
                findMedianSortedArrays(nums2, nums1);
            }

            int low = 0;
            int high = n1;

            int left = (n1 + n2 + 1) / 2;
            int n = n1 + n2;

            while (low < high) {

                int m1 = (low + high) / 2;
                int m2 = left - m1;

                int l1 = Integer.MIN_VALUE;
                int l2 = Integer.MIN_VALUE;

                int r1 = Integer.MAX_VALUE;
                int r2 = Integer.MAX_VALUE;

                if (m1 - 1 >= 0) {
                    l1 = nums1[m1 - 1];
                }

                if (m2 - 1 >= 0) {
                    l2 = nums2[m2 - 1];
                }

                if (m1 < n) {
                    r1 = nums2[m1];
                }

                if (m2 < n) {
                    r2 = nums2[m2];
                }

                if (l1 <= r2 && l2 <= r1) {
                    if (n % 2 == 1) {
                        return Math.max(l1, l2);
                    } else {
                        return Math.max(l1, l2) + Math.min(r1, r2) / 2.0;
                    }
                } else if (l1 > l2) {
                    high = m1 - 1;
                } else {
                    low = m1 + 1;
                }
            }

            return 0.0;
        }
    }

    // revised on 11/04/2026
    class SolutionRevisionThirdDay {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {

            int n1 = nums1.length;
            int n2 = nums2.length;

            if (n1 > n2) {
                findMedianSortedArrays(nums2, nums1);
            }

            int low = 0;
            int high = n1;

            int left = (n1 + n2 + 1) / 2;
            int n = n1 + n2;

            while (low <= high) {

                int m1 = low + (high - low) / 2;
                int m2 = left - m1;

                int l1 = Integer.MIN_VALUE;
                int l2 = Integer.MIN_VALUE;
                int r1 = Integer.MIN_VALUE;
                int r2 = Integer.MIN_VALUE;

                if (m1 > 0) {
                    l1 = nums1[m1 - 1];
                }

                if (m2 > 0) {
                    l2 = nums2[m2 - 1];
                }

                if (m1 < n1) {
                    r1 = nums1[m1];
                }

                if (m2 < n2) {
                    r2 = nums2[m2];
                }

                if (l1 <= r2 && l2 <= r1) {
                    if (n % 2 == 1) {
                        return Math.max(l1, l2);
                    } else {
                        return Math.max(l1, l2) + Math.min(r1, r2);
                    }
                } else if (l1 > r2) {
                    high = m1 - 1;
                } else {
                    low = m1 + 1;
                }
            }

            return 0.0;
        }
    }

    // revised on 17/04/2026
    class SolutionRevisionSeventhDay {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {

            int n1 = nums1.length;
            int n2 = nums2.length;

            if (n1 > n2) {
                findMedianSortedArrays(nums2, nums1);
            }

            int low = 0;
            int high = n1;

            int left = (n1 + n2 + 1) / 2;
            int n = n1 + n2;

            while (low <= high) {

                int m1 = low + (high - low) / 2;
                int m2 = left - m1;

                int l1 = Integer.MIN_VALUE;
                int l2 = Integer.MIN_VALUE;
                int r1 = Integer.MIN_VALUE;
                int r2 = Integer.MIN_VALUE;

                if (m1 > 0) {
                    l1 = nums1[m1 - 1];
                }

                if (m2 > 0) {
                    l2 = nums2[m2 - 1];
                }

                if (m1 < n) {
                    r1 = nums1[m2];
                }

                if (m2 < n) {
                    r2 = nums2[m2];
                }

                if (l1 <= r2 && l2 <= r1) {
                    if (n % 2 == 1) {
                        return Math.max(l1, l2);
                    } else {
                        return Math.max(l1, l2) + Math.min(r1, r2);
                    }
                } else if (l1 > r2) {
                    high = m1 - 1;
                } else {
                    low = m1 + 1;
                }
            }

            return 0.0;
        }
    }

    // revised on 01/05/2026
    class SolutionRevisionFourteenDay {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {

            int n1 = nums1.length;
            int n2 = nums2.length;

            if (n1 > n2) {
                findMedianSortedArrays(nums2, nums1);
            }

            int low = 0;
            int high = n1;

            int left = (n1 + n2 + 1) / 2;
            int n = n1 + n2;

            while (low <= high) {

                int m1 = low + (high - low) / 2;
                int m2 = left - m1;

                int l1 = Integer.MIN_VALUE;
                int l2 = Integer.MIN_VALUE;
                int r1 = Integer.MIN_VALUE;
                int r2 = Integer.MIN_VALUE;

                if (m1 > 0) {
                    l1 = nums1[m1 - 1];
                }

                if (m2 > 0) {
                    l2 = nums2[m2 - 1];
                }

                if (m1 < n) {
                    r1 = nums1[m2];
                }

                if (m2 < n) {
                    r2 = nums2[m2];
                }

                if (l1 <= r2 && l2 <= r1) {
                    if (n % 2 == 1) {
                        return Math.max(l1, l2);
                    } else {
                        return Math.max(l1, l2) + Math.min(r1, r2);
                    }
                } else if (l1 > r2) {
                    high = m1 - 1;
                } else {
                    low = m1 + 1;
                }
            }

            return 0.0;
        }
    }
}
