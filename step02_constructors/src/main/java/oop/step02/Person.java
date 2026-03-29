package oop.step02;

public class Person {
    String name;
    int age;

    // デフォルトコンストラクタ — 引数付きに委譲（DRY）
    Person() {
        this("unknown", 0);
    }

    // 引数付きコンストラクタ — 初期化ロジックはここだけ
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // コピーコンストラクタ — 独立した新しいオブジェクトを作る
    Person(Person other) {
        this(other.name, other.age);
    }

    String describe() {
        return name + " (age: " + age + ")";
    }
}
