/*
1358. Number of Substrings Containing All Three Characters

Given a string s consisting only of characters a, b and c.
Return the number of substrings containing at least one occurrence of all these characters a, b and c.

Example 1:
Input: s = "abcabc"
Output: 10
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again). 

Example 2:
Input: s = "aaacb"
Output: 3
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb". 

Example 3:
Input: s = "abc"
Output: 1

Constraints:
3 <= s.length <= 5 x 10^4
s only consists of a, b or c characters.
*/

// The Approach
/*
We initially store the last index of every character of a, b, c
When every character is found, we try to find how many substrings are possible from the surrent index which works on the formula - (n*(n+1))/2
*/

// The Solution
class Solution {
    public int numberOfSubstrings(String s) {
        int lastA = -1, lastB = -1, lastC = -1;
        char arr[] = s.toCharArray();
        int count = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == 'a') lastA = i;
            else if(arr[i] == 'b') lastB = i;
            else lastC = i;

            if(lastA != -1 && lastB != -1 && lastC != -1){
                count += Math.min(lastA, Math.min(lastB, lastC)) + 1;
            }
        }
        return count;
    }
}