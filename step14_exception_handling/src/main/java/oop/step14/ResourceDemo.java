package oop.step14;

public class ResourceDemo implements AutoCloseable {
    private final String name;

    ResourceDemo(String name) {
        this.name = name;
        System.out.println("リソースをオープン: " + name);
    }

    void process() {
        System.out.println("処理中...");
    }

    @Override
    public void close() {
        System.out.println("リソースを自動クローズ: " + name);
    }
}
