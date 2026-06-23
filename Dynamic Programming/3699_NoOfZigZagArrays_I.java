// The Question
/*
3699. Number of ZigZag Arrays I (Hard)
You are given three integers n, l, and r.
A ZigZag array of length n is defined as follows:
Each element lies in the range [l, r].
No two adjacent elements are equal.
No three consecutive elements form a strictly increasing or strictly decreasing sequence.
Return the total number of valid ZigZag arrays.
Since the answer may be large, return it modulo 109 + 7.
A sequence is said to be strictly increasing if each element is strictly greater than its previous one (if exists).
A sequence is said to be strictly decreasing if each element is strictly smaller than its previous one (if exists).

Example 1:
Input: n = 3, l = 4, r = 5
Output: 2

Explanation:
There are only 2 valid ZigZag arrays of length n = 3 using values in the range [4, 5]:
[4, 5, 4]
[5, 4, 5]​​​​​​​

Example 2:
Input: n = 3, l = 1, r = 3
Output: 10

Explanation:
There are 10 valid ZigZag arrays of length n = 3 using values in the range [1, 3]:
[1, 2, 1], [1, 3, 1], [1, 3, 2]
[2, 1, 2], [2, 1, 3], [2, 3, 1], [2, 3, 2]
[3, 1, 2], [3, 1, 3], [3, 2, 3]
All arrays meet the ZigZag conditions.

** Constraints:
3 <= n <= 2000
1 <= l < r <= 2000
 */

/* 
States:
** Index
** Direction
*/

// Optimized Solution
class Solution2 {
    
    // Initial Approach
    public int zigZagArrays1(int n, int l, int r){
        int MOD = 1_000_000_007;
        int m = r - l + 1;
        /*
        Shift as [l, r] is similar to [0, r-l+1] as we don't need the values, just their range
        - [4, 5, 4], [5, 4, 5]​ is similar to [0, 1, 0], [1, 0, 1]​​​​​ ​
        */
        if(m < 2 && n >= 3) return 0;

        int dp[][] = new int[m][2]; // No of elements, UP and DOWN directions
        for(int x = 0; x < m; x++){
            dp[x][1] = x; // No:of elements strinctly lesser than x
            dp[x][0] = m - x -1; // No:of elements strictly greater than x
        }

        for(int i = 3; i <= n; i++){
            int nextDp[][] = new int[m][2];
            
            long runningSum = 0;
            // Position UP - Moving DOWN
            for(int x = 0; x < m; x++){
                nextDp[x][1] = (int) runningSum;
                runningSum = (runningSum + dp[x][0]) % MOD;
            }

            runningSum = 0;
            // Position DOWN - Moving UP
            for(int x = m-1; x >= 0; x--){
                nextDp[x][0] = (int) runningSum;
                runningSum = (runningSum + dp[x][1]) % MOD;
            }

            // Assigning the updated prefix sum to original dp
            dp = nextDp;
        }

        int totalWays = 0;
        for(int x = 0; x < m; x++){
            totalWays = (totalWays + dp[x][0] + dp[x][1]) % MOD;
        }
        return totalWays;
    }

    // Optimized Solution
    public int zigZagArrays2(int n, int l, int r) {
        int MOD = 1_000_000_007;
        int m = r - l + 1;
        if (m < 2 && n >= 3) return 0;

        int[] dp = new int[m];
        java.util.Arrays.fill(dp, 1); // for n = 1, you could choose any value once in the interval (l, r)

        // Alternating transition passes
        for (int i = 1; i < n; i++) {
            int runningSum = 0, nextVal;
            if ((i & 1) == 1) { // Going UP
                for (int x = 0; x < m; x++) {
                    nextVal = (runningSum + dp[x]) % MOD;
                    dp[x] = runningSum;
                    runningSum = nextVal;
                }
            } else { // Going DOWN
                for (int x = m - 1; x >= 0; x--) {
                    nextVal = (runningSum + dp[x]) % MOD;
                    dp[x] = runningSum;
                    runningSum = nextVal;
                }
            }
        }

        long totalWays = 0;
        for (int v : dp) {
            totalWays = (totalWays + v) % MOD;
        }

        return (int) (totalWays * 2 % MOD); // Double mapping as up and down form a mirror image
    }
}