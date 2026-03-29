# Step 03 - カプセル化

## カプセル化とは何か

カプセル化は **データ（フィールド）を隠蔽し、公開するインタフェース（メソッド）を通じてのみ操作させる** 設計原則です。

step01・step02 では `car.speed = -100` のように外部から直接フィールドを書き換えられました。これは意図しない状態を引き起こす原因になります。カプセル化でこれを防ぎます。

```java
// カプセル化なし — 不正な値を誰でも入れられる
account.balance = -99999;

// カプセル化あり — メソッドを通じてのみ変更できる
account.deposit(1000);   // 内部でバリデーションされる
```

---

## アクセス修飾子

| 修飾子 | 同クラス | 同パッケージ | サブクラス | 全体 |
| --- | --- | --- | --- | --- |
| `private` | ✓ | - | - | - |
| （なし） | ✓ | ✓ | - | - |
| `protected` | ✓ | ✓ | ✓ | - |
| `public` | ✓ | ✓ | ✓ | ✓ |

カプセル化の基本方針: **フィールドは `private`、外部に公開したいものだけ `public` メソッドにする**。

---

## getter / setter

`private` フィールドへのアクセスを提供するメソッドです。

```java
private String owner;

// getter — 値を読み取るだけ
public String getOwner() {
    return owner;
}

// setter — バリデーションを挟んで書き込む
public void setOwner(String owner) {
    if (owner == null || owner.isBlank()) {
        throw new IllegalArgumentException("owner must not be blank");
    }
    this.owner = owner;
}
```

setter が不要なフィールド（読み取り専用にしたい）には getter だけ用意します。

---

## `final` フィールド

`final` をつけたフィールドは **一度だけ代入でき、以後変更不可** になります。口座番号のように生成後に変えてはいけない値に使います。

```java
private final String accountNumber;  // コンストラクタで1回だけ設定できる
```

---

## コード解説

### [BankAccount.java](../src/main/java/oop/step03/BankAccount.java)

銀行口座を題材に、カプセル化の要素をすべて盛り込んだクラスです。

```java
public class BankAccount {
    private final String accountNumber;  // 読み取り専用（getter のみ）
    private String owner;
    private double balance;

    public BankAccount(String accountNumber, String owner) {
        this.accountNumber = accountNumber;
        setOwner(owner);   // setter 経由でバリデーションを通す
        this.balance = 0;
    }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("amount must be positive");
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("amount must be positive");
        if (amount > balance) throw new IllegalStateException("insufficient balance");
        balance -= amount;
    }

    // getter のみ（balance は直接変更させない）
    public double getBalance() { return balance; }
    public String getAccountNumber() { return accountNumber; }

    // getter + setter（owner は変更を許可するがバリデーション付き）
    public String getOwner() { return owner; }
    public void setOwner(String owner) {
        if (owner == null || owner.isBlank()) {
            throw new IllegalArgumentException("owner must not be blank");
        }
        this.owner = owner;
    }
}
```

### [Main.java](../src/main/java/oop/step03/Main.java)

正常な操作と、不正な操作（マイナス入金・残高超過引き出し）が例外で弾かれることを確認します。

---

## 実行してみよう

```bash
cd step03_encapsulation
mvn compile exec:java
```

### 期待する出力

```text
=== 口座を開設 ===
口座番号: ACC-001
名義: Alice
残高: 0.0 円

=== 入金・引き出し ===
50000.0 円入金後: 50000.0 円
20000.0 円引き出し後: 30000.0 円

=== 名義変更 ===
名義変更後: Bob

=== 不正な操作は例外で弾かれる ===
マイナス入金: amount must be positive
残高超過引き出し: insufficient balance
空の名義: owner must not be blank
```

---

## まとめ

| 要素 | 目的 |
| --- | --- |
| `private` フィールド | 外部から直接変更させない |
| getter | 読み取りのみ許可 |
| setter | バリデーション付きで変更を許可 |
| `final` フィールド | 生成後に変更不可にする |

次のステップ → [Step 04 - 継承](../../step04_inheritance/docs/article.md)
