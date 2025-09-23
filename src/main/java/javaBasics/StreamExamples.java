package javaBasics;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamExamples {

  public static void main (String [] args )
  {
      List<Integer> input = Arrays.asList(1,2,3,4,5,6);

      Map<Boolean, List<Integer>> partition =  input.stream().collect(Collectors.partitioningBy(n -> n % 2 ==0));

      List<Integer> even = partition.get(true);
      List<Integer> odd = partition.get(false);

      System.out.println("input " + input );
      System.out.println("even " + even);
      System.out.println("odd " + odd );


      String charfrequency  = "banana";

     Map<Character , Long> frequencyMap  =    charfrequency.chars().mapToObj(c ->  (char) c)
                .collect(Collectors.groupingBy(Function.identity() , Collectors.counting()));


      System.out.println("charfrequency " + charfrequency);

      System.out.println( "frequencyMap " + frequencyMap);







  }


}
