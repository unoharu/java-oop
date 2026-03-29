package oop.step12;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== record: Point ===");
        Point p1 = new Point(3.0, 4.0);
        Point p2 = new Point(0.0, 0.0);
        System.out.println("p1 = " + p1);
        System.out.println("p2 = " + p2);
        System.out.println("p1.x() = " + p1.x());
        System.out.println("p1 から p2 までの距離 = " + p1.distanceTo(p2));
        System.out.println("p1.equals(new Point(3.0, 4.0)) = " + p1.equals(new Point(3.0, 4.0)));

        System.out.println("\n=== record: Person（コンパクトコンストラクタ）===");
        Person alice = new Person("Alice", 30);
        System.out.println(alice);

        System.out.println("\n=== バリデーション ===");
        try {
            new Person("Bob", -1);
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException: " + e.getMessage());
        }
    }
}
