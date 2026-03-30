# Step 09 - 内部クラス

## クラスの中にクラスを定義する

Java では、クラスの内部に別のクラスを定義できます。これを**ネストクラス**と総称します。定義場所と `static` 修飾子の有無によって4種類に分かれます。

| 種類 | `static` | 外部インスタンス参照 | 名前 |
| --- | --- | --- | --- |
| 静的ネストクラス | あり | なし | あり |
| 内部クラス（インナークラス） | なし | あり | あり |
| ローカルクラス | なし | あり（実質 final 変数のみ） | あり |
| 匿名クラス | なし | あり | なし |

---

## 静的ネストクラス

`static` が付いているため、外部クラスのインスタンスなしに生成できます。

```java
static class StaticNested {
    String describe() { return "外部インスタンス不要"; }
}

// 使い方
Outer.StaticNested nested = new Outer.StaticNested();
```

外部クラスのインスタンスフィールドには**アクセスできません**。外部クラスの `static` メンバーにはアクセスできます。

---

## 内部クラス（インナークラス）

外部クラスのインスタンスへの暗黙的な参照を持ちます。

```java
class Inner {
    String describe() {
        return "外部フィールドにアクセス = " + field; // Outer.this.field
    }
}

// 使い方: 外部インスタンスが必要
Outer outer = new Outer();
Outer.Inner inner = outer.new Inner();
```

---

## ローカルクラス

メソッドの本体内に定義します。そのメソッドの**実質的 final な変数**をキャプチャできます。

```java
String createLocalClass(String localVar) {
    class Local {
        String describe() {
            return "キャプチャ = " + localVar; // localVar は実質 final
        }
    }
    return new Local().describe();
}
```

---

## 匿名クラス

名前のない1回限りのクラスです。インタフェースや抽象クラスをその場で実装します。

```java
Greeter greeter = new Greeter() {
    @Override
    public String greet(String name) {
        return "こんにちは、" + name + "！";
    }
};
```

Java 8 以降はラムダ式で置き換えられることが多いですが、複数メソッドを持つインタフェースには今でも使われます。

---

## コード解説

### [Greeter.java](../src/main/java/oop/step09/Greeter.java)

匿名クラスデモ用の単純なインタフェース。`greet(String name)` を1つ持ちます。

### [Outer.java](../src/main/java/oop/step09/Outer.java)

4種類のネストクラスをすべて内包します。`field` は内部クラス・ローカルクラス・匿名クラスからアクセスできます（静的ネストクラスからは不可）。

### [Main.java](../src/main/java/oop/step09/Main.java)

各ネストクラスの生成方法と呼び出し方を示します。特に内部クラスの `outer.new Inner()` という構文に注目してください。

---

## 実行してみよう

```bash
mvn compile exec:java -pl step09_inner_classes
```

```text
=== 静的ネストクラス ===
StaticNested: 外部インスタンス不要

=== 内部クラス ===
Inner: 外部フィールドにアクセス = Outer のフィールド値

=== ローカルクラス ===
Local: メソッド内変数をキャプチャ = ローカル変数の値

=== 匿名クラス ===
Anonymous: Greeter の実装 = こんにちは、Java！
```

---

## まとめ

| 種類 | 主な用途 |
| --- | --- |
| 静的ネストクラス | 外部クラスと論理的に関連するが独立したクラス（例: `Builder`） |
| 内部クラス | 外部インスタンスの状態に密接に依存する補助クラス |
| ローカルクラス | メソッド内でのみ使う一時的なロジック |
| 匿名クラス | コールバック・イベントハンドラのような使い捨て実装 |

次のステップ → [Step 10 - ジェネリクス](../../step10_generics/docs/article.md)
