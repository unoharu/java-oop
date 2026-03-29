package oop.step05;

public class Circle extends Shape {
    private double radius;

    Circle(double radius) {
        super("円");
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * radius * radius;
    }
}
