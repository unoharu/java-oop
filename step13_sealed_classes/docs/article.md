# Step 13 - シールドクラス（sealed）

## 継承できるクラスを制限する

シールドクラス（`sealed`）は、**継承・実装できるクラスを明示的に制限する**仕組みです（Java 17+）。`permits` 句で許可するサブタイプを列挙します。

```java
public sealed interface Shape permits Circle, Rectangle, Triangle {
    double area();
}
```

この宣言以降、`Shape` を実装できるのは `Circle`・`Rectangle`・`Triangle` の3つだけです。それ以外のクラスが `implements Shape` を書くと**コンパイルエラー**になります。

---

## なぜ sealed が必要か

インタフェースや抽象クラスは、誰でも継承・実装できます（**オープン**な階層）。しかし「図形は必ず円・長方形・三角形のいずれかである」のような**閉じたドメイン**では、そのオープン性が問題になります。

| | 通常のインタフェース | sealed インタフェース |
| --- | --- | --- |
| 誰でも実装可能 | はい | `permits` で列挙したクラスのみ |
| switch の網羅性チェック | `default` 必須 | すべての許可型を書けば不要 |
| ドメインモデリング | オープンな拡張 | 閉じた代数的データ型 |

---

## permitted サブタイプの制約

`permits` に列挙されたクラスは、以下のいずれかでなければなりません。

- `final`: これ以上継承不可（`record` は暗黙的に `final`）
- `sealed`: さらに継承を制限する
- `non-sealed`: 制限を解除して継承を許可する

---

## sealed + record の組み合わせ

`record` は暗黙的に `final` なので、`sealed` の `permits` メンバーとして最適です。

```java
public record Circle(double radius) implements Shape {
    @Override
    public double area() { return Math.PI * radius * radius; }
}
```

---

## 網羅的な switch 式（Java 21+）

sealed 階層を `switch` で処理するとき、すべての許可型を列挙すれば `default` が不要になります。抜け漏れがあるとコンパイルエラーです。

```java
String description = switch (shape) {
    case Circle c    -> "Circle: 半径 = " + c.radius();
    case Rectangle r -> "Rectangle: 幅 = " + r.width() + ", 高さ = " + r.height();
    case Triangle t  -> "Triangle: 底辺 = " + t.base() + ", 高さ = " + t.height();
    // default 不要: sealed なので Circle/Rectangle/Triangle 以外は存在しない
};
```

---

## コード解説

### [Shape.java](../src/main/java/oop/step13/Shape.java)

`sealed interface` として `Circle`・`Rectangle`・`Triangle` のみを許可します。`area()` メソッドを定義します。

### [Circle.java](../src/main/java/oop/step13/Circle.java) / [Rectangle.java](../src/main/java/oop/step13/Rectangle.java) / [Triangle.java](../src/main/java/oop/step13/Triangle.java)

すべて `record` として実装。`record` は `final` なので `sealed` の要件を満たします。各クラスが `area()` を実装します。

### [Main.java](../src/main/java/oop/step13/Main.java)

`Shape` のリストをループし、`switch` でパターンマッチングします。`switch` は網羅的で `default` を持ちません。

---

## 実行してみよう

```bash
mvn compile exec:java -pl step13_sealed_classes
```

```
=== Sealed Interface: Shape ===
Circle の面積: 78.53981633974483
Rectangle の面積: 24.0
Triangle の面積: 10.0

=== Pattern Matching switch（網羅的）===
Circle: 半径 = 5.0
Rectangle: 幅 = 4.0, 高さ = 6.0
Triangle: 底辺 = 5.0, 高さ = 4.0

=== コンパイル時の網羅性チェック ===
// permits 以外のクラスが Shape を実装しようとするとコンパイルエラー
```

---

## まとめ

| 要素 | ポイント |
| --- | --- |
| `sealed` | 継承・実装を `permits` で列挙したクラスに制限（Java 17+）|
| `permits` | 許可するサブタイプを明示的に列挙 |
| `final` / `record` | `permits` メンバーとして最もよく使われる形 |
| 網羅的 `switch` | sealed 階層のすべてを列挙すれば `default` 不要（Java 21+）|
| 用途 | 閉じたドメイン型・代数的データ型のモデリング |

次のステップ → [Step 14 - 例外処理](../../step14_exception_handling/docs/article.md)
