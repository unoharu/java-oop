# Step 02 - コンストラクタ

## コンストラクタとは何か

コンストラクタは **オブジェクト生成時に自動で呼ばれる特殊なメソッド** です。クラス名と同じ名前を持ち、戻り値の型を持ちません。

step01 では `init()` メソッドを手動で呼んでいましたが、コンストラクタを使えばオブジェクト生成と同時に初期化できます。

```java
// step01 の方法（2ステップ）
Car car = new Car();
car.init("Toyota", "Corolla");

// コンストラクタを使う方法（1ステップ）
Person person = new Person("Alice", 30);
```

---

## コンストラクタの種類

### 1. デフォルトコンストラクタ（引数なし）

引数なしで生成できるコンストラクタ。自分で何も書かない場合、Java が自動で追加します（ただし他のコンストラクタを1つでも書いたら自動追加されなくなる）。

```java
Person p = new Person();  // 名前不明・年齢 0 として生成
```

### 2. 引数付きコンストラクタ

生成時に必要な情報を渡して初期化する。

```java
Person p = new Person("Alice", 30);
```

### 3. コピーコンストラクタ

同じ型のオブジェクトを受け取り、フィールドの値をコピーして新しいオブジェクトを作る。

```java
Person original = new Person("Bob", 25);
Person copy = new Person(original);  // 独立したコピー
```

step01 で見た「参照のコピー（`car3 = car1`）」とは異なり、コピーコンストラクタは **別のオブジェクト** を作ります。

---

## コンストラクタチェイニング（`this()`）

同クラス内で別のコンストラクタを呼び出す仕組みです。処理の重複を排除できます。

```java
Person() {
    this("unknown", 0);  // 引数付きコンストラクタに委譲
}

Person(String name, int age) {
    this.name = name;
    this.age = age;
}
```

`this(...)` は **コンストラクタの先頭行** に書く必要があります。

---

## コード解説

### [Person.java](../src/main/java/oop/step02/Person.java)

3種類のコンストラクタと `this()` チェイニングをすべて含むクラスです。

```java
public class Person {
    String name;
    int age;

    // デフォルトコンストラクタ — 引数付きに委譲
    Person() {
        this("unknown", 0);
    }

    // 引数付きコンストラクタ — フィールドを初期化する唯一の場所
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // コピーコンストラクタ — 独立したコピーを作る
    Person(Person other) {
        this(other.name, other.age);  // 引数付きコンストラクタに委譲
    }
}
```

`this(other.name, other.age)` のように、コピーコンストラクタも引数付きに委譲することで、初期化ロジックが1箇所に集約されます（DRY）。

### [Main.java](../src/main/java/oop/step02/Main.java)

各コンストラクタの動作と、コピーコンストラクタが参照コピーとは異なることを確認します。

---

## 実行してみよう

```bash
cd step02_constructors
mvn compile exec:java
```

### 期待する出力

```text
=== デフォルトコンストラクタ ===
unknown (age: 0)

=== 引数付きコンストラクタ ===
Alice (age: 30)

=== コピーコンストラクタ ===
コピー前: Bob (age: 25)
copy を変更しても original は変わらない
original: Bob (age: 25)
copy:     Charlie (age: 99)

=== 参照コピーとの違い ===
ref を変更すると original も変わる
original: Charlie (age: 99)
```

---

## まとめ

| 種類 | 構文 | 用途 |
| --- | --- | --- |
| デフォルト | `Person() { }` | 引数なしで生成したいとき |
| 引数付き | `Person(String name, int age) { }` | 生成時に初期値を渡すとき |
| コピー | `Person(Person other) { }` | 独立した複製を作るとき |
| チェイニング | `this(...)` | 重複する初期化ロジックを1箇所にまとめるとき |

次のステップ → [Step 03 - カプセル化](../../step03_encapsulation/docs/article.md)
