package javaBasics;

import javax.print.attribute.standard.RequestingUserName;
import java.util.*;

public class Lambda {

     public static void main (String [] args){
         List<String> names = Arrays.asList("bob" , "tom" , "jon");

         Collections.sort(names, new Comparator<String>() {
             @Override
             public int compare(String o1, String o2) {
                 return o1.compareTo(o2);
             }
         });

         System.out.println(names);


         List<String> gobi  = Arrays.asList("lla" , "ama" , "sas");
         gobi.sort(String::compareTo);

         System.out.println(gobi);


         Runnable r = new Runnable() {
             @Override
             public void run() {
                 System.out.println("hello");
             }
         };

         Runnable t = () -> System.out.println("hello");

         List <String> names1 = Arrays.asList("Aman" , "Ravi" , "Kiran" , "Anil");
         List <String> result = new ArrayList<>();

         for (String n : names1){
             if (n.startsWith("A")){
                 result.add(n.toUpperCase());
             }
         }

         System.out.println(result);


         List <String> rwww = names1.stream()
                 .filter(n-> n.startsWith("A"))
                 .map(String::toUpperCase)
                 .toList();

         System.out.println(rwww);

         List<Integer> nums = Arrays.asList(1,1,1,2,3,4,8,9,10,12,14,16,18);

         int sum = nums.stream()
                 .filter(n -> n % 2 == 0)
                 .map(n -> n*n)
                 .reduce(0, Integer::sum);

         System.out.println(sum);
     }

    @FunctionalInterface
    interface Calculator {
        int add(int a, int b);

    }

    public class LambdaDemo {
        public static void main(String[] args) {
            Calculator calc = (a, b) -> a + b;
            System.out.println(calc.add(5, 10)); // 15
        }
    }












}
