package javaBasics;

// Base sealed class
sealed abstract class Shape permits Circle, Rectangle, Square {
    abstract double area();
}

// Allowed subclasses
final class Circle extends Shape {
    private final double radius;
    Circle(double radius) { this.radius = radius; }

    @Override
    double area() {
        return Math.PI * radius * radius;
    }
}

final class Rectangle extends Shape {
    private final double width, height;
    Rectangle(double width, double height) {
        this.width = width; this.height = height;
    }

    @Override
    double area() {
        return width * height;
    }
}

// Example of non-sealed subclass
non-sealed class Square extends Shape {
    private final double side;
    Square(double side) { this.side = side; }

    @Override
    double area() {
        return side * side;
    }
}

class Hexagon extends Square {

    Hexagon(double side) {
        super(side);
    }
}




// Demo
public class SealedExample {
    public static void main(String[] args) {
        Shape s1 = new Circle(5);
        Shape s2 = new Rectangle(4, 6);
        Shape s3 = new Square(3);

        System.out.println("Circle area = " + s1.area());
        System.out.println("Rectangle area = " + s2.area());
        System.out.println("Square area = " + s3.area());
    }
}

