package questions;

public class Simpledfs1 {

    private static final int[][] GRID = {
            {1, 1, 0, 0, 0},
            {0, 1, 1, 0, 1},
            {0, 0, 1, 0, 1},
            {1, 0, 0, 1, 1}
    };

    private static final int[][] DIRECTIONS = {
            {1, 0},  // down
            {-1, 0}, // up
            {0, 1},  // right
            {0, -1}  // left
    };

    private static int largestIsland() {
        int rows = GRID.length;
        int cols = GRID[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int maxSize = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (GRID[row][col] == 1 && !visited[row][col]) {
                    int size = dfs(row, col, visited);
                    if (size > maxSize) {
                        maxSize = size;
                    }
                }
            }
        }

        return maxSize;
    }

    private static int dfs(int row, int col, boolean[][] visited) {
        visited[row][col] = true;
        int size = 1;

        for (int[] dir : DIRECTIONS) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];

            if (nextRow < 0 || nextRow >= GRID.length || nextCol < 0 || nextCol >= GRID[0].length) {
                continue;
            }
            if (GRID[nextRow][nextCol] == 0 || visited[nextRow][nextCol]) {
                continue;
            }

            size += dfs(nextRow, nextCol, visited);
        }

        return size;
    }

    public static void main(String[] args) {
        int maxIsland = largestIsland();
        System.out.println("Largest island size = " + maxIsland);
    }

}
