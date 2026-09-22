
class Solution {

    int k, n;
    int[][] tree;
    int[] prod;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        this.n = nums.length;

        tree = new int[4 * n][k];
        prod = new int[4 * n];

        build(1, 0, n - 1, nums);

        int[] result = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            nums[index] = value;

            update(1, 0, n - 1, index, value);

            int[] ans = query(1, 0, n - 1, start, n - 1);

            result[q] = ans[x];
        }

        return result;
    }

    void build(int node, int l, int r, int[] nums) {
        if (l == r) {
            int rem = nums[l] % k;
            prod[node] = rem;
            tree[node][rem] = 1;
            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        merge(node);
    }

    void merge(int node) {
        int left = node * 2;
        int right = node * 2 + 1;

        prod[node] = (int)((long) prod[left] * prod[right] % k);

        for (int r = 0; r < k; r++) {
            tree[node][r] = tree[left][r];
        }

        for (int r = 0; r < k; r++) {
            int rem = (int)((long) prod[left] * r % k);
            tree[node][rem] += tree[right][r];
        }
    }

    void update(int node, int l, int r, int index, int value) {
        if (l == r) {
            int rem = value % k;

            prod[node] = rem;

            for (int i = 0; i < k; i++) {
                tree[node][i] = 0;
            }

            tree[node][rem] = 1;
            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }

        merge(node);
    }

    int[] query(int node, int l, int r, int ql, int qr) {
        if (r < ql || l > qr) {
            return new int[k];
        }

        if (ql <= l && r <= qr) {
            return tree[node].clone();
        }

        int mid = (l + r) / 2;

        int[] left = query(node * 2, l, mid, ql, qr);
        int[] right = query(node * 2 + 1, mid + 1, r, ql, qr);

        int leftProd = getProd(node * 2, l, mid, ql, qr);

        int[] res = new int[k];

        for (int i = 0; i < k; i++) {
            res[i] += left[i];

            int rem = (int)((long) leftProd * i % k);
            res[rem] += right[i];
        }

        return res;
    }

    int getProd(int node, int l, int r, int ql, int qr) {
        if (r < ql || l > qr) {
            return 1;
        }

        if (ql <= l && r <= qr) {
            return prod[node];
        }

        int mid = (l + r) / 2;

        int left = getProd(node * 2, l, mid, ql, qr);
        int right = getProd(node * 2 + 1, mid + 1, r, ql, qr);

        return (int)((long) left * right % k);
    }
}