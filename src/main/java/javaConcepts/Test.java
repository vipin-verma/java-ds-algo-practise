package javaConcepts;

class A {
    public  void show() { System.out.println("A"); }
}

class B extends A {
    public  final void show() { System.out.println("B"); }
}

class C extends B {
    //private void show() { System.out.println("C"); }
}

public class Test {
    public static void main(String[] args) {
        A obj = new C();
        obj.show();
    }
}
