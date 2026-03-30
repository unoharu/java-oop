# Step 10 - ジェネリクス

## 型を「パラメータ」にする

ジェネリクスとは、**クラスやメソッドが扱う型をパラメータとして指定できる**仕組みです。`List<String>` の `<String>` がジェネリクスです。型安全性をコンパイル時に保証し、キャストを不要にします。

```java
// ジェネリクスなし: キャストが必要でコンパイル時に型エラーを検出できない
Object box = "Hello";
String s = (String) box; // 実行時に ClassCastException の可能性

// ジェネリクスあり: コンパイル時に型チェック
Box<String> box = new Box<>("Hello");
String s = box.get(); // キャスト不要・型安全
```

---

## 型パラメータ `<T>`

```java
public class Box<T> {
    private T value;

    Box(T value) { this.value = value; }
    T get() { return value; }
}
```

`T` は任意の名前（慣習: `T`=Type, `E`=Element, `K`=Key, `V`=Value, `R`=Return）。`new Box<String>("Hello")` とすると、`T` が `String` に置き換わります。

---

## 複数の型パラメータ

```java
public class Pair<A, B> {
    private A first;
    private B second;
    // ...
}

Pair<String, Integer> person = new Pair<>("Alice", 30);
```

---

## 境界型パラメータ `<T extends Comparable<T>>`

型パラメータに制約を付けられます。

```java
static <T extends Comparable<T>> T max(T a, T b) {
    return a.compareTo(b) >= 0 ? a : b;
}
```

`T extends Comparable<T>` は「`T` は `Comparable` を実装しているはず」という制約。`compareTo()` が使えるため、`max(3, 7)` も `max("apple", "mango")` も同じメソッドで処理できます。

---

## ワイルドカード `<?>`

型が不明なコレクションを受け取るときに使います。

```java
static void printAll(List<?> list) { ... }         // 任意の型
static double sum(List<? extends Number> list) { ... } // Number またはサブタイプ
```

**PECS ルール（Producer Extends, Consumer Super）**
- データを**読み出す**（Producer）: `<? extends T>` を使う
- データを**書き込む**（Consumer）: `<? super T>` を使う

---

## 型消去（Type Erasure）

ジェネリクスはコンパイル時のみ存在します。実行時には `Box<String>` も `Box<Integer>` も同じ `Box` クラスです。

```java
box1.getClass() == box2.getClass() // → true
```

---

## コード解説

### [Box.java](../src/main/java/oop/step10/Box.java)

単一型パラメータ `<T>` を持つコンテナクラス。`get()`/`set()` で値を操作します。

### [Pair.java](../src/main/java/oop/step10/Pair.java)

2つの異なる型 `<A, B>` を保持するクラス。名前と年齢など、異なる型の組み合わせを型安全に扱えます。

### [GenericUtils.java](../src/main/java/oop/step10/GenericUtils.java)

静的ジェネリックメソッドのコレクション。`max()` は境界型パラメータ、`printAll()` はワイルドカード、`sum()` は上限境界ワイルドカードを示します。

### [Main.java](../src/main/java/oop/step10/Main.java)

各機能をデモ。型消去の確認では `getClass()` が同じ `Box` クラスを返すことを示します。

---

## 実行してみよう

```bash
mvn compile exec:java -pl step10_generics
```

```
=== Box<T> ===
Box<String>: Hello
Box<Integer>: 42

=== Pair<A, B> ===
Pair<String, Integer>: (Alice, 30)

=== 境界型パラメータ: max() ===
max(3, 7) = 7
max("apple", "mango") = mango

=== ワイルドカード ===
整数リストの合計: 15
数値リストを表示: 1 2.5 3 4.0 5

=== 型消去の確認 ===
Box<String> と Box<Integer> は同じクラス: true
```

---

## まとめ

| 要素 | ポイント |
| --- | --- |
| `<T>` | 型をパラメータ化。キャスト不要・型安全 |
| `<T extends X>` | 型パラメータに上限を設ける（制約付き）|
| `<?>` | 型不明のコレクションを扱うワイルドカード |
| `<? extends T>` | Producer Extends — 読み出し用（PECS）|
| 型消去 | ジェネリクスはコンパイル時のみ。実行時は `T` が消える |

次のステップ → [Step 11 - 列挙型](../../step11_enums/docs/article.md)
