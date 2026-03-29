package oop.step07;

public class HtmlDocument extends Document {
    HtmlDocument(String title) {
        super(title);
    }

    @Override
    String generate() {
        return "<html><body>" + getTitle() + " の内容</body></html>";
    }

    @Override
    String getFormatName() {
        return "HTML";
    }
}
