package oop.step08;

public class Report implements Printable, Saveable {
    private String title;

    Report(String title) {
        this.title = title;
    }

    @Override
    public void print() {
        System.out.println("[印刷] " + title);
    }

    @Override
    public void save(String path) {
        System.out.println("[保存] " + path + " に保存しました");
    }

    @Override
    public void printPreview() {
        System.out.println("プレビュー: " + title + "（プレビューモード）");
    }
}
