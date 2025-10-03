package questions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
    public static  void main (String [] args)
    {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] ans1 = merge1(intervals);
        System.out.println("Merged: " + Arrays.deepToString(ans1));
    }

    private static int[][] merge1(int[][] intervals) {

        if (intervals == null || intervals.length ==0  ) return new int [0][0];

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int []> res = new ArrayList<>();

        int [] cur = intervals[0].clone();

        System.out.println(Arrays.toString(cur));

        for (int i =1 ; i < intervals.length ; i++){
            int [] next = intervals [i];
            if (next[0] <= cur[1]) {
                cur [1] = Math.max(cur[1], next[1]);
            }else {
                res.add(cur);
                cur = next.clone();
            }
        }
        res.add(cur);

        return res.toArray(new int [res.size()][]);
    }

}
