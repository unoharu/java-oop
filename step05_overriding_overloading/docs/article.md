# Step 05 - オーバーライドとオーバーロード

## 2つの「上書き」を整理する

名前が似ていますが、まったく異なる仕組みです。

| | オーバーライド | オーバーロード |
| --- | --- | --- |
| 定義場所 | **サブクラス** | **同じクラス** |
| メソッド名 | 同じ | 同じ |
| 引数 | 同じ | **型・数が異なる** |
| 解決タイミング | **実行時**（動的） | **コンパイル時**（静的） |
| アノテーション | `@Override` 推奨 | なし |

---

## オーバーライド

サブクラスで親クラスのメソッドを**再定義**することです。step04 で `describe()` をオーバーライドしましたが、ここでは `area()` を使ってより深く掘り下げます。

```java
class Shape {
    double area() { return 0; }
}

class Circle extends Shape {
    private double radius;

    @Override
    double area() {
        return Math.PI * radius * radius;  // 親の実装を完全に置き換える
    }
}
```

`@Override` はアノテーションです。付けなくても動きますが、**付けることでコンパイラが「本当に親のメソッドをオーバーライドしているか」を確認**してくれます。スペルミスを防ぐためにも常に付けましょう。

---

## オーバーロード

同じクラスに**引数の型・数が異なる同名メソッド**を複数定義することです。呼び出し時の引数によってどのメソッドが使われるかはコンパイル時に決定されます。

```java
class Calculator {
    int add(int a, int b) { return a + b; }
    double add(double a, double b) { return a + b; }  // 型が違う
    int add(int a, int b, int c) { return a + b + c;  // 数が違う
}
```

戻り値の型だけが異なるオーバーロードは**コンパイルエラー**になります（呼び出し側がどちらを使うか区別できないため）。

---

## コード解説

### [Shape.java](../src/main/java/oop/step05/Shape.java) / [Circle.java](../src/main/java/oop/step05/Circle.java) / [Rectangle.java](../src/main/java/oop/step05/Rectangle.java)

オーバーライドのデモ。`Shape` の `area()` を各図形でオーバーライドします。

```java
class Shape {
    String name;
    Shape(String name) { this.name = name; }
    double area() { return 0; }
    String describe() { return name + ": 面積 = " + area(); }
}

class Circle extends Shape {
    private double radius;
    Circle(double radius) {
        super("円");
        this.radius = radius;
    }
    @Override
    double area() { return Math.PI * radius * radius; }
}
```

### [Calculator.java](../src/main/java/oop/step05/Calculator.java)

オーバーロードのデモ。`add()` を4パターン定義します。

```java
class Calculator {
    int add(int a, int b) { ... }
    double add(double a, double b) { ... }
    int add(int a, int b, int c) { ... }
    String add(String a, String b) { ... }  // 文字列結合
}
```

---

## 実行してみよう

```bash
cd step05_overriding_overloading
mvn compile exec:java
```

### 期待する出力

```text
=== オーバーライド: 図形の面積 ===
円: 面積 = 78.53981633974483
長方形: 面積 = 24.0

=== オーバーロード: Calculator.add() ===
add(int, int)       = 7
add(double, double) = 7.5
add(int, int, int)  = 12
add(String, String) = Hello World
```

---

## まとめ

| 要素 | ポイント |
| --- | --- |
| `@Override` | オーバーライドを明示。コンパイル時に検証される |
| オーバーライド | 実行時に「実際の型」に基づいてメソッドが選ばれる |
| オーバーロード | コンパイル時に「引数の型・数」でメソッドが選ばれる |

次のステップ → [Step 06 - ポリモーフィズム](../../step06_polymorphism/docs/article.md)
