package javaBasics;

public class RecordExample {
    public static void main(String[] args) {
        Person p1 = new Person("Amit", 30);
        Person p2 = new Person("Amit", 30);

        System.out.println(p1);         // Person[name=Amit, age=30]
        System.out.println(p1.name());  // Amit
        System.out.println(p1.age());   // 30

        System.out.println(p1.equals(p2)); // true
    }
}

record Person(String name, int age) { }
