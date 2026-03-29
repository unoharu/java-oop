package oop.step06;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Shape 配列によるポリモーフィズム ===");
        Shape[] shapes = {
            new Circle(5),
            new Rectangle(4, 6),
            new Triangle(5, 4)
        };

        for (Shape s : shapes) {
            System.out.println(s.describe());
        }

        System.out.println("\n=== instanceof パターンマッチング ===");
        for (Shape s : shapes) {
            if (s instanceof Circle c) {
                System.out.println("Circle — 半径: " + c.getRadius());
            } else if (s instanceof Rectangle r) {
                System.out.println("Rectangle — 幅: " + r.getWidth() + ", 高さ: " + r.getHeight());
            } else if (s instanceof Triangle t) {
                System.out.println("Triangle — 底辺: " + t.getBase() + ", 高さ: " + t.getHeight());
            }
        }
    }
}
