class Solution {
    static final int MOD = 1_000_000_007;

    public int uniquePaths(int[][] grid) {
        return dfs(grid, 0, 0);
    }

    private int dfs(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;

        if (i == m - 1 && j == n - 1) {
            return 1;
        }

        long ans = 0;

        // Try moving right
        int right = resolve(grid, i, j, true);

        if (right != -1) {
            ans += dfs(grid, right / n, right % n);
        }

        // Try moving down
        int down = resolve(grid, i, j, false);

        if (down != -1) {
            ans += dfs(grid, down / n, down % n);
        }

        return (int)(ans % MOD);
    }

    private int resolve(int[][] grid, int i, int j,
                        boolean movingRight) {

        int m = grid.length;
        int n = grid[0].length;

        if (movingRight) {
            j++;
        } else {
            i++;
        }

        while (i < m && j < n) {

            if (grid[i][j] == 0) {
                return i * n + j;
            }

            // Mirror reflection
            if (movingRight) {
                i++;
                movingRight = false;
            } else {
                j++;
                movingRight = true;
            }
        }

        return -1;
    }
}
