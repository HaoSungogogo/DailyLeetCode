/*
'AXXAXA' unique A subsring = 3 * 2
index[c][0:1] represents the last two locations of character c
*/

class Solution {
    public int uniqueLetterString(String s) {
        int module = 1000000007;
        int len = s.length();
        int[][] index = new int[26][2];
        for (int i = 0; i < 26; i++) {
            Arrays.fill(index[i], -1);
        }
        int res = 0;
        for (int i = 0; i < len; i++) {
            int c = s.charAt(i) - 'A';
            res = (res + (i - index[c][1]) * (index[c][1] - index[c][0]) % module) % module;
            index[c][0] = index[c][1];
            index[c][1] = i;
        }
        for (int i = 0; i < 26; i++) {
            res = (res + (len - index[i][1]) * (index[i][1] - index[i][0]) % module) % module;
        }
        return res;
    }
}
