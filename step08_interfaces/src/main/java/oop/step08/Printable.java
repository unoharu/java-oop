package oop.step08;

public interface Printable {
    // Abstract method: implementors must define printing behavior
    void print();

    // Default method (Java 8+): optional override
    default void printPreview() {
        System.out.println("プレビュー: " + getPreviewText());
    }

    // Private helper shared between default methods (Java 9+)
    private String getPreviewText() {
        return "(プレビューモード)";
    }

    // Static utility method on the interface itself
    static String describe() {
        return "印刷可能なオブジェクトのインタフェースです";
    }
}
