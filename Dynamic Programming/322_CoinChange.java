// The Question
/*
322. Coin Change (Medium)
You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
You may assume that you have an infinite number of each kind of coin.

Example 1:
Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1

Example 2:
Input: coins = [2], amount = 3
Output: -1

Example 3:
Input: coins = [1], amount = 0
Output: 0

Constraints:
1 <= coins.length <= 12
1 <= coins[i] <= 231 - 1
0 <= amount <= 104
*/

// The Approach

// The Solution
class Solution {

    // Top Down
    public int topDown(int ind, int amount, int coins[], int dp[][]){
        if(amount == 0) return 0;
        if(ind == 0){
            if(amount % coins[0] == 0) return amount/coins[0];
            else return (int) 1e9;
        }
        if(dp[ind][amount] != -1) return dp[ind][amount];
        int notTake = 0 + topDown(ind-1, amount, coins, dp);
        int take = (int) 1e9;
        if(coins[ind] <= amount) take = 1 + topDown(ind, amount - coins[ind], coins, dp);
        return dp[ind][amount] = Math.min(take, notTake);
    }
    public int coinChangeTopDown(int[] coins, int amount) {
        int n = coins.length;
        int dp[][] = new int[n][amount+1];
        for(int arr[] : dp){
            Arrays.fill(arr, -1);
        }
        int count = topDown(n-1, amount, coins, dp);
        return (count >= (int)(1e9)) ? -1 : count;
    }

    // Bottom Up
    public int coinChangeBottomUp(int[] coins, int amount) {
        int n = coins.length;
        int dp[][] = new int[n][amount+1];
        for(int i = 0; i < n; i++){
            dp[i][0] = 0;
        }
        for(int amt = 1; amt <= amount; amt++){
            if(amt % coins[0] == 0) dp[0][amt] = amt/coins[0];
            else dp[0][amt] = (int) 1e9;
        }
        for(int i = 1; i < n; i++){
            for(int j = 1; j <= amount; j++){
                int notTake = dp[i-1][j];
                int take = (int) 1e9;
                if(coins[i] <= j) take = 1 + dp[i][j - coins[i]];
                dp[i][j] = Math.min(take, notTake);
            }
        }
        int count = dp[n-1][amount];
        return (count >= (int)(1e9)) ? -1 : count;
    }

    // Optimized (Both time and space)
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        if (n == 1) return amount % coins[0] == 0 ? amount / coins[0] : -1;
        int dp[] = new int[amount+1];
        Arrays.fill(dp, (int)1e9);
        dp[0] = 0;
        for(int coin : coins){
            for(int j = coin; j <= amount; j++){
                dp[j] = Math.min(dp[j], 1 + dp[j - coin]);
            }
        }
        int count = dp[amount];
        return (count >= (int)(1e9)) ? -1 : count;
    }

}