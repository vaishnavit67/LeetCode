// The question
/*
49. Group Anagrams

Given an array of strings strs, group the anagrams together. You can return the answer in any order.

Example 1:
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
Explanation:
There is no string in strs that can be rearranged to form "bat".
The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.

Example 2:
Input: strs = [""]
Output: [[""]]

Example 3:
Input: strs = ["a"]
Output: [["a"]]

Constraints:
1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.
*/

// The Approach
/*
Create a HashMap that stores (string: its anagrams)
Iterate over the given array
- for every string, convert it to a character array and sort it
  - if the map contains the sorted string you just add the current string as its anagram
  - else you create a new map entry for this string and add the current string as its anagram
*/

// The Solution
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for(String str : strs){
            char arr[] = str.toCharArray();
            Arrays.sort(arr);
            map.computeIfAbsent(new String(arr), key -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }
}