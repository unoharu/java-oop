# Step 09 - 例外処理

## エラーを型として扱う

例外処理は、**プログラムが異常状態になったとき、制御を安全に移す**仕組みです。Java の例外は `Throwable` を頂点とする継承ツリーで表現されます。

```text
Throwable
├── Error           (JVM の深刻なエラー: OutOfMemoryError など — 通常は捕捉しない)
└── Exception
    ├── RuntimeException   (Unchecked: プログラムのバグ)
    │   ├── NullPointerException
    │   ├── IllegalArgumentException
    │   └── ...
    └── IOException, SQLException, ...  (Checked: 回復可能な異常)
```

| | Checked 例外 | Unchecked 例外 |
| --- | --- | --- |
| 基底クラス | `Exception` | `RuntimeException` |
| `throws` 宣言 | **必須** | 不要 |
| 代表例 | `IOException`、`SQLException` | `NullPointerException`、`IllegalArgumentException` |
| 使いどころ | 呼び出し側が回復できる異常 | プログラムのバグ・前提条件違反 |

---

## try-catch-finally

```java
try {
    account.withdraw(1000.0);
} catch (InsufficientFundsException e) {
    System.out.println("残高不足: " + e.getMessage());
} finally {
    // 例外の有無に関わらず必ず実行される（リソース解放など）
}
```

**実行順序**:

1. `try` ブロックを実行
2. 例外が発生: 対応する `catch` へジャンプ
3. 例外がない: `catch` をスキップ
4. 必ず `finally` を実行

---

## multi-catch（Java 7+）

複数の例外型を `|` でまとめて捕捉できます。

```java
catch (InsufficientFundsException | NegativeAmountException e) {
    System.out.println("いずれかの例外: " + e.getMessage());
}
```

---

## try-with-resources（Java 7+）

`AutoCloseable` を実装したリソースを自動でクローズします。

```java
try (ResourceDemo resource = new ResourceDemo("demo")) {
    resource.process();
} // resource.close() が自動的に呼ばれる
```

`finally { resource.close(); }` を手動で書く必要がなくなります。

---

## カスタム例外

ドメイン固有の意味を持つ例外クラスを作ることで、エラーの意図が明確になります。

```java
// Checked 例外
public class InsufficientFundsException extends Exception {
    InsufficientFundsException(double balance, double amount) {
        super("残高不足: 残高 " + balance + ", 要求 " + amount);
    }
}

// Unchecked 例外
public class NegativeAmountException extends RuntimeException {
    NegativeAmountException(double amount) {
        super("金額は正の値が必要: " + amount);
    }
}
```

---

## コード解説

### [InsufficientFundsException.java](../src/main/java/oop/step09/InsufficientFundsException.java)

Checked 例外（`extends Exception`）。残高と要求額をフィールドに保持します。`throws` 宣言が必須です。

### [NegativeAmountException.java](../src/main/java/oop/step09/NegativeAmountException.java)

Unchecked 例外（`extends RuntimeException`）。不正な引数を表します。`throws` 宣言は不要です。

### [BankAccount.java](../src/main/java/oop/step09/BankAccount.java)

`deposit()` は `NegativeAmountException`（unchecked）を投げます。`withdraw()` は `InsufficientFundsException`（checked）を `throws` 宣言して投げます。

### [ResourceDemo.java](../src/main/java/oop/step09/ResourceDemo.java)

`AutoCloseable` を実装。`close()` でリソース解放のメッセージを出力します。try-with-resources で自動クローズされます。

### [Main.java](../src/main/java/oop/step09/Main.java)

正常フロー・checked 例外・unchecked 例外・multi-catch・try-with-resources を順番にデモします。

---

## 実行してみよう

```bash
mvn compile exec:java -pl step09_exception_handling
```

```text
=== 正常な入金・出金 ===
入金: 1000.0 円 → 残高: 1000.0 円
出金: 500.0 円 → 残高: 500.0 円

=== Checked 例外: 残高不足 ===
例外捕捉: 残高不足です。残高: 500.0, 要求: 1000.0

=== Unchecked 例外: 負の金額 ===
例外捕捉: 金額は正の値でなければなりません: -100.0

=== multi-catch ===
InsufficientFundsException または NegativeAmountException を一括処理: 金額は正の値でなければなりません: -50.0

=== try-with-resources ===
リソースをオープン: ResourceDemo
処理中...
リソースを自動クローズ: ResourceDemo
```

---

## まとめ

| 要素 | ポイント |
| --- | --- |
| Checked 例外 | `extends Exception`。`throws` 必須。回復可能な異常 |
| Unchecked 例外 | `extends RuntimeException`。宣言不要。プログラムのバグ |
| `try-catch-finally` | 例外を捕捉し、必ず後処理を実行 |
| `catch (A \| B e)` | 複数例外をまとめて処理（Java 7+） |
| try-with-resources | `AutoCloseable` リソースを自動クローズ（Java 7+） |

次のステップ → [Step 10 - Singleton パターン](../../step10_pattern_singleton/docs/article.md)
