// The Question
/*
3974. Maximum Total Sum of K Selected Elements

You are given an integer array nums and two integers k and mul.
Select exactly k elements from nums. Process these elements one by one in any order you choose.
For each selected element, independently choose one of the following:
Add the element's value to the total sum, or
Multiply the element by the current value of mul and add the result to the total sum.
After processing each selected element, mul decreases by 1, regardless of which option was chosen. The current value of mul may become 0 or negative.

Return an integer denoting the maximum possible total sum.

Example 1:
Input: nums = [6,1,2,9], k = 3, mul = 2
Output: 26
Explanation:

One optimal way:
One optimal selection is nums[3] = 9, nums[0] = 6, and nums[2] = 2.
Process nums[3] = 9 first: choose multiplication, so it contributes 9 * 2 = 18. Now, mul becomes 1.
Process nums[0] = 6 next: choose multiplication, so it contributes 6 * 1 = 6. Now, mul becomes 0.
Process nums[2] = 2 last: choose addition, so it contributes 2.
The total sum is 18 + 6 + 2 = 26.

Example 2:
Input: nums = [3,7,5,2], k = 2, mul = 4
Output: 43
Explanation:

One optimal way:
One optimal selection is nums[1] = 7 and nums[2] = 5.
Process nums[1] = 7 first: choose multiplication, so it contributes 7 * 4 = 28. Now, mul becomes 3.
Process nums[2] = 5 next: choose multiplication, so it contributes 5 * 3 = 15.
The total sum is 28 + 15 = 43.

Example 3:
Input: nums = [4,4], k = 1, mul = 1
Output: 4
Explanation:

One optimal way:
One optimal selection is nums[0] = 4.
Process nums[0] = 4: choose multiplication, so it contributes 4 * 1 = 4.
The total sum is 4.

Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 105
1 <= k <= nums.length
1 <= mul <= 105
*/

// The Approach
/*
Sort the elements and iterate over the array from right to left until you process k elements (n - k) from the end, so you add only the maximum elements
You try to multipy the current element with mul until mul > 1, then you just add the remaining elements to the result (mul becomes 1) and return it
NOTE: Cast to long during calculation to prevent 32-bit integer overflow.
*/

// The Solution
class Solution {
    public long maxSum(int[] nums, int k, int mul) {
        Arrays.sort(nums);
        int n = nums.length;
        long sum = 0;
        for(int i = n-1; i >= n - k; i--){
            int currMul = Math.max(1, mul);
            sum += (long) nums[i] * currMul;
            mul--;
        }
        return sum;
    }
}