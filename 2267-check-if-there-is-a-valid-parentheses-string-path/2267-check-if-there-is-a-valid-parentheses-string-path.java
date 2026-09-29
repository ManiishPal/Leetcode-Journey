class Solution {
    private Boolean[][][] memo;

    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        memo = new Boolean[m][n][m + n + 1];

        return dfs(grid, 0, 0, 0);
    }

    private boolean dfs(char[][] grid, int row, int col, int balance) {
        int m = grid.length;
        int n = grid[0].length;

        // Update balance
        if (grid[row][col] == '(') {
            balance++;
        } else {
            balance--;
        }

        // Invalid balance
        if (balance < 0) {
            return false;
        }

        // Too large to ever return to zero
        if (balance > (m - row) + (n - col) - 1) {
            return false;
        }

        // Destination
        if (row == m - 1 && col == n - 1) {
            return balance == 0;
        }

        if (memo[row][col][balance] != null) {
            return memo[row][col][balance];
        }

        boolean result = false;

        // Down
        if (row + 1 < m) {
            result |= dfs(grid, row + 1, col, balance);
        }

        // Right
        if (col + 1 < n) {
            result |= dfs(grid, row, col + 1, balance);
        }

        return memo[row][col][balance] = result;
    }
}