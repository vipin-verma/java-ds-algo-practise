package leetcode;

import java.util.Date;

public final class ImmutableEmployee {

      private final String name ;
      private final int age ;
      private final Date joiningDate;


     public ImmutableEmployee (String name , int age , Date joiningDate)
     {
         this.age= age;
         this.name = name ;
         this.joiningDate = new  Date (joiningDate.getTime());


     }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }


    @Override
    public String toString() {
        return "Employee{name='" + name + "', age=" + age + ", joiningDate=" + joiningDate + "}";
    }


}


