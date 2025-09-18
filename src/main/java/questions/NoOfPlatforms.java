package questions;

import java.util.Arrays;
import java.util.Map;

public class NoOfPlatforms {



    public static void main(String[] args) {
        int[][] trains = {
                {900, 910},
                {940, 1200},
                {950, 1120},
                {1100, 1130},
                {1500, 1900},
                {1800, 2000}
        };

        System.out.println("Minimum Platforms Required = " + findMinPlatforms(trains));
    }

    private static int findMinPlatforms(int[][] trains) {

            int n = trains.length;


            int [] arr = new int [n];
            int [] dep = new int [n];

            for (int i =0 ; i < n ; i++){
                arr [i] = trains [i][0];
                dep [i] = trains [i][1];

            }

        Arrays.sort(arr);
            Arrays.sort(dep);

            int i =0, j=0 ;
            int platformNeeded = 0,  maxPLatforms = 0;


            while (i < n && j < n)
            {

                if (arr[i] <= dep [j]){

                    platformNeeded++ ;
                    i++;
                    maxPLatforms = Math.max(maxPLatforms , platformNeeded);


                }else {

                    platformNeeded--;
                    j++;

                }
            }

            return maxPLatforms;
    }


}
