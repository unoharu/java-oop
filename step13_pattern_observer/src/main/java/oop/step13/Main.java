package oop.step13;

public class Main {
    public static void main(String[] args) {
        EventBus bus = new EventBus();
        StockTracker tracker = new StockTracker();
        AlertSystem alert = new AlertSystem();

        bus.subscribe("AAPL", tracker);
        bus.subscribe("AAPL", alert);
        bus.subscribe("GOOGL", tracker);
        bus.subscribe("GOOGL", alert);

        System.out.println("=== Observer パターン: EventBus ===");
        bus.publish("AAPL", 155.0);
        System.out.println();
        bus.publish("GOOGL", 2750.0);

        System.out.println("\n=== 購読解除 ===");
        bus.unsubscribe("AAPL", alert);
        System.out.println("AlertSystem を AAPL から購読解除");
        bus.publish("AAPL", 160.0);
        System.out.println("// AlertSystem は購読解除済みのため通知なし");
    }
}
