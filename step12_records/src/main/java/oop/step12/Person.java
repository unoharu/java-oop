package oop.step12;

public record Person(String name, int age) {
    // Compact constructor: validation without repeating parameter list
    public Person {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("名前は空にできません");
        }
        if (age < 0) {
            throw new IllegalArgumentException("年齢は 0 以上にしてください: " + age);
        }
    }
}
