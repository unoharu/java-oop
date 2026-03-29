# Step 01 - クラスとオブジェクト

## クラスとは何か

クラスは **オブジェクトの設計図** です。

「車」という概念を例にすると、車には「メーカー」「モデル」「速度」といった **状態（フィールド）** と、「加速する」「ブレーキをかける」といった **振る舞い（メソッド）** があります。この設計図を Java のコードで表したものがクラスです。

```java
public class Car {
    String make;   // メーカー（フィールド）
    String model;  // モデル（フィールド）
    int speed;     // 現在速度（フィールド）

    void accelerate(int amount) { ... }  // 加速（メソッド）
    void brake(int amount) { ... }       // 制動（メソッド）
}
```

---

## オブジェクトとは何か

オブジェクトはクラスから生成された **実体（インスタンス）** です。設計図（クラス）は1つですが、そこから何台でも車（オブジェクト）を作れます。

```java
Car car1 = new Car();  // car1 というオブジェクトを生成
Car car2 = new Car();  // car2 は別のオブジェクト
```

`new` 演算子を使うと、JVM はヒープメモリ上に新しいオブジェクトを確保し、その **参照（アドレス）** を変数に代入します。`car1` と `car2` はそれぞれ独立した状態を持ちます。

---

## 参照型と値型の違い

Java の変数は2種類に分かれます。

| 種類 | 例 | 変数が持つもの |
| --- | --- | --- |
| 値型（プリミティブ） | `int`, `double`, `boolean` | 値そのもの |
| 参照型 | クラス、配列 | オブジェクトへの参照（アドレス） |

```java
int a = 10;
int b = a;   // 値がコピーされる
b = 20;
// a は 10 のまま

Car c1 = new Car();
Car c2 = c1;   // 参照がコピーされる（同じオブジェクトを指す）
c2.speed = 100;
// c1.speed も 100 になる（同一オブジェクト）
```

---

## コード解説

### [Car.java](../src/main/java/oop/step01/Car.java)

`Car` クラスはフィールドとメソッドを持つ基本的なクラスです。

```java
public class Car {
    String make;
    String model;
    int speed;

    // フィールドに初期値を設定するメソッド
    void init(String make, String model) {
        this.make = make;    // this = 自インスタンスのフィールド
        this.model = model;
        this.speed = 0;
    }

    void accelerate(int amount) {
        speed += amount;
    }

    void brake(int amount) {
        speed = Math.max(0, speed - amount);  // 0 未満にならないよう制限
    }

    String describe() {
        return make + " " + model + " (speed: " + speed + " km/h)";
    }
}
```

`this` キーワードは自分自身のインスタンスを指します。引数名とフィールド名が同じとき、`this.make` と書くことでフィールドを明示します。

### [Main.java](../src/main/java/oop/step01/Main.java)

2つの `Car` オブジェクトを生成し、それぞれが独立して状態を持つことを確認します。

---

## 実行してみよう

```bash
cd step01_classes_objects
mvn exec:java
```

### 期待する出力

```text
=== 2つの Car オブジェクトを生成 ===
car1: Toyota Corolla (speed: 0 km/h)
car2: Honda Civic (speed: 0 km/h)

=== car1 だけ加速 ===
car1: Toyota Corolla (speed: 60 km/h)
car2: Honda Civic (speed: 0 km/h)   ← car2 は変わらない

=== car1 にブレーキ ===
car1: Toyota Corolla (speed: 40 km/h)

=== 参照のコピーを確認 ===
car3 は car1 と同じオブジェクトを指している
car3.speed を 0 にすると car1.speed も 0 になる: Toyota Corolla (speed: 0 km/h)
```

---

## まとめ

| 概念 | ポイント |
| --- | --- |
| クラス | オブジェクトの設計図。フィールド（状態）とメソッド（振る舞い）を定義する |
| オブジェクト | `new` でヒープ上に生成されたクラスの実体 |
| 参照型 | 変数はオブジェクト本体ではなく、その場所（参照）を持つ |
| `this` | メソッド内で自インスタンスのメンバを明示するキーワード |

次のステップ → [Step 02 - コンストラクタ](../../step02_constructors/docs/article.md)
