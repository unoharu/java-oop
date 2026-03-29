package oop.step07;

public class MarkdownDocument extends Document {
    MarkdownDocument(String title) {
        super(title);
    }

    @Override
    String generate() {
        return "# " + getTitle() + " の内容";
    }

    @Override
    String getFormatName() {
        return "Markdown";
    }
}
