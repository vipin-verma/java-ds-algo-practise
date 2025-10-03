package questions;

import java.util.ArrayDeque;
import java.util.Arrays;

public class ShortestPathGrid_BFS {


    public static void main (String [] args)
    {
        int [] [] grid = {
                {0,0,0,1},
                {1,0,0,0},
                {1,1,0,1},
                {0,0,0,0}
        };

        System.out.println(shortestPath(grid));
    }

    private static int shortestPath(int[][] grid) {

        int r = grid.length , c = grid[0].length;

        if (grid[0][0] == 1 || grid[r-1][c-1] == 1) return -1 ;

        int [][] dist = new int[r][c];

        for (int [] d : dist) Arrays.fill(d , -1 );

        int [][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        ArrayDeque<int[]> q = new ArrayDeque<>();

        q.offer(new int []{0,0});

        dist[0][0] = 0;

        while (!q.isEmpty()){

            int [] cur = q.poll();

            int row = cur[0] , col = cur[1];

            if (row == r-1 && col == c-1) return dist[row][col];

            for (int [] d : dirs){
                int nr = row + d[0] , nc = col + d[1];

                if (nr >= 0 && nr < r && nc >=0 && nc < c && grid[nr][nc]==0 && dist[nr][nc] ==-1  ){
                    dist[nr][nc] = dist[row][col] + 1;
                    q.offer(new int [] {nr, nc });

                }
            }

        }
        return -1 ;
    }


}
