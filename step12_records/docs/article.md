# Step 12 - レコード（record）

## イミュータブルなデータ保持クラスを簡潔に

レコード（`record`）は、**データを保持するだけのイミュータブルなクラス**を簡潔に書くための構文です（Java 16+）。`equals()`・`hashCode()`・`toString()`・アクセサーが自動生成されます。

```java
// 通常のクラスで書くと... (フィールド・コンストラクタ・getter・equals・hashCode・toString で 30+ 行)
public final class Point {
    private final double x;
    private final double y;
    // ... (多くのボイラープレート)
}

// record なら1行
public record Point(double x, double y) { }
```

---

## 自動生成される機能

```java
record Point(double x, double y) { }

Point p = new Point(3.0, 4.0);
p.x();                        // アクセサー（getX() ではなく x()）
p.toString();                 // "Point[x=3.0, y=4.0]"
p.equals(new Point(3.0, 4.0)); // → true（フィールドの値で比較）
p.hashCode();                 // フィールドの値に基づくハッシュ
```

アクセサーのメソッド名は **`getX()` ではなく `x()`** です（フィールド名と同じ）。

---

## カスタムメソッドの追加

レコードにも通常のメソッドを追加できます。

```java
record Point(double x, double y) {
    double distanceTo(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
```

---

## コンパクトコンストラクタ（バリデーション）

パラメータリストを繰り返さずにバリデーションを書けます。

```java
record Person(String name, int age) {
    public Person { // パラメータリストを省略できる
        if (age < 0) {
            throw new IllegalArgumentException("年齢は 0 以上にしてください: " + age);
        }
    }
}
```

フィールドへの代入は自動的に行われます（明示しなくて良い）。

---

## record の制約

- 暗黙的に `final`（継承不可）
- フィールドはすべて `private final`（セッターなし・イミュータブル）
- 他のクラスを `extends` できない（`java.lang.Record` を自動的に継承）
- インタフェースは実装できる

---

## コード解説

### [Point.java](../src/main/java/oop/step12/Point.java)

2次元座標を表すレコード。`distanceTo()` メソッドを追加してカスタムロジックを持たせています。

### [Person.java](../src/main/java/oop/step12/Person.java)

コンパクトコンストラクタでバリデーションを行うレコード。`name` が空・`age` が負の場合に `IllegalArgumentException` を投げます。

### [Main.java](../src/main/java/oop/step12/Main.java)

自動生成された `toString()`・`equals()`・アクセサーの動作確認と、コンパクトコンストラクタによるバリデーションのデモです。

---

## 実行してみよう

```bash
mvn compile exec:java -pl step12_records
```

```
=== record: Point ===
p1 = Point[x=3.0, y=4.0]
p2 = Point[x=0.0, y=0.0]
p1.x() = 3.0
p1 から p2 までの距離 = 5.0
p1.equals(new Point(3.0, 4.0)) = true

=== record: Person（コンパクトコンストラクタ）===
Person[name=Alice, age=30]

=== バリデーション ===
IllegalArgumentException: 年齢は 0 以上にしてください: -1
```

---

## まとめ

| 要素 | ポイント |
| --- | --- |
| `record` | イミュータブルなデータクラスを簡潔に定義（Java 16+）|
| 自動生成 | コンストラクタ・アクセサー・`equals()`・`hashCode()`・`toString()`|
| アクセサー名 | `getX()` ではなく `x()`（フィールド名と同じ）|
| コンパクトコンストラクタ | パラメータリスト省略でバリデーションを書ける |
| イミュータブル | フィールドは `private final`。セッターなし |

次のステップ → [Step 13 - シールドクラス](../../step13_sealed_classes/docs/article.md)
