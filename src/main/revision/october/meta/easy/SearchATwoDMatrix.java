package main.revision.october.meta.easy;

public class SearchATwoDMatrix {

    class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {

            int rowIndx = searchPotentialRow(matrix, target);
            if (rowIndx != -1)
                return binarySearchOverRow(rowIndx, matrix, target);
            else {
                return false;

            }
        }

        private int searchPotentialRow(int[][] matrix, int target) {
            int low = 0;
            int high = matrix.length - 1;

            while (low <= high) {

                int mid = low + (high - low) / 2;

                if (matrix[mid][0] <= target && target <= matrix[mid][matrix[0].length]) {

                    return mid;
                }

                else if (matrix[mid][0] < target)
                    low = mid + 1;
                else if (matrix[mid][0] > target)
                    high = mid + -1;
            }

            return -1;
        }

        private boolean binarySearchOverRow(int rowIndx, int[][] matrix, int target) {
            int low = 0;
            int high = matrix[rowIndx].length - 1;

            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (matrix[rowIndx][mid] == target)
                    return true;
                else if (matrix[rowIndx][mid] > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            return false;
        }
    }

    // revised on 11/25/2025
    class SolutionRevisonOnThirdDay {
        public boolean searchMatrix(int[][] matrix, int target) {

            int rowIndx = searchPotentialRow(matrix, target);

            if (rowIndx != -1) {
                return binarySearchOverRow(rowIndx, matrix, target);
            }

            return false;
        }

        private int searchPotentialRow(int[][] matrix, int target) {

            int low = 0;
            int high = matrix.length - 1;

            while (low <= high) {

                int mid = low + (high - low) / 2;

                if (matrix[mid][0] <= target && target <= matrix[mid][matrix[0].length]) {
                    return mid;
                }

                else if (matrix[mid][0] < target) {
                    low = mid + 1;
                } else if (matrix[mid][0] > target) {
                    high = mid - 1;
                }
            }

            return -1;
        }

        private boolean binarySearchOverRow(int rowIndx, int[][] matrix, int target) {

            int low = 0;
            int high = matrix[rowIndx].length - 1;

            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (matrix[rowIndx][mid] == target)
                    return true;
                else if (matrix[rowIndx][mid] > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            return false;
        }
    }
}
