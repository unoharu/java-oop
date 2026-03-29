package oop.step08;

public class Main {
    public static void main(String[] args) {
        Report report = new Report("月次売上レポート");

        System.out.println("=== 複数インタフェースの実装 ===");
        report.print();
        report.save("/reports/monthly.pdf");

        System.out.println("\n=== インタフェース参照型 ===");
        Printable p = report;
        Saveable s = report;
        System.out.print("Printable として呼び出し: ");
        p.print();
        System.out.print("Saveable として呼び出し: ");
        s.save("/reports/monthly.pdf");

        System.out.println("\n=== static メソッド ===");
        System.out.println("Printable.describe(): " + Printable.describe());

        System.out.println("\n=== default メソッド ===");
        report.printPreview();
    }
}
