package main.revision.october.meta.medium;

public class Pow {
    public class Solution {
        public double myPow(double x, int n) {

            if (n == 0) {
                return 1;
            }

            if (n < 0) {
                return 1 / (x * myPow(x, -(n + 1)));
            }

            double result = myPow(x, n / 2);

            if (n % 2 == 0) {
                return result * result;
            } else {
                return result * result * x;
            }
        }
    }

    // revised on 11/29/2025
    public class SloutionRevisionOnThirtyDay {
        public double myPow(double x, int n) {

            if (n == 0) {
                return 1;
            }

            if (n < 0) {
                return 1 / (x * myPow(x, -(n + 1)));
            }

            double result = myPow(x, n / 2);

            if (n % 2 == 0) {
                return result * result;
            } else {
                return result * result * x;
            }
        }
    }

}
