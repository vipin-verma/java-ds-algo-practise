package javaConcepts;

import java.util.HashSet;
import java.util.Set;

public class TestCollection{
    public static void main (String [] args )
    {
         Student1 s1 = new Student1(1 , "vipin");
         Student1 s2 = new Student1( 2, "ll");
         Student1 s3 = new Student1( 3, "lolo");

         Set<Student1> set = new HashSet<>();

         set.add(s1);
        set.add(s2);
        set.add(s3);


        System.out.println(set);

        s2.setId(1);

        System.out.println(set);

        System.out.println(s2);







    }
}

class Student1 {
    private int id ;
    private String name ;

    public Student1(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

  @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student1 student1 = (Student1) o;
        return id == student1.id;
    }

    @Override
    public String toString() {
        return "Student1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

}
