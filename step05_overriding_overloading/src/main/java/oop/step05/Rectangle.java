package oop.step05;

public class Rectangle extends Shape {
    private double width;
    private double height;

    Rectangle(double width, double height) {
        super("長方形");
        this.width = width;
        this.height = height;
    }

    @Override
    double area() {
        return width * height;
    }
}
