
class Solution {
    private int m, n;
    private boolean[][] visited;
    private int[][] grid;

    private long dfs(int r, int c) {
        if (r < 0 || r >= m || c < 0 || c >= n
                || grid[r][c] == 0 || visited[r][c]) {
            return 0;
        }

        visited[r][c] = true;

        long sum = grid[r][c];

        sum += dfs(r + 1, c);
        sum += dfs(r - 1, c);
        sum += dfs(r, c + 1);
        sum += dfs(r, c - 1);

        return sum;
    }

    public int countIslands(int[][] grid, int k) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];

        int count = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] > 0 && !visited[r][c]) {
                    long sum = dfs(r, c);

                    if (sum % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
