# Java OOP チートシート

Java のオブジェクト指向プログラミングで使う主要キーワード・構文・概念の早見表です。

---

## クラスとオブジェクト

| 構文 | 説明 |
| --- | --- |
| `class Foo { }` | クラス定義 |
| `new Foo()` | オブジェクトの生成（ヒープに確保） |
| `Foo obj = new Foo()` | 参照変数への代入 |
| `obj.field` | フィールドアクセス |
| `obj.method()` | メソッド呼び出し |
| `this` | 自インスタンスへの参照 |
| `static` | クラスに属する（インスタンス不要）メンバ |

---

## アクセス修飾子

| 修飾子 | 同クラス | 同パッケージ | サブクラス | 全体 |
| --- | --- | --- | --- | --- |
| `private` | ✓ | - | - | - |
| （なし） | ✓ | ✓ | - | - |
| `protected` | ✓ | ✓ | ✓ | - |
| `public` | ✓ | ✓ | ✓ | ✓ |

---

## コンストラクタ

```java
class Person {
    String name;

    // デフォルトコンストラクタ（引数なし）
    Person() { this("unknown"); }

    // 引数付きコンストラクタ
    Person(String name) { this.name = name; }

    // コピーコンストラクタ
    Person(Person other) { this.name = other.name; }
}
```

- `this(...)` で同クラスの別コンストラクタを呼び出せる（コンストラクタチェイニング）
- `super(...)` で親クラスのコンストラクタを呼び出せる（必ず先頭行）

---

## 継承

```java
class Animal { }
class Dog extends Animal { }  // 単一継承のみ
```

| キーワード | 用途 |
| --- | --- |
| `extends` | クラスの継承 |
| `super.method()` | 親クラスのメソッド呼び出し |
| `super(args)` | 親クラスのコンストラクタ呼び出し |
| `@Override` | メソッドオーバーライドの明示 |
| `final class` | 継承禁止 |
| `final method` | オーバーライド禁止 |

---

## オーバーライド vs オーバーロード

| | オーバーライド | オーバーロード |
| --- | --- | --- |
| 定義場所 | サブクラス | 同じクラス |
| シグネチャ | 同じ | 引数の型・数が異なる |
| 解決タイミング | 実行時（動的） | コンパイル時（静的） |
| アノテーション | `@Override` 推奨 | なし |

---

## 抽象クラスとインタフェース

| | 抽象クラス | インタフェース |
| --- | --- | --- |
| キーワード | `abstract class` | `interface` |
| インスタンス化 | 不可 | 不可 |
| 多重継承 | 不可（単一のみ） | 可（複数 `implements`） |
| フィールド | 任意 | `public static final` のみ |
| コンストラクタ | 持てる | 持てない |
| `default` メソッド | - | Java 8+ で可 |
| `static` メソッド | - | Java 8+ で可 |
| `private` メソッド | - | Java 9+ で可 |

**使い分けの指針:**

- IS-A 関係で状態（フィールド）を共有したい → 抽象クラス
- 能力（振る舞い）を複数クラスに付与したい → インタフェース

---

## ポリモーフィズム

```java
Animal a = new Dog();  // アップキャスト（暗黙）
Dog d = (Dog) a;       // ダウンキャスト（明示・ClassCastException の可能性）

// instanceof パターンマッチング (Java 16+)
if (a instanceof Dog dog) {
    dog.bark();  // キャスト不要
}
```

---

## 内部クラス 4種

| 種類 | 宣言場所 | 外部インスタンス参照 | 用途 |
| --- | --- | --- | --- |
| 静的ネストクラス | クラス内 `static` | なし | 外部クラスと関連するユーティリティ |
| 内部クラス | クラス内 | あり | 外部インスタンスの状態へのアクセスが必要な場合 |
| ローカルクラス | メソッド内 | あり（effectively final） | メソッド内のみで使う小さな実装 |
| 匿名クラス | 式の中 | あり（effectively final） | 一度だけ使うインタフェース実装 |

---

## ジェネリクス

```java
class Box<T> { T value; }          // 型パラメータ
class Pair<A, B> { A a; B b; }    // 複数の型パラメータ

<T extends Number> T sum(T a, T b) // 上限境界
List<? extends Number>             // ワイルドカード上限（読み取り専用）
List<? super Integer>              // ワイルドカード下限（書き込み専用）
```

**PECS 原則:** Producer Extends, Consumer Super

---

## 列挙型（enum）

```java
enum Planet {
    EARTH(5.976e+24, 6.37814e6);

    final double mass;
    final double radius;

    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
    }
}
```

- `values()` — 全要素の配列
- `ordinal()` — 宣言順のインデックス
- `name()` — 宣言名の文字列
- `switch` 式と相性が良い

---

## レコード（Java 16+）

```java
record Point(int x, int y) { }
// equals / hashCode / toString が自動生成される
// フィールドは暗黙的に private final

record Range(int min, int max) {
    // コンパクトコンストラクタ（バリデーション用）
    Range {
        if (min > max) throw new IllegalArgumentException();
    }
}
```

---

## シールドクラス（Java 17+）

```java
public sealed interface Shape permits Circle, Rectangle { }

public record Circle(double radius) implements Shape { }
public record Rectangle(double w, double h) implements Shape { }

// パターンマッチング switch で網羅性チェック（Java 21+）
double area = switch (shape) {
    case Circle c    -> Math.PI * c.radius() * c.radius();
    case Rectangle r -> r.w() * r.h();
    // default 不要 — permits で網羅済み
};
```

| サブクラス宣言 | 意味 |
| --- | --- |
| `final` | それ以上継承不可 |
| `sealed` | さらに `permits` で制限 |
| `non-sealed` | 制限解除（誰でも継承可） |

---

## 例外処理

```java
// checked 例外 — throws 宣言または try-catch が必須
class ValidationException extends Exception { }

// unchecked 例外 — 宣言不要
class AppException extends RuntimeException { }

try (var res = new Resource()) {   // try-with-resources
    // ...
} catch (ValidationException | AppException e) {  // multi-catch
    // ...
} finally {
    // 必ず実行
}
```

| 種類 | 基底クラス | throws 宣言 |
| --- | --- | --- |
| checked | `Exception` | 必要 |
| unchecked | `RuntimeException` | 不要 |
| エラー | `Error` | 通常は catch しない |

---

## デザインパターン早見表

| パターン | 分類 | 目的 |
| --- | --- | --- |
| Singleton | 生成 | インスタンスを1つに限定 |
| Builder | 生成 | 複雑なオブジェクトを段階的に構築 |
| Factory Method | 生成 | 生成するクラスをサブクラスに委譲 |
| Observer | 振る舞い | 状態変化を複数のオブジェクトに通知 |
| Strategy | 振る舞い | アルゴリズムを交換可能にカプセル化 |

---

## よく使うキーワード一覧

| キーワード | 意味 |
| --- | --- |
| `final` | 変数=再代入不可 / メソッド=オーバーライド不可 / クラス=継承不可 |
| `abstract` | インスタンス化不可・サブクラスで実装必須 |
| `interface` | 多重継承可能な型定義 |
| `implements` | インタフェースの実装 |
| `extends` | クラス継承 / ジェネリクスの上限境界 |
| `instanceof` | 型チェック（パターンマッチング変数も宣言可） |
| `sealed` | 継承可能なクラスを `permits` で限定 |
| `record` | イミュータブルなデータクラスを簡潔に定義 |
| `var` | ローカル変数の型推論（Java 10+） |
