package main.revision.meta.easy;

/**
 * @author agond
 * @date May 23, 2025
 * @time 8:29:42 PM
 */
public class SparseVector {

    static class Pair {
        int index;
        int value;

        Pair(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    private Pair[] pairs;

    SparseVector(int[] nums) {
        // Constructor to initialize the object with the vector
        // The input vector is a sparse vector represented by an array of integers

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                pairs[i] = new Pair(i, nums[i]);
            }
        }
    }

    public int dotProduct(SparseVector vec) {
        // Return the dotProduct of two sparse vectors
        // The dot product is calculated as the sum of the products of corresponding
        // elements

        int result = 0;

        // Two pointers to traverse the pairs of both vectors
        int i = 0, j = 0;
        while (i < pairs.length && j < vec.pairs.length) {
            if (pairs[i].index == vec.pairs[j].index) {
                result += pairs[i].value * vec.pairs[j].value;
                i++;
                j++;
            } else if (pairs[i].index < vec.pairs[j].index) {
                i++;
            } else {
                j++;
            }
        }
        return result;
    }

}
