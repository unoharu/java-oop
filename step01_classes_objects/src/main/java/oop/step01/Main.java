package oop.step01;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== 2つの Car オブジェクトを生成 ===");
        Car car1 = new Car();
        car1.init("Toyota", "Corolla");

        Car car2 = new Car();
        car2.init("Honda", "Civic");

        System.out.println("car1: " + car1.describe());
        System.out.println("car2: " + car2.describe());

        System.out.println("\n=== car1 だけ加速 ===");
        car1.accelerate(60);
        System.out.println("car1: " + car1.describe());
        System.out.println("car2: " + car2.describe() + "   ← car2 は変わらない");

        System.out.println("\n=== car1 にブレーキ ===");
        car1.brake(20);
        System.out.println("car1: " + car1.describe());

        System.out.println("\n=== 参照のコピーを確認 ===");
        Car car3 = car1;  // car1 の参照をコピー（同じオブジェクトを指す）
        System.out.println("car3 は car1 と同じオブジェクトを指している");
        car3.speed = 0;
        System.out.println("car3.speed を 0 にすると car1.speed も 0 になる: " + car1.describe());
    }
}
