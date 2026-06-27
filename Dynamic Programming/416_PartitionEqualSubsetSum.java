// The Question
/*
416. Partition Equal Subset Sum
Given an integer array nums, return true if you can partition the array into two subsets 
such that the sum of the elements in both subsets is equal or false otherwise.

Example 1:
Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].

Example 2:
Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.

Constraints:
1 <= nums.length <= 200
1 <= nums[i] <= 100
*/

// The Approach
/*
If you had to divide the total array into two subsets (subset 1 & subset 2) with equal sum, then the sum of subset 1 would be equal to subset 2
sum(subset 1) + sum(subset 2) = total sum
sum(subset 1) = sum(subset 2)
This implies that sum(subset 1) = sum(subset 2) = total sum / 2 (which ultimately becomes our target and we need to find a subset whose sum equals the target)
NOTE: If the sum is odd you can't partition it between both the subsets equally so you can immediately return that it is impossible

Top Down:
    Base Cases:
    1. If the target is 0 at any moment you return true
    2. If only one element remains (ind == 0), the target can be formed only if nums[0] == target
    
    Verify if the current iter is already stored in the memoization table

    You check if the target can be formed by any of these two ways
    - You do not take the current value and move to the next index
    - If the current value is less than the target, you take it and move to the next index

    If the target is found by any of the ways you would return true at the end

Bottom Up:
    Base Cases:
    1. For every index, if target is 0 then we would return true
    2. For the 0th index only two sums can be true, it would be either 0 or nums[0]

    Iterate from 1 -> n-1 for ind (as you have already done 0)
        Iterate from 1 -> k for target (as you have already done 0)
            You check if the target can be formed by any of these two ways
            - You do not take the current value and move to the next index
            - If the current value is less than the target, you take it and move to the next index
    
    Finally return dp[n-1][target]

Optimized:
Instead of storing every row, we store only one row because each state
depends only on the previous row.

The target is iterated from right to left so that each number is used
at most once. If we iterate from left to right, the current number could
be reused multiple times within the same iteration.
*/

// The Solution
class Solution {

    // Top Down
    public boolean topDown(int ind, int target, int nums[], Boolean dp[][]){
        if(target == 0) return true;
        if(ind == 0) return (target == nums[0]);
        if(dp[ind][target] != null) return dp[ind][target];

        boolean notTake = topDown(ind - 1, target, nums, dp);
        boolean take = false;
        if(target >= nums[ind]) take = topDown(ind - 1, target - nums[ind], nums, dp);

        return dp[ind][target] = take || notTake;
    }

    // Bottom Up
    public boolean bottomUp(int nums[], int target){
        int n = nums.length;
        boolean dp[][] = new boolean[n][target + 1];
        for(int i = 0; i < n; i++){
            dp[i][0] = true;
        }
        if(target >= nums[0]) dp[0][nums[0]] = true;
        
        for(int ind = 1; ind < n; ind++){
            for(int k = 1; k <= target; k++){
                boolean notTake = dp[ind - 1][k];
                boolean take = false;
                if(k >= nums[ind]) take = dp[ind - 1][k - nums[ind]];
                dp[ind][k] = take | notTake;
            }
        }
        return dp[n-1][target];
    }

    // An Optimized Solution
    public boolean optimized(int nums[], int target){
        int n = nums.length;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int k = target; k >= num; k--) {
                dp[k] = dp[k] || dp[k - num];
            }
        }
        return dp[target];
    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        if(sum % 2 != 0) return false;
        int target = sum/2;
        return optimized(nums, target);   
    }
}