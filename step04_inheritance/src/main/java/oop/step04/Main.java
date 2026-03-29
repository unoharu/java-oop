package oop.step04;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Animal（親クラス）===");
        Animal animal = new Animal("名無し");
        animal.eat();

        System.out.println("\n=== Dog（子クラス）===");
        Dog dog = new Dog("ポチ", "柴犬");
        dog.eat();       // 親から継承したメソッド
        dog.bark();      // Dog 固有のメソッド
        System.out.println(dog.describe());

        System.out.println("\n=== Cat（子クラス）===");
        Cat cat = new Cat("タマ", "三毛猫");
        cat.eat();       // 親から継承したメソッド
        cat.meow();      // Cat 固有のメソッド
        System.out.println(cat.describe());

        System.out.println("\n=== IS-A 関係の確認 ===");
        System.out.println("dog は Animal のインスタンス: " + (dog instanceof Animal));
        System.out.println("cat は Animal のインスタンス: " + (cat instanceof Animal));
        // Animal 型変数を通じて確認（Dog と Cat は互いに無関係）
        Animal a = dog;
        System.out.println("dog は Cat のインスタンス: " + (a instanceof Cat));
    }
}
