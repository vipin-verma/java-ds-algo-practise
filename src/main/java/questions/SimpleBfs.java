package questions;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Breadth-first search over a 2D matrix: start at the top-left cell and visit every reachable "1".
 */
public class SimpleBfs {

    private static final int[][] GRID = {
            {1, 1, 0, 0},
            {0, 1, 1, 0},
            {0, 0, 1, 1}
    };

    private static final int[][] DIRECTIONS = {
            {1, 0},  // down
            {-1, 0}, // up
            {0, 1},  // right
            {0, -1}  // left
    };

    private static void bfsFrom(int startRow, int startCol) {
        int rows = GRID.length;
        int cols = GRID[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new ArrayDeque<>();

        if (GRID[startRow][startCol] == 0) {
            System.out.println("Start cell is blocked.");
            return;
        }

        queue.add(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        System.out.println("Visiting reachable cells:");
        while (!queue.isEmpty()) {
            int[] cell = queue.remove();
            int row = cell[0];
            int col = cell[1];
            System.out.println("(" + row + ", " + col + ")");

            for (int[] direction : DIRECTIONS) {
                int nextRow = row + direction[0];
                int nextCol = col + direction[1];

                if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols) {
                    continue; // outside grid
                }
                if (visited[nextRow][nextCol] || GRID[nextRow][nextCol] == 0) {
                    continue; // already seen or blocked
                }

                visited[nextRow][nextCol] = true;
                queue.add(new int[]{nextRow, nextCol});
            }
        }
    }

    public static void main(String[] args) {
        bfsFrom(0, 0);
    }
}
