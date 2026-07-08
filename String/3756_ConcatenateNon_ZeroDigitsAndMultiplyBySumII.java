// The Question
/*
3756. Concatenate Non-Zero Digits and Multiply by Sum II (Medium)

You are given a string s of length m consisting of digits. You are also given a 2D integer array queries, where queries[i] = [li, ri].
For each queries[i], extract the substring s[li..ri]. Then, perform the following:
Form a new integer x by concatenating all the non-zero digits from the substring in their original order. If there are no non-zero digits, x = 0.
Let sum be the sum of digits in x. The answer is x * sum.
Return an array of integers answer where answer[i] is the answer to the ith query.
Since the answers may be very large, return them modulo 109 + 7.

Example 1:
Input: s = "10203004", queries = [[0,7],[1,3],[4,6]]
Output: [12340, 4, 9]
Explanation:
s[0..7] = "10203004"
x = 1234
sum = 1 + 2 + 3 + 4 = 10
Therefore, answer is 1234 * 10 = 12340.
s[1..3] = "020"
x = 2
sum = 2
Therefore, the answer is 2 * 2 = 4.
s[4..6] = "300"
x = 3
sum = 3
Therefore, the answer is 3 * 3 = 9.

Example 2:
Input: s = "1000", queries = [[0,3],[1,1]]
Output: [1, 0]
Explanation:
s[0..3] = "1000"
x = 1
sum = 1
Therefore, the answer is 1 * 1 = 1.
s[1..1] = "0"
x = 0
sum = 0
Therefore, the answer is 0 * 0 = 0.

Example 3:
Input: s = "9876543210", queries = [[0,9]]
Output: [444444137]
Explanation:
s[0..9] = "9876543210"
x = 987654321
sum = 9 + 8 + 7 + 6 + 5 + 4 + 3 + 2 + 1 = 45
Therefore, the answer is 987654321 * 45 = 44444444445.
We return 44444444445 modulo (109 + 7) = 444444137.

Constraints:
1 <= m == s.length <= 105
s consists of digits only.
1 <= queries.length <= 105
queries[i] = [li, ri]
0 <= li <= ri < m
*/

// The Approach
/*
Create 5 arrays 
    - count -> to count the no of non zero numbers at index i
    - sum -> to calculate the running sum at index i
    - prefix -> to form the number till index i skipping zeroes
    - powerOf10 -> to have the rising powers of 10 starting from 1 (simply put Math.pow(10,i))
    - result -> to store the final query results
Use 1-based indexing
store 1 at the 0th index of powerOf10 (powerOf10[0] = 1) -> so that if you have no digits you don't multiply anything
for every char/digit of the string
    - increase power of 10 by multiplying it to 0
    - if it is 0 -> you store the prev values recorded for count, sum, prefix
    - if it is not 0
        - increment count by 1
        - add the number to the running sum
        - add the number to the prefix to store the non zero numbers preserving their order
Iterate through every query,, assign variables start and end for every query and compute the following (following 1-indexing, therefore adding 1 for every index used)
    - the current sum would be sum at the end+1 - sum at the start
    - no:of digits at the current index would be count of digits at the end+1 - count of digits at the start
    - to compute the current num - prefix at end+1 - (prefix at start x(*) no:of digits calculated in the previous step) {this helps us remove the digits that were not required for the current interval}
    - store the current sum * current num in the result
return result

NOTE: check to it that the somputed value, doesn't exceed integer limit, make it a modulo of 10^9
*/

// The Solution
class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int MOD = 1_000_000_007;
        int n = s.length();
        char str[] = s.toCharArray();
        int count[] = new int[n+1];
        int sum[] = new int[n+1];
        long prefix[] = new long[n+1];
        long powerOf10[] = new long[n+1];
        powerOf10[0] = 1;
        for(int i = 0; i < n; i++){
            int num = str[i]-'0';
            powerOf10[i+1] = (powerOf10[i] * 10) % MOD;
            if(num == 0){
                count[i+1] = count[i];
                sum[i+1] = sum[i];
                prefix[i+1] = prefix[i];
            }
            else{
                count[i+1] = count[i] + 1;
                sum[i+1] = (sum[i] + num) % MOD;
                prefix[i+1] = (prefix[i]*10 + num) % MOD;
            }
        }
        int res[] = new int[queries.length];
        for(int i = 0; i < queries.length; i++){
            int start = queries[i][0];
            int end = queries[i][1];
            long currSum = (sum[end+1] - sum[start] + MOD) % MOD;
            int digits = count[end+1] - count[start];
            long currNum = (prefix[end+1] - (prefix[start] * powerOf10[digits]) % MOD + MOD) % MOD;
            res[i] = (int) ((currSum * currNum) % MOD);
        }
        return res;
    }
}