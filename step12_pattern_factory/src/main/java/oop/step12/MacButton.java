package oop.step12;

public class MacButton implements Button {
    @Override
    public void render() {
        System.out.println("[Mac] ボタンを描画: クリック！");
    }

    @Override
    public void onClick() {
        System.out.println("[Mac] クリックイベントを処理");
    }
}
