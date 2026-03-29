package oop.step02;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== デフォルトコンストラクタ ===");
        Person p1 = new Person();
        System.out.println(p1.describe());

        System.out.println("\n=== 引数付きコンストラクタ ===");
        Person p2 = new Person("Alice", 30);
        System.out.println(p2.describe());

        System.out.println("\n=== コピーコンストラクタ ===");
        Person original = new Person("Bob", 25);
        Person copy = new Person(original);
        System.out.println("コピー前: " + original.describe());

        // copy を変更しても original に影響しないことを確認
        copy.name = "Charlie";
        copy.age = 99;
        System.out.println("copy を変更しても original は変わらない");
        System.out.println("original: " + original.describe());
        System.out.println("copy:     " + copy.describe());

        System.out.println("\n=== 参照コピーとの違い ===");
        Person ref = original;  // 参照のコピー（同じオブジェクトを指す）
        ref.name = "Charlie";
        ref.age = 99;
        System.out.println("ref を変更すると original も変わる");
        System.out.println("original: " + original.describe());
    }
}
