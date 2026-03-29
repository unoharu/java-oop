package oop.step18;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBus {
    private final Map<String, List<EventListener>> listeners = new HashMap<>();

    void subscribe(String topic, EventListener listener) {
        listeners.computeIfAbsent(topic, k -> new ArrayList<>()).add(listener);
    }

    void unsubscribe(String topic, EventListener listener) {
        List<EventListener> topicListeners = listeners.get(topic);
        if (topicListeners != null) {
            topicListeners.remove(listener);
        }
    }

    void publish(String topic, Object data) {
        List<EventListener> topicListeners = listeners.getOrDefault(topic, List.of());
        for (EventListener listener : topicListeners) {
            listener.onEvent(topic, data);
        }
    }
}
