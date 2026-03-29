package oop.step17;

public class ButtonFactory {
    public static Button create(String os) {
        return switch (os.toLowerCase()) {
            case "windows" -> new WindowsButton();
            case "mac"     -> new MacButton();
            case "linux"   -> new LinuxButton();
            default -> throw new IllegalArgumentException("未知の OS: " + os);
        };
    }
}
