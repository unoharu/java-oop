# Step 08 - インタフェース

## 「できること」の契約

インタフェースとは、**クラスが持つべき能力を定義する契約**です。抽象クラス（step07）が「何者であるか」を表すのに対し、インタフェースは「何ができるか」を表します。

| | 抽象クラス | インタフェース |
| --- | --- | --- |
| インスタンス化 | 不可 | 不可 |
| フィールド | 持てる | 定数のみ（`public static final`） |
| コンストラクタ | 持てる | 持てない |
| 多重継承 | 不可 | 複数実装可能（`implements A, B`） |
| 使いどころ | 共通状態・テンプレートメソッド | 能力の契約・複数の役割 |

---

## 複数インタフェースの実装

```java
public class Report implements Printable, Saveable {
    @Override
    public void print() { ... }

    @Override
    public void save(String path) { ... }
}
```

クラスは複数のインタフェースを実装できます。`Report` は「印刷できる（Printable）」かつ「保存できる（Saveable）」オブジェクトです。

---

## インタフェース参照型

```java
Printable p = new Report("月次レポート");
p.print(); // Report の print() が呼ばれる
```

`p` は `Printable` 型として扱われるため、`save()` は呼べません。必要な能力だけを公開できます。

---

## default / static / private メソッド（Java 8/9+）

```java
public interface Printable {
    void print(); // abstract（実装必須）

    default void printPreview() { // Java 8+: 任意でオーバーライド可
        System.out.println("プレビュー: " + getPreviewText());
    }

    private String getPreviewText() { // Java 9+: default メソッドの共通ロジック
        return "(プレビューモード)";
    }

    static String describe() { // Java 8+: インタフェース自体のユーティリティ
        return "印刷可能なオブジェクトのインタフェースです";
    }
}
```

| メソッド種別 | キーワード | 用途 |
| --- | --- | --- |
| 抽象メソッド | （なし） | 実装クラスが必ず実装 |
| デフォルトメソッド | `default` | 任意でオーバーライドできる共通実装 |
| スタティックメソッド | `static` | インタフェース名で呼ぶユーティリティ |
| プライベートメソッド | `private` | `default` メソッド間の共有ロジック |

---

## コード解説

### [Printable.java](../src/main/java/oop/step08/Printable.java)

印刷能力の契約。`print()` が必須実装、`printPreview()` が `default` 実装（上書き可）、`describe()` が `static` ユーティリティ、`getPreviewText()` が `private` ヘルパーです。

### [Saveable.java](../src/main/java/oop/step08/Saveable.java)

保存能力の契約。`save(String path)` のみを定義するシンプルなインタフェースです。

### [Report.java](../src/main/java/oop/step08/Report.java)

`Printable` と `Saveable` を両方実装。`printPreview()` をオーバーライドして、タイトルを含む独自プレビューを提供します。

### [Main.java](../src/main/java/oop/step08/Main.java)

複数インタフェース実装・インタフェース参照型・`static` メソッド・`default` メソッドをすべてデモします。

---

## 実行してみよう

```bash
mvn compile exec:java -pl step08_interfaces
```

```text
=== 複数インタフェースの実装 ===
[印刷] 月次売上レポート
[保存] /reports/monthly.pdf に保存しました

=== インタフェース参照型 ===
Printable として呼び出し: [印刷] 月次売上レポート
Saveable として呼び出し: [保存] /reports/monthly.pdf に保存しました

=== static メソッド ===
Printable.describe(): 印刷可能なオブジェクトのインタフェースです

=== default メソッド ===
プレビュー: 月次売上レポート（プレビューモード）
```

---

## まとめ

| 要素 | ポイント |
| --- | --- |
| `interface` | 能力の契約。実装の強制 |
| `implements A, B` | 複数インタフェースを同時実装できる |
| `default` メソッド | 後から追加した共通実装（Java 8+） |
| `static` メソッド | `InterfaceName.method()` で呼ぶユーティリティ（Java 8+） |
| `private` メソッド | `default` メソッド内のコード共有（Java 9+） |

次のステップ → [Step 09 - 例外処理](../../step09_exception_handling/docs/article.md)
