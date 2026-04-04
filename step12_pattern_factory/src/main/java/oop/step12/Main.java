package oop.step12;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Factory パターン ===");

        for (String os : new String[]{"Windows", "Mac", "Linux"}) {
            System.out.println("OS: " + os + " → " + os + "Button を生成");
            Button button = ButtonFactory.create(os);
            button.render();
            System.out.println();
        }

        System.out.println("=== 不明な OS ===");
        try {
            ButtonFactory.create("FreeBSD");
        } catch (IllegalArgumentException e) {
            System.out.println("例外捕捉: " + e.getMessage());
        }
    }
}
