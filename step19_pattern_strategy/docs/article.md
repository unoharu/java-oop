# Step 19 - Strategy パターン

## アルゴリズムを交換可能にする

Strategy パターンとは、**アルゴリズムをインタフェースとして定義し、実行時に差し替えられるようにする**設計パターンです。

```java
// Strategy なし: ソート方法をクラスにハードコード
class Sorter {
    void sort(int[] data) {
        bubbleSort(data); // 別のアルゴリズムに変えたい → Sorter を修正必要
    }
}

// Strategy あり: アルゴリズムを外から渡す
Sorter sorter = new Sorter(new BubbleSort());
sorter.setStrategy(new SelectionSort()); // 実行時に切り替え可能
```

---

## 構造

```
SortStrategy (interface)
├── BubbleSort
└── SelectionSort

Sorter (context)
└── strategy: SortStrategy  ← 差し替え可能
```

`Sorter`（コンテキスト）は `SortStrategy` に処理を委譲します。どのアルゴリズムが使われるかを知りません。

---

## @FunctionalInterface との接続

`SortStrategy` は `sort(int[])` という1つのメソッドしか持ちません。`@FunctionalInterface` を付けることで、**ラムダ式で実装を渡せる**ようになります。

```java
@FunctionalInterface
interface SortStrategy {
    void sort(int[] data);
}

// クラスを作らずインラインで Strategy を指定できる
sorter.setStrategy(data -> Arrays.sort(data));
```

これは Java 8 以降の関数型スタイルへの橋渡しです。OOP の Strategy パターンと関数型プログラミングが接続される場所です。

---

## コード解説

### [SortStrategy.java](../src/main/java/oop/step19/SortStrategy.java)

`@FunctionalInterface` を付けた1メソッドインタフェース。ラムダ式・メソッド参照でも実装できます。

### [BubbleSort.java](../src/main/java/oop/step19/BubbleSort.java)

バブルソートの実装。隣接する要素を比較・交換するシンプルな O(n²) アルゴリズムです。

### [SelectionSort.java](../src/main/java/oop/step19/SelectionSort.java)

選択ソートの実装。未整列部分から最小値を探して先頭と交換する O(n²) アルゴリズムです。

### [Sorter.java](../src/main/java/oop/step19/Sorter.java)

コンテキストクラス。`setStrategy()` で実行時にアルゴリズムを差し替えられます。`sort()` は戦略に処理を委譲するだけです。

### [Main.java](../src/main/java/oop/step19/Main.java)

BubbleSort → SelectionSort → ラムダの3段階で戦略を切り替えます。同じ入力・同じ呼び出しで同じ結果が得られることを確認します。

---

## 実行してみよう

```bash
mvn compile exec:java -pl step19_pattern_strategy
```

```
=== Strategy パターン: ソートアルゴリズム ===
BubbleSort を使用:
入力: [5, 3, 8, 1, 9, 2]
結果: [1, 2, 3, 5, 8, 9]

SelectionSort に切り替え:
入力: [5, 3, 8, 1, 9, 2]
結果: [1, 2, 3, 5, 8, 9]

=== ラムダ式で Strategy を置き換え ===
Arrays.sort をラムダで渡す:
入力: [5, 3, 8, 1, 9, 2]
結果: [1, 2, 3, 5, 8, 9]
```

---

## まとめ

| 要素 | ポイント |
| --- | --- |
| `SortStrategy` | アルゴリズムの共通インタフェース |
| `Sorter`（コンテキスト）| 戦略に処理を委譲。アルゴリズムを知らない |
| `setStrategy()` | 実行時にアルゴリズムを差し替えられる |
| `@FunctionalInterface` | ラムダ式での実装を許可。OOP と関数型をつなぐ |
| Open/Closed Principle | 新アルゴリズム追加 = 新クラス追加のみ。Sorter は変更不要 |

---

## カリキュラム完了

このステップで Java OOP カリキュラムのすべてのトピックを学習しました。

| カテゴリ | ステップ |
| --- | --- |
| 基礎 | step01〜05（クラス・コンストラクタ・カプセル化・継承・オーバーライド）|
| OOP コア | step06〜10（ポリモーフィズム・抽象クラス・インタフェース・内部クラス・ジェネリクス）|
| モダン Java | step11〜13（enum・record・sealed）|
| 例外 | step14（例外処理）|
| デザインパターン | step15〜19（Singleton・Builder・Factory・Observer・Strategy）|

[README へ戻る](../../README.md)
