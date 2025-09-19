package questions;

import java.util.Arrays;
import java.util.List;

public class RemoveDuplicates {

    public static  void main (String [] args)
    {
        Integer[] input = {8, 7, 3, 4, 3, 5, 6, 8, 7};

        List<Integer> uniqueSortedList = Arrays.stream(input)
                .distinct()
                .sorted()
                .toList();

        Integer [] output = new Integer[input.length];

        for (int  i =0 ; i < uniqueSortedList.size(); i++){

            output [i] = uniqueSortedList.get(i);
        }

        System.out.println(Arrays.toString(input));
        System.out.println(Arrays.toString(output));

    }
}
