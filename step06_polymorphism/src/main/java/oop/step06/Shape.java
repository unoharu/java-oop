package oop.step06;

public class Shape {
    String name;

    Shape(String name) {
        this.name = name;
    }

    double area() {
        return 0.0;
    }

    String describe() {
        return name + ": 面積 = " + area();
    }
}
