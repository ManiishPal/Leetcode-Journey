class Solution {
    static final long MOD = 1_000_000_007L;

    public int numberOfSets(int n, int k) {
        int N = n + k - 1;
        int R = 2 * k;

        long ans = 1;

        for (int i = 1; i <= R; i++) {
            ans = ans * (N - R + i) % MOD;
            ans = ans * modInverse(i) % MOD;
        }

        return (int) ans;
    }

    private long modInverse(long x) {
        return power(x, MOD - 2);
    }

    private long power(long base, long exponent) {
        long result = 1;

        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = result * base % MOD;
            }

            base = base * base % MOD;
            exponent >>= 1;
        }

        return result;
    }
}