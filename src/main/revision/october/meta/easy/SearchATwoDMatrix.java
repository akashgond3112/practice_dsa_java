package main.revision.october.meta.easy;

public class SearchATwoDMatrix {

    class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            // Pehle potential row dhoondho jisme target ho sakta hai
            int rowIndx = searchPotentialRow(matrix, target);
            if (rowIndx != -1) {
                // Agar row mil gayi toh us row mein binary search karke target dhoondho
                return binarySearchOverRow(rowIndx, matrix, target);
            } else {
                // Agar koi row nahi mili toh target matrix mein nahi hai
                return false;
            }
        }

        private int searchPotentialRow(int[][] matrix, int target) {
            int low = 0;
            int high = matrix.length - 1;

            // Binary search lagake potential row dhoondho
            while (low <= high) {
                int mid = low + (high - low) / 2;

                // Agar target row ke range mein hai toh yeh row return karo
                if (matrix[mid][0] <= target && target <= matrix[mid][matrix[0].length - 1]) {
                    return mid;
                }
                // Agar target current row ke first element se bada hai toh neeche wali rows
                // check karo
                else if (matrix[mid][0] < target) {
                    low = mid + 1;
                }
                // Agar target current row ke first element se chhota hai toh upar wali rows
                // check karo
                else {
                    high = mid - 1;
                }
            }

            // Agar koi row nahi mili toh -1 return karo
            return -1;
        }

        private boolean binarySearchOverRow(int rowIndx, int[][] matrix, int target) {
            int low = 0;
            int high = matrix[rowIndx].length - 1;

            // Binary search lagake row ke andar target dhoondho
            while (low <= high) {
                int mid = low + (high - low) / 2;

                // Agar target mil gaya toh true return karo
                if (matrix[rowIndx][mid] == target) {
                    return true;
                }
                // Agar target current element se chhota hai toh left side check karo
                else if (matrix[rowIndx][mid] > target) {
                    high = mid - 1;
                }
                // Agar target current element se bada hai toh right side check karo
                else {
                    low = mid + 1;
                }
            }

            // Agar target nahi mila toh false return karo
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

    // revised on 12/1/2025
    class SolutionRevisonOnSeventhDay {
        public boolean searchMatrix(int[][] matrix, int target) {
            int rowIndex = searchPotentialRow(matrix, target);

            if (rowIndex != -1) {
                return binarySearchOverRow(rowIndex, matrix, target);
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
                } else if (matrix[mid][0] < target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            return -1;
        }

        private boolean binarySearchOverRow(int rowIndx, int[][] matrix, int target) {

            int low = 0;
            int high = matrix.length - 1;

            while (low <= high) {
                int mid = low + (high - low) / 2;

                if (matrix[rowIndx][mid] == target) {
                    return true;
                } else if (matrix[rowIndx][mid] < target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            return false;

        }
    }

    // revised on 12/15/2025
    class SolutionRevisonOnFourteenDay {
        public boolean searchMatrix(int[][] matrix, int target) {

            int rowIndex = searchPotentialRow(matrix, target);

            if (rowIndex != -1) {
                return binarySearchOverRow(rowIndex, matrix, target);
            } else {
                return false;
            }
        }

        private int searchPotentialRow(int[][] matrix, int target) {
            int low = 0;
            int high = matrix.length - 1;

            while (low <= high) {

                int mid = low + (high - low) / 2;

                if (matrix[mid][0] <= target && target <= matrix[mid][matrix[0].length - 1]) {
                    return mid;
                } else if (matrix[mid][0] < target) {
                    low = mid + 1;
                } else {
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

                if (matrix[rowIndx][mid] == target) {
                    return true;
                } else if (matrix[rowIndx][mid] > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            return false;
        }
    }

    // revised on 1/13/2026
    class SolutionRevisedOnDayThirty {
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

                if (matrix[mid][0] == target) {
                    return mid;
                } else if (matrix[mid][0] > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            return -1;
        }

        private boolean binarySearchOverRow(int rowIndx, int[][] matrix, int target) {
            int low = 0;

            int high = matrix[rowIndx].length - 1;

            while (low <= high) {

                int mid = low + (high - low) / 2;

                if (matrix[rowIndx][mid] == target) {
                    return true;
                } else if (matrix[rowIndx][mid] > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            return false;
        }
    }
}
