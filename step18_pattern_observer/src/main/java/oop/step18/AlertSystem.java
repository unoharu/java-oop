package oop.step18;

public class AlertSystem implements EventListener {
    @Override
    public void onEvent(String topic, Object data) {
        System.out.println("[AlertSystem] 警告: " + topic + " が $" + data + " に達しました！");
    }
}
