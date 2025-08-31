package javaBasics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamApiExamples {

    public  static void main (String [] args)
    {
        List<Integer> array = Arrays.asList(1,2,3,4,5,6);


        List<Integer> square = array.stream()
                .filter(n -> n % 2 == 0)
                .map( n -> n * n)
                .toList();

        System.out.println(square);


    }
}
