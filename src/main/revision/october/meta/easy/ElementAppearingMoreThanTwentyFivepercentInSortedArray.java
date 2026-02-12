package main.revision.october.meta.easy;

public class ElementAppearingMoreThanTwentyFivepercentInSortedArray {

    class Solution {
        public int findSpecialInteger(int[] arr) {
            // arr ki length nikal lo.
            int n = arr.length;
            // n ka 25% (yaani quarter) calculate karo.
            int quarter = n / 4;
            // Array ke through loop chalao. Loop n - quarter tak chalega.
            // Aisa isliye kyunki agar koi element 25% se zyada baar aata hai,
            // toh woh element aur usse 'quarter' distance aage wala element same hoga.
            for (int i = 0; i < n - quarter; i++) {
                // Check karo ki current element (arr[i]) aur 'quarter' positions aage wala
                // element (arr[i + quarter]) same hai ya nahi.
                if (arr[i] == arr[i + quarter]) {
                    // Agar same hain, toh yahi hamara special integer hai, isse return kar do.
                    return arr[i];
                }
            }
            // Problem ke constraints ke hisaab se, hamesha ek solution milega,
            // isliye ye line kabhi execute nahi honi chahiye.
            // Lekin Java ko ek return statement chahiye function ke end me.
            return -1; // Should not be reached given the problem constraints
        }
    }

    // revised on 12/25/2025
    class SolutionRevisedOnThirdDay {
        public int findSpecialInteger(int[] arr) {

            int n = arr.length;
            int quarter = n / 4;

            for (int i = 0; i < n - quarter; i++) {

                if (arr[i] == arr[i + quarter]) {
                    return arr[i];
                }
            }

            return -1;
        }
    }

    // revised on 12/31/2025
    class SolutionRevisedOnSeventhDay {
        public int findSpecialInteger(int[] arr) {

            int n = arr.length;
            int quarter = n / 4;

            for (int i = 0; i < n; i++) {

                if (arr[i] == arr[i + quarter]) {
                    return arr[i];
                }
            }

            return -1;
        }
    }

    // revised on 1/14/2026
    class SolutionRevisedOnDayFourteen {
        public int findSpecialInteger(int[] arr) {

            int n = arr.length;
            int quarter = n / 4;

            for (int i = 0; i < n; i++) {

                if (arr[i] == arr[i + quarter]) {
                    return arr[i];
                }
            }

            return -1;
        }
    }

    // revised on 2/12/2026
    class SolutionRevisedOnDayThirty {
        public int findSpecialInteger(int[] arr) {

            int n = arr.length;
            int quarter = n / 4;

            for (int i = 0; i < n; i++) {

                if (arr[i] == arr[i + quarter]) {
                    return arr[i];
                }
            }

            return -1;
        }
    }
}
