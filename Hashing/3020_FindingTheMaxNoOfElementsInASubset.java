// The Question
/*
3020. Find the Maximum Number of Elements in Subset (Medium)

You are given an array of positive integers nums.
You need to select a subset of nums which satisfies the following condition:
You can place the selected elements in a 0-indexed array such that it follows the pattern: [x, x2, x4, ..., xk/2, xk, xk/2, ..., x4, x2, x] (Note that k can be be any non-negative power of 2). For example, [2, 4, 16, 4, 2] and [3, 9, 3] follow the pattern while [2, 4, 8, 4, 2] does not.
Return the maximum number of elements in a subset that satisfies these conditions.

Example 1:
Input: nums = [5,4,1,2,2]
Output: 3
Explanation: We can select the subset {4,2,2}, which can be placed in the array as [2,4,2] which follows the pattern and 22 == 4. Hence the answer is 3.

Example 2:
Input: nums = [1,3,2,4]
Output: 1
Explanation: We can select the subset {1}, which can be placed in the array as [1] which follows the pattern. Hence the answer is 1. Note that we could have also selected the subsets {2}, {3}, or {4}, there may be multiple subsets which provide the same answer. 

Constraints:
2 <= nums.length <= 105
1 <= nums[i] <= 109
*/

// The Approach
/*
Use a HashMap to store the frequency of each number, while simultaneously tracking the maximum value in the array.
Handle '1' as a special case: since 1 raised to any power is 1, a valid sequence of 1s can look like [1, 1, 1, 1, 1]. The maximum valid length we can get from 1s is the largest odd count available. Initialize `maxLength` with this value.
Iterate through the unique numbers (keys) in the map to skip duplicates. 
For each number, set a tracking variable `curr` and enter a while loop. The loop checks how many times we can square `curr` where each step has a frequency of 2 or more.
If `curr` ever exceeds the maximum value in the array, break early to prevent unnecessary calculations and integer overflow.
Once the loop ends, check if the next power (`curr`) exists in the map with at least 1 copy to act as the "peak" element:
 - If yes, add 1 to the length.
 - If no, subtract 1 from the length (as the last paired element must act as the peak instead).
Update `maxLength` with the largest length found and return it.
*/

// The Solution
class Solution {
    public int maximumLength(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        long max = 0;
        for(int i : nums){
            map.merge(i, 1, Integer::sum); // equivalent to map.put(i, map.getOrDefault(i, 0) + 1) - can be applied in Java 8
            max = Math.max(max, i);
        }
        int counts1 = map.getOrDefault(1, 0);
        int maxLength = (counts1 % 2 == 0) ? counts1 - 1 : counts1;
        for(int i : map.keySet()){
            if(i == 1) continue;
            int len = 0;
            long curr = i;
            while(map.getOrDefault((int) curr, 0) >= 2){
                len += 2;
                curr *= curr;
                if(curr > max) break;
            }
            if(map.containsKey((int) curr)) len++;
            else len--;
            maxLength = Math.max(len, maxLength);
        }
        return maxLength;
    }
}