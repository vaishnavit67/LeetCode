// The Question
/*
3975. Filter Occupied Intervals
You are given a 2D integer array occupiedIntervals, where occupiedIntervals[i] = [starti, endi] represents a time interval during which you are occupied. Each interval starts at starti and ends at endi, inclusive. These intervals may overlap.
You are also given two integers freeStart and freeEnd, which define a free time interval from freeStart to freeEnd, inclusive.
Your task is to merge all occupied intervals that overlap or touch, then remove all integer points in the free interval from the merged occupied intervals.
Two intervals touch if the second interval starts immediately after the first one ends. For example, [1, 1] and [2, 2] touch and should be merged into [1, 2].
Return the remaining occupied intervals in sorted order. The returned intervals must be non-overlapping and must contain the minimum number of intervals possible. If there are no remaining occupied points, return an empty list.

Example 1:
Input: occupiedIntervals = [[2,6],[4,8],[10,10],[10,12],[14,16]], freeStart = 7, freeEnd = 11
Output: [[2,6],[12,12],[14,16]]
Explanation:
After merging, the occupied intervals are [2, 8], [10, 12], and [14, 16].
Excluding the free interval [7, 11] results in [2, 6], [12, 12], and [14, 16].

Example 2:
Input: occupiedIntervals = [[1,5],[2,3]], freeStart = 3, freeEnd = 8
Output: [[1,2]]
Explanation:
After merging, the occupied interval is [1, 5].
Excluding the free interval [3, 8] results in [1, 2].

Constraints:
1 <= occupiedIntervals.length <= 5 * 104
occupiedIntervals[i].length == 2
1 <= starti <= endi <= 109
1 <= freeStart <= freeEnd <= 109
*/

// The Approach
/*
Initially, sort the intervals on the basis of start
Merge the continuous intervals, if the next start is currend end+1 then you merge the intervals and add it into a Integer List array
In the merge array you check if the interval does not occur in the free interval
- If yes, you add it to the final result
- If not
    - You check if the start is less than the freeStart, the end can be anywhere - then you narrow down the interval to (start, freeStart-1) and add it to the result
    - you check if the end is greater than the freeEnd, the start can be anywhere - then you narrow down the interval to (freeEnd+1, end) and add it to the result
*/

// The Solution
class Solution {
    public List<List<Integer>> filterOccupiedIntervals(int[][] occupiedIntervals, int freeStart, int freeEnd) {
        if(occupiedIntervals.length == 0) return new ArrayList<>();

        Arrays.sort(occupiedIntervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merge = new ArrayList<>();
        int curr[] = occupiedIntervals[0];

        for(int i = 1; i < occupiedIntervals.length; i++){
            int next[] = occupiedIntervals[i];
            if(next[0] <= curr[1] + 1){
                curr[1] = Math.max(curr[1], next[1]);
            }
            else{
                merge.add(curr);
                curr = next;
            }
        }
        merge.add(curr);

        List<List<Integer>> res = new ArrayList<>();

        for(int[] interval : merge){
            int start = interval[0];
            int end = interval[1];

            if(end < freeStart || start > freeEnd) res.add(Arrays.asList(start, end));
            else{
                if(start < freeStart) res.add(Arrays.asList(start, freeStart - 1));
                if(end > freeEnd) res.add(Arrays.asList(freeEnd + 1, end));
            }
        }
        return res;
    }
}