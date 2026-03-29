package oop.step07;

public abstract class Document {
    private String title;

    Document(String title) {
        this.title = title;
    }

    String getTitle() {
        return title;
    }

    // Abstract method: subclasses must implement their own content generation
    abstract String generate();

    // Template method: defines the structure, delegates generate() to subclasses
    void print() {
        System.out.println("[" + getFormatName() + "] ドキュメントを印刷します: " + title);
        System.out.println("--- 内容 ---");
        System.out.println(generate());
        System.out.println("------------");
    }

    abstract String getFormatName();
}
