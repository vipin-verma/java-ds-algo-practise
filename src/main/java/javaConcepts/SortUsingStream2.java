package javaConcepts;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortUsingStream2 {

    public static void main(String[] args) {

        List<People> peopleList = Arrays.asList(
                new People(
                        101,
                        "Alice Johnson",
                        List.of(new Phone(5551234), new Phone(5551235)) // Two phones
                ),
                new People(
                        102,
                        "Bob Smith",
                        Collections.singletonList(new Phone(5559876)) // One phone
                ),
                new People(
                        103,
                        "Charlie Brown",
                        List.of(new Phone(5555678), new Phone(5555679), new Phone(5555680)) // Three phones
                ),
                new People(
                        104,
                        "Diana Prince",
                        Arrays.asList(new Phone(5550001), new Phone(5550002)) // Two phones (using Arrays.asList)
                ),
                new People(
                        105,
                        "Ethan Hunt",
                        List.of(new Phone(5554321)) // One phone
                )
        );

        // Example: Print the list
        peopleList.forEach(System.out::println);

        peopleList.stream()
                .flatMap( n -> n.phone().stream().map(Phone::number))
                .forEach(System.out::println);


    }


}

record Phone (int number){}
record People  (int id , String name  , List<Phone> phone  ) {}
