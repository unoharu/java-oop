package oop.step11;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== 基本的な列挙型 ===");
        System.out.print("曜日一覧: ");
        for (Day d : Day.values()) {
            System.out.print(d.name() + " ");
        }
        System.out.println();

        System.out.println("\n=== フィールド付き enum: Planet ===");
        System.out.printf("EARTH: 質量 = %.3e kg, 重力 = %.2f m/s²%n",
            Planet.EARTH.getMass(), Planet.EARTH.surfaceGravity());
        System.out.printf("MARS:  質量 = %.3e kg, 重力 = %.2f m/s²%n",
            Planet.MARS.getMass(), Planet.MARS.surfaceGravity());

        System.out.println("\n=== 抽象メソッド付き enum: Day ===");
        System.out.println("MONDAY の業務: " + Day.MONDAY.work());
        System.out.println("SATURDAY の業務: " + Day.SATURDAY.work());

        System.out.println("\n=== switch 式 ===");
        for (Day d : new Day[]{Day.WEDNESDAY, Day.SUNDAY}) {
            String type = switch (d) {
                case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "平日";
                case SATURDAY, SUNDAY -> "休日";
            };
            System.out.println(d + " は: " + type);
        }
    }
}
