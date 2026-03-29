package oop.step07;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ドキュメント生成（テンプレートメソッド）===");
        Document[] docs = {
            new PdfDocument("月次レポート"),
            new HtmlDocument("月次レポート"),
            new MarkdownDocument("月次レポート")
        };

        for (Document doc : docs) {
            doc.print();
            System.out.println();
        }

        System.out.println("=== 抽象クラスはインスタンス化できない ===");
        System.out.println("// Document d = new Document(\"test\"); → コンパイルエラー");
    }
}
