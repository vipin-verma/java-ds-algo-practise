package javaConcepts;

 class Parent {
    public void test (){
        System.out.println("parent");
    }

     public void test2 (){
         System.out.println("parent");
     }

     public static void test3 (){
         System.out.println("parent");
     }


}


class Child extends Parent {
     public void test (){
         System.out.println("child");
     }

    public static void test3 (){
        System.out.println("child");
    }


}


public class Polymorphism {

     public static void main (String [] args )
     {
         Parent parent = new Child ();
         parent.test();
         parent.test2();
         parent.test3();
     }
}
