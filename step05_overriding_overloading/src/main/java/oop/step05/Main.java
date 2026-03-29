package oop.step05;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== オーバーライド: 図形の面積 ===");
        Circle circle = new Circle(5);
        Rectangle rect = new Rectangle(4, 6);
        System.out.println(circle.describe());
        System.out.println(rect.describe());

        System.out.println("\n=== オーバーロード: Calculator.add() ===");
        Calculator calc = new Calculator();
        System.out.println("add(int, int)       = " + calc.add(3, 4));
        System.out.println("add(double, double) = " + calc.add(3.0, 4.5));
        System.out.println("add(int, int, int)  = " + calc.add(3, 4, 5));
        System.out.println("add(String, String) = " + calc.add("Hello ", "World"));
    }
}
