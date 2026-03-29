package oop.step06;

public class Triangle extends Shape {
    private double base;
    private double height;

    Triangle(double base, double height) {
        super("三角形");
        this.base = base;
        this.height = height;
    }

    @Override
    double area() {
        return base * height / 2.0;
    }

    double getBase() {
        return base;
    }

    double getHeight() {
        return height;
    }
}
