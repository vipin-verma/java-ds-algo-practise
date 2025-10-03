package questions;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Generaltest {

    public static  void main (String [] args)
    {
        ArrayDeque<int []> k = new ArrayDeque<>();

        k.offer(new int [] {0,0});

        System.out.println(Arrays.toString(k.peek()));



    }
}
