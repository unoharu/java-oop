package oop.step12;

public class LinuxButton implements Button {
    @Override
    public void render() {
        System.out.println("[Linux] ボタンを描画: クリック！");
    }

    @Override
    public void onClick() {
        System.out.println("[Linux] クリックイベントを処理");
    }
}
