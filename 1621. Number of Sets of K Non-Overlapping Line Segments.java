Solution1:
dp[i][j] = dp[m][j - 1] * (i - m)  m < i [must choose j]
ans = sum(dp[i][k])

O(n^3) TLE
class Solution {
    public int numberOfSets(int n, int k) {
        int module = 1000000007;
        long[][] dp = new long[n][k + 1];
        dp[0][0] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                for (int m = 0; m < i; m++) {
                    dp[i][j] = (dp[i][j] + dp[m][j - 1] * (i - m)) % module;
                }
            }
        }
        long res = 0;
        for (int i = 0; i < n; i++) {
            res = (res + dp[i][k]) % module;
        }
        return (int)res;
    }
}


Solutioh2

dp[i][j]  = dp[k][j - 1] <choose j> + dp[i - 1][j] <not choose j> (k < i)
[may not choose j]
class Solution {
    public int numberOfSets(int n, int k) {
        int module = 1000000007;
        long[][] dp = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j <= k; j++) {
            long s = 0;
            for (int i = 1; i <= n; i++) {
                dp[i][j] = (dp[i - 1][j] + s) % module;
                s = (s + dp[i][j - 1]) % module;
            }
        }
        return (int)dp[n][k];
    }
}
