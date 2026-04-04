# Step 11 - Builder パターン

## 多くのパラメータを持つオブジェクトの生成

オプションパラメータが多いクラスを通常のコンストラクタで作ると、呼び出しが読みにくくなります（telescoping constructor アンチパターン）。

```java
// どれが何の引数か分からない
new Computer("Core i9", 64, 2000, "RTX 4090", "Windows 11");

// Builder なら意図が明確
new Computer.Builder("Core i9", 64)
    .storage(2000)
    .gpu("RTX 4090")
    .os("Windows 11")
    .build();
```

---

## Builder の構造

```java
public final class Computer {
    // 外部からの変更不可（final + セッターなし）
    private final String cpu;
    private final int ram;
    private final int storage;
    // ...

    private Computer(Builder builder) { // コンストラクタは private
        this.cpu = builder.cpu;
        // ...
    }

    public static class Builder {
        private final String cpu; // 必須: Builder コンストラクタで受け取る
        private final int ram;
        private int storage = 256; // オプション: デフォルト値あり
        // ...

        public Builder(String cpu, int ram) { ... }

        public Builder storage(int storage) { // メソッドチェーン: this を返す
            this.storage = storage;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}
```

---

## メソッドチェーン

各 setter-like メソッドが `this`（Builder 自身）を返すため、`.メソッド()` を連続して呼べます。

```java
Computer c = new Computer.Builder("Core i5", 16) // 必須パラメータ
    .storage(512)    // オプション
    .gpu("GTX 1080") // オプション
    .build();        // Computer を生成
```

---

## イミュータブルオブジェクト

`Computer` クラスはすべてのフィールドが `final` でセッターがありません。一度 `build()` で生成した後は変更できません。スレッドセーフで状態管理が簡単になります。

---

## コード解説

### [Computer.java](../src/main/java/oop/step11/Computer.java)

`Computer` クラスは `final`（継承不可）でフィールドはすべて `final`。コンストラクタは `private` で外部から `new Computer(...)` できません。`Builder` を静的内部クラスとして内包しています。

> **Java 言語メモ: 静的ネストクラス（`static` 内部クラス）**
> `static class Builder` はアウタークラス（`Computer`）のインスタンスなしで使える入れ子クラスです。`new Computer.Builder(cpu, ram)` のように生成します。Builder パターンでは「生成ロジックを対象クラスの近くに置きつつ、インスタンス生成だけを担う」ために静的ネストクラスがよく使われます。

### [Main.java](../src/main/java/oop/step11/Main.java)

必須フィールドのみの最小構成と、すべてのオプションを指定したフル構成の2パターンをデモします。

---

## 実行してみよう

```bash
mvn compile exec:java -pl step11_pattern_builder
```

```text
=== Builder パターン ===
最小構成:
Computer{cpu='Core i5', ram=16, storage=256, gpu='なし', os='なし'}

フル構成:
Computer{cpu='Core i9', ram=64, storage=2000, gpu='RTX 4090', os='Windows 11'}

=== イミュータブルの確認 ===
// full.setCpu("...") → コンパイルエラー（setter なし）
```

---

## まとめ

| 要素 | ポイント |
| --- | --- |
| `Builder` 内部クラス | 生成ロジックを `Computer` と同じ場所に置く |
| 必須パラメータ | `Builder` コンストラクタで受け取る |
| オプションパラメータ | デフォルト値付きメソッドで設定 |
| メソッドチェーン | `return this` で流れるような API を実現 |
| `build()` | `Computer` のコンストラクタを呼んで返す |
| イミュータブル | `Computer` フィールドは `final`。セッターなし |

次のステップ → [Step 12 - Factory パターン](../../step12_pattern_factory/docs/article.md)
