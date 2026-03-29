package oop.step13;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Shape> shapes = List.of(
            new Circle(5),
            new Rectangle(4, 6),
            new Triangle(5, 4)
        );

        System.out.println("=== Sealed Interface: Shape ===");
        for (Shape s : shapes) {
            System.out.println(s.getClass().getSimpleName() + " の面積: " + s.area());
        }

        System.out.println("\n=== Pattern Matching switch（網羅的）===");
        for (Shape s : shapes) {
            // Compiler enforces exhaustiveness: all permitted subtypes must be covered
            String description = switch (s) {
                case Circle c      -> "Circle: 半径 = " + c.radius();
                case Rectangle r   -> "Rectangle: 幅 = " + r.width() + ", 高さ = " + r.height();
                case Triangle t    -> "Triangle: 底辺 = " + t.base() + ", 高さ = " + t.height();
            };
            System.out.println(description);
        }

        System.out.println("\n=== コンパイル時の網羅性チェック ===");
        System.out.println("// permits 以外のクラスが Shape を実装しようとするとコンパイルエラー");
    }
}
