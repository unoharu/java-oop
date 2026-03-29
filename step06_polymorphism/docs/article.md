# Step 06 - ポリモーフィズム

## 同じ呼び出し、異なる動作

ポリモーフィズム（多態性）とは、**同じメソッド呼び出しが、オブジェクトの実際の型に応じて異なる動作をする**仕組みです。step05 のオーバーライドを基盤に、それを「配列やリストで活用する」のがポリモーフィズムの本質です。

| | ポリモーフィズム | オーバーロード |
| --- | --- | --- |
| 解決タイミング | **実行時**（動的ディスパッチ） | コンパイル時 |
| 決定する要因 | オブジェクトの**実際の型** | 引数の型・数 |
| 必要な仕組み | 継承 + オーバーライド | 同じクラス内の多重定義 |

---

## Shape 配列によるポリモーフィズム

`Shape` 型の配列に `Circle`・`Rectangle`・`Triangle` を混在させ、ループで `area()` を呼び出します。

```java
Shape[] shapes = {
    new Circle(5),
    new Rectangle(4, 6),
    new Triangle(5, 4)
};

for (Shape s : shapes) {
    System.out.println(s.describe()); // 実行時に実際の型のメソッドが呼ばれる
}
```

`s.describe()` が呼ばれるとき、Java は `s` が指す**実際のオブジェクト**を見て、そのクラスのメソッドを実行します。これを**動的ディスパッチ**と言います。

---

## instanceof パターンマッチング（Java 16+）

型ごとに異なる処理をしたいときは、`instanceof` パターンマッチングを使います。

```java
if (s instanceof Circle c) {
    System.out.println("Circle — 半径: " + c.getRadius());
} else if (s instanceof Rectangle r) {
    System.out.println("Rectangle — 幅: " + r.getWidth() + ", 高さ: " + r.getHeight());
}
```

`instanceof Circle c` は「`s` が `Circle` なら `c` という変数に束縛する」という意味です。型チェックとキャストを一度に行えます。

---

## コード解説

### [Shape.java](../src/main/java/oop/step06/Shape.java)

基底クラス。`area()` は `0.0` を返す具象メソッドとして定義（abstract は step07 で扱います）。`describe()` はテンプレートメソッドとして機能し、サブクラスの `area()` を呼び出します。

### [Circle.java](../src/main/java/oop/step06/Circle.java)

`Shape` を継承し、`area()` をオーバーライド。`getRadius()` を持つため、`instanceof` パターンマッチング後に半径にアクセスできます。

### [Rectangle.java](../src/main/java/oop/step06/Rectangle.java)

幅と高さで面積を計算。`getWidth()`・`getHeight()` でパターンマッチング後の詳細アクセスを提供。

### [Triangle.java](../src/main/java/oop/step06/Triangle.java)

底辺と高さで面積を計算（`base * height / 2.0`）。

### [Main.java](../src/main/java/oop/step06/Main.java)

`Shape[]` 配列を使ったポリモーフィズムのデモと、`instanceof` パターンマッチングのデモを示します。

---

## 実行してみよう

```bash
mvn compile exec:java -pl step06_polymorphism
```

```
=== Shape 配列によるポリモーフィズム ===
円: 面積 = 78.53981633974483
長方形: 面積 = 24.0
三角形: 面積 = 10.0

=== instanceof パターンマッチング ===
Circle — 半径: 5.0
Rectangle — 幅: 4.0, 高さ: 6.0
Triangle — 底辺: 5.0, 高さ: 4.0
```

---

## まとめ

| 要素 | ポイント |
| --- | --- |
| `Shape[] shapes` | 異なるサブクラスを同じ型で扱える |
| 動的ディスパッチ | `s.area()` が実行時に実際の型のメソッドを呼ぶ |
| `instanceof パターンマッチング` | 型チェック + キャストを1行で記述（Java 16+）|
| ポリモーフィズムの利点 | 新しいサブクラスを追加しても呼び出し側のコードは変わらない |

次のステップ → [Step 07 - 抽象クラス](../../step07_abstract_classes/docs/article.md)
