/**
 * @author agond
 * @date May 29, 2025
 * @time 9:26:46 PM
 */
package main.revision.meta.medium;

public class Power {

    public double myPow(double x, int n) {
        double ans = 1.0;
        long nn = n;

        if (nn < 0) {

            nn = -1 * nn;
        }

        while (nn > 0) {
            if (nn % 2 == 1) {

                ans = ans * x;
                nn = nn - 1;
            } else {
                x = x * x;
                nn = nn / 2;
            }
        }

        if (n < 0) {
            ans = (1.0) / (ans);
        }

        return ans;
    }
}
