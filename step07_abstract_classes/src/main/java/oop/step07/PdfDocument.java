package oop.step07;

public class PdfDocument extends Document {
    PdfDocument(String title) {
        super(title);
    }

    @Override
    String generate() {
        return "%PDF-1.4 ... " + getTitle() + " の内容";
    }

    @Override
    String getFormatName() {
        return "PDF";
    }
}
