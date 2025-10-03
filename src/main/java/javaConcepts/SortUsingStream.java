package javaConcepts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortUsingStream {

    public static void main (String [] args) {
        Employee a = new Employee(1, "ram");
        Employee b = new Employee(2, "shyam");
        Employee c = new Employee(7, "aa");
        Employee d = new Employee(8, "test");

        List<Employee> list = Arrays.asList(a, b, c, d);

        System.out.println(list);

        list.stream().sorted(Comparator.comparing(Employee::id)).forEach(System.out::println);

        list.stream().sorted(Comparator.comparing(Employee::id)).forEach(System.out::println);
    }



}




record Employee (int id , String name ) {}