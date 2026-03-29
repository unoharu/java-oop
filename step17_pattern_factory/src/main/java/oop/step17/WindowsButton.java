package oop.step17;

public class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("[Windows] ボタンを描画: クリック！");
    }

    @Override
    public void onClick() {
        System.out.println("[Windows] クリックイベントを処理");
    }
}
