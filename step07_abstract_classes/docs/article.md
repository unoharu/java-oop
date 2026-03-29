# Step 07 - 抽象クラス

## 「実装を強制する」クラス

抽象クラスとは、**インスタンス化できないクラス**です。サブクラスに「このメソッドは必ず実装しなさい」と強制するために使います。step06 の `Shape.area()` が `return 0.0` という意味のない実装を持っていた問題を、抽象クラスで解決できます。

| | 抽象クラス | インタフェース（step08） |
| --- | --- | --- |
| インスタンス化 | 不可 | 不可 |
| フィールド | 持てる | 定数のみ |
| コンストラクタ | 持てる | 持てない |
| 多重継承 | 不可（単一継承） | 複数実装可能 |
| 使いどころ | 共通状態・テンプレートメソッド | 能力の契約 |

---

## abstract キーワード

```java
public abstract class Document {
    abstract String generate(); // 実装なし — サブクラスが実装必須
    abstract String getFormatName();

    void print() {              // 具象メソッド — そのまま使える
        System.out.println("[" + getFormatName() + "] ...");
        System.out.println(generate()); // サブクラスの実装を呼ぶ
    }
}
```

- `abstract class`: このクラスは `new Document()` できない
- `abstract` メソッド: 本体 `{}` を書かない。サブクラスが `@Override` で実装しなければコンパイルエラー

---

## テンプレートメソッドパターン

`print()` は**処理の骨格**を定義し、可変部分（`generate()`）をサブクラスに委ねます。これをテンプレートメソッドパターンと言います。

```java
public class PdfDocument extends Document {
    @Override
    String generate() {
        return "%PDF-1.4 ... " + getTitle() + " の内容";
    }

    @Override
    String getFormatName() { return "PDF"; }
}
```

呼び出し側は `doc.print()` を呼ぶだけ。どのフォーマットかを意識しません。

---

## コード解説

### [Document.java](../src/main/java/oop/step07/Document.java)

抽象基底クラス。`title` フィールドとコンストラクタを持ちます。`generate()` と `getFormatName()` を abstract として宣言し、`print()` でテンプレートメソッドパターンを実装します。

### [PdfDocument.java](../src/main/java/oop/step07/PdfDocument.java)

PDF 形式の実装。`generate()` で PDF ヘッダ形式の文字列を返します。

### [HtmlDocument.java](../src/main/java/oop/step07/HtmlDocument.java)

HTML 形式の実装。`generate()` で HTML タグで囲んだ文字列を返します。

### [MarkdownDocument.java](../src/main/java/oop/step07/MarkdownDocument.java)

Markdown 形式の実装。`generate()` で `#` 見出しを返します。

### [Main.java](../src/main/java/oop/step07/Main.java)

`Document[]` 配列でポリモーフィズムを活用。`doc.print()` が各サブクラスの `generate()` を呼び出します。

---

## 実行してみよう

```bash
mvn compile exec:java -pl step07_abstract_classes
```

```
=== ドキュメント生成（テンプレートメソッド）===
[PDF] ドキュメントを印刷します: 月次レポート
--- 内容 ---
%PDF-1.4 ... 月次レポート の内容
------------

[HTML] ドキュメントを印刷します: 月次レポート
--- 内容 ---
<html><body>月次レポート の内容</body></html>
------------

[Markdown] ドキュメントを印刷します: 月次レポート
--- 内容 ---
# 月次レポート の内容
------------

=== 抽象クラスはインスタンス化できない ===
// Document d = new Document("test"); → コンパイルエラー
```

---

## まとめ

| 要素 | ポイント |
| --- | --- |
| `abstract class` | インスタンス化不可。継承専用クラス |
| `abstract` メソッド | 本体なし。サブクラスが必ず実装 |
| テンプレートメソッド | 骨格を定義し、可変部分をサブクラスに委ねる |
| 抽象クラス vs インタフェース | 状態（フィールド）が必要なら抽象クラス、能力の契約なら step08 のインタフェース |

次のステップ → [Step 08 - インタフェース](../../step08_interface/docs/article.md)
