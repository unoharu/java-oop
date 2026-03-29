# Step 04 - 継承

## 継承とは何か

継承は **既存のクラスの機能を引き継いで、新しいクラスを定義する** 仕組みです。`extends` キーワードを使います。

```java
class Animal { }
class Dog extends Animal { }  // Dog は Animal を継承
```

`Dog` は `Animal` の IS-A（〜は〜である）関係を表します。「犬は動物である」が成り立つときに継承を使います。

---

## 親クラス・子クラス

| 呼び名 | 別名 | 役割 |
| --- | --- | --- |
| 親クラス | スーパークラス・基底クラス | 共通の状態・振る舞いを定義 |
| 子クラス | サブクラス・派生クラス | 親の機能を引き継ぎ、独自の機能を追加 |

子クラスは親クラスの `public` / `protected` メンバをすべて継承します。

---

## `super` キーワード

`super` は親クラスを参照するキーワードです。2つの使い方があります。

```java
// 1. 親クラスのコンストラクタを呼び出す（子コンストラクタの先頭行に書く）
Dog(String name) {
    super(name);  // Animal(String name) を呼び出す
}

// 2. 親クラスのメソッドを呼び出す
@Override
String describe() {
    return super.describe() + " / 犬種: " + breed;
}
```

`super()` を明示しない場合、Java は自動で親の引数なしコンストラクタを呼び出します。親に引数なしコンストラクタがなければコンパイルエラーになります。

---

## すべてのクラスは Object を継承している

Java では明示的に `extends` を書かない場合、暗黙的に `java.lang.Object` を継承します。`toString()` や `equals()` が使えるのはこのためです。

```
Object
  └── Animal
        ├── Dog
        └── Cat
```

---

## コード解説

### [Animal.java](../src/main/java/oop/step04/Animal.java)

すべての動物に共通する状態（名前）と振る舞い（鳴き声・食事）を持つ親クラスです。

```java
public class Animal {
    protected String name;  // protected — サブクラスから直接アクセスできる

    Animal(String name) {
        this.name = name;
    }

    void eat() {
        System.out.println(name + " が食事をする");
    }

    String describe() {
        return "動物: " + name;
    }
}
```

### [Dog.java](../src/main/java/oop/step04/Dog.java) / [Cat.java](../src/main/java/oop/step04/Cat.java)

`Animal` を継承し、犬・猫固有の機能（鳴き声・独自メソッド）を追加します。

```java
public class Dog extends Animal {
    private String breed;  // 犬種（Dog 固有のフィールド）

    Dog(String name, String breed) {
        super(name);        // 親コンストラクタに name を渡す
        this.breed = breed;
    }

    void bark() {
        System.out.println(name + " が吠える: ワン！");
    }

    @Override
    String describe() {
        return super.describe() + " / 犬種: " + breed;
    }
}
```

---

## 実行してみよう

```bash
cd step04_inheritance
mvn compile exec:java
```

### 期待する出力

```text
=== Animal（親クラス）===
動物: 名無し が食事をする

=== Dog（子クラス）===
ポチ が食事をする        ← 親から継承したメソッド
ポチ が吠える: ワン！    ← Dog 固有のメソッド
動物: ポチ / 犬種: 柴犬  ← super.describe() を拡張

=== Cat（子クラス）===
タマ が食事をする        ← 親から継承したメソッド
タマ が鳴く: ニャー！    ← Cat 固有のメソッド
動物: タマ / 種類: 三毛猫

=== IS-A 関係の確認 ===
dog は Animal のインスタンス: true
cat は Animal のインスタンス: true
dog は Cat のインスタンス: false
```

---

## まとめ

| 要素 | ポイント |
| --- | --- |
| `extends` | 親クラスの機能を引き継ぐ |
| `super()` | 親コンストラクタの呼び出し（先頭行必須） |
| `super.method()` | 親メソッドの呼び出し |
| `protected` | サブクラスからアクセス可能なアクセス修飾子 |
| IS-A 関係 | 継承が適切かどうかの判断基準 |

次のステップ → [Step 05 - オーバーライドとオーバーロード](../../step05_overriding_overloading/docs/article.md)
