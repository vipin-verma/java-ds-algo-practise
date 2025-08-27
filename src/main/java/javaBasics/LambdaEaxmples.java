package javaBasics;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaEaxmples {

    public static void main (String [] args)
    {

        //map
        List<String> names = Arrays.asList("ram" , "shyam" , "radha");
        List<Integer> lengths = names.stream().map(String::length).toList();

        System.out.println(lengths);


        //flatmap
        List<List<Integer>> ListOfList = Arrays.asList(
                Arrays.asList(1,2,3),
                Arrays.asList(4,5,6),
                Arrays.asList(7,8,9)
        );

        List<Integer> flatlist = ListOfList.stream().flatMap(List::stream).toList();

        System.out.println(flatlist);


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        };

        Runnable r = () -> System.out.println("test");




        @FunctionalInterface
        interface Calculator {
            int add(int a, int b);
        }

        class LambdaDemo {
            public static void main(String[] args) {
                Calculator calc = (a, b) -> a + b;
                System.out.println(calc.add(5, 10)); // 15
            }
        }


        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println(isEven.test(4)); // true


        Function<String, Integer> length = str -> str.length();
        System.out.println(length.apply("Hello")); // 5










    }
}
