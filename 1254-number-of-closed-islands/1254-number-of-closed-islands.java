
class Solution {
    private int rows, cols;
    private boolean[][] visited;
    private boolean closed;

    private void dfs(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= rows || c >= cols) {
            closed = false;
            return;
        }

        if (grid[r][c] == 1 || visited[r][c]) {
            return;
        }

        visited[r][c] = true;

        dfs(grid, r - 1, c); // Up
        dfs(grid, r + 1, c); // Down
        dfs(grid, r, c - 1); // Left
        dfs(grid, r, c + 1); // Right
    }

    public int closedIsland(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];

        int count = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0 && !visited[r][c]) {
                    closed = true;
                    dfs(grid, r, c);

                    if (closed) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
