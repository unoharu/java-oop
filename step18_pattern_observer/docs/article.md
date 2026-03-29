# Step 18 - Observer パターン

## 発行者と購読者を疎結合にする

Observer パターン（pub/sub）とは、**イベントの発行者（Publisher）と受信者（Subscriber）を直接つながずに通知する**設計パターンです。発行者は「誰が聞いているか」を知らなくて良く、受信者は「誰が発行しているか」を知らなくて良くなります。

```
StockPriceSource   →   EventBus   →   StockTracker
                                   →   AlertSystem
```

`StockPriceSource` は `EventBus.publish()` を呼ぶだけ。`StockTracker` と `AlertSystem` の存在を知らなくて良いです。

---

## 構造

```java
// 受信者の共通インタフェース
interface EventListener {
    void onEvent(String topic, Object data);
}

// 仲介者
class EventBus {
    subscribe(topic, listener)
    unsubscribe(topic, listener)
    publish(topic, data)  // 購読者の onEvent() を呼び出す
}
```

---

## 複数購読者

1つのトピックに複数のリスナーを登録できます。

```java
bus.subscribe("AAPL", tracker);  // 価格追跡
bus.subscribe("AAPL", alert);    // アラート
```

`publish("AAPL", 155.0)` を呼ぶと、両方のリスナーが通知されます。

---

## 購読解除

```java
bus.unsubscribe("AAPL", alert);
bus.publish("AAPL", 160.0); // tracker だけが通知される
```

---

## コード解説

### [EventListener.java](../src/main/java/oop/step18/EventListener.java)

受信者の契約インタフェース。`onEvent(topic, data)` を実装します。

### [EventBus.java](../src/main/java/oop/step18/EventBus.java)

`Map<String, List<EventListener>>` でトピックごとのリスナーリストを管理します。`publish()` でそのトピックの全リスナーを呼び出します。

### [StockTracker.java](../src/main/java/oop/step18/StockTracker.java)

前回の価格を記録し、変動を表示するリスナー。`HashMap` で銘柄ごとに前回価格を保持します。

### [AlertSystem.java](../src/main/java/oop/step18/AlertSystem.java)

価格到達時に警告メッセージを出力する単純なリスナー。

### [Main.java](../src/main/java/oop/step18/Main.java)

`subscribe()`・`publish()`・`unsubscribe()` の3操作をデモします。購読解除後は該当リスナーへの通知がなくなることを確認します。

---

## 実行してみよう

```bash
mvn compile exec:java -pl step18_pattern_observer
```

```
=== Observer パターン: EventBus ===
[StockTracker] AAPL の価格変動: $155.0 → $155.0
[AlertSystem] 警告: AAPL が $155.0 に達しました！

[StockTracker] GOOGL の価格変動: $2750.0 → $2750.0
[AlertSystem] 警告: GOOGL が $2750.0 に達しました！

=== 購読解除 ===
AlertSystem を AAPL から購読解除
[StockTracker] AAPL の価格変動: $155.0 → $160.0
// AlertSystem は購読解除済みのため通知なし
```

---

## まとめ

| 要素 | ポイント |
| --- | --- |
| `EventListener` | 受信者の共通インタフェース |
| `EventBus` | 発行者と受信者を仲介。疎結合を実現 |
| `subscribe()` | リスナーをトピックに登録 |
| `publish()` | トピックの全リスナーに通知 |
| `unsubscribe()` | リスナーの登録解除 |
| 実世界での応用 | GUI イベント・ゲームエンジン・メッセージキュー・リアクティブストリーム |

次のステップ → [Step 19 - Strategy パターン](../../step19_pattern_strategy/docs/article.md)
