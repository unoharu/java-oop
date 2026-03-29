# Step 11 - 列挙型（enum）

## 型安全な定数の集合

列挙型（enum）は、**決まった値の集合を型として表現する**仕組みです。`int` の定数（`static final int MONDAY = 1`）と違い、コンパイル時に値の範囲を保証できます。

```java
// int 定数: 誤った値を渡してもコンパイルエラーにならない
static final int MONDAY = 1;
void process(int day) { ... }
process(999); // コンパイル通る

// enum: 定義した値以外は渡せない
enum Day { MONDAY, TUESDAY, ... }
void process(Day day) { ... }
process(Day.MONDAY); // 型安全
```

---

## 基本的な enum

```java
enum Day { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }
```

- `Day.values()` — 全定数の配列を返す
- `Day.MONDAY.name()` — 定数名を文字列で返す（`"MONDAY"`）
- `Day.MONDAY.ordinal()` — 宣言順のインデックス（`0`）
- `Day.valueOf("FRIDAY")` — 名前から定数を取得

---

## フィールド付き enum（Planet）

enum はクラスのように**フィールド・コンストラクタ・メソッド**を持てます。

```java
enum Planet {
    EARTH(5.976e+24, 6.37814e6),
    MARS (6.421e+23, 3.3972e6);

    private final double mass;
    private final double radius;
    private static final double G = 6.67300E-11;

    Planet(double mass, double radius) { ... }

    double surfaceGravity() {
        return G * mass / (radius * radius);
    }
}
```

`Planet.EARTH.surfaceGravity()` で各惑星の重力加速度を計算できます。

---

## 抽象メソッド付き enum（Day）

各定数が異なる実装を持てます。

```java
enum Day {
    MONDAY {
        @Override public String work() { return "週初めのミーティング"; }
    },
    SATURDAY {
        @Override public String work() { return "休日です"; }
    };

    public abstract String work(); // 全定数が実装必須
}
```

---

## switch 式（Java 14+）

enum の `switch` 式は**網羅性をコンパイラが検証**します。

```java
String type = switch (day) {
    case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "平日";
    case SATURDAY, SUNDAY -> "休日";
};
```

`default` を書かなくても、すべての enum 定数を列挙すればコンパイルエラーになりません。

---

## コード解説

### [Planet.java](../src/main/java/oop/step11/Planet.java)

フィールド付き enum の典型例。`mass`・`radius` フィールドと `surfaceGravity()` メソッドを持ちます。`G` は enum 内の `static final` 定数です。

### [Day.java](../src/main/java/oop/step11/Day.java)

抽象メソッド `work()` を持つ enum。各定数（MONDAY〜SUNDAY）がそれぞれ独自の実装を提供します。`isWeekend()` は全定数が共有する具象メソッドです。

### [Main.java](../src/main/java/oop/step11/Main.java)

`values()` によるイテレーション・フィールド付き enum・抽象メソッド・`switch` 式のすべてをデモします。

---

## 実行してみよう

```bash
mvn compile exec:java -pl step11_enums
```

```
=== 基本的な列挙型 ===
曜日一覧: MONDAY TUESDAY WEDNESDAY THURSDAY FRIDAY SATURDAY SUNDAY

=== フィールド付き enum: Planet ===
EARTH: 質量 = 5.976e+24 kg, 重力 = 9.80 m/s²
MARS:  質量 = 6.421e+23 kg, 重力 = 3.71 m/s²

=== 抽象メソッド付き enum: Day ===
MONDAY の業務: 週初めのミーティング
SATURDAY の業務: 休日です

=== switch 式 ===
WEDNESDAY は: 平日
SUNDAY は: 休日
```

---

## まとめ

| 要素 | ポイント |
| --- | --- |
| `enum` | 型安全な定数の集合 |
| `values()` / `name()` / `ordinal()` | enum の基本メタデータ |
| フィールド付き enum | コンストラクタ・メソッドでリッチな定数を定義できる |
| 抽象メソッド | 定数ごとに異なる振る舞いを強制できる |
| `switch` 式 | 網羅性をコンパイラが保証する（Java 14+）|

次のステップ → [Step 12 - レコード](../../step12_records/docs/article.md)
