package oop.step18;

import java.util.HashMap;
import java.util.Map;

public class StockTracker implements EventListener {
    private final Map<String, Double> previousPrices = new HashMap<>();

    @Override
    public void onEvent(String topic, Object data) {
        double newPrice = (Double) data;
        double oldPrice = previousPrices.getOrDefault(topic, newPrice);
        System.out.println("[StockTracker] " + topic + " の価格変動: $" + oldPrice + " → $" + newPrice);
        previousPrices.put(topic, newPrice);
    }
}
