# Step 17 - Factory パターン

## オブジェクト生成を隠蔽する

Factory パターンとは、**オブジェクトの生成をファクトリに委ね、呼び出し側が具体的なクラスを知らなくて済むようにする**設計パターンです。

```java
// Factory なし: 呼び出し側が具体クラスを知っている
if (os.equals("Windows")) {
    button = new WindowsButton();
} else if (os.equals("Mac")) {
    button = new MacButton();
}

// Factory あり: 呼び出し側は Button インタフェースだけを知ればよい
Button button = ButtonFactory.create(os);
button.render(); // どの OS でも同じ呼び方
```

---

## 構造

```
Button (interface)
├── WindowsButton
├── MacButton
└── LinuxButton

ButtonFactory.create(String os) → Button
```

呼び出し側（`Main`）は `Button` インタフェースしか使いません。`WindowsButton` などの具体クラスは `Main` からは見えない設計です。

---

## switch 式でファクトリを実装

```java
public static Button create(String os) {
    return switch (os.toLowerCase()) {
        case "windows" -> new WindowsButton();
        case "mac"     -> new MacButton();
        case "linux"   -> new LinuxButton();
        default -> throw new IllegalArgumentException("未知の OS: " + os);
    };
}
```

新しい OS を追加するとき:
1. `XxxButton` クラスを追加
2. `ButtonFactory` に1行追加
3. `Main` は変更不要

これが **Open/Closed Principle（開放/閉鎖原則）**: 拡張に対してオープン、変更に対してクローズ。

---

## コード解説

### [Button.java](../src/main/java/oop/step17/Button.java)

`render()` と `onClick()` を持つインタフェース。呼び出し側はこの型だけを使います。

### [WindowsButton.java](../src/main/java/oop/step17/WindowsButton.java) / [MacButton.java](../src/main/java/oop/step17/MacButton.java) / [LinuxButton.java](../src/main/java/oop/step17/LinuxButton.java)

`Button` を実装する具体クラス。OS ごとに異なる実装を持ちます。

### [ButtonFactory.java](../src/main/java/oop/step17/ButtonFactory.java)

`switch` 式でOS文字列から適切な `Button` を生成します。未知のOSには `IllegalArgumentException` を投げます（step14 の接続）。

### [Main.java](../src/main/java/oop/step17/Main.java)

`Button button = ButtonFactory.create(os)` という形でしか使いません。変数型が `Button`（インタフェース）なので、`WindowsButton` を直接操作していないことに注目してください。

---

## 実行してみよう

```bash
mvn compile exec:java -pl step17_pattern_factory
```

```
=== Factory パターン ===
OS: Windows → WindowsButton を生成
[Windows] ボタンを描画: クリック！

OS: Mac → MacButton を生成
[Mac] ボタンを描画: クリック！

OS: Linux → LinuxButton を生成
[Linux] ボタンを描画: クリック！

=== 不明な OS ===
例外捕捉: 未知の OS: FreeBSD
```

---

## まとめ

| 要素 | ポイント |
| --- | --- |
| Factory | オブジェクト生成を1箇所に集約 |
| インタフェース参照型 | 呼び出し側が具体クラスを知らなくて済む |
| `switch` 式 | 生成ロジックを簡潔に記述（default で不正値処理）|
| Open/Closed Principle | 新クラス追加 = Factory に1行 + 呼び出し側変更なし |

次のステップ → [Step 18 - Observer パターン](../../step18_pattern_observer/docs/article.md)
