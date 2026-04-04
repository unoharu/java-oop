package oop.step10;

// Enum singleton: serialization-safe, reflection-safe, thread-safe by JVM
// Joshua Bloch's recommended approach (Effective Java)
public enum EnumSingleton {
    INSTANCE;

    private int value = 42;

    public int getValue() {
        return value;
    }
}
