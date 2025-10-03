package javaConcepts;

class A1 {
    A1 show() {
        System.out.println("A");
        return this;
    }
}

class B1 extends A1 {
    @Override
    B1 show() {   // 👈 return type narrowed down (subtype)
        System.out.println("B");
        return this;
    }
}

class C1 extends B1 {
    @Override
    C1 show() {   // 👈 aur narrow kiya (C)
        System.out.println("C");
        return this;
    }
}


public class Test1 {
    public static void main(String[] args) {
        A1 obj = new C1();
        A1 a = obj.show();   // returns C but reference type A
        System.out.println("Returned: " + a.getClass().getSimpleName());

        B1 b = new C1().show();  // returns C but reference type B
        System.out.println("Returned: " + b.getClass().getSimpleName());

        C1 c = new C1().show();  // returns C
        System.out.println("Returned: " + c.getClass().getSimpleName());
    }
}

