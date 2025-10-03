package questions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinNoOfPlatforms {
    public static void main(String[] args) {
        int[][] trains = {
                {900, 910},
                {940, 1200},
                {950, 1120},
                {1100, 1130},
                {1500, 1900},
                {1800, 2000}
        };
        System.out.println(minPlatforms(trains)); // Output: 3
    }

    private static int minPlatforms(int[][] trains) {

        if (trains == null || trains.length ==0) return 0;


        Arrays.sort(trains , Comparator.comparingInt(a -> a[0]));


      //minheap on departure times of trains currently occupying platforms
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int maxPlatforms = 0;

        for (int [] t : trains ){

         int arr = t[0] , dep = t [1] ;

         //free kr do minheap abhi jo arr time hai ya jo deprt ho gaya hai ab arrival time se pehle dusre train ke
          while (!minHeap.isEmpty() && minHeap.peek() <= arr){
              minHeap.poll();
          }

          //allocate a platform for the current train
            minHeap.offer(dep);
          maxPlatforms = Math.max(maxPlatforms , minHeap.size());

        }

       return maxPlatforms;
    }

}
