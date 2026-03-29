# Step 15 - Singleton パターン

## インスタンスを1つに制限する

Singleton パターンとは、**クラスのインスタンスがプロセス全体で必ず1つだけ存在することを保証する**設計パターンです。設定管理・ログ・キャッシュなど、「1つで十分」なオブジェクトに使います。

基本構造:
1. コンストラクタを `private` にして外部からの `new` を禁止
2. インスタンスをクラス内で1つだけ保持
3. `getInstance()` でそのインスタンスを返す

---

## 実装 1: Eager Singleton（早期初期化）

```java
public class EagerSingleton {
    private static final EagerSingleton INSTANCE = new EagerSingleton();
    private EagerSingleton() {}
    public static EagerSingleton getInstance() { return INSTANCE; }
}
```

- クラスロード時にインスタンスを作成
- JVM のクラスローディングはスレッドセーフなので同期不要
- インスタンスが使われなくても作られる（遅延なし）

---

## 実装 2: Lazy Singleton（ダブルチェックロッキング）

```java
public class LazySingleton {
    private static volatile LazySingleton instance;
    private LazySingleton() {}

    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}
```

- 最初の呼び出し時にのみインスタンスを作成（遅延初期化）
- `volatile` でメモリの可視性を保証
- 初期化後は同期コストなし

---

## 実装 3: Enum Singleton（推奨）

```java
public enum EnumSingleton {
    INSTANCE;
    public int getValue() { return 42; }
}
```

- シリアライズに対して安全（デシリアライズで新インスタンスが作られない）
- リフレクションによる攻撃に対して安全
- JVM が enum のインスタンス数を保証
- **Joshua Bloch（Effective Java）が推奨する方法**

---

## 実装 4: Holder Singleton（遅延初期化ホルダーイディオム）

```java
public class HolderSingleton {
    private HolderSingleton() {}

    private static final class Holder {
        static final HolderSingleton INSTANCE = new HolderSingleton();
    }

    public static HolderSingleton getInstance() {
        return Holder.INSTANCE;
    }
}
```

- `Holder` 内部クラスは `getInstance()` が呼ばれるまでロードされない
- `synchronized` なしで遅延初期化とスレッドセーフを両立
- 通常のクラスで最もエレガントな実装

---

## コード解説

### [EagerSingleton.java](../src/main/java/oop/step15/EagerSingleton.java)

最もシンプルな実装。`static final` フィールドで起動時に初期化されます。

### [LazySingleton.java](../src/main/java/oop/step15/LazySingleton.java)

ダブルチェックロッキング。`volatile` と二重の `null` チェックでスレッドセーフな遅延初期化を実現します。

### [EnumSingleton.java](../src/main/java/oop/step15/EnumSingleton.java)

enum による実装。シリアライズ・リフレクションに対して最も堅牢です。

### [HolderSingleton.java](../src/main/java/oop/step15/HolderSingleton.java)

内部ホルダークラスによる遅延初期化。`synchronized` 不要で遅延とスレッドセーフを両立します。

### [Main.java](../src/main/java/oop/step15/Main.java)

各実装で `getInstance()` を2回呼び、`==` で同一インスタンスであることを確認します。

---

## 実行してみよう

```bash
mvn compile exec:java -pl step15_pattern_singleton
```

```
=== Eager Singleton ===
同じインスタンス: true

=== Lazy Singleton ===
同じインスタンス: true

=== Enum Singleton ===
同じインスタンス: true
EnumSingleton.INSTANCE.getValue() = 42

=== Holder Singleton ===
同じインスタンス: true
```

---

## まとめ

| 実装方法 | 遅延初期化 | スレッドセーフ | シリアライズ安全 | 推奨度 |
| --- | --- | --- | --- | --- |
| Eager | なし | ○ | △ | シンプルな場合 |
| Lazy（DCL） | あり | ○ | △ | 遅延が必要な場合 |
| Enum | なし | ○ | ○ | **最も推奨** |
| Holder | あり | ○ | △ | 通常クラスで最良 |

> **注意**: Singleton はグローバル状態を作るため、テストが難しくなります。乱用せず、本当に1つで良いオブジェクトにのみ使いましょう。

次のステップ → [Step 16 - Builder パターン](../../step16_pattern_builder/docs/article.md)
