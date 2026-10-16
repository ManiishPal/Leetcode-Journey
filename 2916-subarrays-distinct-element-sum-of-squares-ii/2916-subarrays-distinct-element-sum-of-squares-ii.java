
import java.util.Arrays;

class Solution {
    static final long MOD = 1_000_000_007L;

    long[] sum, sq, lazy;
    int n;

    public int sumCounts(int[] nums) {
        n = nums.length;

        sum = new long[4 * n];
        sq = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        Arrays.fill(last, -1);

        long ans = 0;

        for (int r = 0; r < n; r++) {
            int prev = last[nums[r]];

            update(1, 0, n - 1, prev + 1, r);

            ans = (ans + sq[1]) % MOD;
            last[nums[r]] = r;
        }

        return (int) ans;
    }

    void apply(int node, int l, int r, long d) {
        long len = r - l + 1L;
        long oldSum = sum[node];

        sq[node] = (
            sq[node] + 2L * d % MOD * oldSum % MOD
            + len % MOD * d % MOD * d % MOD
        ) % MOD;

        sum[node] = (sum[node] + len % MOD * d) % MOD;
        lazy[node] += d;
    }

    void push(int node, int l, int r) {
        if (lazy[node] == 0 || l == r) return;

        int mid = l + (r - l) / 2;
        apply(node * 2, l, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, r, lazy[node]);

        lazy[node] = 0;
    }

    void update(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return;

        if (ql <= l && r <= qr) {
            apply(node, l, r, 1);
            return;
        }

        push(node, l, r);

        int mid = l + (r - l) / 2;
        update(node * 2, l, mid, ql, qr);
        update(node * 2 + 1, mid + 1, r, ql, qr);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }
}
