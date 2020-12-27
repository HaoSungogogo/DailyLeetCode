/***
intuition: find all s and t substrings to compare
Solution1:
  1. find all t substrings to store into trie
  2. exhaust all s subsrings to traverse on the trie
Solutioh2: like the code below
Choose the start point from s or t and compare characters one by one.
cur is the current number of consecutive same characters.
pre is the previous number of consecutive same characters.
***/

class Solution {
    public int countSubstrings(String s, String t) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res += helper(s, t, i, 0);
        }
        for (int i = 1; i < t.length(); i++) {
            res += helper(s, t, 0, i);
        }
        return res;
    }

    private int helper(String s, String t, int i, int j) {
        int sLen = s.length();
        int tLen = t.length();
        int res = 0;
        int prev = 0;
        int cur = 0;
        for (; i < sLen && j < tLen; i++, j++) {
            cur++;
            if (s.charAt(i) != t.charAt(j)) {
                prev = cur;
                cur = 0;
            }
            res += prev;
        }
        return res;
    }
}
